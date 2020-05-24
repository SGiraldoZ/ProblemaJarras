package gui2;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ventana extends JFrame {
	private JTextField txtJarraA;
	private JTextField txtJarraB;
	private JTextField txtCantObj;
	private ButtonGroup opJarras;
	private JRadioButton opJarraA;
	private JRadioButton opJarraB;
	private JPanel paneArbol;
	
	public Ventana() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 400);
		this.setTitle("Arbol de Estado Problema De las Jarras");
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		paneArbol = new JPanel();
		paneArbol.add(new JTextField("ESTOY AGREGANDO EL PANE"), BorderLayout.CENTER);
		this.getContentPane().add(paneArbol, BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel();
		panel.add(panel1,BorderLayout.CENTER);
		
		//Agregar campo de texto para definir la primera jarra
		txtJarraA = new JTextField();
		txtJarraA.setBorder(BorderFactory.createTitledBorder("L\u00EDmite Jarra A:"));
		txtJarraA.setText("0");
		this.setFiltroNum(txtJarraA);
		panel1.add(txtJarraA);
		txtJarraA.setColumns(10);

		//Agregar campo de texto para definir la segunda jarra
		txtJarraB = new JTextField();
		txtJarraB.setText("0");
		txtJarraB.setColumns(10);
		txtJarraB.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "L\u00EDmite Jarra B:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.setFiltroNum(txtJarraB);
		panel1.add(txtJarraB);
		
		//Agregar campo de texto para definir la cantidad deseada
		txtCantObj = new JTextField();
		txtCantObj.setText("0");
		txtCantObj.setColumns(10);
		txtCantObj.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Cantidad Deseada:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.setFiltroNum(txtCantObj);
		panel1.add(txtCantObj);
		
		//Agregar opciones para Escoger jarra Final
		JPanel panelJarras = new JPanel();
		panelJarras.setLayout(new GridLayout(2, 1));
		opJarras = new ButtonGroup();
		opJarraA = new JRadioButton("JarraA");
		opJarras.add(opJarraA);
		panelJarras.add(opJarraA);
		opJarraB = new JRadioButton("JarraB");
		opJarraB.setSelected(true);
		opJarras.add(opJarraB);
		panelJarras.add(opJarraB);
		panelJarras.setBorder(BorderFactory.createTitledBorder("Jarra de Destino"));
		panel1.add(panelJarras);
		
		
		//AGREGAR BOTON
		JPanel panel2 = new JPanel();
		actualizarArbol baa = new actualizarArbol(this);
		panel2.add(baa);
		panel.add(panel2, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}

	public void setFiltroNum(JTextField field) {
		((AbstractDocument)field.getDocument()).setDocumentFilter(new DocumentFilter(){
	        Pattern regEx = Pattern.compile("\\d*");

	        @Override
	        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {          
	            Matcher matcher = regEx.matcher(text);
	            if(!matcher.matches()){
	                return;
	            }
	            super.replace(fb, offset, length, text, attrs);
	        }
	    });
	}

	public void actualizarArbol() {
		JTextArea descrA = new JTextArea();
		String s = "";
		s += "Jarra A: " + this.txtJarraA.getText() + "\n";
		s += "Jarra B: " + this.txtJarraB.getText() + "\n";
		s += "Cantidad Final: " + this.txtCantObj.getText() + "\n";
		s += "Jarra A es destino: " + opJarraA.isSelected();
		descrA.setText(s);
		this.paneArbol.removeAll();
		this.paneArbol.add(descrA, BorderLayout.CENTER);
		this.paneArbol.revalidate();
		this.revalidate();
		this.repaint();
		
	}
	
	public static void main(String[] args) {
		Ventana v = new Ventana();
		
		
	}
}


class actualizarArbol extends JButton {
	private Ventana v;
	
	public actualizarArbol(Ventana v) {
		super(); 
		this.v = v;
		this.setText("Actualizar El Árbol");
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				v.actualizarArbol();
			}
		});
		
	}
}
