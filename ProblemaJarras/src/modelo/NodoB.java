package modelo;

public class NodoB<E> {
	protected E llave;
	protected NodoB<E> hijoIzq;
	protected NodoB<E> hijoDer;
	protected NodoB<E> padre;

	public NodoB(E llave) {
		this.llave = llave;
		padre = null;
		hijoIzq = null;
		hijoDer = null;
	}

	public NodoB(E llave, NodoB<E> hijoIzq, NodoB<E> hijoDer, NodoB<E> padre) {
		super();
		this.llave = llave;
		this.hijoIzq = hijoIzq;
		this.hijoDer = hijoDer;
		this.padre = padre;
	}

	public E getLlave() {
		return llave;
	}

	public void setLlave(E llave) {
		this.llave = llave;
	}

	public NodoB<E> getHijoIzq() {
		return hijoIzq;
	}

	public void setHijoIzq(NodoB<E> hijoIzq) {
		if (hijoIzq != null) {
			hijoIzq.setPadre(this);
		}
		this.hijoIzq = hijoIzq;
	}

	public NodoB<E> getHijoDer() {
		return hijoDer;
	}

	public void setHijoDer(NodoB<E> hijoDer) {
		if (hijoDer != null) {
			hijoDer.setPadre(this);
		}
		this.hijoDer = hijoDer;
	}

	public NodoB<E> getPadre() {
		return padre;
	}

	public void setPadre(NodoB<E> padre) {
		this.padre = padre;
	}

	public int altura(NodoB<E> n) {
		if (n == null)
			return -1;
		int altder = (n.getHijoDer() == null ? 0 : 1 + altura(n.getHijoDer()));
		int altizq = (n.getHijoIzq() == null ? 0 : 1 + altura(n.getHijoIzq()));
		return Math.max(altder, altizq);
	}

	public int altura() {
		return altura(this);
	}

	public boolean yaExiste(E llave) {
		NodoB<E> aux = this;
		while (aux != null) {
			if (aux.getLlave().equals(llave)) return true;
			aux = aux.getPadre();
		}
		return false;
	}
	

	
	//EMPIEZAN MÉTODOS MAFIOLY SIN REVISAR
	public boolean nodoRepetido(E llave, NodoB<E> nodo)  {
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
	
	
	@Override
	public String toString() {
		String HI = (hijoIzq == null) ? "null" : hijoIzq.getLlave().toString();
		String HD = (hijoDer == null) ? "null" : hijoDer.getLlave().toString();
		return (llave).toString() + "(" + HI + "," + HD+ ")";
	}



}
