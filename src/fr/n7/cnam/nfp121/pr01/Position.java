package fr.n7.cnam.nfp121.pr01;

/** Définir une position.  */
public class Position {
	public int x;
	public int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		// System.out.println("...appel à Position(" + x + "," + y + ")" + " --> " + this);
	}

	@Override public String toString() {
		return super.toString() + "(" + x + "," + y + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		Position posObj = (Position)obj;
		return (this.x == posObj.x && this.y == posObj.y);
	}
}
