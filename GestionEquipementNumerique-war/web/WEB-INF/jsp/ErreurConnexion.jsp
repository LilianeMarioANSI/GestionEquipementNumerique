<%-- 
    Document   : ErreurConnexion
    Created on : 22 mars 2024, 11:14:35
    Author     : Utilisateur
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Erreur de connexion</title>
    <style>
        /* Styles CSS pour la mise en forme de la page */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
            padding-top: 50px;
        }
        .container {
            max-width: 500px;
            margin: auto;
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #ff0000;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Erreur de connexion</h2>
        <p><%= request.getAttribute("message") %></p>
        <p>Veuillez réessayer plus tard ou contacter l'administrateur du système.</p>
    </div>
</body>
</html>

