<%-- 
    Document   : DetailOffre
    Created on : 19 mars 2024, 15:50:30
    Author     : loulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id="offre" scope="request" class="Entite.Offre"></jsp:useBean>
        <title>Détail offre</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation_membre.jspf" %>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <p><%=offre.getId()%></p>
        
    </body>
</html>
