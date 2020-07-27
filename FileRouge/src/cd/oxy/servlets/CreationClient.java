package cd.oxy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cd.oxy.beans.Client;

/**
 * Servlet implementation class CreationClient
 */
@WebServlet( "/CreationClient" )
public class CreationClient extends HttpServlet {
    private static final long   serialVersionUID = 1L;

    private static final String ATT_CLIENT       = "client";
    private static final String ATT_MESSAGE      = "message";
    private static final String ATT_ERREUR       = "erreur";

    private static final String VUE_FORMS        = "/WEB-INF/creerClient.jsp";
    private static final String VUE_DATA         = "/WEB-INF/afficherClient.jsp";

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /*
         * R�cup�ration des donn�es saisies, envoy�es en tant que param�tres de
         * la requ�te GET g�n�r�e � la validation du formulaire
         */
        String nom = request.getParameter( ATT_NOM );
        String prenom = request.getParameter( ATT_PRENOM );
        String adresse = request.getParameter( ATT_ADRESSE );
        String telephone = request.getParameter( ATT_TELEPHONE );
        String email = request.getParameter( ATT_EMAIL );

        String Message;
        boolean eRReur;
        /*
         * Initialisation du message � afficher : si un des champs obligatoires
         * du formulaire n'est pas renseign�, alors on affiche un message
         * d'erreur, sinon on affiche un message de succ�s
         */
        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() ) {
            Message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> "
                    + "<a href=\"creerClient.jsp\">Cliquez ici</a> pour acc�der au formulaire de cr�ation d'un client.";
            eRReur = true;
        } else {
            Message = "Cr�ation client Avec Succes";
            eRReur = false;
        }

        /*
         * Cr�ation du bean Client et initialisation avec les donn�es r�cup�r�es
         */
        Client client = new Client();
        client.setNom( nom );
        client.setPrenom( prenom );
        client.setAdresse( adresse );
        client.setTelephone( telephone );
        client.setEmail( email );

        /* Ajout du bean et du message � l'objet requ�te */
        request.setAttribute( ATT_ERREUR, eRReur );
        request.setAttribute( ATT_MESSAGE, Message );
        request.setAttribute( ATT_CLIENT, client );

        /* Transmission � la page JSP en charge de l'affichage des donn�es */
        this.getServletContext().getRequestDispatcher( VUE_FORMS ).forward( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost( req, resp );
    }
}
