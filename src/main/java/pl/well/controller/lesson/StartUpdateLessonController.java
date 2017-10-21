package pl.well.controller.lesson;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.entity.Lesson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Admin on 11.11.2015.
 */


public class StartUpdateLessonController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(StartUpdateLessonController.class);

    public static final String PAGE_OK = "/JSP/admin/editLessons.jsp";
    public static final String PAGE_ERROR = "/JSP/error.jsp";
    public static final String PAGE_RE_ADD = "/showLessons.do";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String theme = req.getParameter("theme");
        String place = req.getParameter("place");
        String feedback = req.getParameter("feedback");
        String oldDate = req.getParameter("date");


        if (id != null & theme != null & place != null & oldDate != null) {

            log.debug("Получены старые данные для обновления урока: theme = {}, place = {}, oldDate = {}.", theme, place, oldDate);
            Lesson lesson = new Lesson(Integer.parseInt(id), theme, place, feedback, oldDate);
            HttpSession session = req.getSession();
            session.setAttribute("lesson", lesson);
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            return;
        } else {
            req.getRequestDispatcher(PAGE_RE_ADD).forward(req, resp);
            return;

        }

    }


}



