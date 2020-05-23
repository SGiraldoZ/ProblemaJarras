package modelo;

class ExcepcionJarra extends Exception{
	public ExcepcionJarra(String s) {
		super(s);
	}
}

public class Juego {
	private Jarra jarraA;
	private Jarra jarraB;
	private int cantObj;//La cantidad que queremos al final en la jarra objetivo
	private boolean ObjA; //Un booleano que marca si la jarra objetivo es la A
	private ArbolEstados<EstadoJuego> arbolEstados;	
	
	public Juego(int limJarraA, int limJarraB, int cantObj, boolean ObjA) {
		jarraA = new Jarra(limJarraA);
		jarraB = new Jarra(limJarraB);
		this.cantObj = cantObj;
		this.ObjA = ObjA;
		this.arbolEstados = new ArbolEstados<EstadoJuego>(this.estadoInicial());
	}
	
	public EstadoJuego estadoInicial() {
		return new EstadoJuego(0, 0,this);
	}
	
	

	public boolean estadoIsSolucion(EstadoJuego ej){
		
		int cant = (ObjA)? ej.getLevelA(): ej.getLevelB();
		return cant == cantObj;
		
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
