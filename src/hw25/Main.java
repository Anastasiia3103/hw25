package hw25;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DataBaseConnection.getConnection();

            LessonDao lessonDao = new LessonDao(connection);

            Homework homework1 = new Homework(1, "Homework 1", "Description 1");

            Lesson lesson1 = new Lesson(1, "Lesson 1", homework1);

            lessonDao.addLesson(lesson1);
            System.out.println("Lesson added: " + lesson1.getName());

            List<Lesson> lessons = lessonDao.getAllLessons();
            System.out.println("All Lessons:");
            for (Lesson lesson : lessons) {
                System.out.println(lesson.getName());
            }

            int lessonId = 1;
            Lesson retrievedLesson = lessonDao.getLessonById(lessonId);
            if (retrievedLesson != null) {
                System.out.println("Retrieved Lesson (ID " + lessonId + "): " + retrievedLesson.getName());
            } else {
                System.out.println("Lesson not found with ID " + lessonId);
            }

            int lessonToDeleteId = 1;
            lessonDao.deleteLesson(lessonToDeleteId);
            System.out.println("Lesson deleted with ID " + lessonToDeleteId);

            DataBaseConnection.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
