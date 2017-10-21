package pl.well.dao;

import pl.well.dao.exception.DaoSystemException;
import pl.well.entity.Student;

import java.sql.SQLException;

public interface PresenceDao {

    void init();


    void setFalse(String studentId, String column) throws SQLException;

    void setTrue(String studentId, String column) throws SQLException;


    int[] selectAllUserPresense(int id) throws DaoSystemException, SQLException;

    void addStudent(Student student) throws SQLException;


}
