package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Ventana extends JFrame {
	public Ventana() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.initialize();
	}
	
	public void initialize() {
		this.setSize(550,700);
		
		this.setResizable(false);
		this.setTitle("Visualizacion Arboles");
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(new ventanaInicial(), BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public static void main(String args []) {
		Ventana v = new Ventana();
	}
}
