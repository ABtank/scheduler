

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
      exercises.id as exerciseId,
      exercises.duration as exerciseDuration,
      lessons.id as lessonId,
      lessons.name as lessonName,
      lessons.dt_start as lessonsDt_start,
      disciplines.name as disciplineName,
      CONCAT(teachers.last_name, ' ', teachers.first_name, ' ', teachers.middle_name) as teacherFullName,
      students.email as studentEmail
FROM exercises as exercises
    INNER JOIN lessons as lessons
    ON lessons.exercise_id = exercises.id
    INNER JOIN lessons_students as lessons_students
    ON lessons.id = lessons_students.lesson_id
    INNER JOIN users as students
    ON lessons_students.student_id = students.id
    INNER JOIN users as teachers
    ON exercises.teacher_id = teachers.id
    INNER JOIN disciplines as disciplines
    ON exercises.discipline_id = disciplines.id
WHERE
    (lessons.dt_modify  >= (NOW () - INTERVAL '73 MINUTE')
    AND
    lessons.dt_modify  <= (NOW () - INTERVAL '60 MINUTE') )
    OR
    (exercises.dt_modify  >= (NOW () - INTERVAL '73 MINUTE')
    AND
    exercises.dt_modify  <= (NOW () - INTERVAL '60 MINUTE') );


        DATE_PART('hour', '2022-03-06 16:24:00' - exercises.dt_modify) <= 24;
--        AND DATE_PART('hour', :curDate - lessons.dt_start) <= 24) as ls;


   @Query("UPDATE Person p SET p.name = :name WHERE p.id = :id")
    void updatePersonName(@Param("user_id") Integer id);





