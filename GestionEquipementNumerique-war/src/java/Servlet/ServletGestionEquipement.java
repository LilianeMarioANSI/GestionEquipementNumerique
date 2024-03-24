/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Entite.Accessoire;
import Entite.Agence;
import Entite.Demande;
import Entite.EtatAccessoire;
import Entite.EtatOffre;
import Entite.Membre;
import Entite.Offre;
import Entite.Personne;
import Entite.Souhait;
import Entite.TypeAccessoire;
import Entite.TypeOffre;
import Entite.TypeSouhait;
import Facade.MembreFacadeLocal;
import Facade.OffreFacadeLocal;
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
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    
    @EJB
    private OffreFacadeLocal offreFacade;
    
    @EJB
    private MembreFacadeLocal membreFacade;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        String jsp = null;
        
        if(action==null || action.equals("accueil")){
            HttpSession session = request.getSession(false);
            if(session != null){
                Personne p = (Personne) session.getAttribute("membre");
                
                if(p.getId() != null){
                    jsp = "/ServletGestionEquipement?action=tableauBord";
                    request.setAttribute("titrePage", "Mon espace !");
                    request.setAttribute("titrePage", "Bienvenue !");
                }else{
                    jsp = "/WEB-INF/jsp/Accueil.jsp";
                    //Titre de la page
                    request.setAttribute("titrePage", "Bienvenue !");
                }
            }else{
                jsp = "/WEB-INF/jsp/Accueil.jsp";
                //Titre de la page
                request.setAttribute("titrePage", "Bienvenue !");
            }
            
            
            
            
        }
        /*
            Inscription
        */
        else if(action.equals("creerMembre")){
            jsp = "/WEB-INF/jsp/Accueil.jsp";
            doInscrireUtilisateur(request, response);
            
            request.setAttribute("titrePage", "Bienvenue !"); //Titre de la page
        }
        
        else if(action.equals("inscription")){
            jsp = "/WEB-INF/jsp/Inscription.jsp";

            request.setAttribute("titrePage", "Inscription"); //Titre de la page
        }
        
        /*
            Authentification
        */
        else if (action.equals("authentification")) {
            String login = request.getParameter("login");
            String mdp = request.getParameter("mdp");
                Membre m = sessionMembre.IdentificationMembre(login, mdp);
                if (m != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("membre", m);                 
                    // Afficher le profil de l'utilisateur après une authentification réussie
                    jsp = "/ServletGestionEquipement?action=tableauBord";
                } else {
                    // Rediriger vers la page d'accueil avec un message d'erreur si l'authentification échoue
                    String message = "Identifiant ou mot de passe incorrect.";
                    request.setAttribute("message", message);
                    request.setAttribute("typeMessage", "error");
                    request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp").forward(request, response);
                }
        }
        
        /*
            Catalogue
        */
        else if(action.equals("afficherCatalogue")){
            String type = request.getParameter("type");
            String etat = request.getParameter("etat");
            String categorie = request.getParameter("categorie");

            List<Offre> offres;

            // Vérifier s'il y a des paramètres de filtre spécifiés
            if (type != null || etat != null || categorie != null) {
                // Si des paramètres de filtre sont spécifiés, récupérer les offres filtrées
                offres = sessionMembre.ConsulterCatalogueFiltre(type, etat, categorie);
            } else {
                offres = sessionMembre.ConsulterCatalogue();
            }

            request.setAttribute("offres", offres);
            request.setAttribute("titrePage", "Offres en ligne"); // Titre de la page
            jsp = "/WEB-INF/jsp/Catalogue.jsp";
        }
        
        /*
            TDB Reporting 
            autorisation : administrateur
        */
        else if(action.equals("analytics")){
            
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
            int nombreMembreAvecOffre = sessionAdministrateur.getNombreMembreAvecOffreByPeriode(dateDeb_sql, dateFin_sql);
            int nombreMembreAvecDemande = sessionAdministrateur.getNombreMembreAvecDemandeByPeriode(dateDeb_sql, dateFin_sql);
            int nombreOffrePublic = sessionAdministrateur.getNombreOffrePublic();
            int nombreDonPublic = sessionAdministrateur.getNombreOffrePublicByType(TypeOffre.DON);
            int nombrePretPublic = sessionAdministrateur.getNombreOffrePublicByType(TypeOffre.PRET);
            Collection<String> agenceByOffre = sessionAdministrateur.getTop5AgenceByOffre(dateDeb_sql, dateFin_sql);
            System.out.println(agenceByOffre);
            List<String> etatsAccessoiresData = new ArrayList<>();
    
            // Récupération du nombre d'accessoires pour chaque état d'accessoire
            for (EtatAccessoire e : EtatAccessoire.values()) {
                List<Accessoire> listeAccessoire = sessionAdministrateur.getAccessoireByEtat(e);
                int nombreAccessoires = listeAccessoire.size(); // Nombre d'accessoires pour cet état

                // Création d'une chaîne représentant les données pour cet état
                String data = "{\"etat\": \"" + e.label + "\", \"quantite\": " + nombreAccessoires + "}";
                etatsAccessoiresData.add(data);
            }
            
            //Réglage des attributs pour les graphiques
            request.setAttribute("dataOffres", listesOffres);
            request.setAttribute("dataEtatAccessoire", etatsAccessoiresData);
            request.setAttribute("dataAgenceByOffre", agenceByOffre);
            
            //Réglage des attributs pour les cartes
            request.setAttribute("nbMembre", Integer.toString(nombreMembre));
            request.setAttribute("nbMembreAvecDemande", Integer.toString(nombreMembreAvecDemande));
            request.setAttribute("nbMembreAvecOffre", Integer.toString(nombreMembreAvecOffre));
            request.setAttribute("nbOffrePublic", Integer.toString(nombreOffrePublic));
            request.setAttribute("nbDonPublic", Integer.toString(nombreDonPublic));
            request.setAttribute("nbPretPublic", Integer.toString(nombrePretPublic));
            
        }
        
        /* 
            TDB Membre
        */
        else if(action.equals("tableauBord")){
            HttpSession session = request.getSession();
            Membre membre = (Membre) session.getAttribute("membre");
            // Pour chaque membre, récupérer la liste de ses dons, prêts, offres, souhaits
            List<Demande> dons = sessionMembre.listeDon(membre);
            List<Demande> prets = sessionMembre.listePrêts(membre);
            List<Offre> offres=sessionMembre.listeMesOffres(membre);
            List<Souhait> souhaits=sessionMembre.listeMesSouhaits(membre);
            
            request.setAttribute("dons", dons);
            request.setAttribute("prets", prets);
            request.setAttribute("offres", offres);
            request.setAttribute("souhaits", souhaits);
            // Transmettre les informations du membre à la vue JSP
            request.setAttribute("membre", membre);

            // Rediriger vers la vue JSP du tableau de bord
            jsp="/WEB-INF/jsp/TableauBordMembre.jsp";
        }
        else if(action.equals("mesEquipements")){
            jsp = "/WEB-INF/jsp/mesEquipements.jsp";
            //Titre de la page
            request.setAttribute("titrePage", "Mon equipements");
        }
        else if(action.equals("SupprimerMembre")){
            long membreId = Long.parseLong(request.getParameter("membreId"));
            
            boolean m = sessionMembre.SupprimerMembre(membreId);
            if(m ==true){
                jsp="/WEB-INF/jsp/Accueil.jsp";
                request.setAttribute("message", "Membre supprimée");
            }
            else{
                jsp="/ServletGestionEquipement?action=tableauBord";
                request.setAttribute("message", "Erreur, Membre non supprimée");
            }
        }
        else if(action.equals("ModifierMembre")){
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");
            String bureau = request.getParameter("bureau");
            String agence = request.getParameter("agence");
            
            HttpSession session = request.getSession();
            Membre membre = (Membre) session.getAttribute("membre");
            long membreId = membre.getId();
            
            Agence ag = sessionMembre.getAgenceById(agence);
            boolean m = sessionMembre.ModifierMembre(membreId, nom, prenom, email, telephone, bureau, ag);
  
            if(m){
                jsp="/ServletGestionEquipement?action=tableauBord";
                request.setAttribute("message", "Modifications enregistrées");
            }
            else{
                jsp="/ServletGestionEquipement?action=tableauBord";
                request.setAttribute("message", "Erreur, Modifications non prises en compte");
            }
        }
        
        /*
            Souhaits
        */
        else if(action.equals("mesSouhaits")){
            HttpSession session = request.getSession(false);
            Membre membre = (Membre) session.getAttribute("membre");
            
            if(membre.getId() != null){
                System.out.println(membre.getId());
                long idMembre = membre.getId();
                Collection<Souhait> listeSouhaits = sessionMembre.GetSouhaitByMembre(idMembre);

                request.setAttribute("listeSouhaits", listeSouhaits); 
                jsp = "/WEB-INF/jsp/MesSouhaits.jsp";
                //Titre de la page
                request.setAttribute("titrePage", "Mes Souhaits");
            }else {
                jsp = "/ServletGestionEquipement?action=accueil";
            }
            
            
            
        }
        
        else if(action.equals("creerSouhait")){
            jsp = "/ServletGestionEquipement?action=mesSouhaits";
            doCreerSouhait(request, response);
        }
        else if(action.equals("supprimerSouhait")){
            long idSouhait = Long.parseLong(request.getParameter("idSouhait"));
            Souhait souhaitToRemove = souhaitFacade.find(idSouhait);
            souhaitFacade.remove(souhaitToRemove);
            jsp = "/ServletGestionEquipement?action=mesSouhaits";
        }
        
        /*
            Offre
        */
        else if (action.equals("creerOffre")){
            jsp="/WEB-INF/jsp/FormCreationOffre.jsp";
        }
        else if (action.equals("AjouterOffre")){
            // Réception des données du formulaire pour Accessoire 
            HttpSession session = request.getSession();
            Membre membre = (Membre) session.getAttribute("membre");
            // Transmettre les informations du membre à la vue JSP
            String intituleAccessoire = request.getParameter("nomA");
            String descriptionAccessoire = request.getParameter("DescriptionA");
            String typeAccessoire = request.getParameter("TypeA");
            String etatAccessoire = request.getParameter("etatA");
            if(intituleAccessoire.trim().isEmpty() || descriptionAccessoire.trim().isEmpty() || typeAccessoire.trim().isEmpty() || etatAccessoire.trim().isEmpty()){
                jsp="/WEB-INF/jsp/FormCreationOffre.jsp";
                request.setAttribute("message", "Vous n'avez pas rempli tous les champs obligatoires");
                request.setAttribute("typeMessage", "error");
            }
            else{
                TypeAccessoire typeAccessoireEnum = TypeAccessoire.valueOfLabel(typeAccessoire);
                EtatAccessoire etatAccessoireEnum = EtatAccessoire.valueOfLabel(etatAccessoire);
                Accessoire a = creerAccessoire(intituleAccessoire, descriptionAccessoire, typeAccessoireEnum, etatAccessoireEnum, membre);
                //Récupération des données du formulaire pour Offre
                String dateDebut = request.getParameter("DateDeb");
                String dateFin = request.getParameter("DateFin");
                String intitule= request.getParameter("titreO");
                String description = request.getParameter("DescriptionO");
                String typeOffre= request.getParameter("typeO");
                Date dd = null;
                Date df = null;
                try {
                    dd = Date.valueOf(dateDebut);
                    df = Date.valueOf(dateFin);
                } 
                catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                if(intitule.trim().isEmpty() || description.trim().isEmpty() || typeOffre.trim().isEmpty() || dd==null || df==null){
                    jsp="/WEB-INF/jsp/FormCreationOffre.jsp";
                    request.setAttribute("message", "Vous n'avez pas rempli tous les champs obligatoires");
                    request.setAttribute("typeMessage", "error");
                }
                else if( dd.before(df) && //La date de début doit être avant la date de fin
                    dd.after(new Date(System.currentTimeMillis())) &&//La date de début doit être après la date actuelle
                    df.after(new Date(System.currentTimeMillis())) //La date de fin doit être après la date actuelle
                    )
                {
                    TypeOffre typeOffreEnum = TypeOffre.valueOfLabel(typeOffre);
                    Offre o= creerOffre(intitule, description, typeOffreEnum, dd, df, a, membre);
                    if(o!=null){
                        Accessoire accessoire = sessionMembre.CreerAccessoire(a);
                        Offre offre = sessionMembre.creationOffre(o);
                        if (offre != null && accessoire != null){
                            request.setAttribute("message", "Offre crée");
                            request.setAttribute("typeMessage", "success");
                            jsp="/ServletGestionEquipement?action=tableauBord";
                        }
                        else{
                            jsp="/WEB-INF/jsp/FormCreationOffre.jsp";
                            request.setAttribute("message", "Erreur lors de la création de l'offre");
                            request.setAttribute("typeMessage", "error");
                        }
                    }
                    else{
                        jsp="/WEB-INF/jsp/FormCreationOffre.jsp";
                        request.setAttribute("message", "Erreur lors de la création de l'offre");
                        request.setAttribute("typeMessage", "error");
                    }
                }
                else{
                    jsp="/WEB-INF/jsp/FormCreationOffre.jsp";
                    request.setAttribute("message", "La date de début doit être après la date actuelle et avant la date de fin, et la date de fin doit être après la date actuelle");
                    request.setAttribute("typeMessage", "error");
                    
                }      
        }
        }

        // Afficher mes dons
        else if(action.equals("AfficherMesDons")){
            HttpSession session = request.getSession();
            Membre membre = (Membre) session.getAttribute("membre");
            List<Demande> dons = sessionMembre.listeDon(membre);
            jsp = "/WEB-INF/jsp/mesDons.jsp";
            request.setAttribute("dons", dons);
            if(dons.isEmpty()){
                request.setAttribute("message", "Vous n'avez aucun don");
            }
        }
        
        // Afficher mes Prets
        else if(action.equals("AfficherMesPrets")){
            HttpSession session = request.getSession();
            Membre membre = (Membre) session.getAttribute("membre");
            List<Demande> prets = sessionMembre.listePrêts(membre);
            jsp = "/WEB-INF/jsp/mesPrets.jsp";
            request.setAttribute("prets", prets);
            if(prets.isEmpty()){
                request.setAttribute("message", "Vous n'avez aucun prets");
            }
        }
        else if(action.equals("afficherDetailOffre")){
            Long idOffre = Long.valueOf(request.getParameter("idOffre"));
            Offre offre = offreFacade.find(idOffre);
            
            request.setAttribute("offre", offre);
            request.setAttribute("titrePage", "Détail de l\'offre");
            jsp = "/WEB-INF/jsp/DetailOffre.jsp";
        }else if(action.equals("reclamerOffre")){
            String idOffre_string = request.getParameter("idOffre");
            String idMembre_string = request.getParameter("idUtilisateur");
            if(idOffre_string.trim().isEmpty() || idMembre_string.trim().isEmpty()){
                request.setAttribute("message", "Échecs de la réclamation de l'offre");
                request.setAttribute("typeMessage", "error");
            }else{
                Offre offre = offreFacade.find(Long.valueOf(idOffre_string));
                Membre membre = membreFacade.find(Long.valueOf(idMembre_string));
                sessionMembre.CreerDemande(membre, offre);
                
                
                sessionMembre.updateEtatOffre(offre);
                
                
                request.setAttribute("message", "Offre réclamé avec succès !");
                request.setAttribute("typeMessage", "success");
            }
            jsp = "/ServletGestionEquipement?action=tableauBord";
        }
        else {
            jsp = "/WEB-INF/jsp/Accueil.jsp";
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
        
        HttpSession session = request.getSession(false);
        
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter( "dateFin" );
        String description = request.getParameter( "description" );
        
        String typeSouhait_string = request.getParameter("typeSouhait");
        TypeSouhait typeSouhait;
        
        if(typeSouhait_string.equals("DON")){
            typeSouhait = TypeSouhait.DON;
        }else{
            typeSouhait = TypeSouhait.PRET;
        }
        
        TypeAccessoire typeAccessoire = TypeAccessoire.valueOfLabel(request.getParameter("categorie"));
        
        
        String message;
        String typeMessage;
        
        if ( dateDebut.trim().isEmpty() || dateFin.trim().isEmpty()|| description.trim().isEmpty()){
            message = "Vous n'avez pas rempli tous les champs obligatoires. ";
            typeMessage = "error";
        }else if(session == null){
            message = "Vous n'avez pas rempli tous les champs obligatoires. ";
            typeMessage = "error";
            response.sendRedirect("/ServletGestionEquipement");
        } else {
            Membre membre = (Membre) session.getAttribute("membre");
            Date datePublication = Date.valueOf(LocalDate.now());
            Date dateDebut_sql = Date.valueOf(dateDebut);
            Date dateFin_sql = Date.valueOf(dateFin);
            sessionMembre.CreerSouhait(datePublication, dateDebut_sql, dateFin_sql, typeSouhait, typeAccessoire, description, membre);
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

    protected Accessoire creerAccessoire (String intituleAccessoire, String descriptionAccessoire, TypeAccessoire typeAccessoire, EtatAccessoire etatAccessoire, Membre membre){
        Accessoire a= new Accessoire();
        a.setPersonnes(new ArrayList<Personne>());
        a.setDesignation(intituleAccessoire);
        a.setDescription(descriptionAccessoire);
        a.setTypeAccessoire(typeAccessoire);
        a.setEtat(etatAccessoire);
        a.setDisponibilite(true);
        a.getPersonnes().add(membre);
        return a;
    }

    protected Offre creerOffre (String intitule, String description, TypeOffre typeOffreEnum, Date dateDebut, Date dateFin, Accessoire a, Membre membre){
        Offre o= new Offre();
        o.setIntitule(intitule);
        o.setDescription(description);
        o.setTypeOffre(typeOffreEnum);
        o.setDateDebut(dateDebut);
        o.setDateFin(dateFin);
        o.setDatePublication(new Date(System.currentTimeMillis()));
        o.setEtat(EtatOffre.DISPONIBLE);
        o.setAccessoire(a);
        o.setUtilisateur(membre);
        return o;
    }

}

