package pl.well.controller.login;


import org.slf4j.LoggerFactory;
import pl.well.dao.StudentDao;
import pl.well.dao.exception.DaoSystemException;
import pl.well.dao.exception.NoSuchUserException;
import pl.well.dao.impl.StudentDaoDBImpl;
import pl.well.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class LoginController extends HttpServlet {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoginController.class);


    public static final String PAGE_ADMIN = "/admin/showLessons.do";
    public static final String PAGE_USER = "/user/showLessons.do";
    public static final String PAGE_ERROR = "/JSP/error.jsp";

    //    /JSP/okLogin.jsp
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private StudentDao studentDao = new StudentDaoDBImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("login");
        String pass = req.getParameter("pass");
        String forgot = req.getParameter("forgot");
        HttpSession session = req.getSession();
        if (email != null) {
            try {

                Student student = studentDao.selectByEmail(email);
                if (student.getPass().equals(pass)) {


                    log.info("Password valid ");
                    if (student.getRole().equals("admin")) {
                        log.debug("Hello admin {}", student.getName());
                        session.setAttribute("admin", student);
                        req.getRequestDispatcher(PAGE_ADMIN).forward(req, resp);
                    } else if (student.getRole().equals("user")) {

                        session.setAttribute("student", student);
                        req.getRequestDispatcher(PAGE_USER).forward(req, resp);

                    } else {
                        throw new SQLException("Invalide role");
                    }
                    return;
                }

                throw new NoSuchUserException("Invalid password");

            } catch (SQLException e) {
                log.error("DB connect error");
                req.getRequestDispatcher(PAGE_ERROR).forward(req, resp);
                e.printStackTrace();


            } catch (NoSuchUserException e) {
                log.error("Invalid password");
                session.setAttribute("error", "error");
                req.getRequestDispatcher("./login.jsp").forward(req, resp);
                return;

            } catch (DaoSystemException e) {
                e.printStackTrace();
            }

        }

        resp.sendRedirect(PAGE_ERROR);
    }


}



