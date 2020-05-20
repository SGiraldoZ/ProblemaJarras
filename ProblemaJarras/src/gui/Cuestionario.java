package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.Format;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class Cuestionario extends JComponent {
	Border borde = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Tamaño de la Jarra A: ");
	private JFormattedTextField limA = new JFormattedTextField(),
			limB = new JFormattedTextField(BorderFactory.createTitledBorder(borde, "Tamaño de la Jarra B: "));
	JRadioButton objetivo;
	
	public Cuestionario(ventanaInicial vi) {
		this.setLayout(new GridLayout(2,2));
		this.add(limA);
		this.add(limB);
	}
}
