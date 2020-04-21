package fr.n7.cnam.nfp121.pr01;

import java.util.Objects;

/**
  * SupprimerPlusPetit supprime les valeurs plus petites qu'un seuil.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class SupprimerPlusPetit extends Traitement {

private double limit;
	
	public SupprimerPlusPetit(double limit) {
		this.limit = limit;
	}
	
	@Override
	public void traiter(Position pos, double val) {
		Objects.requireNonNull(pos, "La position ne peut etre nulle ....");
		
		if(val >= this.limit) {
			super.traiter(pos, val);
		}
	}

}
