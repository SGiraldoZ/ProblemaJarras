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
	
	
	//METODOS SEBAS
	public void fill() {
		this.fill((NodoEstado<E>)this.raiz);
	}
	
	private void fill(NodoEstado<E> raiz) {
		if (raiz == null) return;
		raiz.estadosSiguientes1();
		fill(raiz.getHijoIzq());
		fill(raiz.getHijoDer());
		
	}
	
	
	//FIN METODOS SEBAS
	
	//METODOS MERY
	public void FillUntilSolve1() {
		this.FillUntilSolve1((NodoEstado<E>)this.raiz);
	}
	
	public void FillUntilSolve1(NodoEstado<E> actual) {

		if (actual.getLlave().isSolucion() == true) {
			FillUntilSolved1(actual);
		} else {

			LinkedList<E> posiblesEstados = new LinkedList<E>();
			
			
			actual.estadosSiguientes1();
			
			if (actual.getHijoIzq() != null) {
				FillUntilSolve1((NodoEstado<E>) actual.getHijoIzq());
			} else if (actual.getHijoIzq() == null && actual.getHijoDer() != null) {
				FillUntilSolve1((NodoEstado<E>) actual.getHijoDer());
			} else { 															// Se vuelve a llamar el m�todo pasando el hijo de la derecha del papa
				FillUntilSolve1((NodoEstado<E>) actual.getPadre().getHijoDer());
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
	
	public void FillUntilSolve() {
		this.FillUntilSolve((NodoEstado<E>)this.raiz);
	}
	
	public void FillUntilSolve(NodoEstado<E> actual) {

		if (actual.getLlave().isSolucion() == true) {
			FillUntilSolved1(actual);
		} else {

			LinkedList<E> posiblesEstados = new LinkedList<E>();
			
			
			actual.estadosSiguientes1();
			
			if (actual.getHijoIzq() != null) {
				FillUntilSolve((NodoEstado<E>) actual.getHijoIzq());
			} else if (actual.getHijoIzq() == null && actual.getHijoDer() != null) {
				FillUntilSolve((NodoEstado<E>) actual.getHijoDer());
			} else { 															// Se vuelve a llamar el m�todo pasando el hijo de la derecha del papa
				FillUntilSolve((NodoEstado<E>) actual.getPadre().getHijoDer());
			}

		}
	}
	
	public void FillUntilSolved1(NodoEstado<E> solucion){     // consigue el camino hacia la solucion
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
	
	//METODOS MESA
	public void mensaje(LinkedList<NodoEstado<E>> s) {
		int t = s.size();
		System.out.println("Un camino a la solucion es: ");
		for (int i = 0; i < t; i++) {
			System.out.print(s.pollFirst().getLlave() + " ");
		}
		System.out.println("/nfin de la solución.");
	}

	public void fillUntilFull1(NodoEstado<E> n, LinkedList<NodoEstado<E>> solucion) {
		if (!solucion.peekLast().llave.isSolucion()) {
			n.estadosSiguientes1();
			NodoEstado<E> aux = n.getHijoIzq();
			if (aux != null) {
				solucion.add((NodoEstado<E>) n.getHijoIzq());
				fillUntilFull1(solucion.getLast(), solucion);
				if (!solucion.isEmpty())solucion.removeLast();
				if (!solucion.isEmpty() && solucion.getLast().getHijoDer() != null) {
					solucion.add((NodoEstado<E>) n.getHijoDer());
					fillUntilFull1((NodoEstado<E>) solucion.getLast().getHijoDer(), solucion);
					if (!solucion.isEmpty())solucion.removeLast();
				}
			}
		} else {
			mensaje(solucion);
		}
	}
	
	public void fillUntilFull1() {
		LinkedList<NodoEstado<E>> solu= new LinkedList<NodoEstado<E>>();
		solu.addFirst((NodoEstado<E>) raiz);
		fillUntilFull1((NodoEstado<E>) raiz,solu); 	
	}


	public void fillUntilFull(NodoEstado<E> n, LinkedList<NodoEstado<E>> solucion) {
		if (!solucion.peekLast().llave.isSolucion()) {
			n.estadosSiguientes();
			NodoEstado<E> aux = n.getHijoIzq();
			if (aux != null) {
				solucion.add((NodoEstado<E>) n.getHijoIzq());
				fillUntilFull(solucion.getLast(), solucion);
				if (!solucion.isEmpty())solucion.removeLast();
				if (!solucion.isEmpty() && solucion.getLast().getHijoDer() != null) {
					solucion.add((NodoEstado<E>) n.getHijoDer());
					fillUntilFull((NodoEstado<E>) solucion.getLast().getHijoDer(), solucion);
					if (!solucion.isEmpty())solucion.removeLast();
				}
			}
		} else {
			mensaje(solucion);
		}
	}
	
	public void fillUntilFull() {
		LinkedList<NodoEstado<E>> solu= new LinkedList<NodoEstado<E>>();
		solu.addFirst((NodoEstado<E>) raiz);
		fillUntilFull((NodoEstado<E>) raiz,solu); 	
	}
	
	//FIN METODOS MESA
	
	//METODOS MAFIOLY
	public boolean buscarRepetido(Estado llave){
		return buscarRepetido(llave, (NodoEstado)raiz);
	}

	public static boolean buscarRepetido(Estado llave, NodoB r) {
		boolean a=false,b=false;
		if (r == null) return false;
		if (((Estado)r.getLlave()).equals(llave)) {
			return true;
		}

			a= buscarRepetido(llave, r.getHijoIzq());
		
			b= buscarRepetido(llave, r.getHijoDer());
		return (a||b);
	}
	//FIN METODOS MAFIOLY
	
	
	public static void main(String[] args) {
		Juego j = new Juego(3,2,1,true);
		j.getArbolEstados().fill();
		j.getArbolEstados().preorden();
	}
}
