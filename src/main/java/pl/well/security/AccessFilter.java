package pl.well.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by serfer on 18.07.16.
 */
public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) (servletRequest);
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        long timeStart = new Date().getTime();

        System.out.println("Тип запроса = " + request.getMethod());
        System.out.println("Запрос с адресса = " + request.getRemoteAddr());
        System.out.println("Порт клиента = " + servletRequest.getRemotePort());
        System.out.println("Строка запроса = " + request.getQueryString());
        System.out.println("getRequestURI = " + request.getRequestURI());

        System.out.println("Инфа залогирована и запрос отправлен на сервлет '/login.jsp' \n--------------------------------");
        servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);

        long timeFinish = new Date().getTime();


        System.out.println("Управление с ответом вернулось обратно  в фильтр 'AccessFilter'");
        System.out.println("Время обработки запроса - " + (timeFinish - timeStart));
    }

    @Override
    public void destroy() {

    }
}
