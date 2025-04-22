CREATE TABLE "exercises" (
  "id" serial PRIMARY KEY,
  "name" varchar,
  "description" varchar
);

CREATE TABLE "workouts" (
  "id" serial PRIMARY KEY,
  "name" varchar,
  "created_at" timestamp
);

CREATE TABLE "entries" (
  "id" serial PRIMARY KEY,
  "workout_id" integer,
  "exercise_id" integer,
  "weight" decimal,
  "sets" integer,
  "reps" integer
);

ALTER TABLE "entries" ADD FOREIGN KEY ("workout_id") REFERENCES "workouts" ("id");
ALTER TABLE "entries" ADD FOREIGN KEY ("exercise_id") REFERENCES "exercises" ("id");
