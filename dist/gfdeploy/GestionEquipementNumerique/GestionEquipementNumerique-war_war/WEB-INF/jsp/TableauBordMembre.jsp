<%-- 
    Document   : TableauBordMembre
    Created on : 14 mars 2024, 20:48:16
    Author     : loulo
--%>

<%@page import="Entite.NiveauBadge"%>
<%@page import="Entite.Badge"%>
<%@page import="Entite.Souhait"%>
<%@page import="Entite.Offre"%>
<%@page import="java.util.List"%>
<%@page import="Entite.Demande"%>
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
        <%@include file="/WEB-INF/jspf/navigation_membre.jspf" %>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <main>
            <%@include file="/WEB-INF/jspf/message.jspf" %>
            
            <section class="user-data">
                <div class="container">
                    <div class="container-header">
                        <h3>Mes Offres d'équipement</h3>
                    </div>
                    <jsp:useBean id="offres" scope="request" class="java.util.List"></jsp:useBean>
                    <div class="container-body">
                        <%
                            List<Offre> lesOffres= offres;
                                if (lesOffres.size() == 0) {%>
                                    <p>Vous n'avez pas encore créer d'offre pour le moment</p>
                                <%} else {%>
                                    <ul>
                                        <%for(Offre o : lesOffres){%>
                                            <li><%= o.getTypeOffre() %> - <%= o.getAccessoire().getTypeAccessoire()%></li>
                                        <%}%>
                                    </ul>
                                <%}
                        %>
                        <form class="action-wrapper right">
                            <input type="hidden" name="action" value="mesEquipements">
                            <button type="submit" class="submit">Voir plus</button>
                        </form>
                        
                    </div>
                </div>
                
                <div class="container">
                    <div class="container-header">
                        <h3>Mes prêts</h3>
                    </div>
                    <jsp:useBean id="prets" scope="request" class="java.util.List"></jsp:useBean>
                    <div class="container-body">
                        <%
                            List<Demande> lesPrets= prets;
                                if (lesPrets.size() == 0) {%>
                                    <p>Vous n'avez pas encore fait de Prêts</p>
                                <%} else {%>
                                    <ul>
                                        <%for(Demande d : lesPrets){%>
                                            <li><%= d.getOffre().getIntitule()%></li>
                                        <%}%>
                                    </ul>
                                <%}
                        %>
                        <form class="action-wrapper right">
                            <input type="hidden" name="action" value="AfficherMesPrets">
                            <button type="submit" class="submit">Voir plus</button>
                        </form>
                    </div>
                </div>
                
                <div class="container">
                    <div class="container-header">
                        <h3>Mes souhaits</h3>
                    </div>
                    <jsp:useBean id="souhaits" scope="request" class="java.util.List"></jsp:useBean>
                    <div class="container-body">
                        <%
                            List<Souhait> lesSouhaits= souhaits;
                                if (lesSouhaits.size() == 0) {%>
                                    <p>Vous n'avez pas encore fait de souhaits pour le moment</p>
                                <%} else {%>
                                    <ul>
                                        <%for(Souhait s : lesSouhaits){%>
                                            <li><%= s.getTypeSouhait()%> - <%= s.getTypeAccessoire() %></li>
                                        <%}%>
                                    </ul>
                                <%}
                        %>
                        <form class="action-wrapper right">
                            <input type="hidden" name="action" value="mesSouhaits">
                            <button type="submit" class="submit">Voir plus</button>
                        </form>
                    </div>
                </div>
                <jsp:useBean id="dons" scope="request" class="java.util.List"></jsp:useBean>
                <div class="container">
                    <div class="container-header">
                        <h3>Mes dons</h3>
                    </div>
                    
                    <div class="container-body">
                        <%
                            List<Demande> lesDons = dons;
                                if (lesDons.size() == 0) {%>
                                    <p>Vous n'avez pas encore fait de dons</p>
                                <%} else {%>
                                    <ul>
                                        <%for(Demande d : lesDons){%>
                                            <li><%= d.getOffre().getIntitule()%></li>
                                        <%}%>
                                    </ul>
                                <%}
                        %>
                        <form class="action-wrapper right">
                            <input type="hidden" name="action" value="AfficherMesDons">
                            <button type="submit" class="submit">Voir plus</button>
                        </form>
                    </div>
                </div>
                
            </section>
            <section class="user-info">
                <jsp:useBean id="badges" scope="request" class="java.util.List"></jsp:useBean>
                
                <img class="large-icon profil-icon" src="Assets/icons/person-circle.svg" alt="logo"/>
                <div class="badge-wrapper">
                    <% List<Badge> listeBadge = badges;
                        for (Badge a : listeBadge) {
                            if(a.getNiveau() == NiveauBadge.UN){%>
                                <img class="badge" src="Assets/icons/3.svg" alt="logo"/>
                            <%} else if(a.getNiveau() == NiveauBadge.DEUX){%>
                                <img class="badge" src="Assets/icons/2.svg" alt="logo"/>
                            <%} else if(a.getNiveau() == NiveauBadge.TROIS){%>
                                <img class="badge" src="Assets/icons/1.svg" alt="logo"/>
                            <%}%>
                    <% }%>
                </div>
                
                <div class="field-wrapper"><h3>Informations personnelles</h3></div>
                
                <form action="ServletGestionEquipement" method="post" id="editForm">
                        <div class="field-wrapper">
                            <label for="nom">Nom :</label>
                            <input type="text" name="nom" id="nom" class="editable" required readonly value="${membre.nom}">
                        </div>

                        <div class="field-wrapper">
                            <label for="prenom">Prenom :</label>
                            <input type="text" name="prenom" id="prenom" class="editable" required readonly value="${membre.prenom}">
                        </div>

                        <div class="field-wrapper">
                            <label for="email">Email :</label>
                            <input type="text" name="email" id="email" class="editable" required readonly value="${membre.login}">
                        </div>

                        <div class="field-wrapper">
                            <label for="telephone">Téléphone :</label>
                            <input type="tel" id="telephone" name="telephone" pattern="[0-9]{10}" class="editable" required readonly value="${membre.telephone}">
                        </div>

                        <div class="field-wrapper">
                            <label for="agence">Agence :</label>
                            <input type="text" name="agence" id="agence" class="editable" required readonly value="${membre.agence.toString()}">
                        </div>


