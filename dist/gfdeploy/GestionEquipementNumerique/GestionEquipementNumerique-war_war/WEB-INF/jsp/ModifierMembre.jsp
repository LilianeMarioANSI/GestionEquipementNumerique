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
    <!-- Ajoutez vos liens vers les fichiers CSS si nécessaire -->
</head>
<body>
    <h2>Modifier Membre</h2>
    <%-- Récupération des détails du membre préremplis --%>
    <%
        Membre membre = (Membre)request.getAttribute("membre");
        // Vérifiez si le membre existe et n'est pas null
        if (membre != null) {
    %>
    <form action="ServletGestionEquipement" method="post" id="editForm">
        <input type="hidden" name="id" value="<%= membre.getId() %>">
                    <div class="field-wrapper">
                        <label for="nom">Nom :</label>
                        <input type="text" name="nom" id="nom" class="editable" required value="${membre.nom}">
                    </div>

                    <div class="field-wrapper">
                        <label for="prenom">Prenom :</label>
                        <input type="text" name="prenom" id="prenom" class="editable" required  value="${membre.prenom}">
                    </div>

                    <div class="field-wrapper">
                        <label for="email">Email :</label>
                        <input type="text" name="email" id="email" class="editable" required  value="${membre.login}">
                    </div>

                    <div class="field-wrapper">
                        <label for="telephone">Téléphone :</label>
                        <input type="tel" id="telephone" name="telephone" pattern="[0-9]{10}" class="editable" required  value="${membre.telephone}">
                    </div>

                    <div class="field-wrapper">
                        <label for="agence">Agence :</label>
                        <input type="text" name="agence" id="agence" class="editable" required  value="${membre.agence.toString()}">
                    </div>


                    <div class="field-wrapper">
                        <label for="bureau">Bureau :</label>
                        <input type="text" name="bureau" id="bureau" class="editable" required  value="${membre.bureau}">
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
</body>
</html>
