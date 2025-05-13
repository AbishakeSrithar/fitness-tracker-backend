-- Insert dummy data into Exercises
INSERT INTO "exercises" ("name", "description") VALUES
  ('Push Up', 'An upper-body exercise for chest and triceps'),
  ('Squat', 'A lower-body exercise for quads and glutes'),
  ('Plank', 'A core stability exercise');

-- Insert dummy data into Workouts
INSERT INTO "workouts" ("name", "date") VALUES
  ('Push Routine', '2025-05-01'),
  ('Pull Routine', '2025-04-02'),
  ('Legs Routine', '2024-03-03');

-- Insert dummy data into Entries
INSERT INTO "entries" ("workout_id", "exercise_id", "weight", "sets", "reps") VALUES
  (1, 1, 0, 4, 15),  -- 15 push ups in 3 sets during Morning Routine
  (1, 2, 50, 4, 10), -- 10 squats with 50kg in 4 sets during Morning Routine
  (2, 3, 0, 2, 60);  -- 60 seconds plank in 2 sets during Evening Routine