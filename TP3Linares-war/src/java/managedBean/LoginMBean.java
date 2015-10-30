/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Utilisateur;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import session.GestionnaireUtilisateur;

/**
 *
 * @author user
 */
@Named(value = "loginMBean")
@SessionScoped
public class LoginMBean implements Serializable {
    @EJB
    private GestionnaireUtilisateur gestionnaireUtilisateur;

    private Utilisateur utilisateur;
    private String login;
    private String password;
    private boolean connected = false;
    private String message = "Veuillez vous identifier";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /** Creates a new instance of LoginMBean */
    public LoginMBean() {
    }

    public void deconnexion() {
        connected = false;
        message = "Veuillez vous identifier :";
    }

    public void checkLogin() {
              
        connected = gestionnaireUtilisateur.checkUser(login, password);
        if (connected) {
            message = "Bienvenue, vous êtes connecté en tant que " + login + " ! ";
        } else {
            message = "Mauvais login/password, veuillez recommencer !";
        }

    }
    
}
