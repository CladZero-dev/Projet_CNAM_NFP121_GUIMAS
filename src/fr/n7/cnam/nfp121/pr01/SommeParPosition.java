package fr.n7.cnam.nfp121.pr01;

import java.util.*;

/**
  * SommeParPosition 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class SommeParPosition extends Traitement {

	private Donnees data;
	
	@Override 
	public void gererDebutLotLocal(String nomLot){
		this.data = new Donnees();
		this.data.gererDebutLotLocal(nomLot);
	}
	
	
	@Override
	public void gererFinLotLocal(String nomLot) {
		System.out.println("Somme par Position " + nomLot + ":");
		
		for(int i = 0; i < this.data.getValeursList().size(); i++) {
			System.out.println("Position : " + this.data.getPositionsList().get(i) + " ==> " + this.data.getValeursList().get(i));
		}
	}
	
	
	@Override
	public void traiter(Position pos, double val) {
		Objects.requireNonNull(pos, "La position ne peut etre nulle...");
		
		if(this.data.getPositionsList().contains(pos)) {
			int i = this.data.getPositionsList().indexOf(pos);
			this.data.getValeursList().set(i, this.data.getValeursList().get(i) + val);
		}else {
			this.data.traiter(pos, val);
		}
		
		super.traiter(pos, val);
	}

}
