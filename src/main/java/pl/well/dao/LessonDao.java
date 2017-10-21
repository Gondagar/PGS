package pl.well.dao;

import pl.well.dao.exception.DaoSystemException;
import pl.well.dao.exception.EntityExistsException;
import pl.well.entity.Lesson;

import java.sql.SQLException;
import java.util.ArrayList;


public interface LessonDao {


    void init();

    ArrayList<Lesson> selectAllLesson() throws DaoSystemException, SQLException;

    void addLesson(Lesson lesson) throws SQLException;

    void removeLesson(int id) throws SQLException, EntityExistsException;


    void updateLesson(Lesson lesson) throws SQLException;


}
