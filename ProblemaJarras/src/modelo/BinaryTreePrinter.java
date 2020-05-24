package modelo;

import java.io.PrintStream;

public class BinaryTreePrinter<T> {
	
	ArbolB<T> arbol;
	
	public BinaryTreePrinter(ArbolB<T> arbol) {
		this.arbol = arbol;
	}
	
	public String traversePreOrder(NodoB<T> root) {
		 
	    if (root == null) {
	        return "";
	    }
	 
	    StringBuilder sb = new StringBuilder();
	    sb.append(root.getLlave());
	 
	    String pointerRight = "'--";
	    String pointerLeft = (root.getHijoDer() != null) ? "|--" : "'--";
	 
	    traverseNodes(sb, "", pointerLeft, root.getHijoIzq(), root.getHijoDer() != null);
	    traverseNodes(sb, "", pointerRight, root.getHijoDer(), false);
	 
	    return sb.toString();
	}
	
	public void traverseNodes(StringBuilder sb, String padding, String pointer, NodoB<T> node, 
			  boolean hasRightSibling) {
			    if (node != null) {
			        sb.append("\n");
			        sb.append(padding);
			        sb.append(pointer);
			        sb.append(node.getLlave());
			 
			        StringBuilder paddingBuilder = new StringBuilder(padding);
			        if (hasRightSibling) {
			            paddingBuilder.append("|  ");
			        } else {
			            paddingBuilder.append("   ");
			        }
			 
			        String paddingForBoth = paddingBuilder.toString();
			        String pointerRight = "'--";
			        String pointerLeft = (node.getHijoDer() != null) ? "|--" : "'--";
			 
			        traverseNodes(sb, paddingForBoth, pointerLeft, node.getHijoIzq(), node.getHijoDer() != null);
			        traverseNodes(sb, paddingForBoth, pointerRight, node.getHijoDer(), false);
			    }
			}
	
	/*public void preOrder(StringBuilder sb,String padding,String pointer, NodoB<T> nodo) {
		if(nodo != null) {
			sb.append(padding);
			sb.append(pointer);
			sb.append(nodo.getLlave());
			sb.append("\n");
			StringBuilder paddingBuilder = new StringBuilder(padding);
			paddingBuilder.append("|  ");
			
			String paddingForBoth = paddingBuilder.toString();
			String pointerForRight = "'--";
			String pointerForLeft = (nodo.getHijoDer()!=null)?"|-- " : "'-- ";
			
			preOrder(sb,paddingForBoth,pointerForLeft, nodo.getHijoIzq());
			preOrder(sb,paddingForBoth,pointerForRight, nodo.getHijoDer());
		}
	}*/
	
	public void print(PrintStream os) {
	    /*StringBuilder sb = new StringBuilder();
	    preOrder(sb,"","", arbol.getRaiz());
	    os.print(sb.toString());*/
		os.print(traversePreOrder(this.arbol.getRaiz()));
	}

}
