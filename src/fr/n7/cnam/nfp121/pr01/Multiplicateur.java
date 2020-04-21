package fr.n7.cnam.nfp121.pr01;

import java.util.Objects;

/**
  * Multiplicateur transmet la valeur multipliée par un facteur.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Multiplicateur extends Traitement {

	private double multi;
	
	public Multiplicateur(double multi) {
		this.multi = multi;
	}

	@Override
	public void traiter(Position pos, double val) {
		Objects.requireNonNull(pos, "La position ne peut etre nulle ...");
		
		val = val * this.multi;
		
		super.traiter(pos, val);
	}
}
