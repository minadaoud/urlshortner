# URL Shortner

## Requirements

Creating a basic (non-production grade) microservice using Springboot/Java to shorten URLs with the following features:

- Given a URL, our service should generate a shorter and unique alias of it. This is called a short link. This link
  should be short enough to be easily copied and pasted into applications.
- When users access a short link, our service should redirect them to the original link.
- The service should run on the cloud

## Design decisions

- I have used the classic N-tier architecture due to the simplicity of this microservice (providing it is not a
  production grade).
- I opted for Redis embedded server to make starting the application easier (no need to install Redis locally or on a
  server). 
- Java 11 is used as programming language.

## Improvement if time allowed

- For shortening URLs, use better hashing algorithm or base 62 conversion.
- Instead of using embedded cache/DB, use a database server (or multiple) to avoid data loss in case of server down.
- Write more unit/integration/functional testing (positive and negative scenarios). Due to time restrictions I wrote
  basic unit and functional tests.
- Write performance and pen testing to ensure the code is able to handle high load of requests.
- Dockerising the microservice.
- Better URL validation in the POST endpoint. For this simple exercise I used a simple URL validator.

## To run the service

- Using command line (Maven is needed):
    1. Compile code
  ```
  mvn clean install
  ```

    2. start the server
  ```
  mvn spring-boot:run
  
  ```
    3. Use Curl or Postman to create a short URL request. A curl example for the app running locally would be:
  ```
   curl -X POST -H "Content-Type: application/json" \
    -d '{"longUrl": "http://www.google.com"}' \
    http://localhost:8080/short-url
  ```
    4. To test the redirect endpoint, simply copy the short URL and paste it in your browser.


- Note: You can always use your IDE to compile and run the application.

## Deployment to the cloud

- I have used Azure as my cloud solution.
- To deploy the code to Azure, Azure-maven plugin is used. the full maven plugin is in the pom file. Please note that I
  removed the subscription ID for security reasons.
- If you wish to try the app on the cloud, the base URL for the app on the cloud is:
```
https://urlshortner-1607259372927.azurewebsites.net/
```
P.S. Please note that I am using a free tier app service, thus the server could be down from time to time.

## References

These are some of the references which helped me writing the code:

- Understanding Murmur3 - https://greenrobot.org/what-is-murmur-and-what-is-murmur-3a-and-murmer3f/
- Spring boot functional testing - https://spring.io/guides/gs/testing-web/
- Mockito referesher - https://site.mockito.org/
- Azure deployment - https://spring.io/guides/gs/spring-boot-for-azure/