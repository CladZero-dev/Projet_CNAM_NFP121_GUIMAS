package fr.n7.cnam.nfp121.pr01;

public class CycleException extends RuntimeException{
	
	public CycleException() {
		super("Traitement deja existant");
	}
	
}
