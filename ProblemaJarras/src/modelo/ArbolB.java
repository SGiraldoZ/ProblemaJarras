package modelo;

import java.util.ArrayList;
import java.util.Iterator;

class ExcepcionNodo extends Exception{
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
	
	public void preorden(){
		preorden(raiz);
		System.out.println();
	}
	
	public void preorden(NodoB<E> r){
		if (r!=null){
			System.out.print(r.getLlave()+" ");
			preorden(r.getHijoIzq());
			preorden(r.getHijoDer());
		}	
	}
	
	
	public ArrayList<E> preordenList(){
		ArrayList<E> list=new ArrayList<E>();
		preordenList(raiz, list);
		return list;
	}
	
	public void preordenList(NodoB<E> r, ArrayList<E> list){
		if (r!=null){
			list.add(r.getLlave());
			preordenList(r.getHijoIzq(),list);
			preordenList(r.getHijoDer(),list);
		}	
	}
	
	
	public void inorden(){
		inorden(raiz);
	}
	
	public void inorden(NodoB<E> r){
		if (r!=null){
			inorden(r.getHijoIzq());
			//System.out.print(r.getLlave()+ " ");
			System.out.print(r+ " ");
			inorden(r.getHijoDer());
		}	
	}
	

	
	public ArrayList<E> inordenA(){
		ArrayList<E> a=new ArrayList<E>();
		return inordenA(raiz, a);
	}
	public ArrayList<E> inordenA(NodoB<E> r, ArrayList<E> a){
		if (r!=null){
			inordenA(r.getHijoIzq(), a);
			a.add(r.getLlave());
			inordenA(r.getHijoDer(),a);
			return a;
		}
		return a;
	}
	
	public void postorden(){
		postorden(raiz);
	}
	
	public void postorden(NodoB<E> r){
		if (r!=null){
			postorden(r.getHijoIzq());
			postorden(r.getHijoDer());
			System.out.print(r.getLlave()+ " ");
		}	
	}
	
	
	//EMPIEZAN MÉTODOS MAFIOLY SIN REVISAR
	public boolean nodoRepetido(E llave, NodoB<E> nodo) throws ExcepcionNodo  {
		boolean a= false, b = false;
		
		NodoB<E> nodopadre = nodo.getPadre();
		if (nodopadre!=null) {
			if (nodopadre.getLlave().equals(llave)) {
				return true;
			}
			if (esHijoIzquierdo(nodopadre,nodo)) {
				if (nodopadre.getHijoDer()!=null) {
					a = buscaEnNodo(llave, nodopadre.getHijoDer());
				} 
			} else  if (nodopadre.getHijoIzq()!=null){
				b = buscaEnNodo(llave,nodopadre.getHijoIzq());

			}
			if (a||b) {
				return true;
			} else {
				nodoRepetido(llave,nodo.getPadre());
			}

		} return false;
	}

	private boolean buscaEnNodo(E llave, NodoB<E> nodo) {
		boolean a = false,b = false;
		if (nodo.getLlave()==llave) {
			return true;
		} else if (tieneHijos(nodo)){
			if (nodo.getHijoIzq()!=null) {
				a = buscaEnNodo( llave, nodo.getHijoIzq());
			}
			if (nodo.getHijoDer()!=null) {
				b = buscaEnNodo( llave, nodo.getHijoDer());
			}
		}
		return (a||b);
	}
	
	public boolean tieneHijos(NodoB<E> nodo) {
		if (nodo.getHijoDer()!=null || nodo.getHijoIzq()!= null) {
			return true;
		} else return false;
	}
	
	public boolean esHijoIzquierdo(NodoB<E> padre, NodoB<E> Hijo) {
		if (padre.getHijoIzq().getLlave().equals(Hijo.getLlave())) {
			return true;
		} else { return false;
		}
	}
	//TERMINAN MÉTODOS MAFIOLY
	
	public ArbolB(NodoB<E> raiz) {
		super();
		this.raiz = raiz;
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
		ArbolB<Integer> a= new ArbolB<Integer>(n1);
		System.out.println("Inorden:");
		a.inorden();
		System.out.println();
		System.out.println("Postorden:");

		a.postorden();
		
		
	}

}
