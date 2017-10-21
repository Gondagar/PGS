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

/**
 * Created by Admin on 11.11.2015.
 */


public class UpdateLessonController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UpdateLessonController.class);


    public static final String PAGE_OK = "/admin/showLessons.do";
    public static final String PAGE_ERROR = "/JSP/error.jsp";
    public static final String PAGE_RE_ADD = "/JSP/admin/editLessons.jsp";

    //  @Autowired   /JSP/admin/editLessons.jsp
    private LessonDao lessonDao = new LessonDaoBDImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String theme = req.getParameter("theme");
        String place = req.getParameter("place");
        String feedback = req.getParameter("feedback");

        String dt = req.getParameter("date");
        String date = parseDate(dt);

        // 2017-10-19T17:00


        if (id != null & theme != null & place != null & date != null) {
            try {
                log.debug("Получены новые данные для обновления урока в DB: theme = {}, place = {}, Date = {}.", theme, place, date);

                Lesson lesson = new Lesson(Integer.parseInt(id), theme, feedback, place, date);
                lessonDao.updateLesson(lesson);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            req.getRequestDispatcher(PAGE_RE_ADD).forward(req, resp);

        }

        resp.sendRedirect(PAGE_ERROR);
    }

    private String parseDate(String dt) {
        dt = dt.replace('T', ' ') + ":00";
        log(dt);

        return dt;
    }
}
