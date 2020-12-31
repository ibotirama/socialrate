# Social Rate Calculator

## Cyber Cube - Code Challenge

## :hammer: Building the project

### Prerequisites
- Docker (version 19.03.8)
- Java 8 (openjdk version "1.8.0_252")
- Maven 3 (version v3.6.3)
- Git

### Clone the project
```
git clone https://github.com/ibotirama/socialrate.git
```

### Build the project
```
mvn clean install
```

## :rocket: Running the project
* Load the required infrastructure with the command below 
```
docker-compose up 
``` 
* Run the web api (these command line is assuming that you are in the systems folder)
```
java -jar webapi/target/webapi-0.0.1-SNAPSHOT.jar
``` 
```
java -jar consumer/target/consumer-0.0.1-SNAPSHOT.jar
``` 

## :test_tube: Testing
* For testing, just send a REST call POST in the body one JSON with the data below.
```
{
"first_name": "Max",
"last_name": "Payne",
"age": 37
}
``` 
