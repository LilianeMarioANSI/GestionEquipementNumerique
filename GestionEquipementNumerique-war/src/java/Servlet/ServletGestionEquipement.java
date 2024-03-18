/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Entite.Agence;
import Entite.Offre;
import Entite.Personne;
import Entite.Souhait;
import Entite.TypeAccessoire;
import Entite.TypeOffre;
import Entite.TypeSouhait;
import Facade.SouhaitFacadeLocal;
import Session.SessionAdministrateurLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import Session.SessionMembreLocal;
import static java.lang.System.out;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
/**
 *
 * @author loulo
 */
@WebServlet(name = "ServletGestionEquipement", urlPatterns = {"/ServletGestionEquipement"})
public class ServletGestionEquipement extends HttpServlet {

   

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB
    private SouhaitFacadeLocal souhaitFacade;
    
    @EJB
    private SessionMembreLocal sessionMembre;
    
    @EJB
    private SessionAdministrateurLocal sessionAdministrateur;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        String jsp = null;
        if(action == null){
            jsp = "/WEB-INF/jsp/Acceuil.jsp";
            
            //Titre de la page
            request.setAttribute("titrePage", "Bienvenue !");
        }
        else if(action.equals("creerMembre")){
            jsp = "/WEB-INF/jsp/Acceuil.jsp";
            doInscrireUtilisateur(request, response);
            
            //Titre de la page
            request.setAttribute("titrePage", "Bienvenue !");
        }
        else if(action.equals("inscription")){
            jsp = "/WEB-INF/jsp/Inscription.jsp";
            
            //Titre de la page
            request.setAttribute("titrePage", "Inscription");
        }else if(action.equals("analytics")){
            
            jsp = "/WEB-INF/jsp/TableauBordAdmin.jsp";
            //Titre de la page
            request.setAttribute("titrePage", "Tableau de bord");
            
            //Récupération de la période de temps
            String dateDebut_String = request.getParameter("dateDeb");
            String dateFin_String = request.getParameter("dateFin");
            
            //Conversion Date_String en sql Date pour être utilisé dans la méthode GetOffresParPeriode
            Date dateDeb_sql;
            Date dateFin_sql;
            
            if (dateDebut_String == null || dateFin_String == null){
                
                
                dateDeb_sql = Date.valueOf("2024-01-01");
                dateFin_sql = Date.valueOf("2024-12-31");
                
                //On renvoie les date de début et de fin pour pré-remplir les champs date du formulaire 
                request.setAttribute("dateDeb","2024-01-01");
                request.setAttribute("dateFin","2024-12-31");
            }else{
                dateDeb_sql = Date.valueOf(dateDebut_String);
                dateFin_sql = Date.valueOf(dateFin_String);
                
                //On renvoie les date de début et de fin pour pré-remplir les champs date du formulaire 
                request.setAttribute("dateDeb",dateDebut_String);
                request.setAttribute("dateFin",dateFin_String);
            }
            
            //Récupération des données pour le tableau de bord
            Collection <String> listesOffres = sessionAdministrateur.getOffresParPeriode_Json(dateDeb_sql, dateFin_sql);
            int nombreMembre = sessionAdministrateur.getNombreMembre();
            int nombreOffrePublic = sessionAdministrateur.getNombreOffrePublic();
            int nombreDonPublic = sessionAdministrateur.getNombreOffrePublicByType(TypeOffre.DON);
            int nombrePretPublic = sessionAdministrateur.getNombreOffrePublicByType(TypeOffre.PRET);
            
            //Réglage des attributs
            request.setAttribute("dataOffres", listesOffres);
            request.setAttribute("nbMembre", Integer.toString(nombreMembre));
            request.setAttribute("nbOffrePublic", Integer.toString(nombreOffrePublic));
            request.setAttribute("nbDonPublic", Integer.toString(nombreDonPublic));
            request.setAttribute("nbPretPublic", Integer.toString(nombrePretPublic));
            
        }else if(action.equals("tableauBord")){
            
            
            jsp = "/WEB-INF/jsp/TableauBordMembre.jsp";
            //Titre de la page
            request.setAttribute("titrePage", "Mon espace");
            
        }else if(action.equals("mesSouhaits")){
            jsp = "/WEB-INF/jsp/MesSouhaits.jsp";
            //Titre de la page
            request.setAttribute("titrePage", "Mes Souhaits");
            
            //long idMembre = Récupérer l'Id du membre connecté
            //Collection<Souhait> listeSouhaits = sessionMembre.GetSouhaitByMembre(idMembre);
            
            //request.setAttribute("listeSouhaits", listeSouhaits);
            
        }else if(action.equals("creerSouhait")){
            jsp = "/WEB-INF/jsp/MesSouhaits.jsp";
            doCreerSouhait(request, response);
        }else if(action.equals("supprimerSouhait")){
            long idSouhait = Long.parseLong(request.getParameter("souhait"));
            Souhait souhaitToRemove = souhaitFacade.find(idSouhait);
            sessionMembre.SupprimerSouhait(souhaitToRemove);
        }
        
