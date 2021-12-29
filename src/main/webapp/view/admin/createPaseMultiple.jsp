<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Dionysium</title>
        <link rel="stylesheet" href="navbar.css">
        <link rel="stylesheet" href="/static/css/espectaculoMultiple.css">
    </head>

    <body>
  	<%@include file="/includes/header.jsp" %>


        <main>
            <div class="main">
                <div class="background">
                    <div class="multiple">
                        <div>
                        </div>
                        <form class="multiple-form" method="POST" action="CreatePaseShow">
                            <h2 class="multiple-title">Espectáculo Múltiple</h2>

                            <div class="multiple-form__item">
                                <label for="nombre">Nombre</label>
                                <input type="text" placeholder="Nombre">
                            </div>
                            <div class="multiple-form__item">
                                <label for="nombre">Fecha</label>
                                <input id="date" type="date" placeholder="Fecha">
                            </div>
                            <div class="multiple-form__item">
                                <label for="nombre">Hora</label>
                                <input id="time" type="time" placeholder="Hora">
                            </div>
                            <button type="button" id="add_date">Añadir Fecha</button>
                            <button type="submit" class="button">Añadir Espectáculo</button>


                        </form>
                        <div class="multiple-form__table" id="dateObjects">
                            <ul class="multiple-form__table__items" id="table">

                            </ul>
                        </div>

                    </div>
                </div>
            </div>
        </main>

        <footer class="main-footer">
            <nav>
                <ul class="main-footer__links">
                    <li class="main-footer__link">
                        <a href="#">Support</a>
                    </li>
                    <li class="main-footer__link">
                        <a href="#">Terms of use</a>
                    </li>
                </ul>
            </nav>
        </footer>


    </body>

    <script src="shared.js"></script>



</html>