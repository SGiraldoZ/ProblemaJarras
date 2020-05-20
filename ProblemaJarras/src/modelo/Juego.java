package modelo;

public class Juego {
	private Jarra jarraA;
	private Jarra jarraB;
	private int cantObj;
	private boolean ObjA; 
	
	public Juego(int limJarraA, int limJarraB, int cantObj, boolean ObjA) {
		jarraA = new Jarra(limJarraA);
		jarraB = new Jarra(limJarraB);
		this.cantObj = cantObj;
		this.ObjA = ObjA;
	}
	
	public EstadoJuego estadoInicial() {
		return new EstadoJuego(0, 0,this);
	}
	
	public static void main(String[] args) {
		
	}

	public boolean estadoIsSolucion(EstadoJuego ej) {
		return false;
	}
	
	/**
	 * @return the jarraA
	 */
	public Jarra getJarraA() {
		return jarraA;
	}

	/**
	 * @return the jarraB
	 */
	public Jarra getJarraB() {
		return jarraB;
	}

	/**
	 * @return the cantObj
	 */
	public int getCantObj() {
		return cantObj;
	}

	/**
	 * @return the objA
	 */
	public boolean isObjA() {
		return ObjA;
	}
	
}
