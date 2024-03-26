<%-- 
    Document   : ListeMembres
    Created on : 20 mars 2024, 10:09:36
    Author     : Utilisateur
--%>

<%@page import="Entite.Superviseur"%>
<%@page import="Entite.Membre"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des Membres</title>
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/chart.css">
        <link rel="stylesheet" type="text/css" href="Style/mesSouhaits.css">
        
    </head>
    
    <body>
        <%@include file="/WEB-INF/jspf/navigation_admin.jspf" %>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main class="vertical">
            <form action="ServletGestionEquipement" method="post">
                <input type="hidden" name="action" value="creationUtilisateur">
                <button type="submit">Créer</button>
            </form>
            <table class="table custom-table">
                <thead>
                    <tr>
                        <th scope="col">Nom</th>
                        <th scope="col">Prénom</th>
                        <th scope="col">Téléphone</th>
                        <th scope="col">Email</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="5" style="text-align: center; font-weight: bold;">Membres</td>
                    </tr>
                    <jsp:useBean id="listeMembres" scope="request" class="java.util.List"></jsp:useBean>
                    <%
                        List<Membre> lesMembres = listeMembres;
                        if (lesMembres != null) {
                        for (Membre m : lesMembres) {
                    %>
                    <tr>
                        <td style="vertical-align: middle"><%= m.getNom() %></td>
                        <td style="vertical-align: middle"><%= m.getPrenom() %></td>
                        <td style="vertical-align: middle"><%= m.getTelephone() %></td>
                        <td style="vertical-align: middle"><%= m.getLogin() %></td>
                        <td class="action-cell" style="vertical-align: middle">
                            <form action="ServletGestionEquipement" method="post">
                                <input type="hidden" name="action" value="modificationUtilisateur">
                                <input type="hidden" name="login" value="<%= m.getId() %>">
                                <button type="submit" class="edit">Modifier</button>
                            </form>
                            <form action="ServletGestionEquipement" method="post">
                                <input type="hidden" name="action" value="suppressionUtilisateur">
                                <input type="hidden" name="login" value="<%= m.getId() %>">
                                <button type="submit" class="delete">Supprimer</button>
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
