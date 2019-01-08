# Cloud Config Client

This app shows example of the config client. 
The config service is identified in the `bootstrap.yml`.

For local, the config server is `http://localhost:8888`.

For cloud deployment, the client will use the config service from the 
binding to the app instance.

## Running locally

1. Please run the config server.
	
	```
	$ cd <spring runtime folder>
	$ spring cloud --list
	  spring cloud eureka configserver h2 kafka stubrunner zipkin
	# --- start the configserver
	$ spring cloud configserver
	```
		
	The config server will use `./configserver.yml`. 
	The git uri is configured in the config file. 
	
	The config server will run on port 8888.
	
	To verify go to `http://localhost:8888/ConfigClient2/default` 

2. Run the config client
	
	```
	$ cd <workspace>/scs/pcf/cloud-config-client
	$ mvn clean spring-boot:run 
	```

3. To verify the config client is running goto

	http://localhost:8080

4. To verify the config client is pulling external configurations, goto

	http://localhost:8080/message 
	
	You should message with values retrieved from the `ConfigClien2.yml`.
	


## Create Service Instance
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
        $> cf create-service p-config-server trial config-server -c '{"git": { "uri": "https://github.com/akoranne/config-repo"} }'
        
        or to use a specific label/branch
        
        $> cf create-service p-config-server trial config-server -c '{"git": { "uri": "https://github.com/akoranne/config-repo", "label": "develop" } }'
        
        $> cf service config-server
    ```

## Local Build & Test
1. Build the project
	```
	$> ./mnvw clean package
	```

## Cloud 
1. Push to PCF
	```
	$> cf push
	```

2. Test locally
	```
	Go to
		http://config-service.apps.<pcf-domain>/message
	
	For example
		http://config-client.apps.local.pcfdev.io/message
	 
	You should see the results.
	```

## Results
```
{
    "configUri": "https://config-381c5166-1939-4bf0-91ca-9839fbbd90a7.cfapps.io",
    "dburl": "jdbc:oracle:thin:joe@acme.com/abc123@host:port:sid",
    "message": "Hello World (config yml)",
    "timestamp": "2019-01-08T21:44:28.804",
    "version": "1.0"
}
```


## References
* [ENCRYPTING AND DECRYPTING CONFIGURATION PROPERTY VALUES IN SPRING CLOUD](https://patrickgrimard.io/2016/03/04/encrypting-and-decrypting-configuration-property-values-in-spring-cloud/)

