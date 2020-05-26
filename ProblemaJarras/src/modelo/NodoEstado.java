package modelo;

import java.util.Iterator;
import java.util.LinkedList;




public class NodoEstado<E extends Estado<E>> extends NodoB<E>{
   
    public NodoEstado(E info) {
        super(info);
    }
   
    public void estadosSiguientes1(){
        LinkedList<E> estados = this.getLlave().estadosPosibles();
        if (estados == null) return;
        Iterator<E> iter = estados.iterator();
        while(iter.hasNext()) {
            E aux = iter.next();
//            if(this.yaExisteEstados(aux)) {
            if(this.yaExiste(aux)) {
                iter.remove();
            }
        }
        this.setHijoIzq(separar(estados));
    }
   
    public void estadosSiguientes() {
    	NodoB<E> raiz = this;
    	while(raiz.getPadre()!= null) raiz = (NodoEstado<E>)raiz.getPadre();
    	LinkedList<E> estados = this.getLlave().estadosPosibles();
        if (estados == null || estados.isEmpty()) return;
        Iterator<E> iter = estados.iterator();
        while(iter.hasNext()) {
            E aux = iter.next();
            if(ArbolEstados.buscarRepetido((Estado)aux, raiz)) {
                iter.remove();
            }
        }
        this.setHijoIzq(separar(estados));
    }
    
    public NodoEstado<E> separar(LinkedList<E> a, NodoEstado<E> hijo){
        if(!a.isEmpty()) {
            hijo.setHijoDer(new NodoEstado<E>(a.poll()));
            separar(a,(NodoEstado<E>)hijo.getHijoDer());
        }
        return hijo;
    }
   
    public NodoEstado<E> separar(LinkedList<E> a) {
        if(a.isEmpty()) {
            return null;
        }else {
            NodoEstado<E> hijo = new NodoEstado<E>(a.poll());
            return separar(a, hijo);
        }
    }
    
    @Override
    public NodoEstado<E> getHijoIzq(){
    	return (NodoEstado<E>)this.hijoIzq;
    }
    
    @Override
    public NodoEstado<E> getHijoDer(){
    	return (NodoEstado<E>)this.hijoDer;
    }

    @Override
    public boolean yaExiste(E llave) {
    	NodoEstado<E> aux = (NodoEstado<E>)this;
    	while (aux!=null) {
    		
    		if (((E)llave).equals(((E)aux.getLlave()))) {
				return true;
			}
    		aux=(NodoEstado<E>)aux.getPadre();
		}
    	return false;
	}
    
    public boolean yaExisteEstados(E llave) {
    	return yaExisteEstados(this, llave);
    }
    
    
    //Revisa si el estado ya existe, ignorando los "Hermanos" que vienen del mismo estado
    private boolean yaExisteEstados(NodoEstado<E> nodo, E llave) {
    	if (nodo == null) return false;
    	if (this.getPadre() == null) {
    		return ((Estado<E>)nodo.getLlave()).equals(llave);
    	}
    	while(nodo.getPadre()!= null && nodo.getPadre().getHijoDer() != null && nodo.getPadre().getHijoDer().equals(nodo)) {
    		nodo = (NodoEstado<E>)nodo.getPadre();
    	}
    	if (((Estado<E>)nodo.getLlave()).equals(llave)) {
    		return true;
    	}
    	return yaExisteEstados(nodo.getPadre(), llave);
    }
    
    @Override
    public NodoEstado<E> getPadre(){
    	return (NodoEstado<E>)super.getPadre();
    }

    
    public static void main(String[] args){
    	NodoB<Integer> n1 = new NodoB<Integer>(1);
    	Juego j =new Juego(4,3,2,false);
    	NodoEstado<EstadoJuego> ne = new NodoEstado<EstadoJuego>(new EstadoJuego(0,0, j));
    	
//    	ne.estadosSiguientes();
//    	ne.getHijoIzq().estadosSiguientes();
    	
    	ArbolEstados<EstadoJuego> a = new ArbolEstados(ne);
    	a.FillUntilSolve1();
    	a.inorden();
    	System.out.println("\n");
    	a.preorden(a.getRaiz());
    	
    	
  
//    	System.out.println((new EstadoJuego(0,2,j).isSolucion()));
    	
    	
    	
    }
}
 