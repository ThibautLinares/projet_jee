/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CompteBancaire;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;

/**
 *
 * @author user
 */
@Singleton
@LocalBean
@Startup
public class InitDB {
    @EJB
    private GestionnaireUtilisateur gestionnaireUtilisateur;
    
    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
    
    @PostConstruct
    public void initDB(){
        gestionnaireDeCompteBancaire.creerComptesTest();
        for (int i = 0; i < 1000; i++) {
            CompteBancaire c = new CompteBancaire("Compte"+i, (int)(Math.random() * (1000000-1)) + 1);
            gestionnaireDeCompteBancaire.creerCompte(c);
        }
        gestionnaireUtilisateur.creerUtilisateurTest();
    }
    
}
