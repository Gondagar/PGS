<%@ page import="pl.well.entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Данные пользователя></title>
</head>
<body>

<hr>
<br>
<% Student student = (Student) request.getAttribute("student");


%>
Id <%=  student.getId() %> <br>
Name <%=  student.getName() %><br>
Email <%= student.getEmail() %><br>


<hr>
<br>


<%--
Id ${student.id} <br>
Name ${student.name}<br>
Email ${student.pl}<br>
Age ${student.age} </br>
Ответ на  анотациях  <%= "${}"%>>;
<hr><br>
--%>
<a href="/user/showLessons.do">Перейти на главную страницу</a>
</body>
</html>
