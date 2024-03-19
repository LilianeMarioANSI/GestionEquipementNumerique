<%-- 
    Document   : TableauBordAdmin
    Created on : 12 mars 2024, 14:17:47
    Author     : loulo
--%>

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
        <%@include file="/WEB-INF/jspf/navigation.jspf" %>
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
                
                if(Integer.parseInt(nbMembre) > 0 ){
                    partDeMembreAvecOffre = Integer.parseInt(nbMembreAvecOffre)/Integer.parseInt(nbMembre);
                    partDeMembreAvecDemande = Integer.parseInt(nbMembreAvecDemande)/Integer.parseInt(nbMembre);
                }else{
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
                
                <input type="hidden" name="action" value="tableauBord">
                <button type="submit" class="submit">Appliquer</button>
            </form>
            
            <div class="chart-wrapper">
                <%@include file="/WEB-INF/jspf/GraphiqueOffrePublie.jspf" %>
                <%@include file="/WEB-INF/jspf/GraphiqueAccessoireEtatUsage.jspf" %>
                <%@include file="/WEB-INF/jspf/GraphiqueAgenceByOffre.jspf" %>
            </div>
            
            
        </main>
    </body>
</html>
