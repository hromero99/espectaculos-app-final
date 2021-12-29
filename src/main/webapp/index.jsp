<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="es">

    <head>
        <meta charset="UTF-8">
        <title>Dionysium</title>
        <link rel="stylesheet" href="static/css/navbar.css" type="text/css">
        <link rel="stylesheet" href="static/css/index.css" type="text/css">
    </head>

    <body>
        

        <div class="backdrop"></div>
        <div class="login-modal">
            <h1 class="login-modal__title">Login</h1>
            <form class="login-modal__login-form" method="POST" action="login">
                
                <div class="login-modal__login-form__input">
                    <label for="email">Email</label>
                    <input type="email" id="email" required>
                </div>

                <div class="login-modal__login-form__input">
                    <label for="password">Password</label>
                    <input type="password" id="password" required>
                </div>


                <button type="submit" class="button">Log in</button>
            </form>
        </div>

	<%@include file="/includes/header.jsp"%>
	
        <main>
            <div class="main">
                <div class="background">

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

    <script src="static/js/shared.js"></script>



</html>