package modelo;

public class NodoEstado<E extends Estado<E>> extends NodoB<E>{
	public NodoEstado(E info) {
		super(info);
	}
}
