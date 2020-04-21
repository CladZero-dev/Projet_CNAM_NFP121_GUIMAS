package fr.n7.cnam.nfp121.pr01;

import java.util.Objects;

/**
 * Max calcule le max des valeurs vues, quelque soit le lot.
 *
 * @author Xavier Crégut <Prenom.Nom@enseeiht.fr>
 */

public class Max extends Traitement {

	private double max;

	public Max() {
		this.max = 0;
	}

	@Override
	public void traiter(Position pos, double val) {
		Objects.requireNonNull(pos, "La position ne peut etre nulle");

		if (val > max) {
			this.max = val;
		}

		super.traiter(pos, val);
	}

	public double getMax() {
		return this.max;
	}
}
