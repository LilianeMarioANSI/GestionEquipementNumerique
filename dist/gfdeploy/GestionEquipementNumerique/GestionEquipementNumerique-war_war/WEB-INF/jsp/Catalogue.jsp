<%-- 
    Document   : Catalogue
    Created on : 18 mars 2024, 09:35:13
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
        
        <form method="get" action="ServletGestionEquipement?action=afficherCatalogue">
            <label for="type">Type :</label>
            <select name="type" id="type">
                <option value="don">Don</option>
                <option value="pret">Prêt</option>
            </select>

            <label for="etat">État :</label>
            <select name="etat" id="etat">
                <option value="neuf">Neuf</option>
                <option value="quasi_neuf">Quasi neuf</option>
                <option value="usagé">Usagé</option>
                <option value="mauvais">Mauvais</option>
            </select>

            <label for="categorie">Catégorie :</label>
            <input type="text" name="categorie" id="categorie">

            <button type="submit">Filtrer</button>
        </form>
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
