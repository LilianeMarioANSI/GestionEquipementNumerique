<%-- 
    Document   : ModifierMembre
    Created on : 20 mars 2024, 10:38:40
    Author     : Utilisateur
--%>

<%@page import="Entite.Membre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier Membre</title>
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/inscription.css">
    </head>
    <body>
        <main>
            <%@include file="/WEB-INF/jspf/header.jspf" %>
            <%@include file="/WEB-INF/jspf/message.jspf" %>
            <%@include file="/WEB-INF/jspf/navigation_admin.jspf" %>
            <section class="auth-section register">
                <%
                    Membre m = (Membre)request.getAttribute("membre");
                    if (m != null) {
                %>
                <form action="ServletGestionEquipement" method="post" id="editForm">
                    <input type="hidden" name="id" value="<%= m.getId() %>">
                    <div class="field-wrapper">
                        <label for="nom">Nom :</label>
                        <input type="text" name="nom" id="nom" class="editable" required value="<%= m.getNom() %>">
                    </div>

                    <div class="field-wrapper">
                        <label for="prenom">Prenom :</label>
                        <input type="text" name="prenom" id="prenom" class="editable" required  value="<%= m.getPrenom() %>">
                    </div>

                    <div class="field-wrapper">
                        <label for="email">Email :</label>
                        <input type="text" name="email" id="email" class="editable" required  value="<%= m.getLogin() %>">
                    </div>

                    <div class="field-wrapper">
                        <label for="telephone">Téléphone :</label>
                        <input type="tel" id="telephone" name="telephone" pattern="[0-9]{10}" class="editable" required  value="<%= m.getTelephone() %>">
                    </div>

                    <div class="field-wrapper">
                        <label for="agence">Agence :</label>
                        <input type="text" name="agence" id="agence" class="editable" required  value="<%= m.getAgence()%>">
                    </div>


                    <div class="field-wrapper">
                        <label for="bureau">Bureau :</label>
                        <input type="text" name="bureau" id="bureau" class="editable" required  value="<%= m.getBureau() %>">
                    </div>
                    <div class="action-wrapper right">
                        <input type="hidden" name="action" value="modifierMembre">
                        <button type="submit" class="submit"> Enregistrer </button>

                    </div>
                </form>
                <%-- Fermeture de la condition --%>
                <% } else { %>
                <p>Aucun membre à modifier.</p>
                <% } %>
            </section>
        </main>
    </body>
</html>
