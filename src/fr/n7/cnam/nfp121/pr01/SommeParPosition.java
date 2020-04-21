package fr.n7.cnam.nfp121.pr01;

import java.util.*;

/**
  * SommeParPosition 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class SommeParPosition extends Traitement {

	private HashMap<Position, Double> mapSommes;
	
	@Override 
	public void gererDebutLotLocal(String nomLot){
		this.gererDebutLotLocal(nomLot);
		this.mapSommes = new HashMap<Position, Double>();
	}
	
	
	@Override
	public void gererFinLotLocal(String nomLot) {
		System.out.println("Somme par Position" + nomLot + ":");
		
		for(Map.Entry<Position, Double> temp : this.mapSommes.entrySet()) {
			System.out.println(temp.getKey() + "==>" + temp.getValue());
		}
	}
	
	
	@Override
	public void traiter(Position pos, double val) {
		Objects.requireNonNull(pos, "La position ne peut etre nulle...");
		
		if(this.mapSommes.containsKey(pos)) {
			
			double somme = this.mapSommes.get(pos) + val;
			
			this.mapSommes.put(pos, somme);
			
		}else {
			
			this.mapSommes.put(pos, val);
			
		}
		
		super.traiter(pos, val);
	}

}
