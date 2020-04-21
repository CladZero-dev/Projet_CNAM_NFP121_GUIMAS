package fr.n7.cnam.nfp121.pr01;

import java.util.ArrayList;
import java.util.Objects;

/**
  * Donnees enregistre toutes les données reçues, quelque soit le lot.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Donnees extends Traitement {

	private ArrayList<Position> positionsList;
	private ArrayList<Double> valeursList;
	
	public Donnees() {
		this.positionsList = new ArrayList<Position>();
		this.valeursList = new ArrayList<Double>();
	}
	
	@Override
	public void traiter(Position pos, double val) {
		Objects.requireNonNull(pos, "La position ne peut etre nulle...");
		
		this.positionsList.add(pos);
		this.valeursList.add(val);
		super.traiter(pos, val);
	}
	
	public ArrayList<Position> getPositionsList(){
		return this.positionsList;
	}

	public ArrayList<Double> getValeursList(){
		return this.valeursList;
	}
}
