<%-- 
    Document   : FormCréationOffre
    Created on : 18 mars 2024, 09:27:08
    Author     : lilia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/inscription.css">
    </head>
    <body>
        <p><%
            String attribut = (String) request.getAttribute("message");
            if(attribut != null){
                out.println( attribut );
            }
            %> 
        </p>

        <h1>Créer Votre Offre</h1>
        <form method="post" action="ServletGestionEquipement">
            <fieldset>
                <label for="titreO">Titre de l'offre<span class="requis">*</span></label>
                <input type="text" id="titreO" name="numM" value="" size="20" maxlength="20" />
                <label for="DescriptionO">Description <span class="requis">*</span></label>
                <textarea id="DescriptionO" name="DescriptionO" rows="10" cols="50"></textarea>
                <label for="typeO">Type de l'Offre<span class="requis">*</span></label>
                <select name="typeO" id="typeO" required>
                    <option value ="1">Offre de stage</option>
                    <option value ="2">Offre d'emploi</option>
                </select>
                <label for="DateDeb">Date de début<span class="requis">*</span></label>
                <input type="Date" id="DateDeb" name="DateDeb" value="" size="20" maxlength="20" />
                <label for="DateFin">Date de fin<span class="requis">*</span></label>
                <input type="Date" id="DateFin" name="DateFin" value="" size="20" maxlength="20" />
               
                <jsp:useBean id="listeAccessoire" scope="request" class="java.util.List"></jsp:useBean> 
                <label for="listeA">L'Accessoire<span class="requis">*</span></label>
                    <select name="listeA"> 
                        <% List<Accessoire> listeAccessoire = listeAccessoire;
                            for (Accessoire a :listeAccessoire) {%>
                                <option value ="<%=a.getDesignation() %>"><%=a.getDesignation() %></option> 
                            }
                        %>
                    </select>

                <input type="hidden" name="action" value="AjouterOffre">
            </fieldset>
            <input type="submit" value="Créer">
        </form>
    </body>
</html>
