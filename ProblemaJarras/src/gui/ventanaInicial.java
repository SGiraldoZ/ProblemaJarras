package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComponent;

public class ventanaInicial extends JComponent {

	public ventanaInicial() {
		this.setLayout(new GridLayout(0,1));
		this.add(new Cuestionario(this));		
	}
}
