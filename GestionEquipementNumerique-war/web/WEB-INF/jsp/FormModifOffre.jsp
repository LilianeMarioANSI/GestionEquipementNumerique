<%-- 
    Document   : FormCréationOffre
    Created on : 18 mars 2024, 09:27:08
    Author     : LILIANE
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Entite.Offre"%>
<%@page import="Entite.TypeOffre"%>
<%@page import="Entite.EtatAccessoire"%>
<%@page import="Entite.TypeAccessoire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modification offre</title>
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <script src="Js/PageCreationOffre.js"></script>

    </head>
    
    <%@include file="/WEB-INF/jspf/navigation_membre.jspf" %>
    <%@include file="/WEB-INF/jspf/header.jspf" %>
    <body>
        
        <main class="vertical">
            <%@include file="/WEB-INF/jspf/message.jspf" %>
            <jsp:useBean id="offre" scope="request" class="Offre"></jsp:useBean> 
            <h1>Votre accessoire</h1>
            <form method="post" action="ServletGestionEquipement">
                <div id="step1">
                    <fieldset>
                        <label for="nomA">Nom de l'accessoire<span class="requis">*</span></label>
                        <input type="text" id="nomA" name="nomA" value="<%= offre.getAccessoire().getDesignation() %>" size="20" maxlength="20" readonly/>
                        <label for="DescriptionA">Description <span class="requis">*</span></label>
                        <textarea id="DescriptionA" name="DescriptionA" value="" rows="10" cols="50" readonly disabled><%= offre.getDescription()%></textarea>
                        <label for="TypeA">Type Accessoire<span class="requis">*</span></label>
                        <select name="TypeA" id="TypeA" required readonly disabled>
                                <option value="<%= offre.getAccessoire().getTypeAccessoire()  %>"><%= offre.getAccessoire().getTypeAccessoire()  %></option>
                        </select>
                        <label for="etatA">Etat de l'accessoire<span class="requis">*</span></label>
                        <select name="etatA" id="etatA" required readonly disabled>
                                <option value="<%= offre.getAccessoire().getEtat()  %>"><%= offre.getAccessoire().getEtat()  %></option>
                        </select>
                    </fieldset>
                </div>

                <h1>Modifier Votre Offre</h1>
                <div id="step2" style=""display:none;">
                    <fieldset>
                        <label for="idO">Identifiant de l'offre<span class="requis">*</span></label>
                        <input type="text" id="idO" name="idO" value="<%= offre.getId()%>" size="20" maxlength="20" readonly/>
                        <label for="titreO">Titre de l'offre<span class="requis">*</span></label>
                        <input type="text" id="titreO" name="titreO" value="<%= offre.getIntitule()%>" size="20" maxlength="20" />
                        <label for="DescriptionO">Description <span class="requis">*</span></label>
                        <textarea id="DescriptionO" name="DescriptionO" value="" rows="10" cols="50"><%= offre.getDescription()%></textarea>
                        <label for="typeO">Type de l'Offre<span class="requis">*</span></label>
                        <select name="typeO" id="typeO" required>
                            <% for (TypeOffre type : TypeOffre.values()) { 
                                if(offre.getTypeOffre().equals(type)){%>
                                    <option value="<%=type %>" selected="selected"><%=type.label%></option>
                                <%}else{%>
                                    <option value="<%=type %>"><%=type.label%></option>
                                <%}%>
                            <%}%>
                        </select>
                        <!-- Limite : si l'utilisateur change de type d'offre de DOn a pret le champs fin de prets n'apparait pas  -->
                            <% SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");%>
                        <label for="DateDeb">Date de début<span class="requis">*</span></label>
                        <input type="Date" id="DateDeb" name="DateDeb" value="<%= dateFormat.format(offre.getDateDebut())%>" size="20" maxlength="20" />
                        <% if(offre.getTypeOffre().equals(TypeOffre.PRET)) {%>
                            <label for="DateFin">Date de fin<span class="requis">*</span></label>
                            <input type="Date" id="DateFin" name="DateFin" value="<%=dateFormat.format(offre.getDateFin())%>" size="20" maxlength="20" />
                        <% }%>

                        <input type="hidden" name="action" value="mettreAjOffre">
                    </fieldset>
                </div>
                        <button type="submit" class="submit">Mettre à jour</button>
            </form>
        </main>
        
    </body>
</html>