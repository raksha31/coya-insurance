/**
 *
 */
package coya.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coya.config.BasePremiumMapper;
import coya.config.HouseSurchargeLevel;
import coya.config.UserSurchargeLevel;
import coya.dto.InsuranceRequestDto;
import coya.dto.InsuranceResponseDto;
import coya.exceptions.CannotInsureException;
import coya.model.Address;
import coya.model.Banana;
import coya.model.Bicycle;
import coya.model.House;
import coya.model.Product;

/**
 * @author Raksha
 *
 */
@Service
public class QuoteProcessor implements Processor {

	@Autowired
	private BasePremiumMapper basePremiumMapper;


	@Override
	public InsuranceResponseDto priceFor(
			InsuranceRequestDto insuranceRequestDto)  throws CannotInsureException{
		Map<String, Double> productInsurance = new HashMap<>();
		InsuranceResponseDto result = new InsuranceResponseDto(productInsurance);

		List<Product> products = insuranceRequestDto.getProducts();
		for(Product each : products) {

			Double basePremium  = 0.0;
			Double finalQuote = 0.0;
			Double userRiskSurcharge =0.0;

			int userRisk = insuranceRequestDto.getUser().getRisk();
			Address userAddress = insuranceRequestDto.getUser().getAddress();
			userRiskSurcharge = getUserRiskSurcharge(userRisk,
					userRiskSurcharge);

			premiumForEachProduct(productInsurance, each, finalQuote,
					userRiskSurcharge, userRisk, userAddress);

		}


		result.setProductInsurance(productInsurance);
		return result;
	}


	/**
	 * @param productInsurance
	 * @param each
	 * @param finalQuote
	 * @param userRiskSurcharge
	 * @param userRisk
	 * @param userAddress
	 */
	private void premiumForEachProduct(Map<String, Double> productInsurance,
			Product each, Double finalQuote, Double userRiskSurcharge,
			int userRisk, Address userAddress) {
		Double basePremium;
		if(each instanceof House) {
			 basePremium = basePremiumMapper.BasePremium.get("House");
			finalQuote = calculatePremiumForHouse(each,userAddress, basePremium,
					userRiskSurcharge);
			productInsurance.put("House", finalQuote);


		}
		else if(each instanceof Bicycle) {
			 basePremium = basePremiumMapper.BasePremium.get("Bicycle");
			finalQuote = calculatePremiumForBicycle(each, basePremium, userRisk,
					userRiskSurcharge);
			productInsurance.put("Bicycle", finalQuote);


		}
		else if(each instanceof Banana) {
			 basePremium = basePremiumMapper.BasePremium.get("Banana");
			finalQuote = calculatePremiumForBanana(each, basePremium,
					finalQuote,userRisk, userRiskSurcharge);
			productInsurance.put("Banana", finalQuote);

		}
	}


	/**
	 * @param userRisk
	 * @param userRiskSurcharge
	 * @return
	 */
	private Double getUserRiskSurcharge(int userRisk, Double userRiskSurcharge) {
		if(userRisk < 20) {
			userRiskSurcharge = UserSurchargeLevel.getUserSurchargeMap().get(UserSurchargeLevel.LESS_THAN_20);
		} else if(userRisk>20 && userRisk<=200) {
			userRiskSurcharge = UserSurchargeLevel.getUserSurchargeMap().get(UserSurchargeLevel.LESS_THAN_201);
		} else if(userRisk>200 && userRisk<=500) {
			userRiskSurcharge = UserSurchargeLevel.getUserSurchargeMap().get(UserSurchargeLevel.LESS_THAN_501);
		} else {
			throw new CannotInsureException("Due to high user risk ");
		}
		return userRiskSurcharge;
	}


	/**
	 * @param each
	 * @param BasePremium
	 * @param finalQuote
	 * @return
	 */
	private Double calculatePremiumForBanana(Product each, Double basePremium,
			Double finalQuote, int userRisk, Double userRiskSurcharge) {
		Banana banana = (Banana)each;
		if(banana.getBlackspots()>=3 && banana.getBlackspots() <=12
				&& userRisk <=200) {
			finalQuote = banana.getValue() * basePremium
					+ userRiskSurcharge * basePremium;
		} else {
			throw new CannotInsureException("Due to higher black spots");
		}
		return finalQuote;
	}


	/**
	 * @param each
	 * @param BasePremium
	 * @param userRisk
	 * @return
	 */
	private Double calculatePremiumForBicycle(Product each, Double basePremium,
			int userRisk, Double userRiskSurcharge) {
		Double finalQuote;
		Bicycle bicycle = (Bicycle) each;
		Double gearSurcharge = bicycle.getGears() * 0.08;
		if(userRisk > 150) {
			throw new CannotInsureException("due to higher user risk");
		}
		finalQuote = bicycle.getValue() * basePremium
				+ gearSurcharge * basePremium
				+ userRiskSurcharge * basePremium;
		return finalQuote;
	}


	/**
	 * @param each
	 * @param BasePremium
	 * @param userRiskSurcharge
	 * @return
	 */
	private Double calculatePremiumForHouse(Product each, Address userAddress, Double basePremium,
			Double userRiskSurcharge) {
		Double finalQuote;
		Double addressRiskSurcharge=0.0;
		Double housePriceSurcharge=0.0;
		House house = (House) each;
		if(house.getSize()>1000 || house.getSize() < 30) {
			throw new CannotInsureException("due to House size not in permissible range");
		}
		if(house.getValue() > 1000000) {
			housePriceSurcharge = 1.15;
		}

		addressRiskSurcharge = getAddressRiskSurcharge(userAddress,
				addressRiskSurcharge);
		finalQuote = house.getValue() * basePremium
				+ housePriceSurcharge * basePremium
				+ userRiskSurcharge * basePremium
				+ addressRiskSurcharge * basePremium;
		return finalQuote;
	}


	/**
	 * @param userAddress
	 * @param addressRiskSurcharge
	 * @return
	 */
	private Double getAddressRiskSurcharge(Address userAddress,
			Double addressRiskSurcharge) {
		if(userAddress.getLocationRisk() < 100) {
			addressRiskSurcharge = HouseSurchargeLevel.getHouseSurchargeMap().get(HouseSurchargeLevel.LESS_THAN_100);
		} else if(userAddress.getLocationRisk() >= 100 && userAddress.getLocationRisk() < 300) {
			addressRiskSurcharge = HouseSurchargeLevel.getHouseSurchargeMap().get(HouseSurchargeLevel.LESS_THAN_300);
		} else if(userAddress.getLocationRisk() >= 300 && userAddress.getLocationRisk() < 502) {
			addressRiskSurcharge = HouseSurchargeLevel.getHouseSurchargeMap().get(HouseSurchargeLevel.LESS_THAN_502);
		}
		return addressRiskSurcharge;
	}

}
