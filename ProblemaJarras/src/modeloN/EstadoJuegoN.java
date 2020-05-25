package modeloN;

import java.util.Arrays;
import java.util.LinkedList;

import modelo.Estado;
import modelo.Jarra;

public class EstadoJuegoN implements Estado<EstadoJuegoN> {
	int[] levels;
	JuegoN juego;

	public EstadoJuegoN(int[] levels, JuegoN juego) {
		this.levels = levels;
		this.juego = juego;
	}

	public EstadoJuegoN pasarJarraJarra(int origen, int destino) {
		int cant = juego.getJarras()[destino].cantMax(levels[destino]);
			cant = Math.min(this.levels[origen], cant);
			if (cant == 0) return null;
			int[] aux = Arrays.copyOf(this.levels, levels.length);
			aux[origen] -= cant;
			aux[destino] += cant;
			return new EstadoJuegoN(aux, this.juego);
	}

	public EstadoJuegoN llenarJarra(int jarra) {
		int cant = this.juego.getJarras()[jarra].cantMax(levels[jarra]);
		if (cant == 0) return null;
		int[] aux = Arrays.copyOf(levels, levels.length);
		aux[jarra] += cant;
		return new EstadoJuegoN(aux, this.juego);

	}

	public EstadoJuegoN vaciarJarra(int jarra) {
		int cant = this.juego.getJarras()[jarra].cantMax(this.levels[jarra]);
		
			if (cant == this.juego.getJarras()[jarra].getLim()) return null; 
					
			int[] aux = Arrays.copyOf(levels, levels.length);
			aux[jarra] = 0;
					
			return new EstadoJuegoN(aux, this.juego);
		
	}

	@Override
	public LinkedList<EstadoJuegoN> estadosPosibles() {
		LinkedList<EstadoJuegoN> lista = new LinkedList<EstadoJuegoN>();
		if (this.isSolucion())
			return null;
		EstadoJuegoN temp;
		for (int i = 0; i < this.levels.length; i++) {
			temp = this.vaciarJarra(i);
			if (temp!= null)lista.add(temp);
			temp = this.llenarJarra(i);
			if (temp!= null)lista.add(temp);
			if (this.levels[i] > 0) {
				for (int j = 0; j < this.levels.length; j++) {
					if (j != i) {
						temp = this.pasarJarraJarra(i, j);
						if (temp != null) lista.add(temp);
					}
				}
			}
		}
		return lista;
	}

	@Override
	public boolean isSolucion() {
		return this.juego.estadoIsSolucion(this);
	}

	@Override
	public boolean equals(EstadoJuegoN ej) {
		boolean levelsEq = true;
		int i = 0;
		if (this.levels.length != ej.getLevels().length) return false;
		while (levelsEq && i < ej.getLevels().length) {
			if (this.levels[i] != ej.getLevels()[i]) levelsEq = false;
			i ++;
		}
		
		return (this.juego.equals(ej.getJuego()) && levelsEq);
	}

	/**
	 * @RETURN THE LEVELA
	 */
	public int[] getLevels() {
		return levels;
	}

	/**
	 * @return the juego
	 */
	public JuegoN getJuego() {
		return juego;
	}

	@Override
	public String toString() {
		String j ="";
		Jarra[] jarras = this.juego.getJarras();
		for(int i = 0;i<jarras.length;i++) {
			j = j + jarras[i].toString()+"-"+this.getLevels()[i]+";";
		}
		return j;
	}

}
