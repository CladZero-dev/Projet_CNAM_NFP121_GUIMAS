package fr.n7.cnam.nfp121.pr01;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Maj indique pour chaque lot les positions mises à jour (ou ajoutées)
 * lors du traitement de ce lot.
 *
 * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
 */
public class Maj extends Traitement{

	private HashMap<String, ArrayList<Position>> posMap;
	
	public Maj() {
		this.posMap = new HashMap<String, ArrayList<Position>>();
	}
	
	@Override
	public void traiter(Position pos, double val) {
		super.traiter(pos, val);
	}
}
