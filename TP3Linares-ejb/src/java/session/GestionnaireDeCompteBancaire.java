/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CompteBancaire;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class GestionnaireDeCompteBancaire {
    @PersistenceContext(unitName = "TP3Linares-ejbPU")
    private EntityManager em;

    public GestionnaireDeCompteBancaire() {
        
    }
    
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }
    
    public void supprimerCompte(Long id) {
        CompteBancaire c = em.find(CompteBancaire.class, id);
        em.remove(em.merge(c));
    }
    
    public List<CompteBancaire> getAllComptes() {
        Query q = em.createQuery("select c from CompteBancaire c");
        return q.getResultList();
    }
    
    public List<CompteBancaire> getComptes(int start, int nbComptes){
        
        Query q = em.createQuery("select c from CompteBancaire c");
        q.setFirstResult(start);
        q.setMaxResults(nbComptes);
        return q.getResultList();
    }
    
    public int getNBComptes(){
        Query query = em.createQuery("Select COUNT(c) FROM CompteBancaire c");
        return ((Long) query.getSingleResult()).intValue();
    }
    
    public List<CompteBancaire> getRequeteFiltre(int start, int nb, Map filters) {
        
        String id = "";
        String nom = "";
        String solde = "";
        String filtre = "";
        int countFilter = 0;
        if (filters != null) {
            Set set = filters.entrySet();
            Iterator i = set.iterator();
            if (i.hasNext()) {
                 Map.Entry me = (Map.Entry) i.next();
                 if(me.getKey().equals("id")) {
                    id = " c.id = "+(String) me.getValue();
                    countFilter++;
                 }
                 else if(me.getKey().equals("nom")) {
                    if(countFilter > 0) {
                        nom = "AND";
                    }
                    nom += " c.nom like '%"+(String) me.getValue()+"%'";
                    countFilter++;
                 }
                 else if(me.getKey().equals("solde")) {
                    if(countFilter > 0) {
                        nom = "AND";
                    }
                    solde += " c.solde = "+(String) me.getValue();
                    countFilter++;
                 }
             }
            if(countFilter > 0) {
                filtre = " WHERE"+id+nom+solde;
            }
        }
        String r = "select c from CompteBancaire c"+filtre;
        Query q = em.createQuery(r);
        q.setFirstResult(start);
        q.setMaxResults(nb);
        return q.getResultList();
    }
    /**
     * 
     * @param commencePar exemple: recherche les comptes commencant par : "compte1"
     * @param nombreMax exemple : selectionne les x premiers comptes commencant par :"compte1"
     * @return 
     */
    public List<String> getComptesAutoComplete(String commencePar, int nombreMax)
    {        //Query query = em.createQuery("SELECT c FROM CompteBancaire c WHERE c.id = :id").setParameter("id", id);
        Query query = em.createQuery("Select c.nom FROM CompteBancaire c WHERE c.nom LIKE '"+commencePar+"%'");
        query.setMaxResults(nombreMax);
        return query.getResultList();

    }
    public Long getIdCompteByName(String nameCompte){
        String nomCompte = nameCompte.trim();
        Query query = em.createQuery("SELECT c.id FROM CompteBancaire c WHERE c.nom='"+nomCompte+"'");
        Long id = (long)query.getSingleResult();
        return id;
    }
    public List<CompteBancaire> getComptesTries(int start, int nb, String order, String tri) {
        
        String orderValue = "";
          if(order.equals("ASCENDING")) {
              orderValue = "ASC";
        } else {
              orderValue = "DESC";
          }
        String r = "select c from CompteBancaire c order by c."+tri+" " 
                + orderValue;
        System.out.println(r);
        Query q = em.createQuery(r);
        q.setFirstResult(start);
        q.setMaxResults(nb);
        return q.getResultList();
    }
    
    public CompteBancaire getCompte(int id) {
        
        Query query = em.createQuery("SELECT c FROM CompteBancaire c WHERE c.id = :id").setParameter("id", id);
        return (CompteBancaire) query.getSingleResult();
    }
    
    public void creerComptesTest() {  
       creerCompte(new CompteBancaire("John Lennon", 150000));  
       creerCompte(new CompteBancaire("Paul McCartney", 950000));  
       creerCompte(new CompteBancaire("Ringo Starr", 20000));  
       creerCompte(new CompteBancaire("Georges Harrisson", 100000));  
    }
    
    public void crediterUnCompte(Long id, double montant){
        CompteBancaire c = em.find(CompteBancaire.class, id);
        c.crediter(montant);
    }   
    
    public void debiterUnCompte(Long id, double montant){
        CompteBancaire c = em.find(CompteBancaire.class, id);
        c.debiter(montant);
    }
    
    public void transferer(Long id1, Long id2, double montant){
        debiterUnCompte(id1,montant);
        crediterUnCompte(id2,montant);
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    
}