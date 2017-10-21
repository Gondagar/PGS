package pl.well.controller.student;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.dao.StudentDao;
import pl.well.dao.impl.StudentDaoDBImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Admin on 11.11.2015.
 */


public class RemoveStudentController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(RemoveStudentController.class);


    public static final String PAGE_OK = "/showStudent.do";
    public static final String PAGE_ERROR = "/jsp/error.jsp";


    private StudentDao studentDao = new StudentDaoDBImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        log.debug("Received ID for delete user = {}", id);


        if (id != null) {
            try {
                studentDao.removeStudent(Integer.parseInt(id));
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        resp.sendRedirect(PAGE_ERROR);
    }


}



