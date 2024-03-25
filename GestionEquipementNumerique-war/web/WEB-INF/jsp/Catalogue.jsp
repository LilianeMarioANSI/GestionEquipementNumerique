<%-- 
    Document   : Catalogue
    Created on : 18 mars 2024, 09:35:13
    Author     : Utilisateur
--%>

<%@page import="Entite.TypeOffre"%>
<%@page import="Entite.TypeAccessoire"%>
<%@page import="Entite.EtatOffre"%>
<%@page import="Entite.EtatAccessoire"%>
<%@page import="java.util.List"%>
<%@page import="Entite.Offre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogue des offres</title>
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/catalogue.css">
    </head>
    
    <%@include file="/WEB-INF/jspf/navigation.jspf" %>
    <%@include file="/WEB-INF/jspf/header.jspf" %>

    <body>
        <main>
            <form method="post" action="ServletGestionEquipement">
                <input type="hidden" name="action" value="afficherCatalogue">
                <label for="type">Type :</label>
                <select name="type" id="type" required>
                    <% for (TypeOffre to : TypeOffre.values()) {%>
                        <option value ="<%=to.label%>"><%=to.label%></option>
                    <% }%>
                </select>

                <label for="etat">État :</label>
                <select name="etat" id="etat" required>
                    <% for (EtatAccessoire e : EtatAccessoire.values()) {%>
                        <option value ="<%=e.label%>"><%=e.label%></option>
                    <% }%>
                </select>

                <label for="categorie">Catégorie :</label>
                <select name="categorie" id="categorie" required>
                    <% for (TypeAccessoire t : TypeAccessoire.values()) {%>
                        <option value ="<%=t.label%>"><%=t.label%></option>
                    <% }%>
                </select>

                <button type="submit">Filtrer</button>
            </form>
            
            <div class="offerContainer">
                <%
                    List<Offre> offres = (List<Offre>) request.getAttribute("offres");
                    if(offres != null && !offres.isEmpty()) {
                        for(Offre offre : offres) {
                %>
                    <div class="offer">
                        <%
                            String icon;
                            TypeAccessoire typeAccessoireOffre = offre.getAccessoire().getTypeAccessoire();
                            switch(typeAccessoireOffre){
                                case ECRAN:
                                    icon = "desktop-solid.svg";
                                    break;
                                case CLAVIER:
                                    icon = "keyboard-solid.svg";
                                    break;
                                case SOURIS:
                                    icon = "computer-mouse-solid.svg";
                                    break;
                                case FILTRE_CONFIDENTIALITE:
                                    icon = "eye-slash-solid.svg";
                                    break;
                                case ADAPTATEUR_VGA:
                                    icon = "plug-solid.svg";
                                    break;
                                case CHARGEUR:
                                    icon = "eye-slash-solid.svg";
                                    break;
                                case CASQUE:
                                    icon = "headphones-solid.svg";
                                    break;
                                case CLE_USB:
                                    icon = "hard-drive-solid.svg";
                                    break;
                                default:
                                    icon = "question-solid.svg";
                                    break;
                            }
                        %>
                        <img class="large-icon" src="Assets/icons/<%=icon%>" alt="logo"/>
                        <h2><%= offre.getIntitule() %></h2>
                        <p><%=offre.getAccessoire().getTypeAccessoire().label%></p>
                        <p>Statut : <%=offre.getEtat().label%></p>
                        <div>
                            <form method="post" action="ServletGestionEquipement">
                            <input type="hidden" name="action" value="afficherDetailOffre">
                            <input type="hidden" name="idOffre" value="<%=offre.getId()%>">
                            <button type="submit" class="submit">Voir l'offre</button>
                        </form>
                        <form action="ServletGestionEquipement" method="post">
                            <input type="hidden" name="action" value="reclamerOffre">
                            <input type="hidden" name="idUtilisateur" value="<%=membre.getId()%>">
                            <input type="hidden" name="idOffre" value="<%=offre.getId()%>">

                            <% if(offre.getEtat() != EtatOffre.DISPONIBLE){ %>
                                <button type="submit" class="submit" disabled>Je veux</button>
                            <%} else {%>
                                <button type="submit" class="submit" >Je veux</button>
                            <%}%>
                        </form>
                        </div>
                        
                    </div>
                <%
                        }
                    } else {
                %>
                <p>Aucune offre n'est disponible pour le moment.</p>
                <%
                    }
                %>
            </div>
        </main>
    </body>
</html>