<<<<<<< HEAD
                    <div class="field-wrapper">
                        <label for="bureau">Bureau :</label>
                        <input type="text" name="bureau" id="bureau" class="editable" required readonly value="${membre.bureau}">
                    </div>
                    <div class="action-wrapper right">
                        <input type="hidden" name="action" value="ModifierMembre">
                        <button type="button" class="edit" id="toggleEdit"> Modifier </button>
                        <button type="submit" class="submit"> Enregistrer </button>
                        
                    </div>
                </form>
                <form action="ServletGestionEquipement" method="post">
                    <input type="hidden" name="action" value="SupprimerMembre">
                    <input type="hidden" name="membreId" value="${membre.id}">
                    <button type="submit">Supprimer mon compte</button>
                </form>
=======
                        <div class="field-wrapper">
                            <label for="bureau">Bureau :</label>
                            <input type="text" name="bureau" id="bureau" class="editable" required readonly value="${membre.bureau}">
                        </div>
                        <div class="action-wrapper right">
                            <input type="hidden" name="action" value="ModifierMembre">
                            <button type="button" class="edit" id="toggleEdit"> Modifier </button>
                            <button type="submit" class="submit"> Enregistrer </button>

                        </div>
                    </form>
                    <form action="ServletGestionEquipement" method="post">
                        <input type="hidden" name="action" value="SupprimerMembre">
                        <input type="hidden" name="membreId" value="${membre.id}">
                        <button type="submit">Supprimer mon compte</button>
                    </form>
                
>>>>>>> 3b5c20f (Badge + vérification date création offre)
            </section>
        </main>
    </body>
</html>
