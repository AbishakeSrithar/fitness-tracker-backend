CREATE TABLE "exercises" (
  "id" integer PRIMARY KEY,
  "name" varchar,
  "desc" varchar
);

CREATE TABLE "workouts" (
  "id" integer PRIMARY KEY,
  "name" varchar,
  "created_at" timestamp
);

CREATE TABLE "entries" (
  "id" integer PRIMARY KEY,
  "workout_id" integer,
  "exercise_id" integer,
  "weight" decimal,
  "sets" integer,
  "reps" integer
);

ALTER TABLE "entries" ADD FOREIGN KEY ("workout_id") REFERENCES "workouts" ("id");
ALTER TABLE "entries" ADD FOREIGN KEY ("exercise_id") REFERENCES "exercises" ("id");
