<%-- 
    Document   : Catalogue
    Created on : 17 mars 2024, 15:43:25
    Author     : Utilisateur
--%>

<%@page import="java.util.List"%>
<%@page import="Entite.Offre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogue des offres</title>
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/inscription.css">
    </head>
    
    <%@include file="/WEB-INF/jspf/navigation.jspf" %>
    <%@include file="/WEB-INF/jspf/header.jspf" %>

    <body>
        <h2 class="form-title">Offres en ligne</h2>
        <%
            List<Offre> offres = (List<Offre>) request.getAttribute("offres");
            if(offres != null && !offres.isEmpty()) {
                for(Offre offre : offres) {
        %>
        <div>
            <h2><%= offre.getIntitule() %></h2>
        </div>
        <%
                }
            } else {
        %>
        <p>Aucune offre n'est disponible pour le moment.</p>
        <%
            }
        %>
    </body>
</html>
