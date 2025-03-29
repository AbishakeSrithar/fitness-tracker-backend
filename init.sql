CREATE TABLE "Exercises" (
  "id" integer PRIMARY KEY,
  "name" varchar,
  "desc" varchar
);

CREATE TABLE "Workouts" (
  "id" integer PRIMARY KEY,
  "name" varchar,
  "created_at" timestamp
);

CREATE TABLE "Entries" (
  "id" integer PRIMARY KEY,
  "workout_id" integer,
  "exercise_id" integer,
  "weight" decimal,
  "sets" integer,
  "reps" integer
);

ALTER TABLE "Entries" ADD FOREIGN KEY ("workout_id") REFERENCES "Workouts" ("id");
ALTER TABLE "Entries" ADD FOREIGN KEY ("exercise_id") REFERENCES "Exercises" ("id");