        RequestDispatcher Rd;
        Rd = getServletContext().getRequestDispatcher(jsp);
        Rd.forward(request, response);
    }
    
    protected void doInscrireUtilisateur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String login = request.getParameter("loginRegister");
        String mdp = request.getParameter("mdpRegister");
        String nom = request.getParameter( "nom" );
        String prenom = request.getParameter( "prenom" );
        String bureau = request.getParameter( "bureau" );
        String tel = request.getParameter( "telephone" );
        //On récupère la valeur de l'agence à partir de son label
        Agence agence = Agence.valueOfLabel(request.getParameter("agence"));
        
        
        
        String message;
        String typeMessage;
        
        if ( prenom.trim().isEmpty() || nom.trim().isEmpty()|| login.trim().isEmpty() || mdp.trim().isEmpty() || tel.trim().isEmpty() || agence == null || bureau.trim().isEmpty()){
            message = "Vous n'avez pas rempli tous les champs obligatoires. ";
            typeMessage = "error";
        } 
        else {
            //Chiffrement du mot de passe
            String encrytptedMdp = BCrypt.hashpw(mdp, BCrypt.gensalt(12));
            sessionMembre.InscriptionUtilisateur(login, encrytptedMdp, nom, prenom, bureau, tel, agence);
            message = "Compte créé avec succès !";
            typeMessage = "success";
        }
        request.setAttribute( "message", message );
        request.setAttribute( "typeMessage", typeMessage );
    }
    
    protected void doCreerSouhait(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter( "dateFin" );
        String description = request.getParameter( "description" );
        String idUtilisateur = request.getParameter( "id" ); //Ici récupérer l'id de l'utilisateur connecté dans les attributs de session.
        
        //On récupère la valeur de l'agence à partir de son label
        TypeSouhait typeSouhait = TypeSouhait.valueOfLabel(request.getParameter("type"));
        TypeAccessoire typeAccessoire = TypeAccessoire.valueOfLabel(request.getParameter("categorie"));
        
        
        String message;
        String typeMessage;
        
        if ( dateDebut.trim().isEmpty() || dateFin.trim().isEmpty()|| description.trim().isEmpty() || idUtilisateur.trim().isEmpty()){
            message = "Vous n'avez pas rempli tous les champs obligatoires. ";
            typeMessage = "error";
        } 
        else {
            Date datePublication = Date.valueOf(LocalDate.now());
            Personne utilisateur = sessionMembre.RechercherPersonne(Long.parseLong(idUtilisateur));
            Date dateDebut_sql = Date.valueOf(dateDebut);
            Date dateFin_sql = Date.valueOf(dateFin);
            sessionMembre.CreerSouhait(datePublication, dateDebut_sql, dateFin_sql, typeSouhait, typeAccessoire, description, utilisateur);
            message = "Souhait créé avec succès !";
            typeMessage = "success";
        }
        request.setAttribute( "message", message );
        request.setAttribute( "typeMessage", typeMessage );
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
