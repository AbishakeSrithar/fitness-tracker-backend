# **Fitness Tracker Backend**
## Setup
Runs just the db which is required for compiling (else flyway cries)
``` shell
    docker compose up db --build
```
Compiles our code into .jar file in ```./target```
``` shell
    mvn clean install
```
Runs our Docker container with the db and app images (created using existing ```.jar```)
``` shelldocker 
    docker compose up --build --force-recreate --remove-orphans
```
While Docker DB is running, you can access it via shell
``` shell
    docker exec -it postgresql psql -U postgres
```

## Test
To run the tests, make sure you run the db in docker first
``` shell
    docker compose up db --build
```
then run
``` shell
    mvn test
```

Kotlin Tests: https://www.baeldung.com/kotlin/spring-boot-testing

## Database Design
![database_design.png](database_design.png)

## Endpoints for MVP
### Create
- [ ] Add new Entry
- [ ] Add new Workout
- [ ] Add new Exercise
### Read
- [x] Get Entries
  - [x] All
  - [x] byId
  - [x] byWorkoutId
  - [x] byExerciseId
- [x] Get Workouts
    - [x] All
    - [x] byId
    - [x] byName
    - [x] byCreatedAt
- [x] Get Exercises
    - [x] All
    - [x] byId
    - [x] byName
### Update
- [ ] Edit Entries
  - [ ] Weight
  - [ ] Sets
  - [ ] Reps
- [ ] Edit Workouts Name
- [ ] Edit Exercises
  - [ ] byName
  - [ ] byDesc
## Delete
- [ ] Delete Entry
- [ ] Delete Workout
- [ ] Delete Exercise

## Resources
Test Containers: https://jskim1991.medium.com/spring-boot-configure-testcontainers-in-your-test-code-this-way-417b221e55b
LocalDate REST Params: https://www.baeldung.com/spring-date-parameters
