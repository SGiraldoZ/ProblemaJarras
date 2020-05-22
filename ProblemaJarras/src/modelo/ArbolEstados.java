package modelo;

public class ArbolEstados<E extends Estado<E>> extends ArbolB<E> {
	public ArbolEstados(NodoEstado<E> raiz) {
		super(raiz);
	}
	
	public ArbolEstados(E info) {
		super ((NodoB<E>)new NodoEstado(info));
	}
}
