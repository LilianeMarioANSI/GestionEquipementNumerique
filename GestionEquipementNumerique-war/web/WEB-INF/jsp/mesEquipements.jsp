<%-- 
    Document   : mesEquipements
    Created on : 18 mars 2024, 10:12:25
    Author     : Utilisateur
--%>

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
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/mesSouhaits.css">
        <title>Mes Equipements</title>
        <script>
            document.addEventListener("DOMContentLoaded", function() {
            // Sélectionnez les éléments nécessaires
            const filterDon = document.getElementById("filterDon");
            const filterPret = document.getElementById("filterPret");
            const applyFilterBtn = document.getElementById("applyFilterBtn");
            const resetFilterBtn = document.getElementById("resetFilterBtn");
            const offreItems = document.querySelectorAll(".offre-item");

            // Fonction pour appliquer le filtre
            function appliquerFiltre() {
                const montrerDon = filterDon.checked;
                const montrerPret = filterPret.checked;

                offreItems.forEach(function(offreItem) {
                    const estDon = offreItem.dataset.offreType === "DON";
                    const estPret = offreItem.dataset.offreType === "PRET";

                    if ((montrerDon && estDon) || (montrerPret && estPret)) {
                        offreItem.style.display = "table-row";
                    } else {
                        offreItem.style.display = "none";
                    }
                });
            }

            // Fonction pour réinitialiser le filtre
            function reinitialiserFiltre() {
                offreItems.forEach(function(offreItem) {
                    offreItem.style.display = "table-row";
                });
                filterDon.checked = false;
                filterPret.checked = false;
            }

            // Ajouter un gestionnaire d'événements pour le bouton "Appliquer"
            applyFilterBtn.addEventListener("click", appliquerFiltre);

            // Ajouter un gestionnaire d'événements pour le bouton "Réinitialiser"
            resetFilterBtn.addEventListener("click", reinitialiserFiltre);
        });
        </script>    
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
                <button id="applyFilterBtn">Appliquer</button>
                <button id="resetFilterBtn">Réinitialiser</button>
            </div>
            
            <table class="table custom-table">
                <thead>
                  <tr>
                    <th scope="col">Titre</th>
                    <th scope="col">Description</th>
                    <th scope="col">Date de publication</th>
                    <th scope="col">Type d'offre</th>
                    <th scope="col">Accessoire</th>
                    <th scope="col">Actions</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                        List<Offre> lesOffres = (List<Offre>) request.getAttribute("offres");
                        if (lesOffres != null) {
                            for (Offre offre : lesOffres) {
                    %>
                        <tr class="offre-item" data-offre-type="<%= offre.getTypeOffre() %>">
                            <td style="vertical-align: middle"><%= offre.getIntitule()%></td>
                            <td style="vertical-align: middle"><%= offre.getDescription()%></td>
                            <td style="vertical-align: middle"><%= offre.getDatePublication() %></td>
                            <td style="vertical-align: middle"><%= offre.getTypeOffre() %></td>
                            <td style="vertical-align: middle"><%= offre.getAccessoire().getDesignation() %></td>
                            <td class="action-cell" style="vertical-align: middle">
                                <form action="ServletGestionEquipement">
                                    <input type="hidden" name="action" value="AfficherModifierOffre">
                                    <button class="edit">Modifier</button>
                                    <button class="delete">Supprimer</button>
                                </form>

                                
                            </td>
                        </tr>
                        <tr class="spacer">
                            <td colspan="100"></td>
                        </tr>
                                <% 
                        } 
                    }
                %>
                </tbody>
            </table>
        </main>
    </body>
</html>