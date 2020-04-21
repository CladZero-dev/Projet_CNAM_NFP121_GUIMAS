package fr.n7.cnam.nfp121.pr01;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class SaisiesSwing extends JFrame implements ActionListener{
	
	//Declarations des differents attributs
	private String nameF; //Nom Fichier
	private JPanel mainPanel; //Bloc principal
	private JButton submitBtn, endBtn, deleteBtn; //Les differents boutons
	private JTextField xAxisField, yAxisField, valeurField; //Les differents champs
	private ArrayList<Integer> xList, yList; //La liste des coordonnees, ici des int
	private ArrayList<Double> valuesList; //La liste des valeurs, ici des doubles
	private int compteur; //Compteur des validations
	
	
	public SaisiesSwing(String nameF) throws IllegalArgumentException {
		
		super();
		
		//Verification du fichier
		if(nameF == null) {
			throw new IllegalArgumentException("le nom de fichier est invalide...");
		}

		
		this.compteur = 0; //Mise à 0 du compteur
		//Taille de la fenetre
		this.setSize(270, 150);
		//Titre de la fenetre
		this.setTitle("Saisie de donnees");
		this.nameF = nameF;
		this.valuesList = new ArrayList<Double>();
		this.xList = new ArrayList<Integer>();
		this.yList = new ArrayList<Integer>();
		
		
		this.mainPanel = new JPanel(); //Bloc principal, contriendra les autres blocks
		JPanel fieldsPanel = new JPanel(); //Bloc contenant les champs
		JPanel buttonsPanel = new JPanel(); //Bloc contenant les bouttons
		
		
		//Initialisation des noms/titres pour les differents champs
		JLabel xAxisLabel = new JLabel("Abscisse");
		JLabel yAxisLabel = new JLabel("Ordonnee");
		JLabel valeurLabel = new JLabel("Valeur");
		this.xAxisField = new JTextField();
		this.xAxisField.setPreferredSize(new Dimension(10,10));
		this.yAxisField = new JTextField();
		this.valeurField = new JTextField();
		this.deleteBtn = new JButton("Effacer");
		this.endBtn = new JButton("Terminer");
		this.submitBtn = new JButton("Valider");
		
		
		//Un gridlayout pour les champs + titres, les titres dans la colonne de gauche, les champs a droite
		GridLayout fieldsLayout = new GridLayout(3,2);
		fieldsLayout.preferredLayoutSize(xAxisField);
		fieldsPanel.setLayout(fieldsLayout);
		//Integration des differents champs dans la grid dans l'ordre (titre puis champs)
		fieldsPanel.add(xAxisLabel);
		xAxisLabel.setHorizontalAlignment(SwingConstants.CENTER);//Centrer le label dans la colonne
		fieldsPanel.add(this.xAxisField);
		xAxisField.setHorizontalAlignment(SwingConstants.CENTER);//Centrer le innertext dans le champ
		fieldsPanel.add(yAxisLabel);
		yAxisLabel.setHorizontalAlignment(SwingConstants.CENTER);//Centrer le label dans la colonne
		fieldsPanel.add(this.yAxisField);
		yAxisField.setHorizontalAlignment(SwingConstants.CENTER);//Centrer le innertext dans le champ
		fieldsPanel.add(valeurLabel);
		valeurLabel.setHorizontalAlignment(SwingConstants.CENTER);//Centrer le label dans la colonne
		fieldsPanel.add(this.valeurField);
		valeurField.setHorizontalAlignment(SwingConstants.CENTER);//Centrer le innertext dans le champ
		
		
		//Un flowlayout pour les boutons, le plus basique
		buttonsPanel.setLayout(new FlowLayout());
		//Integration des boutons dans la grid
		buttonsPanel.add(submitBtn);
		buttonsPanel.add(deleteBtn);
		buttonsPanel.add(endBtn);
		
		
		//Integration dans le panel principal
		mainPanel.setLayout(new GridLayout(2,1));
		mainPanel.add(fieldsPanel);
		mainPanel.add(buttonsPanel);
		
		
		//Disposition des listeners
		this.deleteBtn.addActionListener(this);
		this.deleteBtn.setActionCommand("deleteBtn"); //Pour la gestion des actions
		this.submitBtn.addActionListener(this);
		this.submitBtn.setActionCommand("submitBtn"); //Pour la gestion des actions
		this.endBtn.addActionListener(this);
		this.endBtn.setActionCommand("endBtn"); //Pour la gestion des actions
		
		
		//Finalisation de l'"initialisation"
		this.getContentPane().add(mainPanel);
		this.setResizable(false); //Pour empecher le redimensionnement
	}


	@Override
	public void actionPerformed(ActionEvent a) {
		
		switch(a.getActionCommand()){
			case "deleteBtn" : //Bouton Effacer
				//Vider les champs
				this.valeurField.setText("");
				this.xAxisField.setText("");
				this.yAxisField.setText("");
				this.valeurField.setBackground(Color.white);
				this.xAxisField.setBackground(Color.white);
				this.yAxisField.setBackground(Color.white);
				break;
				
			case "endBtn" : //Bouton Terminer
				
				FileWriter fw;
				
				try {
					fw = new FileWriter(this.nameF, false);//False pour ecraser le fichier
					
					for(int i = 0; i < this.valuesList.size(); i++) {
						fw.write(i+1 + " " + this.xList.get(i) + " " + this.yList.get(i) + " " + this.valuesList.get(i) + "\n");
					}
					
					fw.close();
					
					JOptionPane.showMessageDialog(this, "Ecriture finalisee dans le fichier " + nameF, "Fin de saisie", JOptionPane.INFORMATION_MESSAGE);
				}catch (IOException e) {
					JOptionPane.showMessageDialog(this, "Erreur dans l'ecriture du fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
				this.dispose();
				
				break;
				
			case "submitBtn" : //Bouton Valider
				if(isDouble(this.valeurField.getText()) && isInteger(this.xAxisField.getText()) && isInteger(this.yAxisField.getText())) {
					
					//Gestion enregistrement Liste + compteur
					this.xList.add(Integer.parseInt(this.xAxisField.getText()));
					this.yList.add(Integer.parseInt(this.yAxisField.getText()));
					this.valuesList.add(Double.parseDouble(this.valeurField.getText()));
					this.compteur ++;
					
					//Gestion affichage
					this.valeurField.setBackground(Color.white);
					this.xAxisField.setBackground(Color.white);
					this.yAxisField.setBackground(Color.white);
					JOptionPane.showMessageDialog(this,"Ajout " + this.compteur + " Ok", "Ajout OK",  JOptionPane.INFORMATION_MESSAGE);
					this.valeurField.setText("");
					this.xAxisField.setText("");
					this.yAxisField.setText("");

				}else {
					//Erreur dans les champs
					this.valeurField.setBackground(Color.RED);
					this.xAxisField.setBackground(Color.RED);
					this.yAxisField.setBackground(Color.RED);
					JOptionPane.showMessageDialog(this, "Verifier les champs (X et Y sont des entiers, la valeur un double)", "Attention", JOptionPane.WARNING_MESSAGE);
				}
				break;
		}
		
	}
	
	
	//Verification d'un champ si c'est un entier
	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		}catch (NumberFormatException e){
			return false;
		}
		
		return true;
	}
	
	
	//Verification d'un champ si c'est un double
	public boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		}catch (NumberFormatException e){
			return false;
		}
		
		return true;
	}
	
	public static void main(String [] args) {
		JFrame fenetre = new SaisiesSwing(args[0]);
		
		
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
}
