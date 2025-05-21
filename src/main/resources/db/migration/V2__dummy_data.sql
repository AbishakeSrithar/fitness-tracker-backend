-- Insert dummy data into Exercises
INSERT INTO "exercises" ("name", "description") VALUES
  ('Push Up', 'An upper-body exercise for chest and triceps'),
  ('Squat', 'A lower-body exercise for quads and glutes'),
  ('Plank', 'A core stability exercise'),
  ('Bench Press', 'An upper-body exercise to strengthen pectorals, arms, and shoulders');

-- Insert dummy data into Workouts
INSERT INTO "workouts" ("name", "date") VALUES
  ('Push Routine', '2025-05-01'),
  ('Pull Routine', '2025-04-02'),
  ('Legs Routine', '2024-03-03'),
  ('Push Routine', '2025-05-02'),
  ('Push Routine', '2025-05-03'),
  ('Push Routine', '2025-05-05'),
  ('Push Routine', '2025-05-07'),
  ('Push Routine', '2025-05-08'),
  ('Push Routine', '2025-05-11'),
  ('Push Routine', '2025-05-13'),
  ('Push Routine', '2025-05-16'),
  ('Push Routine', '2025-05-17');

-- Insert dummy data into Entries
INSERT INTO "entries" ("workout_id", "exercise_id", "weight", "sets", "reps") VALUES
  (1, 1, 0, 4, 15),  -- 15 push ups in 3 sets during Morning Routine
  (1, 2, 50, 4, 10), -- 10 squats with 50kg in 4 sets during Morning Routine
  (2, 3, 0, 2, 60),  -- 60 seconds plank in 2 sets during Evening Routine
  (1, 4, 30, 3, 10),
  (4, 4, 30, 4, 10),
  (5, 4, 40, 3, 10),
  (6, 4, 40, 4, 10),
  (7, 4, 50, 3, 10),
  (8, 4, 55, 3, 10),
  (9, 4, 55, 4, 10),
  (10, 4, 60, 3, 8),
  (11, 4, 65, 3, 8),
  (12, 4, 60, 4, 10);