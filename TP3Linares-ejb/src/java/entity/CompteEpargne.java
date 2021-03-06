/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Fabrice
 */
@Entity
public class CompteEpargne extends CompteBancaire implements Serializable {
	private double tauxEpargne;
	public CompteEpargne() {}
	public CompteEpargne(String nom, int solde, double taux) {
		super(nom, solde);
		this.tauxEpargne = taux;
                this.description = "Epargne";
	}
	public void appliquerTaux() {
            solde = solde * (1 + tauxEpargne);
	}
	public double getTauxEpargne() {
		return tauxEpargne;
	}
	public void setTauxEpargne (double taux) {
	}
	@Override 
        public String toString() {
            return "entities.CompteEpargne[ id=" + id + " ]";
        }
}
