package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

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

	
	
	public static void jugar(String limA, String limB, String cantObj, boolean objA) {
		Juego j = new Juego(Integer.parseInt(limA),Integer.parseInt(limB),Integer.parseInt(cantObj), objA);
		j.arbolEstados.FillUntilSolve();
	}
	
	public static void jugar(int limA, int limB, int cantObj, boolean objA) {
		Juego j = new Juego((limA),(limB),(cantObj), objA);
		j.arbolEstados.FillUntilSolve();
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int limA, limB, cantObj;
		boolean objA;
		System.out.println("Tamaño jarra 1: ");
		limA = s.nextInt();
		
		System.out.println("Tamaño jarra 2: ");
		limB = s.nextInt();
		
		System.out.println("Cantidad objetivo: ");
		cantObj = s.nextInt();
		
		System.out.println("Jarra en la que debe haber "+cantObj+"(1 o 2):");
		objA = s.nextInt()==1;
		
		jugar(limA, limB, cantObj, objA);
	}

	/**
	 * @return the arbolEstados
	 */
	public ArbolEstados<EstadoJuego> getArbolEstados() {
		return arbolEstados;
	}
	
}
