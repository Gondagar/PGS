package pl.well.dao.impl;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.well.controller.login.RegistryStudentController;
import pl.well.dao.StudentDao;
import pl.well.dao.exception.DaoSystemException;
import pl.well.dao.exception.EntityExistsException;
import pl.well.dao.exception.NoSuchUserException;
import pl.well.entity.Student;

import java.sql.*;
import java.util.ArrayList;

import static pl.well.pool.pool.getconnection;

@Component
public class StudentDaoDBImpl implements StudentDao {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RegistryStudentController.class);

    public StudentDaoDBImpl() {
        init();
    }


    @Override
    public ArrayList<Student> selectAllStudent() throws DaoSystemException {
        ArrayList<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM student ORDER BY student.id ASC;";
        try {
            Statement statement = getconnection().createStatement();
            log.debug(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);

                students.add(new Student(id, name, lastName, email, "", ""));
            }

        } catch (SQLException e) {
            log.error("SQL error at {}", sql);
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public void addStudent(Student student) throws SQLException, EntityExistsException {
        String name = student.getName();
        String surname = student.getSurname();
        String email = student.getEmail();
        String pass = student.getPass();

        String executeQuery = "INSERT  INTO student (name, surname,email,pass, role) VALUES ('" + name + "','" + surname + "','" + email + "','" + pass + "','user')";


        try {
            Statement statement = getconnection().createStatement();
            System.out.println(executeQuery);
            statement.execute(executeQuery);
            log.info("Запрос выполнен");
            return;
        } catch (SQLException e) {
            log.error("SQL error at {}", executeQuery);
            e.printStackTrace();
        }
        throw new SQLException();
    }

    @Override
    public void removeStudent(int id) throws SQLException {

        try {
            Statement statement = getconnection().createStatement();
            String executeQuery = ("DELETE FROM student WHERE (`id`) = " + id + "; ");
            System.out.println(executeQuery);
            statement.execute(executeQuery);
        } catch (SQLException e) {
            log.error("Удаление не произошло");
            e.printStackTrace();
        }

    }

    @Override
    public void updateStudent(Student student) throws SQLException {

        try {
            Statement statement = getconnection().createStatement();
            String executeQuery = "UPDATE  student SET name = '" + student.getName() + "', surname = '" + student.getSurname() + "', email = '" + student.getEmail() + "' WHERE (`id`) = " + student.getId() + ";";
            System.out.println(executeQuery);
            statement.execute(executeQuery);
            log.info("Запрос выполнен");
        } catch (SQLException e) {
            log.error("Обновление не произошло.");
            e.printStackTrace();
        }
    }

    // UPDATE student SET age = 160 WHERE name = 'Валера';

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
    public void checkPass(String pass) throws SQLException {

    }


    @Override
    public Student selectById(int id) throws NoSuchUserException, DaoSystemException {


        try {
            Statement statement = getconnection().createStatement();

            String queryCheck = "SELECT * FROM  student WHERE id = '" + id + "'";
            ResultSet resultSet = statement.executeQuery(queryCheck);
            while (resultSet.next()) {
                int myid = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String myEmail = resultSet.getString(4);

                return new Student(name, surname, myEmail);

            }

        } catch (SQLException e) {
            log.error("Ошибка выборки по ID");
            e.printStackTrace();
        }


        throw new NoSuchUserException("Пользоватеть с таким 'id' не найден");

    }

    @Override
    public Student selectByEmail(String email) throws NoSuchUserException, SQLException {

        try {

            Statement statement = getconnection().createStatement();
            String sql = "SELECT * FROM student WHERE email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String myEmail = resultSet.getString(4);
                String pass = resultSet.getString(5);
                String role = resultSet.getString(6);
                return new Student(id, name, surname, myEmail, pass, role);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new NoSuchUserException("Нет пользователя с  таким емейлом.");
    }

    @Override
    public void checkEmail(String email) throws EntityExistsException, SQLException {

        try {

            Statement statement = getconnection().createStatement();
            String sql = "SELECT * FROM student WHERE email = '" + email + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                throw new EntityExistsException("Пользователя с  таким емейлом существует.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
