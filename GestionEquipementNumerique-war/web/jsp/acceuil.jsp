<%-- 
    Document   : acceuil
    Created on : 9 mars 2024, 09:34:06
    Author     : loulo
--%>

<%@page import="Entite.Agence"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acceuil</title>
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/acceuil.css">
        
    </head>
    <body>
        
        <%@include file="/jspf/header.jspf" %>
        <%@include file="/jspf/navigation.jspf" %>
        
        <main>
            <%@include file="/jspf/message.jspf" %>
            
            <section class="auth-section login">
                <h2>Authentification</h2>
                <form method="post" action="ServletGestionEquipement">
                    <input type="email" name="login" id="login" placeholder="email" required>

                    <input type="password" name="mdp" id="mdp" placeholder="Mot de passe" required>

                    <input type="hidden" name="action" value="authentification">
                    
                    <div class="btn-wrapper right">
                        <button type="submit" class="submit">Connexion</button>
                    </div>
                   
                </form>
            </section>
            <section class="auth-section register">
                <h2>Inscription</h2>
                
                <p>Vous n'avez pas encore de compte ? Inscrivez vous ici.</p>
                
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
                    
                    
                    <input type="hidden" name="action" value="inscription">
                    
                    
                    
                    <div class="btn-wrapper right">
                        <button type="submit" class="submit">S'enregistrer</button>
                    </div>
                </form>
            </section>
            
        </main>
        
        <script src="./Js/ConfirmPassword.js"></script>
    </body>
</html>
