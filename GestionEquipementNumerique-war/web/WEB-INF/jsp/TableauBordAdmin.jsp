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
                String nbOffrePublic = (String)request.getAttribute("nbOffrePublic");
                String nbDonPublic = (String)request.getAttribute("nbDonPublic");
                String nbPretPublic = (String)request.getAttribute("nbPretPublic");
                String dateDeb = (String)request.getAttribute("dateDeb");
                String dateFin = (String)request.getAttribute("dateFin");
            %>
            
            <div class="container">
                <div class="container-section">
                    <h3><%=nbMembre%></h3>
                    <p>Nombre de membre</p>
                </div>
                    
                <div class="container-section">
                    <h3><%=nbOffrePublic%></h3>
                    <p>Nombre d'offre public</p>
                </div>
                    
                <div class="container-section">
                    <h3><%=nbDonPublic%></h3>
                    <p>Nombre d'offre de don</p>
                </div>
                
                <div class="container-section">
                    <h3><%=nbPretPublic%></h3>
                    <p>Nombre d'offre de prêt</p>
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
            
            
            <%@include file="/WEB-INF/jspf/GraphiqueOffrePublie.jspf" %>
        </main>
    </body>
</html>
