package pl.well.controller.login;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.dao.StudentDao;
import pl.well.dao.exception.EntityExistsException;
import pl.well.dao.impl.StudentDaoDBImpl;
import pl.well.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class RegistryStudentController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(RegistryStudentController.class);


    public static final String PAGE_OK = "/login.do";
    public static final String PAGE_RE_REGISTRY = "/registry.jsp";
    public static final String PAGE_ERROR = "/JSP/error.jsp";

    //  @Autowired
    private StudentDao studentDao = new StudentDaoDBImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String passRepeat = req.getParameter("passRepeat");


        HttpSession session = req.getSession();

        if (email != null & pass != null) {
            try {

                log.debug("name  {}, surname {}, + email {}, pass {},   passRepeat {}", name, surname, email, pass, passRepeat);
                System.out.println(name + " " + surname + " " + email + " " + pass + " " + passRepeat);
                studentDao.checkEmail(email);
                Student student = new Student(name, surname, email, pass);
                studentDao.addStudent(student);
                log.debug("Student add");
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (SQLException e) {
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                e.printStackTrace();
            } catch (EntityExistsException e) {
                System.out.println();
                log.error("Емайл занят");
                session.setAttribute("error", "error");
                req.getRequestDispatcher(PAGE_RE_REGISTRY).forward(req, resp);
            }

        }

        resp.sendRedirect(PAGE_ERROR);
    }


}



