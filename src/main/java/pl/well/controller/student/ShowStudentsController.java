package pl.well.controller.student;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.dao.StudentDao;
import pl.well.dao.exception.DaoSystemException;
import pl.well.dao.impl.StudentDaoDBImpl;
import pl.well.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Admin on 11.11.2015.
 */


public class ShowStudentsController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ShowStudentsController.class);


    public static final String PAGE_OK = "/JSP/admin/showAllStudent.jsp";
    public static final String PAGE_ERROR = "/JSP/error.jsp";
    public static final String PAGE_RE_ADD = "/JSP/admin/showAllStudent.jsp";


    private StudentDao studentDao = new StudentDaoDBImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ArrayList<Student> students = studentDao.selectAllStudent();
            req.setAttribute("students", students);
            HttpSession session = req.getSession();
            log.debug("Время работы сессии {}", session.getMaxInactiveInterval());
            session.setAttribute("students", students);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (DaoSystemException e) {
            e.printStackTrace();
        }


        resp.sendRedirect(PAGE_ERROR);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ArrayList<Student> students = studentDao.selectAllStudent();
            req.setAttribute("students", students);
            HttpSession session = req.getSession();
            session.setAttribute("students", students);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (DaoSystemException e) {
            e.printStackTrace();
        }


        resp.sendRedirect(PAGE_RE_ADD);
    }


}



