<%@ page import="pl.well.entity.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: serfer
  Date: 14.10.2017
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% ArrayList<Student> students = (ArrayList) session.getAttribute("students");
    int count = 0;
%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Lista studentów</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
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
                    <h3 class="card-title">Lista studentów</h3>
                    <p class="card-text text-muted">Liczba studentów: <%= students.size()%>
                    </p>
                </div>
            </div>
        </div>
        <div class="row bg-faded justify-content-md-center mt-5">
            <div class="col col-lg-8 col-md-10 col-sm-12">
                <div class="p-2">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon"><i class="fa fa-search" aria-hidden="true"></i></div>
                        <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Username">
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col pull-sm-0 pull-md-1">
                <div class="p-2">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0 justify-content-end">
                        <button type="button" class="btn btn-sm btn-success" data-toggle="modal"
                                data-target="#additionModal">Dodaj studenta
                        </button>

                    </div>
                </div>
            </div>
        </div>
        <!-- TABLE: Begin -->
        <div class="row justify-content-md-center pt-0">
            <div class="col ol-lg-8 col-md-10 col-sm-12 mb-5">
                <table class="table table-hover ">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Dane studenta</th>
                        <th>Kontant</th>
                        <th style="width:20%;"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${students}" var="student">
                        <tr>
                            <th scope="row"><%= ++count%>
                            </th>
                            <td>${student.name} ${student.surname}</td>
                            <td>${student.email}</td>
                            <td align="right">
                                <a class="pgs-text-color mr-2" href="#" data-toggle="modal" data-target="#editingModal"
                                   data-id="${student.id}" data-name="${student.name}" data-surname="${student.surname}"
                                   data-kontakt="${student.email}"><i class="fa fa-pencil-square-o fa-lg"
                                                                      aria-hidden="true"></i></a>
                                <a class="pgs-text-color mr-2" href="#" data-toggle="modal" data-target="#deletingModal"
                                   data-id="${student.id}"><i class="fa fa-trash-o fa-lg" aria-hidden="true"></i></a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <!-- Modal window for addition data: Begin -->
                <div class="modal fade" id="additionModal" tabindex="-1" role="dialog"
                     aria-labelledby="additionModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="additionModalLabel">Dodaj nowego studenta</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/addStudent.do" method="post">
                                    <div class="form-group">
                                        <label for="recipient-name" class="form-control-label">Imie:</label>
                                        <input type="text" class="form-control" id="additionName" name="name">
                                    </div>
                                    <div class="form-group">
                                        <label for="recipient-name" class="form-control-label">Nazwisko:</label>
                                        <input type="text" class="form-control" id="additionSurname" name="surname">
                                    </div>
                                    <div class="form-group">
                                        <label for="message-text" class="form-control-label">Kontakt:</label>
                                        <input type="text" class="form-control" id="additionKontakt" name="email">
                                    </div>
                                    <div class="form-group has-danger">
                                        <label for="message-text" class="form-control-label">Hasło:</label>
                                        <input type="password" class="form-control" id="additionPass" name="pass"
                                               onkeyup="checkPass()">
                                    </div>
                                    <div class="form-group has-danger">
                                        <label for="message-text" class="form-control-label">Powtóż hasło:</label>
                                        <input type="password" class="form-control" id="additionPassRepeat"
                                               name="passRepeat" onkeyup="checkPass()">
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj
                                        </button>
                                        <button id="additionSubmit" type="submit" class="btn btn-success"
                                                disabled="false"> Wyslij
                                        </button>
                                    </div>
                                    <script>
                                        function checkPass() {
                                            var pass = $("#additionPass");
                                            var passPepeat = $("#additionPassRepeat");

                                            if (pass.val() == passPepeat.val()) {
                                                $("#additionSubmit").removeAttr("disabled");
                                                pass.parent().removeClass("has-danger");
                                                passPepeat.parent().removeClass("has-danger");
                                            } else {
                                                $("#additionSubmit").attr("disabled", "true");
                                                pass.parent().addClass("has-danger");
                                                passPepeat.parent().addClass("has-danger");
                                            }
                                        }
                                    </script>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Modal window for addition data: End -->
                <!-- Modal window for editing data: Begin -->
                <div class="modal fade" id="editingModal" tabindex="-1" role="dialog"
                     aria-labelledby="editingModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="editingModalLabel">Edytowanie danych studenta</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/updateStudent.do" method="post">
                                    <input type="hidden" id="editingId" name="id" value="">
                                    <div class="form-group">
                                        <label for="recipient-name" class="form-control-label">Nowe imie:</label>
                                        <input type="text" class="form-control" id="editingName" name="name">
                                    </div>
                                    <div class="form-group">
                                        <label for="recipient-name" class="form-control-label">Nowe nazwisko:</label>
                                        <input type="text" class="form-control" id="editingSurname" name="surname">
                                    </div>
                                    <div class="form-group">
                                        <label for="message-text" class="form-control-label">Nowy kontakt:</label>
                                        <input type="text" class="form-control" id="editingKontakt" name="email">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj
                                        </button>
                                        <button type="submit" class="btn btn-success"> Wyslij</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Modal window for editing data: End -->
                <!-- Modal window for deleting: Begin -->
                <div class="modal fade" id="deletingModal" tabindex="-1" role="dialog"
                     aria-labelledby="deletingModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deletingModalLabel">Usuwanie studenta</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/removeStudent.do" method="post">
                                    <input id="deletingId" type="hidden" name="id" value="1">
                                    <p><strong>Czy na pewno chcesz usunąć tego studenta ?</strong></p>
                                    <div class="alert alert-danger" role="alert">
                                        <strong>Uwaga!</strong> Nie można będzie wrócić!
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj
                                        </button>
                                        <button type="submit" class="btn btn-danger"> Usuń</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Modal window for deleting: End -->
                <script>
                    window.onload = function () {

                        $('#editingModal').on('show.bs.modal', function (event) {
                            console.log("Modal for editing was show!");
                            var button = $(event.relatedTarget); // Button that triggered the modal

                            var id = button.data('id');
                            var name = button.data('name');
                            var surname = button.data('surname');
                            var kontakt = button.data('kontakt');


                            var modal = $(this);
                            modal.find('.modal-body #editingId').val(id);
                            modal.find('.modal-body #editingName').val(name);
                            modal.find('.modal-body #editingSurname').val(surname);
                            modal.find('.modal-body #editingKontakt').val(kontakt);

                        });

                        $('#deletingModal').on('show.bs.modal', function (event) {
                            console.log("Modal for deleting was show!");
                            var button = $(event.relatedTarget); // Button that triggered the modal
                            var id = button.data('id');

                            var modal = $(this);
                            modal.find('.modal-body #deletingId').val(id);
                        });
                    };

                </script>

                <!-- Modal window for deleting: End -->
            </div>
        </div>
        <!-- TABLE: End -->
    </div>
</article>

<footer>
    <div class="container fixed-bottom">
        <div class="row">
            <div class="col bg-inverse p-2">
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
