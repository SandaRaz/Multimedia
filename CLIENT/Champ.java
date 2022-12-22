package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Champ extends JPanel implements MouseListener{
	public JLabel nom;
	public JTextField field;
	
	public JLabel getNom(){
		return this.nom;
	}
	public void setNom(JLabel nm){
		this.nom = nm;
	}

	public JTextField getField(){
		return this.field;
	}
	public void setField(JTextField jtf){
		this.field = jtf;
	}

	public Champ(String nm,String defaut){
		this.setLayout(null);
		//this.setBackground();

		this.setPreferredSize(new Dimension(500,60));

		JLabel nom = new JLabel();
		nom.setBounds(10,10,100,40);
		nom.setFont(new Font("Verdana",0,14));
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		nom.setText(nm);

		JTextField jtf = new JTextField();
		jtf.setBounds(120,10,370,40);
		jtf.setFont(new Font("Verdana",Font.BOLD,16));
		jtf.setHorizontalAlignment(SwingConstants.CENTER);
		jtf.setForeground(Color.black);
		jtf.setText(defaut);

		this.setNom(nom);
		this.setField(jtf);

		this.add(this.getNom());
		this.add(this.getField());

		this.getNom().addMouseListener(this);
		this.getField().addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e){
		
	}
	@Override
	public void mouseEntered(MouseEvent e){
		if(e.getSource() == this.getNom()){
			this.getNom().setHorizontalAlignment(SwingConstants.LEFT);
			this.getNom().setBounds(10,10,200,40);
			this.getField().setBounds(220,10,270,40);
		}
	}
	@Override
	public void mouseExited(MouseEvent e){
		if(e.getSource() == this.getNom()){
			this.getNom().setHorizontalAlignment(SwingConstants.CENTER);
			this.getNom().setBounds(10,10,100,40);
			this.getField().setBounds(120,10,370,40);
		}
	}
	@Override
	public void mousePressed(MouseEvent e){
		
	}
	@Override
	public void mouseReleased(MouseEvent e){
		
	} 
}