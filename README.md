# coya-insurance
##
Build the project in IDE(like eclipse)--> mvn clean install
 and run CoyaInsuranceApplication class as its a springboot application.
 OR
 via command line --> java -jar Coya-0.0.1-SNAPSHOT.jar
 
Note : There are test cases in test class. I tried covering most of the scenerios but due to time constraint was not able to cover them all.

##To execute service you can execute on POSTMAN
url : http://localhost:50051/coya/premium
Type : POST
request :
{
	"user" :
	{
		"id" : 1,
		"address" :
		{
			"id" : 1,
			"locationRisk" : 70
		},
		"risk" : 70
	},
	"products" :
	[
		{
			"id" : 12,
			"value" : 50,
			"type" : "Banana",
			"blackspots" : 5
		},
		{
			"id" : 13,
			"value" : 50,
			"type" : "Bicycle",
			"gears" : 5
		},
			{
			"id" : 14,
			"value" : 100000,
			"type" : "House",
			"size" : 900
		}
		
	]
}

Response :
{
    "productInsurance": {
        "House": 3000.05,
        "Bicycle": 5.14,
        "Banana": 58.64
    }
}
