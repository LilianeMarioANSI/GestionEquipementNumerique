<%-- 
    Document   : inscription
    Created on : 12 mars 2024, 10:58:48
    Author     : loulo
--%>

<%@page import="Entite.Agence"%>
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
        <script src="./Js/ConfirmPassword.js"></script>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main>
            <%@include file="/WEB-INF/jspf/message.jspf" %>
            <section class="auth-section register">
                    <form method="post" action="ServletGestionEquipement">
                        <fieldset>
                            <legend>Informations personnelles</legend>
                            <div class="field-wrapper">
                                <input type="text" name="nom" id="nom" placeholder="Nom" required>
                                <input type="text" name="prenom" id="prenom" placeholder="Prenom" required>
                            </div>

                            <input type="tel" id="phone" name="telephone" pattern="[0-9]{10}" placeholder="Téléphone ex: 0672842910" required />

                            <select name="agence" id="agence" required>
                                <% for (Agence a : Agence.values()) {%>
                                    <option value ="<%=a.label%>"><%=a.label%></option>
                                <% }%>
                            </select>



                            <input type="text" name="bureau" id="bureau" placeholder="Bureau" required>
                        </fieldset>

                        <fieldset>
                            <legend>Identifiants</legend>
                            <input type="email" name="loginRegister" id="loginRegister" placeholder="Email" required>
                            <input type="password" name="mdpRegister" id="mdpRegister" placeholder="Mot de passe" required>
                            <input type="password" name="confirmMdp" id="confirmMdp" placeholder="Confirmer le mot de passe" required>
                        </fieldset>


                        <input type="hidden" name="action" value="creerMembre">
                        <!--<input type="hidden" name="action" value="creerSuperviseur">-->
                        <input type="checkbox" required>
                        J'accepte les conditions d'utilisation et j'adhére à votre politique de confidentialité
                        <div class="action-wrapper right">
                            <a href="ServletGestionEquipement">Se connecter</a>
                            <button type="submit" class="submit">S'enregistrer</button>
                        </div>
                    </form>
            </section>
        </main>
        <%@include file="/WEB-INF/jspf/Footer.jspf" %>
    </body>
</html>
