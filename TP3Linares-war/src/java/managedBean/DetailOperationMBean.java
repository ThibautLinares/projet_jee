/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.CompteBancaire;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.GestionnaireDeCompteBancaire;

/**
 *
 * @author user
 */
@Named
@ViewScoped
public class DetailOperationMBean implements Serializable {
    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;

    private CompteBancaire cb;
    
    private int idCompteBancaire;

    public int getIdCompteBancaire() {
        System.out.println(idCompteBancaire);
        return idCompteBancaire;
    }

    public void setIdCompteBancaire(int idCompteBancaire) {
        this.idCompteBancaire = idCompteBancaire;
    }
    
    /**
     * Creates a new instance of DetailOperationMBean
     */
    public DetailOperationMBean() {
        
    }
    
    public void loadCompte() {
        System.out.println("LOAD");
        this.cb = gestionnaireDeCompteBancaire.getCompte(idCompteBancaire);
    }

    public CompteBancaire getDetails() {
      return cb;
    }
    
}
