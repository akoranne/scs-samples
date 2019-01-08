# Config Service

This project shows example of the oss config service.

The "@EnableConfigServer" annotation in the main class, enables this service to be a config server. It will read all the properties from the config repo, identified by `spring.cloud.config.server.git.uri` in `application.properties` file.

This example relies on following config repo `https://github.com/akoranne/configs`, specially `a-bootiful-client.properties`.


## Local Build & Test
1. Build the project
	```
	$> ./mnvw clean package
	```

2. Run the project locally
	```
	$> ./mnvw spring-boot:run
	```

3. Test locally
	```
	Go to http://localhost:8888/a-bootiful-client/default

	You should see the jason document.
	```

## Cloud
1. Push to PCF
	```
	$> cf push
	```

2. Test locally
	```
	Go to
		http://config-service-ask.apps.<pcf-domain>/a-bootiful-client/default

	For example
		http://config-service-ask.apps.local.pcfdev.io/a-bootiful-client/default

	You should see the jason document.
	```


## Results
```
{
	"name": "a-bootiful-client",
	"profiles": [
		"default"
	],
	"label": "master",
	"version": "144c56b57dba65179bf4f7be8765fcdb0f9260a0",
	"state": null,
	"propertySources": [
		{
			"name": "https://github.com/akoranne/configs.git/a-bootiful-client.properties",
			"source": {
				"user": "joe@acme.com",
				"password": "abc123",
				"message": "Hello World from Humpty Dumpty"
			}
		}
	]
}
```
