package pl.well.controller.student;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.well.dao.StudentDao;
import pl.well.dao.impl.StudentDaoDBImpl;
import pl.well.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Admin on 11.11.2015.
 */


public class UpdateStudentController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UpdateStudentController.class);


    public static final String PAGE_OK = "/showStudent.do";
    public static final String PAGE_ERROR = "/jsp/error.jsp";
    public static final String PAGE_RE_ADD = "/showStudent.do";

    //  @Autowired
    private StudentDao studentDao = new StudentDaoDBImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");


        if (id != null & name != null & surname != null & email != null) {
            try {
                log.debug("Получены новые данные для обновления урока в DB: name = {}, surname = {}, email = {}.", name, surname, email);
                Student student = new Student(Integer.parseInt(id), name, surname, email, "", "");
                studentDao.updateStudent(student);
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


}



