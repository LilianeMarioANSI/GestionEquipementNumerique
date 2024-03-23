<%-- 
    Document   : mesEquipements
    Created on : 18 mars 2024, 10:12:25
    Author     : Utilisateur
--%>

<%@page import="Entite.TypeAccessoire"%>
<%@page import="Entite.EtatAccessoire"%>
<%@page import="Entite.Accessoire"%>
<%@page import="Entite.Offre"%>
<%@page import="Entite.Membre"%>
<%@page import="Entite.TypeOffre"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/inscription.css">
        <link rel="stylesheet" type="text/css" href="Style/catalogue.css">
        <link rel="stylesheet" type="text/css" href="Style/mesSouhaits.css">
        <title>Mes Equipements</title>
        <script src="Js/filtreOffres.js"></script>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation_membre.jspf" %>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <main class="vertical">
        
        <%@include file="/WEB-INF/jspf/message.jspf" %>
            <div class="filter-section">
                <label for="filterDon">Dons :</label>
                <input type="checkbox" id="filterDon">
                <label for="filterPret">Prêts :</label>
                <input type="checkbox" id="filterPret">
                </br>
                <label for="etatEquipement">État :</label>
                <select id="etatEquipement">
                    <option value="Tous">Tous</option>
                    <% for (EtatAccessoire e : EtatAccessoire.values()) { %>
                    <option value="<%= e.label %>"><%= e.label %></option>
                    <% } %>
                </select>
                </br>
                <label for="categorie">Catégorie :</label>
                <select id="categorie">
                    <option value="Toutes">Toutes</option>
                    <% for (TypeAccessoire t : TypeAccessoire.values()) { %>
                    <option value="<%= t.label %>"><%= t.label %></option>
                    <% } %>
                </select>
                </br>
                <button id="applyFilterBtn">Appliquer</button>
                <button id="resetFilterBtn">Réinitialiser</button>
            </div>
            
            <div class="offerContainer">
                <%
                    List<Offre> offres = (List<Offre>) request.getAttribute("offres");
                    if(offres != null && !offres.isEmpty()) {
                        for(Offre offre : offres) {
                %>
                <div class="offre-item" data-offre-type="<%= offre.getTypeOffre() %>" data-etat="<%= offre.getAccessoire().getEtat().label %>" data-categorie="<%= offre.getAccessoire().getTypeAccessoire().label %>">
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
        </main>
    </body>
</html>