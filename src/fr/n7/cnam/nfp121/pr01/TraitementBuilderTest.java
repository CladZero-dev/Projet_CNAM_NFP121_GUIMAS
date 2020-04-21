package fr.n7.cnam.nfp121.pr01;

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.Scanner;

/**
  * TraitementBuilderTest 
  *
  * @author	Xavier Crégut <Prenom.Nom@enseeiht.fr>
  */

public class TraitementBuilderTest {

	static final double EPSILON = 1e-8;
	static Field suivantsField;

	@BeforeClass
	public static void setUpClasse() throws Exception {
		suivantsField = Traitement.class.getDeclaredField("suivants");
		suivantsField.setAccessible(true);
	}

	@SuppressWarnings("unchecked")
	public static List<Traitement> suivants(Traitement t) throws IllegalAccessException {
		return (List<Traitement>) suivantsField.get(t);
	}


	/** Un traitement seul, sans paramètres. */
	@Test
	public void testerSimple() throws Exception {
		Traitement resultat = new TraitementBuilder().traitement(new Scanner("Somme 0 0"), null);
		assertEquals(Somme.class, resultat.getClass());
		assertEquals(0, suivants(resultat).size());
	}

	/** Un traitement avec un suisant. */
	@Test
	public void testerUnSuivant() throws Exception {
		Traitement resultat = new TraitementBuilder().traitement(new Scanner("Somme 0 1 Max 0 0"), null);
		assertEquals(Somme.class, resultat.getClass());
		assertEquals(1, suivants(resultat).size());
		Traitement s = suivants(resultat).get(0);
		assertEquals(Max.class, s.getClass());
		assertEquals(0, suivants(s).size());
	}

	/** Un traitement avec deux suivants. */
	@Test
	public void testerDeuxSuivants() throws Exception {
		Traitement resultat = new TraitementBuilder().traitement(new Scanner("Somme 0 2 Max 0 0 Positions 0 0"), null);
		assertEquals(Somme.class, resultat.getClass());
		assertEquals(2, suivants(resultat).size());

		Traitement s1 = suivants(resultat).get(0);
		assertEquals(Max.class, s1.getClass());
		assertEquals(0, suivants(s1).size());

		Traitement s2 = suivants(resultat).get(1);
		assertEquals(Positions.class, s2.getClass());
		assertEquals(0, suivants(s2).size());
	}


	/** Cas d'un traitement avec un paramètre. */
	@Test
	public void testerParametre1() throws Exception {
		Traitement resultat = new TraitementBuilder().traitement(new Scanner("SupprimerPlusPetit 1 double 1.0 0"), null);
		assertEquals(SupprimerPlusPetit.class, resultat.getClass());
		assertEquals(0, suivants(resultat).size());
		Field[] fields = resultat.getClass().getDeclaredFields();
		boolean ok = false;
		for (Field f : fields) {
			if (f.getType().equals(double.class)) {
				f.setAccessible(true);
				if (Math.abs(f.getDouble(resultat) - 1.0) < EPSILON) {
					ok = true;
				}
			}
		}
		assertTrue("Paramètre de SupprimerPlusPetit mal initialisé ?", ok);
	}

}
