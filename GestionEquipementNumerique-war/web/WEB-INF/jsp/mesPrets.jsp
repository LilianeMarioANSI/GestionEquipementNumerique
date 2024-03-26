<%-- 
    Document   : mesPrets
    Created on : 23 mars 2024, 10:12:46
    Author     : LILIANE
--%>

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
    </head>
    
    <%@include file="/WEB-INF/jspf/navigation_membre.jspf" %>
    <%@include file="/WEB-INF/jspf/header.jspf" %>
    <body>
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
                                </div>
                           <%}%>

                    <%}%>
            </div>
        </main>
    </body>
</html>
