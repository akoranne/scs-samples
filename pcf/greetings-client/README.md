# Greetings Client

The `greetings-client` is example of auto configuration and discovery.

All app instances, at boot are discovered by the service registry. 

For cloud deployment, the app will use the service registry from the
service binding.

## Running locally

1. Please run the service registry.

	```
	$ cd <spring runtime folder>
	$ spring cloud --list
	  spring cloud eureka configserver h2 kafka stubrunner zipkin

	# --- start the configserver
	$ spring cloud eureka
	```

	The config server will run on port 8761.

	To verify go to `http://localhost:8761`
	
	You should see the Eureka registry page


2. Run the greetings client

	```
	$ cd <workspace>/scs/pcf/greetings-client
	$ mvn clean spring-boot:run
	```

3. To verify the greetings client goto

	http://localhost:8180
	
	http://localhost:8180/hi/foo

4. Verify the greetings client is registered with the service registry goto

	To verify go to `http://localhost:8761`
	
	You should see the `greetings-client` instance on the Eureka registry page


## Cloud

#### Create Service Instance

0. login to pcf

	```
	$> cf login --skip-ssl-validation -a https://api.local2.pcfdev.io -o pcfdev-org -s pcfdev-space
	API endpoint: https://api.local.pcfdev.io
	
	Email> user
	
	Password>
	Authenticating...
	OK
	```

0. create service instance of the Spring Cloud Services Registry (Eureka)

	```
	$> cf create-service p-service-registry trial service-registry
	$> cf service service-registry
	```
	
	Open the url for the `service-registry` in browser and verify 


#### Deploy to PCF 
1. Push to PCF

	```
	$> cf push
	```

2. Test locally

	```
	Go to
		http://greetings-client.apps.<pcf-domain>
		http://greetings-client.apps.<pcf-domain>/hi/foo
	 
	You should see the jason document.
	```
	
3. Open the url for the `service-registry` in browser and verify 
	the `greetings-client` instance on the Eureka registry page
