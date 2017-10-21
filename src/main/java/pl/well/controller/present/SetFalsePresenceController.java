package pl.well.controller.present;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.dao.PresenceDao;
import pl.well.dao.impl.PresentDaoBDImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Admin on 11.11.2015.
 */


public class SetFalsePresenceController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(SetFalsePresenceController.class);


    public static final String PAGE_OK = "/user/showLessons.do";
    public static final String PAGE_ERROR = "/jsp/error.jsp";
    public static final String PAGE_RE_ADD = "/user/showLessons.do";

    //  @Autowired
    private PresenceDao presentDao = new PresentDaoBDImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String column = req.getParameter("column");


        if (id != null) {
            try {
                log.debug("Получены данные для обновления - User id = {}, lesson id = {}.", id, column);
                presentDao.setFalse(id, column);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (SQLException e) {
                log.error("DB connect error");
                e.printStackTrace();
            }

        } else {
            req.getRequestDispatcher(PAGE_RE_ADD).forward(req, resp);

        }

        resp.sendRedirect(PAGE_ERROR);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


}



