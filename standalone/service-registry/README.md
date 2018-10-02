# Service Registry

This is example of OSS Netflix Eureka-based service registry server.

To build it..

`$> mvnw clean package`

Push the app to cloud foundry

`$> cf push custom-eureka-registry -p target/service-registry-0.0.1-SNAPSHOT.jar -m 512M`


After you push it, create a CUPS service

`$> cf cups custom-service-registry -r https://custom-eureka-registry.local.pcfdev.io`

Bind the `custom-service-registry` to any of your app. You should be able to manage it.

On PCF, please use service registry provided by Spring Cloud Services. 
