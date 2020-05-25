package modelo;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class Juego {
	public static class ExcepcionParametros extends Exception {
		public ExcepcionParametros(String s) {
			super(s);
		}
	}
	
	
	private Jarra jarraA;
	private Jarra jarraB;
	private int cantObj;// La cantidad que queremos al final en la jarra objetivo
	private boolean ObjA; // Un booleano que marca si la jarra objetivo es la A
	private ArbolEstados<EstadoJuego> arbolEstados;

	public Juego(int limJarraA, int limJarraB, int cantObj, boolean ObjA) {
		jarraA = new Jarra(limJarraA);
		jarraB = new Jarra(limJarraB);
		this.cantObj = cantObj;
		this.ObjA = ObjA;
		this.arbolEstados = new ArbolEstados<EstadoJuego>(this.estadoInicial());
	}

	public EstadoJuego estadoInicial() {
		return new EstadoJuego(0, 0, this);
	}

	public boolean estadoIsSolucion(EstadoJuego ej) {

		int cant = (ObjA) ? ej.getLevelA() : ej.getLevelB();
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

	public void solve() {
		this.arbolEstados.FillUntilSolve1();
	}

	public void fill() {
		this.arbolEstados.fillUntilFull();

	}

	public void printTree() {
		BinaryTreePrinter<EstadoJuego> btp = new BinaryTreePrinter(this.arbolEstados);
		String s = btp.traversePreOrder(this.arbolEstados.getRaiz());
		btp.print(System.out);
	}

	public void printPreO() {
		this.arbolEstados.preorden();
	}

	public void printIno() {
		this.arbolEstados.inorden();
	}

	public static void jugar(String limA, String limB, String cantObj, boolean objA) {
		Juego j = new Juego(Integer.parseInt(limA), Integer.parseInt(limB), Integer.parseInt(cantObj), objA);
		j.arbolEstados.FillUntilSolve1();
	}

	public static void jugar(int limA, int limB, int cantObj, boolean objA) {
		Juego j = new Juego((limA), (limB), (cantObj), objA);
		j.arbolEstados.FillUntilSolve1();
		BinaryTreePrinter<EstadoJuego> btp = new BinaryTreePrinter(j.getArbolEstados());
		btp.print(System.out);
	}

	public static Juego iniciar() throws ExcepcionParametros {
		Scanner s = new Scanner(System.in);
		int limA, limB, cantObj;
		boolean objA;
		System.out.println("Tamaño jarra 1: ");
		limA = s.nextInt();

		System.out.println("Tamaño jarra 2: ");
		limB = s.nextInt();

		System.out.println("Cantidad objetivo: ");
		cantObj = s.nextInt();

		System.out.println("Jarra en la que debe haber " + cantObj + "(1 o 2):");
		objA = s.nextInt() == 1;
		if ((objA && cantObj > limA) || (!objA && cantObj > limB)) {
			throw new ExcepcionParametros(
					"No se puede conseguir una cantidad mayor al límite de la jarra como objetivo");
		}
		if (limA == limB && cantObj != limA) {
			throw new ExcepcionParametros(
					"Es imposible conseguir la cantidad objetivo si los dos tamaños de las jarras son distintos");
		}

		return new Juego(limA, limB, cantObj, objA);
	}

	public static JPanel paneFullTree(int limA, int limB, int cantObj, boolean objA) throws ExcepcionParametros{
		if ((objA && cantObj > limA) || (!objA && cantObj > limB)) {
			throw new ExcepcionParametros(
					"No se puede conseguir una cantidad mayor al límite de la jarra como objetivo");
		}
		if (limA == limB && cantObj != limA) {
			throw new ExcepcionParametros(
					"Es imposible conseguir la cantidad objetivo si los dos tamaños de las jarras son distintos");
		}
		if (limA%2 == 0 && limB%2 == 0 && cantObj%2==1) {
			throw new ExcepcionParametros(
					"Es imposible conseguir una cantidad impar si los dos tamaños de las jarras son pares");
		}
		Juego j = new Juego(limA, limB, cantObj, objA);
		j.fill();
		return j.getArbolEstados().panelArbol();
	}
	
	public static void main(String[] args) {
//		try {
//			Juego j = iniciar();
		Juego j = new Juego(5, 3, 4, true);
//			j.arbolEstados.FillUntilSolve();
			j.arbolEstados.fillUntilFull1();
//			j.fill();
		JFrame v = new JFrame();
		v.add(j.getArbolEstados().panelArbol(), BorderLayout.CENTER);
		v.setSize(600, 600);
		v.setVisible(true);
//		}catch (ExcepcionParametros e) {
//			System.out.println(e.getMessage());
//		}

	}

	/**
	 * @return the arbolEstados
	 */
	public ArbolEstados<EstadoJuego> getArbolEstados() {
		return arbolEstados;
	}

}
