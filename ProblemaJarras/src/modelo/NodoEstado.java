package modelo;

import java.util.Iterator;
import java.util.LinkedList;




public class NodoEstado<E extends Estado<E>> extends NodoB<E>{
   
    public NodoEstado(E info) {
        super(info);
    }
   
    public void estadosSiguientes(){
        LinkedList<E> estados = this.getLlave().estadosPosibles();
        Iterator<E> iter = estados.iterator();
        while(iter.hasNext()) {
            E aux = iter.next();
            if(this.yaExiste(aux)) {
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
		NodoB<E> aux = this;
		while (aux != null) {
			if (((E)aux.getLlave()).equals(llave)) return true;
			aux = aux.getPadre();
		}
		return false;
	}
    
    public static void main(String[] args){
    	NodoB<Integer> n1 = new NodoB<Integer>(1);
    	Juego j =new Juego(5,3,2,false);
    	NodoEstado<EstadoJuego> ne = new NodoEstado<EstadoJuego>(new EstadoJuego(0,0, j));
    	
    	ne.estadosSiguientes();
    	ne.getHijoIzq().estadosSiguientes();
    	
    	ArbolEstados<EstadoJuego> a = new ArbolEstados(ne);
    	a.inorden();
    	System.out.println("\n");
    	a.preorden(a.getRaiz());
  
    	
    	
    	
    }
}
 