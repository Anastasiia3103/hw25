package hw25;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDao {
        private Connection connection;

        public LessonDao(Connection connection) {
            this.connection = connection;
        }

        public void addLesson(Lesson lesson) throws SQLException {
            String query = "INSERT INTO Lesson (name, homework_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, lesson.getName());
            statement.setInt(2, lesson.getHomework().getId());
            statement.executeUpdate();
            statement.close();
        }

        public void deleteLesson(int lessonId) throws SQLException {
            String query = "DELETE FROM Lesson WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, lessonId);
            statement.executeUpdate();
            statement.close();
        }

        public List<Lesson> getAllLessons() throws SQLException {
            List<Lesson> lessons = new ArrayList<>();
            String query = "SELECT l.id, l.name, h.id, h.name, h.description " +
                    "FROM Lesson l INNER JOIN Homework h ON l.homework_id = h.id";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int lessonId = resultSet.getInt(1);
                String lessonName = resultSet.getString(2);
                int homeworkId = resultSet.getInt(3);
                String homeworkName = resultSet.getString(4);
                String homeworkDescription = resultSet.getString(5);
                Homework homework = new Homework(homeworkId, homeworkName, homeworkDescription);
                Lesson lesson = new Lesson(lessonId, lessonName, homework);
                lessons.add(lesson);
            }

            resultSet.close();
            statement.close();
            return lessons;
        }

        public Lesson getLessonById(int lessonId) throws SQLException {
            String query = "SELECT l.name, h.id, h.name, h.description " +
                    "FROM Lesson l INNER JOIN Homework h ON l.homework_id = h.id " +
                    "WHERE l.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, lessonId);
            ResultSet resultSet = statement.executeQuery();

            Lesson lesson = null;
            if (resultSet.next()) {
                String lessonName = resultSet.getString(1);
                int homeworkId = resultSet.getInt(2);
                String homeworkName = resultSet.getString(3);
                String homeworkDescription = resultSet.getString(4);
                Homework homework = new Homework(homeworkId, homeworkName, homeworkDescription);
                lesson = new Lesson(lessonId, lessonName, homework);
            }

            resultSet.close();
            statement.close();
            return lesson;
        }
}

