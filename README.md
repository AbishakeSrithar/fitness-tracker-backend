# **Fitness Tracker Backend**
## Start Up
Run the start.sh file in ./scripts
``` shell
./scripts/start.sh 
```

## Test
Run the tests
``` shell
mvn test
```

## Database Design
![database_design.png](database_design.png)

## Endpoints for MVP
FindById will error if not found, FindBy anything else will return empty list

## Resources
Test Containers: https://jskim1991.medium.com/spring-boot-configure-testcontainers-in-your-test-code-this-way-417b221e55b
LocalDate REST Params: https://www.baeldung.com/spring-date-parameters
