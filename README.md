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

## Endpoints Available
- [x] CREATE
    - [x] Post Workout
    - [x] Post Entry
    - [x] Post Exercise
- [x] READ
    - [x] Workouts
        - [x] Get All
        - [x] Get By Id
        - [x] Get By Name
        - [x] Get By Date
    - [x] Entry
        - [x] Get All
        - [x] Get By Id
        - [x] Get By WorkoutId
        - [x] Get By ExerciseId
    - [x] Exercise
        - [x] Get All
        - [x] Get By Id
        - [x] Get By Name
- [x] UPDATE
    - [x] Put Entry
    - [x] Put Workout
    - [x] Put
- [x] DELETE
    - [x] Delete Entry
    - [x] Delete Workout
    - [x] Delete Exercise
- [x] Import CSV

The idea of this project was to make something from scratch that connects a Frontend to a Backend without following a tutorial/guide using:
- Frontend
  - Typescript
  - HTML
  - CSS
  - Chartjs (Learn how to use a new Library from scratch with Docs)

- Backend
  - Kotlin
  - Springboot
  - Flyway
  - PostgreSQL
  - Docker
  - Shell Scripts

I chose a Fitness Tracker because my current app charges money to Graph my lifts and I thought it would be fun to try do that myself