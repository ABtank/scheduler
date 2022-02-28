

SELECT
    lessons.name as lesson_name,
    lessons.dt_start as time_start,
    exercises.name as exercise_name,
    exercises.duration as duration,
    users.id as teacher_id,
    disciplines.name as discipline_name
FROM lessons_students as ls
    INNER JOIN lessons as lessons
    ON ls.lesson_id = lessons.id
    INNER JOIN exercises as exercises
    ON lessons.exercise_id = exercises.id
    INNER JOIN users as users
    ON exercises.teacher_id = users.id
    INNER JOIN disciplines as disciplines
    ON exercises.discipline_id = disciplines.id
WHERE
    ls.student_id = :user_id

SELECT
    ls.email,
    ls.lessons_students_id
FROM
    (SELECT
        users.email,
        lessons_students.id as lessons_students_id
    FROM exercises as exercises
        INNER JOIN lessons as lessons
        ON lessons.exercise_id = exercises.id
        INNER JOIN lessons_students as lessons_students
        ON lessons.id = lessons_students.lesson_id
        INNER JOIN users as users
        ON lessons_students.student_id = users.id
    WHERE
        exercises.is_validate
        AND NOT lessons_students.is_confirmation_request_sent
        AND TIMESTAMPDIFF(hour, '2022-02-13 09:15:00', lessons.dt_start) <= 24) as ls;
--        AND TIMESTAMPDIFF(hour, :curDate, lessons.dt_start) <= 24) as ls;


   @Query("UPDATE Person p SET p.name = :name WHERE p.id = :id")
    void updatePersonName(@Param("user_id") Integer id);





