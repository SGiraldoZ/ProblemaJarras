package modelo;

public class ArbolEstados<E extends Estado<E>> extends ArbolB<E> {
	public ArbolEstados(NodoB<E> raiz) {
		super(raiz);
	}
}
