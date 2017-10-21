package pl.well.controller.lesson;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.dao.LessonDao;
import pl.well.dao.exception.DaoSystemException;
import pl.well.dao.impl.LessonDaoBDImpl;
import pl.well.entity.Lesson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class ShowLessonsAdminController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ShowLessonsAdminController.class);


    public static final String PAGE_OK = "/JSP/admin/showAllLesson.jsp";
    public static final String PAGE_ERROR = "/JSP/error.jsp";

    private LessonDao lessonDao = new LessonDaoBDImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            ArrayList<Lesson> lessons = lessonDao.selectAllLesson();
            HttpSession session = req.getSession();
            session.setAttribute("lessons", lessons);
            System.out.println("Session up time " + session.getMaxInactiveInterval());
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (DaoSystemException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(PAGE_ERROR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}



