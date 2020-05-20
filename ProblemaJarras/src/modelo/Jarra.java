package modelo;

public class Jarra {
	private int lim;
	
	public Jarra(int lim) {
		this.lim=lim;
	}
	
	public int cantMax(int tiene){
		if (tiene==0) {
			return lim;
		}else{
			if (tiene==lim) {
				return 0;
			}else {
				return lim-tiene;
			}
		}
		
	}

	/**
	 * @return the lim
	 */
	public int getLim() {
		return lim;
	}

	
}
