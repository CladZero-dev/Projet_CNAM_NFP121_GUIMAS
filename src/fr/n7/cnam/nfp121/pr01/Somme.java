package fr.n7.cnam.nfp121.pr01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
  * Somme calcule la sommee des valeurs, quelque soit le lot.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class Somme extends SommeAbstrait {

	private double somme;
	
	public Somme() {
		this.somme = 0;
	}
	
	@Override
	public double somme() {
		return this.somme;
	}
	
	@Override
	public void traiter(Position pos, double d) {
		Objects.requireNonNull(pos, "L'attribut position doit etre non null");
		
		this.somme +=  d;
		
		super.traiter(pos, d);
	}
	
	@Override
	public void gererDebutLotLocal(String nomLot) {
		this.somme = 0;
	}

	@Override
	public void gererFinLotLocal(String nomLot) {
		System.out.println(nomLot + ": somme = " + this.somme());
	}

}
