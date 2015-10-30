/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 *
 * @author user
 */
@Entity
public class CompteBancaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String nom;
    protected double solde;
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    //@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    @OrderBy("dateOperation ASC")
    protected Collection<OperationBancaire> operations = new ArrayList<>();  
    protected String description;
    public CompteBancaire() {
    }
    
    public CompteBancaire(String nom, int solde) {  
        this.nom = nom;  
        this.solde = solde;
        OperationBancaire op = new OperationBancaire("Création du compte", solde);
        operations.add(op);
    }  

    public void crediter(double montant) {  
        solde += montant;  
        OperationBancaire op = new OperationBancaire("Crédit", (int)montant);
        operations.add(op);
    }  

    public double debiter(double montant) {
            solde -= montant;  
            OperationBancaire op = new OperationBancaire("Débit", (int)montant);
            operations.add(op);
            return montant;
    }  
    
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Collection<OperationBancaire> getOperations() {
        return operations;
    }

    public void setOperations(Collection<OperationBancaire> operations) {
        this.operations = operations;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CompteBancaire[ id=" + id + " ]";
    }
}