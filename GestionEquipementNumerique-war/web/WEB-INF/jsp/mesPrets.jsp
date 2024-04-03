<%-- 
    Document   : mesPrets
    Created on : 23 mars 2024, 10:12:46
    Author     : LILIANE
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="Entite.Demande"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogue des offres</title>
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/inscription.css">
        <link rel="stylesheet" type="text/css" href="Style/catalogue.css">
        <link rel="stylesheet" type="text/css" href="Style/popup.css">
    </head>
    
    
    <body>
        <%@include file="/WEB-INF/jspf/navigation_membre.jspf" %>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main class="vertical">
            <h1>Mes prêts</h1>
            <div class="offerContainer">
                <jsp:useBean id="prets" scope="request" class="java.util.List"></jsp:useBean> 
                <%
                    List<Demande> lesprets= prets;
                    if (prets.isEmpty()){
                        String attribut = (String) request.getAttribute("message");%>
                        <p><%= attribut %></p>
                    <%}
                    else {%>

                            <% for(Demande d : lesprets){%>
                            <div class="offer">
                                <img class="large-icon" src="Assets/icons/computer-mouse-solid.svg" alt="logo"/>
                                    <h2><%= d.getOffre().getIntitule()%></h2>
                                    <p><%= d.getOffre().getAccessoire().getDesignation()%></p>
                                    <p><%= d.getStatut().label%></p>
                                    <% SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");%>
                                    <p>Date Début de l'emprunt: <%= dateFormat.format(d.getDateDemande())%></p>
                                    <p>Date Fin de l'emprunt:<%= dateFormat.format(d.getOffre().getDateFin())%></p>
                                    <div class="action-wrapper right">
                                    <button class="popup-button submit" 
                                            onclick="openPopup('<p>Veuillez vous rendre à l\'agence de <b><%= d.getOffre().getUtilisateur().getAgence().label %></b> au bureau <b><%= d.getOffre().getUtilisateur().getBureau()%></b> ou contactez directement <b><%= d.getOffre().getUtilisateur().getPrenom()%> <%= d.getOffre().getUtilisateur().getNom()%></b> par mail ou téléphone.</p> \n\
                                                                Mail : <b><%= d.getOffre().getUtilisateur().getLogin()%></b><br/> \n\
                                                                Téléphone : <b><%= d.getOffre().getUtilisateur().getTelephone()%></b>')">
                                        Instructions de récupération
                                    </button>
                                    <form action="ServletGestionEquipement" method="post">
                                        <input type="hidden" name="action" value="cloturerDemande">
                                        <input type="hidden" name="demandeId" value=<%=d.getId()%>> <!-- Assuming getId() retrieves the ID of the donation -->
                                        <button type="submit" class="delete">Clôturer</button>
                                    </form>
                                </div>
                           <%}%>

                    <%}%>
            </div>
            
            <div id="popup" class="popup">
                <div class="popup-content" id="popup-content"></div>
                <div class='action-wrapper right'>
                    <button class="close-button submit" onclick="closePopup()">Fermer</button>
                </div>
                
            </div>
        </main>
        <%@include file="/WEB-INF/jspf/Footer.jspf" %>
    </body>
    
    <script>
        
        function openPopup(offreIntitule) {
            // Récupérer la référence de la popup
            var popup = document.getElementById('popup');

            // Modifier le contenu de la popup avec le titre de l'offre
            var popupContent = document.getElementById('popup-content');
            popupContent.innerHTML = offreIntitule;

            // Afficher la popup
            popup.style.display = 'block';
        }

        function closePopup() {
            // Récupérer la référence de la popup
            var popup = document.getElementById('popup');

            // Masquer la popup
            popup.style.display = 'none';
        }
    </script>
</html>
