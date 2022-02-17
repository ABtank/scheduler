
INSERT INTO weekdays (name)
VALUES
('Monday'),
('Tuesday'),
('Wednesday'),
('Thursday'),
('Friday'),
('Saturday'),
('Sunday');

INSERT INTO calendar_days (day_date, weekday_id, is_holiday)
VALUES
('2022-02-14', 1, false),
('2022-02-15', 2, false),
('2022-02-16', 3, false),
('2022-02-17', 4, false),
('2022-02-18', 5, false),
('2022-02-19', 6, false),
('2022-02-20', 7, false),
('2022-02-21', 1, false),
('2022-02-22', 2, false),
('2022-02-23', 3, false),
('2022-02-24', 4, false),
('2022-02-25', 5, false),
('2022-02-26', 6, false),
('2022-02-27', 7, false);

INSERT INTO teacher_working_days (weekday_id, exercise_id, time_start, time_end)
VALUES
('1', '1', '09:00:00', '11:00:00'),
('2', '1', '09:00:00', '11:00:00');