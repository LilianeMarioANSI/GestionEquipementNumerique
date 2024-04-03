<%-- 
    Document   : MesSouhaits
    Created on : 16 mars 2024, 10:41:59
    Author     : loulo
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Entite.TypeSouhait"%>
<%@page import="Entite.TypeOffre"%>
<%@page import="Entite.TypeAccessoire"%>
<%@page import="Entite.Souhait"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/normalize.css">
        <link rel="stylesheet" type="text/css" href="Style/main.css">
        <link rel="stylesheet" type="text/css" href="Style/navigation.css">
        <link rel="stylesheet" type="text/css" href="Style/header.css">
        <link rel="stylesheet" type="text/css" href="Style/mesSouhaits.css">
        <title>Mes souhaits</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navigation_membre.jspf" %>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <main class="vertical">
            
            <%@include file="/WEB-INF/jspf/message.jspf" %>
            
            <form action="ServletGestionEquipement" method="post" class="addForm">
                <div >
                    <h3>De quoi avez vous besoin ?</h3>
                    <div class="action-wrapper right">
                        <input type="hidden" name="action" value="creerSouhait">
                        <button type="submit" class="submit">Valider</button>
                    </div>
                </div>
                
                <div class="formBody">
                    <section class="formSection">
                        <h4>Categorie</h4>
                        <select name="categorie" id="categorie" required>
                            <% for (TypeAccessoire t : TypeAccessoire.values()) {%>
                                <option value ="<%=t.label%>"><%=t.label%></option>
                            <% }%>
                        </select>
                    </section>
                    
                    <div class="separator"></div>
                    
                    <section class="formSection">
                        <h4>Type</h4>
                        
                        <div>
                            <input type="radio" name="typeSouhait" id="pret" value="<%=TypeSouhait.PRET%>" checked>
                            <label for="pret">Prêt</label>
                        </div>
                        <div>
                            <input type="radio" name="typeSouhait" id="don" value="<%=TypeSouhait.DON%>" >
                            <label for="pret">Don</label>
                        </div>
                    </section>
                    
                    <div class="separator"></div>
                    
                    <section class="formSection">
                        <h4>Date</h4>
                        <div>
                            <label for="dateDebut">Date début :</label>
                            <input type="date" name="dateDebut" id="dateDebut" required>
                       
                            <label for="dateFin">Date fin (non requis pour les dons):</label>
                            <input type="date" name="dateFin" id="dateFin">
                        </div>
                    </section>
                    
                    <div class="separator"></div>
                    
                    <section class="formSection">
                        <textarea name="description" id="description" placeholder="description" rows="4" required></textarea>
                    </section>
                </div>
                
            </form>
            <table class="table custom-table">
                <thead>
                  <tr>
                    <th scope="col">Type d'accessoire</th>
                    <th scope="col">Date de création</th>
                    <th scope="col">Date de début</th>
                    <th scope="col">Date de fin</th>
                    <th scope="col">Type de souhait</th>
                    <th scope="col">Actions</th>
                  </tr>
                </thead>
                <tbody>
                    <%
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        
                        List<Souhait> souhaits = (List<Souhait>) request.getAttribute("listeSouhaits");
                        if (souhaits != null) {
                        for(Souhait s : souhaits){
                        
    %>
                            <tr>
                                <td style="vertical-align: middle"><%=s.getTypeAccessoire().label%></td>
                                <td style="vertical-align: middle"><%=df.format(s.getDatePublication())%></td>
                                <td style="vertical-align: middle"><%=df.format(s.getDateDebut())%></td>
                                <% if(s.getDateFin()!=null){ %>
                                <td style="vertical-align: middle"><%=df.format(s.getDateFin())%></td>
                                <%}else{%>
                                <td style="vertical-align: middle"> Pas de date de fin</td>
                                <%}%>
                                <td style="vertical-align: middle"><%=s.getTypeSouhait().label%></td>
                                <td class="action-cell" style="vertical-align: middle">
                                    <form action="ServletGestionEquipement">
                                        <input type="hidden" name="action" value="supprimerSouhait">
                                        <input type="hidden" name="idSouhait" value="<%=s.getId()%>">
                                        <button type="submit" class="delete">Supprimer</button>
                                    </form>
                                </td>
                            </tr>
                            <tr class="spacer">
                                <td colspan="100"></td>
                            </tr>
      <% 
                        } 
                    }
                %>
                </tbody>
                
                
              </table>
        </main>
        <%@include file="/WEB-INF/jspf/Footer.jspf" %>        
    </body>
</html>
