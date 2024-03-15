<%-- 
    Document   : TableauBordMembre
    Created on : 14 mars 2024, 20:48:16
    Author     : loulo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/tableauBordMembre.css">
        <script src="Js/ToggleEdit.js" defer></script>
        <title>Mon profil</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation.jspf" %>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <main >
            <section class="user-data">
                <div class="container">
                    <div class="container-header">
                        <h3>Mes équipements</h3>
                    </div>
                    
                    <div class="container-body">
                        
                        <ul>
                            <li>Souris sans-fil Dell</li>
                            <li>Casque Jabra filaire</li>
                            <li>Adaptateur HDMI/VGA</li>
                        </ul>
                        <div class="action-wrapper right">
                            <button type="submit" class="submit">Voir plus</button>
                        </div>
                    </div>
                </div>
                
                <div class="container">
                    <div class="container-header">
                        <h3>Mes prêts</h3>
                    </div>
                    
                    <div class="container-body">
                        
                        <ul>
                            <li>Souris sans-fil Dell</li>
                            <li>Casque Jabra filaire</li>
                            <li>Adaptateur HDMI/VGA</li>
                        </ul>
                        <div class="action-wrapper right">
                            <button type="submit" class="submit">Voir plus</button>
                        </div>
                    </div>
                </div>
                
                <div class="container">
                    <div class="container-header">
                        <h3>Mes souhaits</h3>
                    </div>
                    
                    <div class="container-body">
                        
                        <ul>
                            <li>Souris sans-fil Dell</li>
                            <li>Casque Jabra filaire</li>
                            <li>Adaptateur HDMI/VGA</li>
                        </ul>
                        <div class="action-wrapper right">
                            <button type="submit" class="submit">Voir plus</button>
                        </div>
                    </div>
                </div>
                
                <div class="container">
                    <div class="container-header">
                        <h3>Mes dons</h3>
                    </div>
                    
                    <div class="container-body">
                        
                        <ul>
                            <li>Souris sans-fil Dell</li>
                            <li>Casque Jabra filaire</li>
                            <li>Adaptateur HDMI/VGA</li>
                        </ul>
                        
                        <div class="action-wrapper right">
                            <button type="submit" class="submit">Voir plus</button>
                        </div>
                    </div>
                </div>
            </section>
            <section class="user-info">
                <img class="large-icon profil-icon" src="Assets/icons/person-circle.svg" alt="logo"/>
                <div class="field-wrapper"><h3>Informations personnelles</h3></div>
                
            <form id="editForm">
                    <div class="field-wrapper">
                        <label for="nom">Nom :</label>
                        <input type="text" name="nom" id="nom" class="editable" required readonly>
                    </div>
                    
                    <div class="field-wrapper">
                        <label for="nom">Prenom :</label>
                        <input type="text" name="prenom" id="prenom" class="editable" required readonly>
                    </div>
                    
                    <div class="field-wrapper">
                        <label for="nom">Email :</label>
                        <input type="text" name="email" id="email" class="editable" required readonly>
                    </div>
                    
                    <div class="field-wrapper">
                        <label for="nom">Téléphone :</label>
                        <input type="tel" id="phone" name="telephone" pattern="[0-9]{10}" class="editable" required readonly/>
                    </div>
                    
                    <div class="field-wrapper">
                        <label for="nom">Agence :</label>
                        <select class="editable" required disabled>
                            <option>Lille</option>
                            <option selected>Lyon</option>
                            <option>Marseille</option>
                            <option>Etc...</option>
                        </select>
                    </div>
                    
                    <div class="field-wrapper">
                        <label for="nom">Bureau :</label>
                        <input type="text" name="bureau" id="bureau" class="editable" required readonly>
                    </div>
                    
                    <div class="action-wrapper right">
                        <button type="button" class="edit" id="toggleEdit">Modifier</button>
                        <button type="submit" class="submit">Enregistrer</button>
                    </div>
                </form>
                
            </section>
        </main>
    </body>
</html>
