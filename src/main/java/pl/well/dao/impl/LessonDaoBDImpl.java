package pl.well.dao.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.dao.LessonDao;
import pl.well.dao.exception.DaoSystemException;
import pl.well.dao.exception.EntityExistsException;
import pl.well.entity.Lesson;

import java.sql.*;
import java.util.ArrayList;

import static pl.well.pool.pool.getconnection;

public class LessonDaoBDImpl implements LessonDao {
    private static final Logger log = LoggerFactory.getLogger(LessonDaoBDImpl.class);


    public LessonDaoBDImpl() {
        init();
    }

    @Override
    public void init() {

        try {
            log.debug("Start load JDBC driver");
            Driver driver = new com.mysql.jdbc.Driver();
            log.debug("Load JDBC driver successful");
            DriverManager.registerDriver(driver);
            log.debug("JDBC driver registered");
        } catch (SQLException e) {
            log.error("Error initializing database connection");
        }


    }

    @Override
    public ArrayList<Lesson> selectAllLesson() throws DaoSystemException, SQLException {
        //  Map<Integer, Lesson> memory = new ConcurrentHashMap<>();
        ArrayList<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM lesson;";
        try {
            Statement statement = getconnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String theme = resultSet.getString(2);
                String place = resultSet.getString(3);
                String feedback = resultSet.getString(4);
                String date = resultSet.getDate(5).toString();
                Time time = resultSet.getTime(5);

                String parceTime = time.toString().substring(0, 5);
                Lesson lesson = new Lesson(id, theme, place, feedback, date, parceTime);
                lessons.add(lesson);
            }

        } catch (SQLException e) {
            log.error("Падение при SELECT * FROM lesson");
            e.printStackTrace();
        }


        return lessons;

    }

    @Override
    public void addLesson(Lesson lesson) throws SQLException {

        String theme = lesson.getTheme();
        String place = lesson.getPlace();
        String data = lesson.getDate();


        try {
            Statement statement = getconnection().createStatement();
            String executeQuery = "INSERT  INTO lesson (theme, place,data) VALUES ('" + theme + "','" + place + "','" + data + "')";
            System.out.println(executeQuery);
            statement.execute(executeQuery);
            log.info("Запрос выполнен");
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException();
    }

    @Override
    public void removeLesson(int id) throws SQLException, EntityExistsException {
        try {
            Statement statement = getconnection().createStatement();
            String executeQuery = ("DELETE FROM lesson WHERE (`id`) = " + id + "; ");
            System.out.println(executeQuery);
            statement.execute(executeQuery);
        } catch (SQLException e) {
            log.error("Удаление не произошло");
            e.printStackTrace();
        }

    }

    @Override
    public void updateLesson(Lesson lesson) throws SQLException {
        try {
            Statement statement = getconnection().createStatement();
            String executeQuery = "UPDATE  lesson SET theme = '" + lesson.getTheme() + "', place = '" + lesson.getPlace() + "', data = '" + lesson.getDate() + "' WHERE (`id`) = " + lesson.getId() + ";";
            System.out.println(executeQuery);
            statement.execute(executeQuery);
            log.info("Запрос на обновление выполнен");
        } catch (SQLException e) {
            log.error("Обновление не произошло (");
            e.printStackTrace();
        }
    }

}
