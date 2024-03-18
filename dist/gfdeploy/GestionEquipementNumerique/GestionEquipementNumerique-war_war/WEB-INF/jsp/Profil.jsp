<%-- 
    Document   : Profil
    Created on : 18 mars 2024, 09:33:41
    Author     : Utilisateur
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profil</title>
</head>
<body>
    <h1>Profil de <%= request.getAttribute("prenom") %> <%= request.getAttribute("nom") %></h1>
    
    <h2>Mes équipements</h2>
    <!-- Afficher les équipements de l'utilisateur -->
    <jsp:include page="mesEquipements.jsp" />

    <h2>Mes prêts</h2>
    <!-- Afficher les prêts de l'utilisateur -->
    <jsp:include page="mesPrets.jsp" />

    <h2>Mes dons</h2>
    <!-- Afficher les dons de l'utilisateur -->
    <jsp:include page="mesDons.jsp" />

    <h2>Mes souhaits</h2>
    <!-- Afficher les souhaits de l'utilisateur -->
    <jsp:include page="mesSouhaitsProfil.jsp" />

    <!-- Lien pour modifier le profil -->
    <a href="modifierProfil.jsp">Modifier le profil</a>
</body>
</html>
