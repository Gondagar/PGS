<%@ page import="pl.well.entity.Lesson" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pl.well.entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% ArrayList<Student> students = (ArrayList) request.getAttribute("students");
    ArrayList<Lesson> lessons = (ArrayList) request.getAttribute("lessons");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Lista studentów</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="bootstrap.css">

</head>

<body>

<!-- HEADER: Begin -->
<header class="navbar navbar-toggleable-md navbar-light bg-faded header-border">
    <div class="container">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand mb-0" href="#">
            <img src="https://png.icons8.com/java/ios7/32" width="32" height="32">
        </a>
        <a class="navbar-brand mb-0" href="#"><strong>Java</strong>Dev</a>


        <div class="collapse navbar-collapse justify-content-end" id="navbarTogglerDemo02">
            <ul class="navbar-nav mt-2 mt-md-0">
                <li class="nav-item active">
                    <a class="nav-link" href="/showStudent.do">Studenci</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/admin/showLessons.do">Zajęcia</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/showStudentsPresence.do">Lista obecności</a>
                </li>
            </ul>
        </div>


    </div>
</header>
<!-- HEADER: End -->

<article>
    <div class="container">
        <div class="row pt-5">
            <div class="col-12">
                <div class="text-center mt-5">
                    <h3 class="card-title">Lista obecności</h3>
                </div>
            </div>
        </div>
        <div class="row bg-faded justify-content-md-center mt-5 mb-5">
            <div class="col col-lg-8 col-md-10 col-sm-12">
                <div class="p-2">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <form style="display: inline-flex; width: inherit;" method="post" action="">
                            <input type="text" class="form-control" id="inlineFormInputGroup" name="search-query"
                                   placeholder="Szukaj">
                            <span class="input-group-btn ">
                                <button class="btn btn-secondary" type=""><i class="fa fa-search"
                                                                             aria-hidden="true"></i></button>
                            </span>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- TABLE: Begin -->
        <div class="row mt-3 justify-content-md-center pt-0">
            <div class="col">
                <div class="row border-bottom pb-3">
                    <table class="table text-center">
                        <thead>
                        <tr class="text-muted border-right border-top-0">
                            <th class="font-weight-normal text-muted">Imie i Nazwisko</th>

                            <% for (int i = 0; i < lessons.size(); i++) { %>
                            <th class="font-weight-normal text-muted"><%=lessons.get(i).getDate()%>
                            </th>
                            <%}%>


                        </tr>
                        </thead>
                        <tbody>

                        <% for (int i = 0; i < students.size(); i++) {
                            Student student = students.get(i);
                            int studentPresentCount = 0;
                            for (int j = 0; j < student.getPresence().length; j++) {
                                if (student.getPresence()[j] == 1) {
                                    studentPresentCount++;
                                }
                            }
                        %>
                        <tr class="border-right">
                            <th><%=student.getName()%> <%=student.getSurname()%> <span
                                    class="font-weight-normal text-muted font-italic">(<%=studentPresentCount%>/8)</span>
                            </th>
                            <% for (int j = 0; j < lessons.size(); j++) {
                                int[] presence = student.getPresence();
                                if (presence[j] == -1) { %>
                            <td>
                                <span class="text-danger  opacity-icon"><i class="fa fa fa-times-circle-o fa-2x"
                            </td>
                            <%} else if (presence[j] == 1) {%>
                            <td>
                                <span class="text-success  opacity-icon"><i class="fa fa-check-circle-o fa-2x"
                            </td>
                            <%} else if (presence[j] == 0) {%>
                            <td>
                                <span class="text-mute" style="opacity:0.2;"><i class="fa fa-check-circle-o fa-2x"
                                                                                aria-hidden="true"></i></span>
                            </td>
                            <%}%>
                            <%}%>
                        </tr>
                        <%}%>
                        <tr class="border-right">
                            <th></th>

                            <% for (int i = 0; i < lessons.size(); i++) {
                                int counter = 0;
                                for (Student student : students) {
                                    int key = student.getPresence()[i];
                                    if (key == 1) {
                                        counter++;
                                    }
                                }
                                System.out.println("Presence to lesson " + i + " = " + counter);
                            %>
                            <td>
                                <span class="font-weight-bold text-muted"><%=((100 / students.size()) * counter)%>/100</span>
                            </td>
                            <%}%>
                        </tr>

                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- TABLE: End -->
    </div>
</article>

<footer>
    <div class="container fixed-bottom">
        <div class="row">
            <div class="col bg-inverse p-4">
                <p class="text-white text-center mb-0">Made with <i class="fa fa-heart text-danger"
                                                                    aria-hidden="true"></i> by Serhij Moskalenko</p>
            </div>
        </div>
    </div>
</footer>


<!-- SCRIPTS: Begin -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
        integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
        crossorigin="anonymous"></script>
<!-- SCRIPTS: End -->
</body>

</html>


