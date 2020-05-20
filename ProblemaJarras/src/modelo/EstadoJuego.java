package modelo;

import java.util.LinkedList;

public class EstadoJuego implements Estado<EstadoJuego>{
	int levelA;
	int levelB;
	Juego juego;
	
	public EstadoJuego(int levelA, int levelB, Juego juego) {
		this.levelA = levelA;
		this.levelB = levelB;
		this.juego = juego;
	}
	
	public EstadoJuego pasarJarraJarra(boolean AtoB) {
		int cant;
		if (AtoB) {
			cant = this.juego.getJarraB().cantMax(levelB);
			cant = Math.min(levelA, cant);
			return (cant==0)? null: new EstadoJuego(levelA-cant,levelB+cant,this.juego);
		}else {
			cant = this.juego.getJarraA().cantMax(levelA);
			cant = Math.min(levelB, cant);
			return (cant==0)? null: new EstadoJuego(levelA+cant,levelB-cant,this.juego);
		}
	}

	public EstadoJuego llenarJarra(boolean A) {
		int cant;
		if (A) {
			cant = this.juego.getJarraA().cantMax(levelA);
			return (cant==0)? null: new EstadoJuego(levelA + cant, levelB, this.juego);
		} else {
			cant = this.juego.getJarraB().cantMax(levelB);
			return (cant==0)? null: new EstadoJuego(levelA , cant+ levelB, this.juego);
		}
	}
	
	public EstadoJuego vaciarJarra(boolean A) {
        int cant;
        if(A) {
            cant = this.juego.getJarraA().cantMax(levelA);
            return (cant == this.juego.getJarraA().getLim())?null: new EstadoJuego(0,levelB,this.juego);
        }else {
            cant = this.juego.getJarraB().cantMax(levelB);
            return (cant == this.juego.getJarraB().getLim())?null: new EstadoJuego(levelA,0,this.juego);
        }
    }
	
	@Override
	public LinkedList<EstadoJuego> estadosPosibles() {
        LinkedList<EstadoJuego> lista = new LinkedList<EstadoJuego>();
        EstadoJuego temp = llenarJarra(true);
        if(temp!=null) {
            lista.add(temp);
        }
        temp = vaciarJarra(true);
        if(temp!=null) {
            lista.add(temp);
        }
        temp = pasarJarraJarra(true);
        if(temp!=null) {
            lista.add(temp);
        }
        temp = llenarJarra(false);
        if(temp!=null) {
            lista.add(temp);
        }
        temp = vaciarJarra(false);
        if(temp!=null) {
            lista.add(temp);
        }
        temp = pasarJarraJarra(false);
        if(temp!=null) {
            lista.add(temp);
        }
        return lista;
    }
	
	@Override
	public boolean isSolucion(){
		return this.juego.estadoIsSolucion(this);
	}
	
	public boolean equals(EstadoJuego ej) {
		return (this.juego.equals(ej.getJuego()) && this.levelA == ej.getLevelA() && this.levelB == ej.getLevelB());
	}

	/**
	 * @RETURN THE LEVELA
	 */
	public int getLevelA() {
		return levelA;
	}

	/**
	 * @return the levelB
	 */
	public int getLevelB() {
		return levelB;
	}

	/**
	 * @return the juego
	 */
	public Juego getJuego() {
		return juego;
	}
	
	
}
