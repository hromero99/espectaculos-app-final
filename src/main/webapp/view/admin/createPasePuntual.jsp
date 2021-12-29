<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Dionysium</title>
        <link rel="stylesheet" href="/static/css/creacionEspectaculo.css">
    </head>

    <body>
        

        <div class="backdrop"></div>

	<%@include file="/includes/header.jsp" %>
	

        <main>
            <div class="main">
                <div class="background">
                    <div class="puntual">
                        <div>
                            <h2 class="puntual-form__title">Espectáculo puntual</h2>
                        </div>
                        <form class="puntual-form" method="POST" action="CreatePaseShow">
                            <div class="puntual-form__item">
                                <label for="nombre">Nombre</label>
                                <input type="text" placeholder="Nombre">
                            </div>
                            <div class="puntual-form__item">
                                <label for="nombre">Fecha</label>
                                <input type="date" placeholder="Fecha">
                            </div>
                            <div class="puntual-form__item">
                                <label for="nombre">Hora</label>
                                <input type="time" placeholder="Hora">
                            </div>

                            <button type="submit" class="button">Añadir Espectáculo</button>

                        </form>
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