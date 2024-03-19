<%-- 
    Document   : Catalogue
    Created on : 18 mars 2024, 09:35:13
    Author     : Utilisateur
--%>

<%@page import="Entite.EtatAccessoire"%>
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
        <link rel="stylesheet" type="text/css" href="Style/catalogue.css">
    </head>
    
    <%@include file="/WEB-INF/jspf/navigation.jspf" %>
    <%@include file="/WEB-INF/jspf/header.jspf" %>

    <body>
        <h2 class="form-title">Offres en ligne</h2>
        
        <form method="get" action="ServletGestionEquipement?action=afficherCatalogue">
            <label for="type">Type :</label>
            <select name="type" id="type">
                <option value="don">Don</option>
                <option value="pret">Prê        <form method="get" action="ServletGestionEquipement?action=afficherCatalogue">t</option>
            </select>

            <label for="etat">État :</label>
            <select name="etat" id="etat" required>
                <% for (EtatAccessoire e : EtatAccessoire.values()) {%>
                    <option value ="<%=e.label%>"><%=e.label%></option>
                <% }%>
            </select>

            <label for="categorie">Catégorie :</label>
            <input type="text" name="categorie" id="categorie">

            <button type="submit">Filtrer</button>
        </form>
            
            <div class="offerContainer">
                <%
                    List<Offre> offres = (List<Offre>) request.getAttribute("offres");
                    if(offres != null && !offres.isEmpty()) {
                        for(Offre offre : offres) {
                %>
                    <div class="offer">
                        <img class="large-icon" src="Assets/icons/computer-mouse-solid.svg" alt="logo"/>
                        <h2><%= offre.getIntitule() %></h2>
                        <form method="post" action="ServletGestionEquipement">
                            <input type="hidden" name="action" value="afficherDetailOffre">
                            <input type="hidden" name="idOffre" value="<%=offre.getId()%>">
                            <button type="submit" class="submit">Voir l'offre</button>
                        </form>
                    </div>
                <%
                        }
                    } else {
                %>
                <p>Aucune offre n'est disponible pour le moment.</p>
                <%
                    }
                %>
            </div>
        
    </body>
</html>
