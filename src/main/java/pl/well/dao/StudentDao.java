package pl.well.dao;

import pl.well.dao.exception.DaoSystemException;
import pl.well.dao.exception.EntityExistsException;
import pl.well.dao.exception.NoSuchUserException;
import pl.well.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by serfer on 24.07.16.
 */
public interface StudentDao {


    void init();

    ArrayList<Student> selectAllStudent() throws DaoSystemException, SQLException;


    void addStudent(Student student) throws SQLException, EntityExistsException;


    void removeStudent(int id) throws SQLException;

    void updateStudent(Student student) throws SQLException;

    void checkEmail(String email) throws SQLException, EntityExistsException;

    void checkPass(String email) throws SQLException;


    Student selectById(int id) throws NoSuchUserException, DaoSystemException;

    Student selectByEmail(String email) throws NoSuchUserException, DaoSystemException, SQLException;
}
