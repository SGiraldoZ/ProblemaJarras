package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

class ExcepcionNodo extends Exception {
	public ExcepcionNodo(String s) {
		super(s);
	}
}

public class ArbolB<E> {

	protected NodoB<E> raiz;

	public NodoB<E> getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoB<E> raiz) {
		this.raiz = raiz;
	}

	public void preorden() {
		preorden(raiz);
		System.out.println();
	}

	public void preorden(NodoB<E> r) {
		if (r != null) {
			System.out.print(r.getLlave() + " ");
			preorden(r.getHijoIzq());
			preorden(r.getHijoDer());
		}
	}

	public ArrayList<E> preordenList() {
		ArrayList<E> list = new ArrayList<E>();
		preordenList(raiz, list);
		return list;
	}

	public void preordenList(NodoB<E> r, ArrayList<E> list) {
		if (r != null) {
			list.add(r.getLlave());
			preordenList(r.getHijoIzq(), list);
			preordenList(r.getHijoDer(), list);
		}
	}

	public void inorden() {
		inorden(raiz);
	}

	public void inorden(NodoB<E> r) {
		if (r != null) {
			inorden(r.getHijoIzq());
			// System.out.print(r.getLlave()+ " ");
			System.out.print(r + " ");
			inorden(r.getHijoDer());
		}
	}

	public ArrayList<E> inordenA() {
		ArrayList<E> a = new ArrayList<E>();
		return inordenA(raiz, a);
	}

	public ArrayList<E> inordenA(NodoB<E> r, ArrayList<E> a) {
		if (r != null) {
			inordenA(r.getHijoIzq(), a);
			a.add(r.getLlave());
			inordenA(r.getHijoDer(), a);
			return a;
		}
		return a;
	}

	public NodoB<E> buscar(E llave){
		return this.buscar(llave, this.raiz);
	}
	
	public NodoB<E> buscar(E llave, NodoB<E> raiz){
		if (raiz == null) return null;
		if (raiz.getLlave().equals(llave)) return raiz;
		NodoB<E> busI = buscar(llave, raiz.getHijoIzq());
		return (busI!= null)? busI: buscar(llave, raiz.getHijoDer());
	}

	public boolean contains(E llave) {
		return this.buscar(llave) != null;
	}
	
	public void postorden() {
		postorden(raiz);
	}

	public void postorden(NodoB<E> r) {
		if (r != null) {
			postorden(r.getHijoIzq());
			postorden(r.getHijoDer());
			System.out.print(r.getLlave() + " ");
		}
	}

	public ArbolB(NodoB<E> raiz) {
		super();
		this.raiz = raiz;
	}

	public JPanel panelArbol() {
		return new ArbolExpresionGrafico(this);
	}
	
	/**
	 * @param args
	 */
 	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NodoB<Integer> n1 = new NodoB<Integer>(10);
		NodoB<Integer> n2 = new NodoB<Integer>(7);
		NodoB<Integer> n3 = new NodoB<Integer>(15);
		NodoB<Integer> n4 = new NodoB<Integer>(5);
		NodoB<Integer> n5 = new NodoB<Integer>(9);
		NodoB<Integer> n6 = new NodoB<Integer>(12);
		n3.setHijoIzq(n6);
		n2.setHijoIzq(n4);
		n2.setHijoDer(n5);
		n1.setHijoIzq(n2);
		n1.setHijoDer(n3);
		ArbolB<Integer> a = new ArbolB<Integer>(n1);
		System.out.println("Inorden:");
		a.inorden();
		System.out.println();
		System.out.println("Postorden:");

		a.postorden();

	}

}
