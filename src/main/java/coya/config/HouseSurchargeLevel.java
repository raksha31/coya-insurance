/**
 *
 */
package coya.config;

import java.util.HashMap;

/**
 * @author Raksha
 *
 */
public enum HouseSurchargeLevel {
	LESS_THAN_100, LESS_THAN_300,LESS_THAN_502;
	private static HashMap<HouseSurchargeLevel, Double> houseSurchargeMap = new HashMap<>();

	public static HashMap<HouseSurchargeLevel, Double> getHouseSurchargeMap() {
		houseSurchargeMap.put(LESS_THAN_100, 0.7);
		houseSurchargeMap.put(LESS_THAN_300, 1.0);
		houseSurchargeMap.put(LESS_THAN_502, 2.5);
		return houseSurchargeMap;
	}

}
