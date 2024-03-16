<%-- 
    Document   : MesSouhaits
    Created on : 16 mars 2024, 10:41:59
    Author     : loulo
--%>

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
        <%@include file="/WEB-INF/jspf/navigation.jspf" %>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        
        <main class="vertical">
            <form action="ServletGestionEquipement" method="post" class="addForm">
                <div >
                    <h3>De quoi avez vous besoin ?</h3>
                    <div class="action-wrapper right">
                        <button type="submit" class="submit">Valider</button>
                    </div>
                </div>
                
                <div class="formBody">
                    <section class="formSection">
                        <h4>Categorie</h4>
                        <select name="categorie" id="categorie">
                            <option>Option1</option>
                            <option>Option1</option>
                            <option>Option1</option>
                        </select>
                    </section>
                    
                    <div class="separator"></div>
                    
                    <section class="formSection">
                        <h4>Type</h4>
                        
                        <div>
                            <input type="radio" name="type" id="pret">
                            <label for="pret">Prêt</label>
                        </div>
                        <div>
                            <input type="radio" name="type" id="don">
                            <label for="pret">Don</label>
                        </div>
                    </section>
                    
                    <div class="separator"></div>
                    
                    <section class="formSection">
                        <h4>Date</h4>
                        <div>
                            <label for="dateDebut">Date début :</label>
                            <input type="date" name="dateDebut" id="dateDebut">
                       
                            <label for="dateFin">Date fin :</label>
                            <input type="date" name="dateFin" id="dateFin">
                        </div>
                    </section>
                    
                    <div class="separator"></div>
                    
                    <section class="formSection">
                        <textarea name="description" id="description" placeholder="description" rows="4"></textarea>
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
                    <tr>
                        <td style="vertical-align: middle">Souris</td>
                        <td style="vertical-align: middle">2024-02-01</td>
                        <td style="vertical-align: middle">2024-02-01</td>
                        <td style="vertical-align: middle"></td>
                        <td style="vertical-align: middle">Don</td>
                        <td class="action-cell" style="vertical-align: middle">
                            <form action="ServletGestionEquipement">
                                <input type="hidden" name="action" value="AfficherModifierSouhait">
                                <button class="edit">Modifier</button>
                            </form>

                            <button class="delete">Supprimer</button>
                        </td>
                    </tr>
                    <tr class="spacer">
                        <td colspan="100"></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle">Souris</td>
                        <td style="vertical-align: middle">2024-02-01</td>
                        <td style="vertical-align: middle">2024-02-01</td>
                        <td style="vertical-align: middle">2024-02-01</td>
                        <td style="vertical-align: middle">Prêt</td>
                        <td class="action-cell" style="vertical-align: middle">
                            <form action="ServletGestionEquipement">
                                <input type="hidden" name="action" value="AfficherModifierSouhait">
                                <button class="edit">Modifier</button>
                            </form>

                            <button class="delete">Supprimer</button>
                        </td>
                    </tr>
                    <tr class="spacer">
                        <td colspan="100"></td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle">Souris</td>
                        <td style="vertical-align: middle">2024-02-01</td>
                        <td style="vertical-align: middle">2024-02-01</td>
                        <td style="vertical-align: middle">2024-02-01</td>
                        <td style="vertical-align: middle">Prêt</td>
                        <td class="action-cell" style="vertical-align: middle">
                            <form action="ServletGestionEquipement">
                                <input type="hidden" name="action" value="AfficherModifierSouhait">
                                <button class="edit">Modifier</button>
                            </form>

                            <button class="delete">Supprimer</button>
                        </td>
                    </tr>
                </tbody>
                </tbody>
                
                
              </table>
        </main>
    </body>
</html>
