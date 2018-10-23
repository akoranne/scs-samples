# Discovery Client

This app shows example of the config service by 
binding to an instance of the config service


The is example of client-side service discovery.

At boot time, `discovery-client` are auto discovered by the service registry.

The `discovery-client`, using **Feign** does a lookup on the service registry
and looks up the `greettings-client` and invokes the rest endpoint. 

For cloud deployment, the app will use the SCS service registry from the
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

2. Run the discovery client

	```
	$ cd <workspace>/scs/pcf/discovery-client
	$ mvn clean spring-boot:run
	```

3. To verify the discovery client goto

	http://localhost:8080
	
	http://localhost:8080/greetings/foo

4. Verify the discovery client is registered with the service registry goto

	To verify go to `http://localhost:8761`
	
	You should see the `DISCOVERY-CLIENT` instance on the Eureka registry page

5. To verify the client side service lookup, goto

	http://localhost:8080/message




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

0. create service instance of the config-server

	```
	$> cf cs p-config-server trial config-server
	$> cf service config-server
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
		http://discovery-client.apps.<pcf-domain>
	 
	You should see the jason document.
	```
	
3. Open the url for the `service-registry` in browser and verify 
	the `discovery-client` instance on the Eureka registry page

4. To verify the client side service lookup, goto
   
   	http://discovery-client.apps.<pcf-domain>/message
   
