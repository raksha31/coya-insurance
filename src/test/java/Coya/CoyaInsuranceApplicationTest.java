package Coya;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.http.ContentType;

import coya.CoyaInsuranceApplication;
import coya.dto.InsuranceRequestDto;
import coya.exceptions.CannotInsureException;
import coya.model.Address;
import coya.model.Banana;
import coya.model.Bicycle;
import coya.model.House;
import coya.model.Product;
import coya.model.User;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CoyaInsuranceApplication.class)
@TestPropertySource(value={"classpath:application.yml"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CoyaInsuranceApplicationTest

{
	int port = 50051;
	List<Product> products;
	Banana banana;
	House house;
	Bicycle bicycle;
	User user;
	Address address;
	InsuranceRequestDto request;

	@Test
	 public void getQuotes_productsInsured() {
		 request = new InsuranceRequestDto();
			products = new ArrayList<>();
			address = new Address(1,70);
			user = new User(1,address,18);
			banana = new Banana(1,90.0,"Banana",8);
			house = new House(2,1000000.0,"House",800);
			bicycle = new Bicycle(3,90.0,"Bicycle",4);
			prepareRequest();
			makeRestCall();
	}
	@Test
	 public void getQuotes_productsNotInsured_HigherUserRisk() {
		 request = new InsuranceRequestDto();
			products = new ArrayList<>();
			address = new Address(1,70);
			user = new User(1,address,550);
			banana = new Banana(1,90.0,"Banana",8);
			house = new House(2,1000000.0,"House",800);
			bicycle = new Bicycle(3,90.0,"Bicycle",4);
			prepareRequest();
			makeRestCall();
	}



	@Test
	 public void getQuotes_productsNotInsured_HigherBlackSpots() {
		 request = new InsuranceRequestDto();
			products = new ArrayList<>();
			address = new Address(1,70);
			user = new User(1,address,50);
			banana = new Banana(1,90.0,"Banana",15);
			house = new House(2,50.0,"House",800);
			bicycle = new Bicycle(3,90.0,"Bicycle",4);
			prepareRequest();

			makeRestCall();
	}
	@Test
	 public void getQuotes_productsNotInsured_HigherHouseSize() {
		 request = new InsuranceRequestDto();
			products = new ArrayList<>();
			address = new Address(1,70);
			user = new User(1,address,50);
			banana = new Banana(1,90.0,"Banana",8);
			house = new House(2,50.0,"House",1100);
			bicycle = new Bicycle(3,90.0,"Bicycle",4);
			prepareRequest();

			makeRestCall();
	}


	private void makeRestCall() {
		given()
			.body(request)
			.when()
			 .contentType(ContentType.JSON)
            .port(port).post("/coya/premium").prettyPeek().
    then()
            .statusCode(200);
	}

	private void prepareRequest() {
		products.add(banana);
		products.add(house);
		products.add(bicycle);

		request.setProducts(products);
		request.setUser(user);
	}
}
