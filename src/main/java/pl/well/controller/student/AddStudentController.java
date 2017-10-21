package pl.well.controller.student;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.dao.PresenceDao;
import pl.well.dao.StudentDao;
import pl.well.dao.exception.DaoSystemException;
import pl.well.dao.exception.EntityExistsException;
import pl.well.dao.exception.NoSuchUserException;
import pl.well.dao.impl.PresentDaoBDImpl;
import pl.well.dao.impl.StudentDaoDBImpl;
import pl.well.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Admin on 11.11.2015.
 */


public class AddStudentController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AddStudentController.class);


    public static final String PAGE_OK = "/showStudent.do";
    public static final String PAGE_ERROR = "/JSP/error.jsp";

    //  @Autowired
    private StudentDao studentDao = new StudentDaoDBImpl();
    private PresenceDao presentDao = new PresentDaoBDImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String passRepeat = req.getParameter("passRepeat");

        if (email != null & pass != null) {
            try {

                log.debug("Received data for user creation: name {}, surname {}, email {}, pass {}, passRepeat {}.", name, surname, email, pass, passRepeat);
                studentDao.checkEmail(email);
                Student student = new Student(name, surname, email, pass);
                studentDao.addStudent(student);
                student = studentDao.selectByEmail(email);
                presentDao.addStudent(student);
                log.debug("Student add");
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (SQLException e) {

                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                log.error("DB connect error");
                e.printStackTrace();
            } catch (EntityExistsException e) {
                log.debug("Email address is busy.");
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                e.printStackTrace();
            } catch (DaoSystemException e) {
                e.printStackTrace();
            } catch (NoSuchUserException e) {
                e.printStackTrace();
            }

        }

        resp.sendRedirect(PAGE_ERROR);
    }


}





