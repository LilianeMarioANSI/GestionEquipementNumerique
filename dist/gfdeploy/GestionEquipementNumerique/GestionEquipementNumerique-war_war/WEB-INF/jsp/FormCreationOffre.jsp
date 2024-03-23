<%-- 
    Document   : FormCréationOffre
    Created on : 18 mars 2024, 09:27:08
    Author     : lilia
--%>

<%@page import="Entite.TypeOffre"%>
<%@page import="Entite.EtatAccessoire"%>
<%@page import="Entite.TypeAccessoire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Creation offre</title>
    <link rel="stylesheet" type="text/css" href="Style/normalize.css">
    <link rel="stylesheet" type="text/css" href="Style/main.css">
    <link rel="stylesheet" type="text/css" href="Style/navigation.css">
    <link rel="stylesheet" type="text/css" href="Style/header.css">
    <script src="Js/PageCreationOffre.js"></script>
</head>
<body>
    <p>
        <%
        String attribut = (String) request.getAttribute("message");
        if(attribut != null){
            out.println( attribut );
        }
        %> 
    </p>
    <h1> Créer un Accessoire</h1>
    <form method="post" action="ServletGestionEquipement">
        <div id="step1">
            <fieldset>
                <label for="nomA">Nom de l'accessoire<span class="requis">*</span></label>
                <input type="text" id="nomA" name="nomA" value="" size="20" maxlength="20" />
                <label for="DescriptionA">Description <span class="requis">*</span></label>
                <textarea id="DescriptionA" name="DescriptionA" rows="10" cols="50"></textarea>
                <label for="TypeA">Type Accessoire<span class="requis">*</span></label>
                <select name="TypeA" id="TypeA" required>
                    <% for (TypeAccessoire type : TypeAccessoire.values()) { %>
                        <option value="<%=type.label%>"><%=type.label%></option>
                    <%}%>
                </select>
                <label for="etatA">Etat de l'accessoire<span class="requis">*</span></label>
                <select name="etatA" id="etatA" required>
                    <% for (EtatAccessoire etat : EtatAccessoire.values()) { %>
                        <option value="<%=etat.label%>"><%=etat.label%></option>
                    <%}%>
                </select>

                <button onclick="showStep(2)">Suivant</button>
            </fieldset>
        </div>

        <div id="step2" style=""display:none;">
            <h1>Créer Votre Offre</h1>
            <fieldset>
                <label for="titreO">Titre de l'offre<span class="requis">*</span></label>
                <input type="text" id="titreO" name="titreO" value="" size="20" maxlength="20" />
                <label for="DescriptionO">Description <span class="requis">*</span></label>
                <textarea id="DescriptionO" name="DescriptionO" rows="10" cols="50"></textarea>
                <label for="typeO">Type de l'Offre<span class="requis">*</span></label>
                <select name="typeO" id="typeO" required>
                    <% for (TypeOffre type : TypeOffre.values()) { %>
                        <option value="<%=type.label%>"><%=type.label%></option>
                    <%}%>
                </select>
                <label for="DateDeb">Date de début<span class="requis">*</span></label>
                <input type="date" id="DateDeb" name="DateDeb" value="" size="20" maxlength="20" />
                <label for="DateFin">Date de fin<span class="requis">*</span></label>
                <input type="date" id="DateFin" name="DateFin" value="" size="20" maxlength="20" />

                <input type="hidden" name="action" value="AjouterOffre">
            </fieldset>
        </div>
        <input type="submit" value="Créer">
    </form>
</body>
</html>
