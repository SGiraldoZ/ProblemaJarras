package modelo;

import java.util.LinkedList;

public interface Estado <E extends Estado>{
	public boolean isSolucion();
	
	public LinkedList<E> estadosPosibles();

	public boolean equals(E estado);
}
