/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CompteBancaire;
import entity.Utilisateur;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
@LocalBean
public class GestionnaireUtilisateur {
    @PersistenceContext(unitName = "TP3Linares-ejbPU")
    private EntityManager em;

    public void creerUtilisateur(Utilisateur util) {
        em.persist(util);
    }
    
    public Utilisateur getCompteByLogin(String login) {
        
        Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login = :login").setParameter("login", login);
        try {
            return (Utilisateur) query.getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }
    
    public void creerUtilisateurTest() {  
       creerUtilisateur(new Utilisateur("test","pass","Thibaut","Linares"));  
    }
    
    public boolean checkUser(String login, String password) {
        Utilisateur u = getCompteByLogin(login);
        if(u != null) {
            return u.getPassword().equals(password);
        }
        else {
            return false;
        }
    }
}
