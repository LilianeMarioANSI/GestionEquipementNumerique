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
        <title>Accueil</title>
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/acceuil.css">
        
    </head>
    <body>
        
        
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <main>
            <%@include file="/WEB-INF/jspf/message.jspf" %>
            
            <section class="auth-section login">
                
                <h2 class="form-title">Authentification</h2>
                <form method="post" action="ServletGestionEquipement">
                    <fieldset>
                    <!--<label for="login">Votre nom utilisateur <span class="requis">*</span></label>-->
                    <input type="email" name="login" id="login" placeholder="email" required>
                    <!--<label for="mdp">Votre mot de passe <span class="requis">*</span></label>-->
                    <input type="password" name="mdp" id="mdp" placeholder="Mot de passe" required>

                    <input type="hidden" name="action" value="authentification">
                    </fieldset> 
                    
                    <div class="action-wrapper right">
                        <button type="submit" class="submit">Connexion</button>
                        <!--<input type="submit" value="Valider" />-->
                    </div>
                   
                </form>
                
                <p>Vous n'avez pas encore de compte ? <a href="ServletGestionEquipement?action=inscription">Inscrivez vous ici.</a></p>
            </section>
        </main>
        
        
    </body>
</html>
