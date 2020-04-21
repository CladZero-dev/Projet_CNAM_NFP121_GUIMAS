package fr.n7.cnam.nfp121.pr01;

import java.util.*;

/**
 * Positions enregistre toutes les positions, quelque soit le lot.
 *
 * @author Xavier Crégut <Prenom.Nom@enseeiht.fr>
 */

public class Positions extends PositionsAbstrait {

	private ArrayList<Position> posList;

	public Positions() {
		this.posList = new ArrayList<Position>();
	}

	@Override
	public int nombre() {
		return this.posList.size();
	}

	@Override
	public int frequence(Position pos) {
		int f = 0;

		for (Position p : this.posList) {
			if (p.equals(pos)) {
				f += 1;
			}
		}

		return f;
	}
	
	@Override
	public Position position(int i) {
		return this.posList.get(i);
	}
	
	@Override
	public void traiter(Position pos, double val) {
		Objects.requireNonNull(pos, "La position ne peut etre nulle...");
		
		this.posList.add(pos);
		
		super.traiter(pos, val);
	}
}
