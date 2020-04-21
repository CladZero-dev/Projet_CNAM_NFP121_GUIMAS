package fr.n7.cnam.nfp121.pr01;

import java.util.Objects;

/**
  * Diviseur transmet la valeur divisee par un diviseur.
  *
  * @author	Leo GUIMAS
  */
public class Diviseur extends Traitement {

	private double divi;
	private double res;
	
	public Diviseur(double divi) {
		this.divi = divi;
		this.res = 0;
	}
	
	public double diviseur() {
		return this.res;
	}

	@Override
	public void traiter(Position pos, double val) {
		Objects.requireNonNull(pos, "La position ne peut etre nulle ...");
		
		this.res = val * this.divi;
		
		super.traiter(pos, this.res);
	}
}