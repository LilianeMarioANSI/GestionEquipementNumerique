<%-- 
    Document   : DetailOffre
    Created on : 19 mars 2024, 15:50:30
    Author     : loulo
--%>

<%@page import="Entite.EtatOffre"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Entite.TypeAccessoire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/detailOffre.css">
        <jsp:useBean id="offre" scope="request" class="Entite.Offre"></jsp:useBean>
        <title>Détail offre</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation.jspf" %>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <%
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            
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
        
        <main>
            <div class="offer-section">
                <div class="accessoireDetailContainer">
                    <h2>Accessoire</h2>
                    <div class="image-wrapper"><img class="large-icon" src="Assets/icons/<%=icon%>" alt="logo"/></div>
                    <div><b>Désignation :</b><p><%=offre.getAccessoire().getDesignation()%></p></div>
                    <div><b>État :</b><p><%=offre.getAccessoire().getEtat().label%></p></div>
                    <div><b>Type d'accessoire :</b><p><%=offre.getAccessoire().getTypeAccessoire().label%></p></div>
                </div>
                
            
                <div class="offerDetailContainer">
                    <h2>Offre</h2>
                    <div><b>Intitulé :</b><p><%=offre.getIntitule()%></p></div>
                    <div>
                        <b>Type de l'offre :</b><p><%=offre.getTypeOffre().label%></p>
                    </div>
                    <div>
                        <b>Date :</b><p><%=df.format(offre.getDateDebut())%>
                        <% if(offre.getDateFin() != null){ %>
                            - <%=df.format(offre.getDateFin())%>
                        <%}%>
                        </p>
                    </div>
                    <div>
                        <b>État :</b><p> <%=offre.getEtat().label%></p>
                    </div>
                    <div>
                        <b>Description :</b><p><%=offre.getDescription()%></p>
                    </div>
                </div>
                    
                <div class="profilDetailContainer">
                    <h2>Propriétaire</h2>
                    <div><b>Prénom NOM :</b><%=offre.getUtilisateur().getPrenom()%> <%=offre.getUtilisateur().getNom()%></div>
                    <div><b>Agence :</b><%=offre.getUtilisateur().getAgence().label%></div>
                    <div><b>Bureau :</b><%=offre.getUtilisateur().getBureau()%></div>
                </div>
            </div>
                    
            

            </div>
            <div class="form-wrapper">
                <form action="ServletGestionEquipement">
                    <input type="hidden" name="action" value="afficherCatalogue">
                    <button type="submit" class="edit">Retour au catalogue</button>
                </form>
                <form action="ServletGestionEquipement" method="post">
                    <input type="hidden" name="action" value="reclamerOffre">
                    <input type="hidden" name="idUtilisateur" value="<%=membre.getId()%>">
                    <input type="hidden" name="idOffre" value="<%=offre.getId()%>">
                    
                    <% if(offre.getEtat() != EtatOffre.DISPONIBLE){ %>
                        <button type="submit" class="submit" disabled>Je veux</button>
                    <%} else {%>
                        <button type="submit" class="submit" >Je veux<%=offre.getEtat().label%></button>
                    <%}%>
                    
                    
                    
                </form>
            </div>        
            
            
        </main>
        
        
    </body>
</html>
