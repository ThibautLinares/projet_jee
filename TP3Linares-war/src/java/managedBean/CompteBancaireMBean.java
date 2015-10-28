/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.CompteBancaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import session.GestionnaireDeCompteBancaire;

/**
 *
 * @author user
 */
@Named(value = "compteBancaireMBean")
@ViewScoped
public class CompteBancaireMBean implements Serializable {
    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
    
    private List<CompteBancaire> compteList;

    private Long idCompteADebiter;

    private Long idCompteACrediter;

    private double montantACrediter;

    private double montantADebiter;
    
    private LazyDataModel<CompteBancaire> modele;
    
    private String message;
    
    private CompteBancaire selectedCompte;


    public String getMessage() {
        return message;
    }
        public CompteBancaire getSelectedCompte() {
        System.out.println("Dans getSelectedCompte");
        return selectedCompte;
    }
 
    public void setSelectedCompte(CompteBancaire selectedCompte) {
        System.out.println("Dans setSelectedCompte");
        this.selectedCompte = selectedCompte;
    }
    public void supprimerUnCompte() {
        System.out.println("Dans supprimerUnCompte");
        this.gestionnaireDeCompteBancaire.supprimerCompte(this.selectedCompte.getId());
        selectedCompte = null;
    }

    /**
     * Get the value of montantADebiter
     *
     * @return the value of montantADebiter
     */
    public double getMontantADebiter() {
        return montantADebiter;
    }

    /**
     * Set the value of montantADebiter
     *
     * @param montantADebiter new value of montantADebiter
     */
    public void setMontantADebiter(double montantADebiter) {
        this.montantADebiter = montantADebiter;
    }

    /**
     * Get the value of montantACrediter
     *
     * @return the value of montantACrediter
     */
    public double getMontantACrediter() {
        return montantACrediter;
    }

    /**
     * Set the value of montantACrediter
     *
     * @param montantACrediter new value of montantACrediter
     */
    public void setMontantACrediter(double montantACrediter) {
        this.montantACrediter = montantACrediter;
    }

    /**
     * Get the value of idCompteACrediter
     *
     * @return the value of idCompteACrediter
     */
    public Long getIdCompteACrediter() {
        return idCompteACrediter;
    }

    /**
     * Set the value of idCompteACrediter
     *
     * @param idCompteACrediter new value of idCompteACrediter
     */
    public void setIdCompteACrediter(Long idCompteACrediter) {
        this.idCompteACrediter = idCompteACrediter;
    }

    /**
     * Get the value of idCompteADebiter
     *
     * @return the value of idCompteADebiter
     */
    public Long getIdCompteADebiter() {
        return idCompteADebiter;
    }

    /**
     * Set the value of idCompteADebiter
     *
     * @param idCompteADebiter new value of idCompteADebiter
     */
    public void setIdCompteADebiter(Long idCompteADebiter) {
        this.idCompteADebiter = idCompteADebiter;
    }

    /**
     * Creates a new instance of CompteBancaireMBean
     */
    public CompteBancaireMBean() {
        modele = new LazyDataModel<CompteBancaire>(){

                    @Override
                    public List<CompteBancaire> load(int start, int nb, 
                            String nomChamp, SortOrder so, 
                            Map filters) {
 
                        if (filters != null) {
                            String filterValue = "";
                            String filterKey = "";
                            Set set = filters.entrySet();
                            Iterator i = set.iterator();
                            if (i.hasNext()) {
                                 Map.Entry me = (Map.Entry) i.next();
                                 filterKey = (String) me.getKey();
                                 filterValue = (String) me.getValue();
                             }
                            System.out.println(filterKey);
                            System.out.println(filterValue);
                        }
                        if(nomChamp != null) {
                            if(nomChamp.equals("nom")) {
                                // Il faut trier
                                System.out.println("Tri: champ= " + 
                                        nomChamp + " ordre: " +so.name());
                                return gestionnaireDeCompteBancaire.getComptesTries(start, nb, so.name(),"nom",filters);
                            }
                            else if(nomChamp.equals("id")){
                                // Il faut trier
                                System.out.println("Tri: champ= " + 
                                        nomChamp + " ordre: " +so.name());
                                return gestionnaireDeCompteBancaire.getComptesTries(start, nb, so.name(),"id",filters);
                            }
                            else if(nomChamp.equals("solde")){
                                // Il faut trier
                                System.out.println("Tri: champ= " + 
                                        nomChamp + " ordre: " +so.name());
                                return gestionnaireDeCompteBancaire.getComptesTries(start, nb, so.name(),"solde",filters);
                            }
                        } else {
                            // Juste la pagination, pas de tri, de filtre
                            return gestionnaireDeCompteBancaire.getComptes(start, nb); 
                        }
                        return null;
                    }

                    @Override
                    public int getRowCount() {
                        return (int) gestionnaireDeCompteBancaire.getNBComptes();
                    }
                      };
    }
    
    public void setModele(LazyDataModel modele) {
        this.modele = modele;
    }

    public LazyDataModel getModele() {
        return modele;
    }
    
    public List<CompteBancaire>getComptes() {
        return gestionnaireDeCompteBancaire.getAllComptes();
    }
    
    public void creerCompteTest() {
        gestionnaireDeCompteBancaire.creerComptesTest();
    }
    
    public void crediterUnCompte() {
        gestionnaireDeCompteBancaire.crediterUnCompte(idCompteACrediter, montantACrediter);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Compte "+idCompteACrediter+" crédité de "+montantACrediter+"€"));
    }
    
    public void debiterUnCompte() {
        gestionnaireDeCompteBancaire.debiterUnCompte(idCompteADebiter, montantADebiter);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Compte "+idCompteADebiter+" débité de "+montantADebiter+"€"));
    }
    
    public void transferer() {
        try {
            gestionnaireDeCompteBancaire.transferer(idCompteADebiter, idCompteACrediter, montantADebiter);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", montantADebiter+"€ transféré du compte "
                    +idCompteADebiter+" au compte "+idCompteACrediter));
        } catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Transfert impossible, pas assez d'argent"));
        }
    }
    
    public void supprimerUnCompte(Long idCompteASupprimer) {
        gestionnaireDeCompteBancaire.supprimerCompte(idCompteASupprimer);
    }
    
    public String showDetails(int idCompteBancaire) {
        return "DetailOperation?idCompteBancaire="+idCompteBancaire;
    }
    
}
