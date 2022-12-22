package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreListener implements WindowListener{
	Fenetre fenetre;

	public Fenetre getFenetre(){
		return this.fenetre;
	}
	public void setFenetre(Fenetre f){
		this.fenetre = f;
	}

	public FenetreListener(Fenetre f){
		this.setFenetre(f);
	}


	@Override
	public void windowOpened(WindowEvent e){

	}

	@Override
	public void windowClosing(WindowEvent e){
		if(e.getSource() == this.getFenetre()){
			try{
				Object[] options = {"Oui","Non"};
				int jopt = JOptionPane.showOptionDialog(
					new JFrame(),
					"Souhaitez vous vous deconnectez ?\n",
					"Deconnexion",  //------------- Titre
					JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE,
					null,
					options,
					options[0]
				);
				if(jopt == 0){ 	// si JOptionpane return 0 = Oui
					try{
						this.getFenetre().getClient().Deconnexion();
					}catch(Exception ex){
						ex.printStackTrace();
						System.exit(1);
					}
					System.exit(1);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void windowClosed(WindowEvent e){

	}

	@Override
	public void windowIconified(WindowEvent e){

	}

	@Override
	public void windowDeiconified(WindowEvent e){

	}

	@Override
	public void windowActivated(WindowEvent e){

	}

	@Override
	public void windowDeactivated(WindowEvent e){

	}
}
