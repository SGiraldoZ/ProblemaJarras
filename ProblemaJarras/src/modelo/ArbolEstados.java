package modelo;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class ArbolEstados<E extends Estado<E>> extends ArbolB<E> {
	public ArbolEstados(NodoEstado<E> raiz) {
		super(raiz);
	}
	
	public ArbolEstados(E info) {
		super ((NodoB<E>)new NodoEstado(info));
	}
	
	//METODOS MERY
	public void FillUntilSolve() {
		this.FillUntilSolve((NodoEstado<E>)this.raiz);
	}
	
	public void FillUntilSolve(NodoEstado<E> actual) {

		if (actual.getLlave().isSolucion() == true) {
			FillUntilSolved(actual);
		} else {

			LinkedList<E> posiblesEstados = new LinkedList<E>();
			
			
			actual.estadosSiguientes();
			
			if (actual.getHijoIzq() != null) {
				FillUntilSolve((NodoEstado<E>) actual.getHijoIzq());
			} else if (actual.getHijoIzq() == null && actual.getHijoDer() != null) {
				FillUntilSolve((NodoEstado<E>) actual.getHijoDer());
			} else { 															// Se vuelve a llamar el m�todo pasando el hijo de la derecha del papa
				FillUntilSolve((NodoEstado<E>) actual.getPadre().getHijoDer());
			}

		}
	}
	
	public void FillUntilSolved(NodoEstado<E> solucion){     // consigue el camino hacia la solucion
		Stack<E> caminoInvertido = new Stack <E>();
		while(solucion!= null) {
			caminoInvertido.add(solucion.getLlave());
			solucion = (NodoEstado<E>)solucion.getPadre();
		}
		imprimirCamino(caminoInvertido);
	}
	
	public void imprimirCamino(Stack<E> s) { 			//Imprime un stack
		System.out.print("[");
		while (!s.isEmpty()) {
			System.out.println(s.pop() + "");
		}
		System.out.println("]");
		System.out.println();
	}
	//FIN METODOS MERY
}
