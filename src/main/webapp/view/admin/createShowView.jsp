<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="es">

    <body>
        

    <%@include file="/includes/header.jsp"%>


        <main>
            <div class="main">
                <div class="background">
                    <p>¿Qué clase de espectáculo quieres añadir?</p>
                    <div class="show-type">
                        <ul class="show-type__items">
                            <li class="show-type__item">
                                <a href="espectaculoPuntual.html">Espectáculo puntual</a>
                            </li>
                            <li class="show-type__item">
                                <a href="espectaculoMultiple.html">Espectáculo múltiple</a>
                            </li>
                            <li class="show-type__item">
                                <a href="espectaculoTemporada.html">Pase de temporada</a>
                            </li>
                        </ul>
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