package pl.well.dao.impl;

import org.slf4j.LoggerFactory;
import pl.well.controller.login.RegistryStudentController;
import pl.well.dao.PresenceDao;
import pl.well.dao.exception.DaoSystemException;
import pl.well.entity.Student;

import java.sql.*;
import java.util.Arrays;

import static pl.well.pool.pool.getconnection;

public class PresentDaoBDImpl implements PresenceDao {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RegistryStudentController.class);


    public PresentDaoBDImpl() {
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
    public void setFalse(String studentId, String column) throws SQLException {

        Statement statement = getconnection().createStatement();
        String executeQuery = "UPDATE  presenceLoh SET les" + column + " = '-1' WHERE (`studentid`) = " + studentId + ";";
        System.out.println(executeQuery);
        statement.execute(executeQuery);
        log.debug("Update request completed: = {}", executeQuery);

    }

    @Override
    public void setTrue(String studentId, String column) throws SQLException {

        Statement statement = getconnection().createStatement();
        String executeQuery = "UPDATE  presenceLoh SET les" + column + " = '1' WHERE (`studentid`) = " + studentId + ";";
        System.out.println(executeQuery);
        statement.execute(executeQuery);
        log.debug("Update request completed: = {}", executeQuery);

    }


    @Override
    public int[] selectAllUserPresense(int id) throws DaoSystemException, SQLException {

        int[] presence = new int[8];

        log.debug("User id = " + id);

        String sql = "SELECT * FROM presenceLoh WHERE studentid = '" + id + "' ORDER BY id ASC ;";
        try {
            Statement statement = getconnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                presence[0] = (resultSet.getInt(3));
                presence[1] = (resultSet.getInt(4));
                presence[2] = (resultSet.getInt(5));
                presence[3] = (resultSet.getInt(6));
                presence[4] = (resultSet.getInt(7));
                presence[5] = (resultSet.getInt(8));
                presence[6] = (resultSet.getInt(9));
                presence[7] = (resultSet.getInt(10));

            }

            log.debug(Arrays.toString(presence));


        } catch (SQLException e) {
            log.error("Падение при SELECT * FROM presence ");
            e.printStackTrace();
        }


        return presence;

    }


    @Override
    public void addStudent(Student student) throws SQLException {


        log.debug("User id = {}", student.getId());

        String sql = "INSERT INTO presenceLoh (studentid) VALUES  ('" + student.getId() + "')";
        log.debug("executeQuery = {}", sql);
        try {
            Statement statement = getconnection().createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            log.error("Падение при " + sql);
            e.printStackTrace();
        }

    }

}


//SELECT * FROM student LEFT JOIN presenceLoh ON student.id = presenceLoh.studentid

//les" + column + "  = '1' WHERE (`id`) = id;



