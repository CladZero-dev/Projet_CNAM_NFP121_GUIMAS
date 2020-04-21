package fr.n7.cnam.nfp121.pr01;

import java.lang.reflect.*;
import java.util.*;
import java.util.Scanner;

/**
  * TraitementBuilder 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */
public class TraitementBuilder {

	/** Retourne un objet de type Class correspondant au nom en paramètre.
	 * Exemples :
	 *   - int donne int.class
	 *   - Normaliseur donne Normaliseur.class
	 */
	Class<?> analyserType(String nomType) throws ClassNotFoundException {
		
		Class<?> classe = null;

		try {
			classe = Class.forName(nomType);
		}catch(ClassNotFoundException e) {
			switch(nomType) {
				case "int" :
					return int.class;
				case "double" :
					return double.class;
			}
		}
		
		return classe;
	}

	/** Crée l'objet java qui correspond au type formel en exploitant le « mot » suviant du scanner.
	 * Exemple : si formel est int.class, le mot suivant doit être un entier et le résulat est l'entier correspondant.
	 * Ici, on peut se limiter aux types utlisés dans le projet : int, double et String.
	 */
	static Object decoderEffectif(Class<?> formel, Scanner in) {
		
		if(formel.equals(int.class)) {
			return in.nextInt();
		}else if(formel.equals(double.class)) {
			return Double.parseDouble(in.next());
		}else if(formel.equals(String.class)) {
			return in.next();
		}
		
		return null;
	}
	


	/** Définition de la signature, les paramètres formels, mais aussi les paramètres formels.  */
	static private class Signature {
		Class<?>[] formels;
		Object[] effectifs;

		public Signature(Class<?>[] formels, Object[] effectifs) {
			this.formels = formels;
			this.effectifs = effectifs;
		}
	}

	/** Analyser une signature pour retrouver les paramètres formels et les paramètres effectifs.
	 * Exemple « 3 double 0.0 String xyz int -5 » donne
	 *   - [double.class, String.class, int.class] pour les paramètres effectifs et
	 *   - [0.0, "xyz", -5] pour les paramètres formels.
	 */
	Signature analyserSignature(Scanner in) throws ClassNotFoundException {
		int nbParam = in.nextInt();
		
		if(nbParam == 0) {
			return new TraitementBuilder.Signature(new Class<?>[] {}, new Object[] {});
		}
		
		Class<?>[] classe = new Class<?>[nbParam];
		Object[] objet = new Object[nbParam];
		
		for(int i = 0; i < nbParam; i++) {
			classe[i] = analyserType(in.next());
			objet[i] = decoderEffectif(classe[i], in);
		}
		
		return new TraitementBuilder.Signature(classe, objet);
	}


	/** Analyser la création d'un objet.
	 * Exemple : « Normaliseur 2 double 0.0 double 100.0 » consiste à charger
	 * la classe Normaliseur, trouver le constructeur qui prend 2 double, et
	 * l'appeler en lui fournissant 0.0 et 100.0 comme paramètres effectifs.
	 */
	Object analyserCreation(Scanner in)
		throws ClassNotFoundException, InvocationTargetException,
						  IllegalAccessException, NoSuchMethodException,
						  InstantiationException
	{
		Signature signature  = this.analyserSignature(in);
		Class<?> classe = this.analyserType(this.getClass().getPackage().getName() + "." + in.next());
		
		return classe.getConstructor(signature.formels).newInstance(signature.effectifs);
	}


	/** Analyser un traitement.
	 * Exemples :
	 *   - « Somme 0 0 »
	 *   - « SupprimerPlusGrand 1 double 99.99 0 »
	 *   - « Somme 0 1 Max 0 0 »
	 *   - « Somme 0 2 Max 0 0 SupprimerPlusGrand 1 double 20.0 0 »
	 *   - « Somme 0 2 Max 0 0 SupprimerPlusGrand 1 double 20.0 1 Positions 0 0 »
	 * @param in le scanner à utiliser
	 * @param env l'environnement où enregistrer les nouveaux traitements
	 */
	Traitement analyserTraitement(Scanner in, Map<String, Traitement> env)
		throws ClassNotFoundException, InvocationTargetException,
						  IllegalAccessException, NoSuchMethodException,
						  InstantiationException
	{
		Traitement t = (Traitement)this.analyserCreation(in);
		
		int nxt = in.nextInt();
		for(int i = 0; i < nxt; i++) {
			t.ajouterSuivants((Traitement)this.analyserTraitement(in, env));
		}
		
		return t;
	}


	/** Analyser un traitement.
	 * @param in le scanner à utiliser
	 * @param env l'environnement où enregistrer les nouveaux traitements
	 */
	public Traitement traitement(Scanner in, Map<String, Traitement> env)
	{
		try {
			return analyserTraitement(in, env);
		} catch (Exception e) {
			throw new RuntimeException("Erreur sur l'analyse du traitement, "
					+ "voir la cause ci-dessous", e);
		}
	}

}
