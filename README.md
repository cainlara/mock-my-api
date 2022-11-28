# Mock My API

This is a very simple lightweight tool that allows you to mock responses from a temporary local Back End service.

## Running the Application
### Requisites
- Java 11+
- Apache Maven 3.6.2+

### Run the application
- Clone this repository
- In command line browse to this repository container folder and execute

```console
user@machine:~$ mvn clean package
```

- Start the application

```console
user@machine:~$ java -jar target/mock-my-api.jar source=path/to/json/source/file
```

## JSON Source File
Mocking RESTs is based on JSON files, which should specify (per mocked REST):

- **Method**: *Mandatory*. Currently only *GET*, *POST*, *PUT* and *DELETE* are allowed.
- **URL**: *Mandatory*. The path that will be requested. It **MUST** starts with slash ("/"), and not blanks nor symbols are allowed.
- **Response Status Code**: *Mandatory*. The status code the mock will respond with. It **MUST** be an integer value between 100 and 600.
- **Response**: *Optional*. The response body.

### Example
```json
[
  {
    "method": "POST",
    "url": "/employee",
    "responseStatusCode": 201,
    "response": {
      "id": 5666556,
      "firstName": "John",
      "lastName": "Smith",
      "age": 25
    }
  },
  {
    "method": "GET",
    "url": "/employee/5666556",
    "responseStatusCode": 200,
    "response": {
      "id": 5666556,
      "firstName": "John",
      "lastName": "Smith",
      "age": 25
    }
  }
]
```

## Starting the Application on Specific Port
By default, application runs in port 8080. This however, can be modified in order to run on any given port (provided it can be used). To do so, just add the argument `port=PORT` to starter command. Let's say for instance, you need to start it on port 9090, then command becomes:

```console
user@machine:~$ java -jar target/mock-my-api.jar source=path/to/json/source/file port=9090
```

## Retrieve responses
Once the application is up and running, you can make a call to 

```console
user@machine:~$ curl --request GET --url http://localhost:8080/employee/5666556
```

And then retrieve the response specified by JSON Source file