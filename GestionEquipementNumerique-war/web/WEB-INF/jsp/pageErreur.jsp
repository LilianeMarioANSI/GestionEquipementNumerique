<%-- 
    Document   : pageErreur
    Created on : 25 mars 2024, 16:30:29
    Author     : LILIANE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404 erreur</title>
        <link rel="stylesheet" type="text/css" href="Style/erreur.css">
    </head>
    
    <body>
        <main>
            <image src="/Assets/image/erreur404.png" alt="404"/>
            <div class="erreur">
                <h1>404</h1>
                <h2>Oh non !</h2>
                <p>Vous avez rencontré une erreur 404 - Page non trouvée.
                    Il semble que la page que vous recherchez n'existe pas. </p>
                <form>
                    <input type="hidden" name="page" value="erreur404">
                    <button onclick="window.location.href='Accueil.jsp'">Retour à l'accueil</button>
                    <button onclick="window.location.href='javascript:history.back()'">Retour à la page précédente</button>
                </form>
            </div>
        </main>
    </body>
</html>
