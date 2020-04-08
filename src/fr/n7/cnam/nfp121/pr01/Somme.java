package fr.n7.cnam.nfp121.pr01;

/**
  * Somme calcule la sommee des valeurs, quelque soit le lot.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class Somme extends SommeAbstrait {

	// TODO à faire...


	@Override
	public void gererFinLotLocal(String nomLot) {
		System.out.println(nomLot + ": somme = " + this.somme());
	}

}
