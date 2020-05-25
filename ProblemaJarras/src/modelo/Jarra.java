package modelo;

public class Jarra {
	private int lim;
	
	public Jarra(int lim) {
		this.lim=lim;
	}
	
	public int cantMax(int tiene){
		return lim - tiene;
		
	}

	/**
	 * @return the lim
	 */
	public int getLim() {
		return lim;
	}

	public void setLim(int lim) {
		this.lim = lim;
	}
	
	public static void main(String[] args) {
		Jarra j = new Jarra(3);
		
		System.out.println(j.cantMax(2));
	}
	
	@Override
	public String toString() {
		return "J"+this.lim;
	}
	
}
