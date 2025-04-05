-- Insert dummy data into Exercises
INSERT INTO "exercises" ("id", "name", "desc") VALUES
  (1, 'Push Up', 'An upper-body exercise for chest and triceps'),
  (2, 'Squat', 'A lower-body exercise for quads and glutes'),
  (3, 'Plank', 'A core stability exercise');

-- Insert dummy data into Workouts
INSERT INTO "workouts" ("id", "name", "created_at") VALUES
  (1, 'Push Routine', CURRENT_TIMESTAMP),
  (2, 'Pull Routine', CURRENT_TIMESTAMP),
  (3, 'Legs Routine', CURRENT_TIMESTAMP);

-- Insert dummy data into Entries
INSERT INTO "entries" ("id", "workout_id", "exercise_id", "weight", "sets", "reps") VALUES
  (1, 1, 1, 0, 3, 15),  -- 15 push ups in 3 sets during Morning Routine
  (2, 1, 2, 50, 4, 10), -- 10 squats with 50kg in 4 sets during Morning Routine
  (3, 2, 3, 0, 2, 60);  -- 60 seconds plank in 2 sets during Evening Routine