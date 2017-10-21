package pl.well.controller.lesson;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.dao.LessonDao;
import pl.well.dao.exception.EntityExistsException;
import pl.well.dao.impl.LessonDaoBDImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Admin on 11.11.2015.
 */


public class RemoveLessonController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(RemoveLessonController.class);
    public static final String PAGE_OK = "/admin/showLessons.do";
    public static final String PAGE_ERROR = "/JSP/error.jsp";

    private LessonDao lessonDao = new LessonDaoBDImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        log.debug("ID for removing = {}", id);

        if (id != null) {
            try {
                lessonDao.removeLesson(Integer.parseInt(id));
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (EntityExistsException e) {
                e.printStackTrace();
            }

        }

        resp.sendRedirect(PAGE_ERROR);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}



