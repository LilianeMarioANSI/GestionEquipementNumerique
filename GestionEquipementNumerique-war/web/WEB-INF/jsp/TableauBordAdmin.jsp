<%-- 
    Document   : TableauBordAdmin
    Created on : 12 mars 2024, 14:17:47
    Author     : loulo
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tableau de bord</title>
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/chart.css">
        <link rel="stylesheet" type="text/css" href="Style/tableauBordAdmin.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation_admin.jspf" %>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main class="vertical">
            
            <%
                String nbMembre = (String)request.getAttribute("nbMembre");
                String nbMembreAvecOffre = (String) request.getAttribute("nbMembreAvecOffre");
                String nbMembreAvecDemande = (String) request.getAttribute("nbMembreAvecDemande");
                String nbOffrePublic = (String)request.getAttribute("nbOffrePublic");
                String nbDonPublic = (String)request.getAttribute("nbDonPublic");
                String nbPretPublic = (String)request.getAttribute("nbPretPublic");
                String dateDeb = (String)request.getAttribute("dateDeb");
                String dateFin = (String)request.getAttribute("dateFin");
                double partDeMembreAvecOffre;
                double partDeMembreAvecDemande;
                
                int intNbMembre = nbMembre != null ? Integer.parseInt(nbMembre) : 0;
                int intNbMembreAvecOffre = nbMembreAvecOffre != null ? Integer.parseInt(nbMembreAvecOffre) : 0;
                int intNbMembreAvecDemande = nbMembreAvecDemande != null ? Integer.parseInt(nbMembreAvecDemande) : 0;
                int intNbOffrePublic = nbOffrePublic != null ? Integer.parseInt(nbOffrePublic) : 0;
                int intNbDonPublic = nbDonPublic != null ? Integer.parseInt(nbDonPublic) : 0;
                int intNbPretPublic = nbPretPublic != null ? Integer.parseInt(nbPretPublic) : 0;

                if(intNbMembre > 0 ){
                    partDeMembreAvecOffre = (intNbMembreAvecOffre / intNbMembre) * 100;
                    partDeMembreAvecDemande = (intNbMembreAvecDemande / intNbMembre) * 100;
                } else {
                    partDeMembreAvecOffre = 0;
                    partDeMembreAvecDemande = 0;
                }
                
                

                 
            %>
            
            <div class="container">
                <div class="container-section">
                    <h3><%=nbMembre%></h3>
                    <p>Nombre d'utilisateurs</p>
                </div>
                <div class="separator"></div>
                <div class="container-section">
                    <h3><%=partDeMembreAvecOffre+"%"%></h3>
                    <p>Membres avec une offre</p>
                </div>
                <div class="separator"></div>    
                <div class="container-section">
                    <h3><%=partDeMembreAvecDemande+"%"%></h3>
                    <p>Membres avec une demande</p>
                </div>
                <div class="separator"></div>    
                <div class="container-section">
                    <h3><%=nbOffrePublic%></h3>
                    <p>Nombre d'offres actives</p>
                </div>
                <div class="separator"></div>    
                <div class="container-section">
                    <h3><%=nbDonPublic%></h3>
                    <p>Nombre d'offres de dons</p>
                </div>
                <div class="separator"></div>
                <div class="container-section">
                    <h3><%=nbPretPublic%></h3>
                    <p>Nombre d'offres de prêts</p>
                </div>
            </div>
            
            
            
            
            <form action="ServletGestionEquipement" method="post">
                <label for="dateDeb">Date de début</label>
                <input type="date" name="dateDeb" id="dateDeb" value=<%=dateDeb%>>
                
                <label for="dateFin">Date de fin</label>
                <input type="date" name="dateFin" id="dateFin" value=<%=dateFin%>>
                
                <input type="hidden" name="action" value="analytics">
                <button type="submit" class="submit">Appliquer</button>
            </form>
            
            <div class="chart-wrapper">
                <%@include file="/WEB-INF/jspf/GraphiqueOffrePublie.jspf" %>
                <%@include file="/WEB-INF/jspf/GraphiqueAccessoireEtatUsage.jspf" %>
                <%@include file="/WEB-INF/jspf/GraphiqueAgenceByOffre.jspf" %>
            </div>

            
            
        </main>
        <%@include file="/WEB-INF/jspf/Footer.jspf" %>    
    </body>
</html>
