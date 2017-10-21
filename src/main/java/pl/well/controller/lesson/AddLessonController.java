package pl.well.controller.lesson;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.dao.LessonDao;
import pl.well.dao.impl.LessonDaoBDImpl;
import pl.well.entity.Lesson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class AddLessonController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(AddLessonController.class);
    public static final String PAGE_OK = "/admin/showLessons.do";
    public static final String PAGE_ERROR = "/JSP/error.jsp";

    //  @Autowired
    private LessonDao lessonDao = new LessonDaoBDImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String theme = req.getParameter("theme");
        String place = req.getParameter("place");
        String feedback = req.getParameter("feedback");
        String date = req.getParameter("date");

        String dt = req.getParameter("date");
        log.debug("Полученая дата - {}", dt);


        if (theme != null & place != null & date != null) {
            try {
                log.debug("Полученны данные для создания пользователя:  theme = {}, place = {}, date = {}.", theme, place, date.toString());
                Lesson lesson = new Lesson(theme, place, feedback, date);
                lessonDao.addLesson(lesson);
                log.debug("Lesson add");
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (SQLException e) {
                log.debug("Lesson add fail via SQL");
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                e.printStackTrace();
            }

        }

        resp.sendRedirect(PAGE_ERROR);
    }


}



