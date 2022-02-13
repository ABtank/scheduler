
получаем "Мои уроки" у студента
SELECT
    lessons.name as lesson_name,
    lessons.dt_start as time_start,
    exercises.name as exercise_name,
    exercises.duration as duration,
    users.name as teacher_name,
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


   @Query("UPDATE Person p SET p.name = :name WHERE p.id = :id")
    void updatePersonName(@Param("user_id") Integer id);





