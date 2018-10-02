# Config Client
This example shows the config client and how it invokes a standalone config server.

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

	a. Start the standalone `config-server`.
	
	b. Click on the [config server](http://localhost:8888/a-bootiful-client/default). 
You should see the jason document.
			
	c. Verify the config client by going [here](http://localhost:8080/message).
You should see the messages
		

## Cloud
1. Push to PCF
	```
	$> cf push
	```

2. Test the config server is running
	```
	Go to
		http://config-service.apps.<pcf-domain>/a-bootiful-client/default

	For example
		http://config-service.apps.local.pcfdev.io/a-bootiful-client/default

	You should see the jason document.
	```

2. Test locally
	```
	Go to
		http://config-client.apps.<pcf-domain>/message

	For example
		http://config-client.apps.local.pcfdev.io/message

	You should see the messages
	```

## Results

Jason document

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

Message from client

```
Message: Hello World from Humpty Dumpty [dburl: jdbc:oracle:thin:joe@acme.com/abc123@host:port:sid]
```

