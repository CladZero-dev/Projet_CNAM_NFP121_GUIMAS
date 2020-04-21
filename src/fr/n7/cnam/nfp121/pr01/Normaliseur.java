package fr.n7.cnam.nfp121.pr01;

import java.util.Objects;

/**
  * Normaliseur normalise les données d'un lot en utilisant une transformation affine.
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class Normaliseur extends Traitement {

	private double debut;
	private double fin;
	private Max max;
	private Max min;
	private Donnees data;
	
	public Normaliseur(double debut, double fin) {
		this.debut = debut;
		this.fin = fin;
		this.max = new Max();
		this.min = new Max();
		this.data = new Donnees();
	}
	
	@Override
	public void traiter(Position pos, double val) {
		Objects.requireNonNull(pos, "La position ne peut etre nulle...");
		
		this.data.traiter(pos, val);
		this.max.traiter(pos, val);
		this.min.traiter(pos, -val);
		
		super.traiter(pos, val);
	}
	
	@Override
	public void gererDebutLotLocal(String nomLot) {
		this.data.gererDebutLot("Normalisation");
	}
	
	@Override
	public void gererFinLotLocal(String nomLot) {
		this.data.gererFinLotLocal("Normalisation");
		
		double maximum = this.max.getMax();
		double minimum = -this.min.getMax();
		
		double x = (maximum - minimum) / (this.fin - this.debut);
		double y = this.debut - x * minimum;
		
		for(int i = 0; i < this.data.getValeursList().size(); i++) {
			this.data.getValeursList().set(i, (x * this.data.getValeursList().get(i) + y));
		}
	}
}