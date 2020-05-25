package modeloN;

import java.util.*;
import modelo.*;
class ExcepcionParametros extends Exception{
	public ExcepcionParametros(String s) {
		super(s);
	}
}

public class JuegoN {
	
	private Jarra[] jarras;
	private int cantObj;
	private int Obj;
	private ArbolEstados<EstadoJuegoN> arbolEstados;
	
	public JuegoN(Jarra[] jarras,int canObj,int Obj) {
		this.jarras = jarras;
		this.cantObj = canObj;
		this.Obj= Obj;
		this.arbolEstados = new ArbolEstados<EstadoJuegoN>(this.estadoInicial());
	}	
	
	public EstadoJuegoN estadoInicial() {
		return new  EstadoJuegoN(new int[jarras.length],this);
	}
	
	public boolean estadoIsSolucion(EstadoJuegoN ej) {
		return ej.getLevels()[Obj] == cantObj;
	}
	
	public Jarra[] getJarras() {
		return jarras;
	}
	
	public void setJarras(Jarra[] jarras) {
		this.jarras = jarras;
	}

	public int getCantObj() {
		return cantObj;
	}

	public void setCantObj(int cantObj) {
		this.cantObj = cantObj;
	}

	public int getObj() {
		return Obj;
	}

	public void setObj(int obj) {
		Obj = obj;
	}

	public ArbolEstados<EstadoJuegoN> getArbolEstados() {
		return arbolEstados;
	}

	public void setArbolEstados(ArbolEstados<EstadoJuegoN> arbolEstados) {
		this.arbolEstados = arbolEstados;
	}

	public void limites() {
		for(int i = 0; i<jarras.length;i++) {
			System.out.println("Ingrese el limite de la Jarra "+i+": ");
			Scanner pc = new Scanner(System.in);
			int lim = pc.nextInt();
			jarras[i].setLim(lim);
		}
	}
	
	public void solve() {
		this.arbolEstados.FillUntilSolve();
	}
	
	public void fill() {
		this.arbolEstados.fill();
	}
	
	public void print() {
		BinaryTreePrinter<EstadoJuegoN> btp = new BinaryTreePrinter<EstadoJuegoN>(this.arbolEstados);
		String s = btp.traversePreOrder(this.arbolEstados.getRaiz());
		btp.print(System.out);
	}
	
	public void printPreO() {
		this.arbolEstados.preorden();
	}
	
	public void printIno() {
		this.arbolEstados.inorden();
	}
	
	public static void jugar(Jarra[] cantJarras, int cantObj,int Obj) {
		JuegoN j = new JuegoN(cantJarras,cantObj,Obj);
		j.arbolEstados.FillUntilSolve();
		BinaryTreePrinter<EstadoJuegoN> btp = new BinaryTreePrinter<EstadoJuegoN>(j.getArbolEstados());
		btp.print(System.out);
	}
	public JuegoN iniciar() throws ExcepcionParametros{
		Scanner pc = new Scanner(System.in);
		int cantJarras,lim,cantObj, Obj;
		System.out.println("Cantidad Jarras: ");
		cantJarras = pc.nextInt();
		Jarra[] jarras = new Jarra[cantJarras];
		for(int i = 0;i<jarras.length;i++) {
			System.out.println("Ingrese el limite de la Jarra "+i+": ");
			lim = pc.nextInt();
			jarras[i].setLim(lim);
		}
		System.out.println("Cantidad objetivo: ");
		cantObj = pc.nextInt();
		System.out.println("Jarra en la que debe haber "+cantObj+": ");
		Obj = pc.nextInt();
		if(Obj>jarras.length-1&&cantObj>jarras[Obj].getLim()) {
			throw new ExcepcionParametros("No se puede conseguir una cantidad mayor al límite de la jarra como objetivo");
		}
		int i = 0;
		while(++i<jarras.length && jarras[0].getLim()==jarras[i].getLim());
		if(i>jarras.length)
			throw new ExcepcionParametros("Es imposible conseguir la cantidad objetivo si los dos tamaños de las jarras son distintos");
		return new JuegoN(jarras,cantObj,Obj);
	}
	
}
