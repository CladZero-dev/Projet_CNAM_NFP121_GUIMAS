package fr.n7.cnam.nfp121.pr01;

import java.io.*;
import java.util.Objects;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * GenerateurXML écrit dans un fichier, à charque fin de lot, toutes
 * les données lues en indiquant le lot dans le fichier XML.
 *
 * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
 */
public class GenerateurXML extends Traitement {

	private String nameF;
	private Element root;
	private Element lot;
	private Document document;
	private Donnees data;
	
	public GenerateurXML(String nameF) {
		this.nameF = nameF;
		this.root = new Element("Root");
		this.document = new Document(this.root, new DocType("src/generateur.dtd"));
	}
	
	@Override
	public void gererDebutLotLocal(String nomLot) {
		this.data = new Donnees();
		this.data.gererDebutLot("Data" + nomLot);
		this.lot = new Element("Lot");
		this.lot.setAttribute("nom", nomLot);
		this.root.addContent(this.lot);
	}
	
	@Override
	public void gererFinLotLocal(String nomLot) {
		this.data.gererFinLot("Data " + nomLot);
		
		for(int i = 0; i < this.data.getPositionsList().size(); i++) {
			Position pos = this.data.getPositionsList().get(i);
			double val = this.data.getValeursList().get(i);
			
			Element ePosition = new Element("Position");
			
			ePosition.setAttribute(new Attribute("X", String.valueOf(pos.x)));
			ePosition.setAttribute(new Attribute("Y", String.valueOf(pos.y)));
			
			Element eValeur = new Element("Valeur");
			eValeur.setText(String.valueOf(val));
			ePosition.addContent(eValeur);
			
			this.lot.addContent(ePosition);
		}
		
		XMLOutputter xml = new XMLOutputter(Format.getPrettyFormat());
		try {
			FileOutputStream outputDoc = new FileOutputStream(this.nameF);
			xml.output(this.document, outputDoc);
			outputDoc.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void traiter(Position pos, double val) {
		Objects.requireNonNull(pos, "La position ne peut etre nulle...");
		
		this.data.traiter(pos, val);
		super.traiter(pos, val);
	}

}
