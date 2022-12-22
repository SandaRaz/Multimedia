package gui;

import lib.inc.*;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Configuration extends JFrame implements MouseListener{
	Contentpane contentpane;
	private Champ client;
	private Champ port;
	private Champ ip;

	Bouton annuler;
	Bouton enregistrer;

	public Contentpane getContentpane(){
		return this.contentpane;
	}
	public void setContentpane(Contentpane cont){
		this.contentpane = cont;
	}

	public Bouton getAnnuler(){
		return this.annuler;
	}
	public void setAnnuler(Bouton jb){
		this.annuler = jb;
	}

	public Bouton getEnregistrer(){
		return this.enregistrer;
	}
	public void setEnregistrer(Bouton jb){
		this.enregistrer = jb;
	}

	public Configuration(){

	}

	public Configuration(Contentpane cp){
		this.setContentpane(cp);

		this.setTitle("Configuration");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(550,400);

		JPanel configContPane = new JPanel();
		configContPane.setBackground(Color.white);
		configContPane.setLayout(new BorderLayout(30,20));

	//-------------------- TITRE ----------------------

		JLabel help = new JLabel();
		help.setFont(new Font("Verdana",0,15));
		help.setBackground(Color.white);
		help.setOpaque(true);
		help.setHorizontalAlignment(SwingConstants.CENTER);
			String parag = "";
				String ophtml = "<html>";
					String opbody = "<body>";
						String title = "<h2>Configurer le <span style=\"color: rgb(186,180,168);\">PORT</span> et <span style=\"color: rgb(186,180,168);\">ADRESSE IP</span> de votre server</h2>";
						String p1 = "<p>Port par defaut: <b><span style='color: rgb(186, 180, 168);'>6969</span></b></p>";
					String clbody = "</body>";
				String clhtml = "</html>";
			parag = ophtml + opbody + title + p1 + clbody + clhtml;
		help.setText(parag);

	//---------------------------------------------------

	//--------- Champ pour ajouter des modification --------

		JPanel form = new JPanel();
		form.setBackground(Color.white);

		this.client = new Champ("Nom du clients: ",this.getContentpane().getFenetre().getClientname());
		this.port = new Champ("Port de votre server: ",this.getContentpane().getFenetre().getPort()+"");
		this.ip = new Champ("Adresse IP de votre server: ",this.getContentpane().getFenetre().getAdresseip());
		form.add(this.client);
		form.add(this.port);
		form.add(this.ip);

	//------------------------------------------------------

		JPanel boutons = new JPanel();
		boutons.setBackground(Color.white);
		boutons.setPreferredSize(new Dimension(550,50));
			JPanel boutons2 = new JPanel();
			boutons2.setBackground(Color.white);
			boutons2.setLayout(new GridLayout(1,4,10,10));
			boutons2.setPreferredSize(new Dimension(505,30));
				boutons2.add(new JLabel());
				boutons2.add(new JLabel());

				this.setAnnuler(new Bouton("Annuler",new Color(245,240,236),new Color(186,180,168)));
				this.setEnregistrer(new Bouton("Enregistrer",new Color(245,240,236),new Color(186,180,168)));

				boutons2.add(this.getAnnuler());
				boutons2.add(this.getEnregistrer());

				this.getAnnuler().addMouseListener(this);
				this.getEnregistrer().addMouseListener(this);
		boutons.add(boutons2);

		configContPane.add(help,BorderLayout.NORTH);
		configContPane.add(form,BorderLayout.CENTER);
		configContPane.add(boutons,BorderLayout.SOUTH);

		this.setContentPane(configContPane);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e){
		if(e.getSource() == this.getAnnuler()){
			System.out.println("Annuler clicked");
			this.dispose();	
		}
		if(e.getSource() == this.getEnregistrer()){
			System.out.println("Enregistrer clicked");

			try{
				int prt = Integer.parseInt(this.port.getField().getText());
			}catch(Exception ex){
				JOptionPane.showMessageDialog(new JFrame(),"Le port entree est non valide ","Erreur de saisie",JOptionPane.ERROR_MESSAGE);
			}

			String cn = this.client.getField().getText();
			String aip = this.ip.getField().getText();
			int port = Integer.parseInt(this.port.getField().getText());

			this.getContentpane().getFenetre().setClientname(cn);
			this.getContentpane().getFenetre().setAdresseip(aip);
			this.getContentpane().getFenetre().setPort(port);	

			System.out.println(this.getContentpane().getFenetre().getClientname());
			System.out.println(this.getContentpane().getFenetre().getAdresseip());
			System.out.println(this.getContentpane().getFenetre().getPort());

			String save = cn + "," + aip + "," + port + ";";
			System.out.println("Save : "+save);

			File infos = new File("./save/infos.snd");
			if(!infos.exists()){
				try{
					infos.createNewFile();
					String pardefaut = "Nouveau Client,localhost,6969;";

					Fonction.FileWrite(pardefaut,infos,false);
				}catch(Exception ex){
					ex.printStackTrace();
				}	
			}
			Fonction.FileWrite(save,infos,false);

			this.getContentpane().revalidate();
			this.getContentpane().repaint();
			this.getContentpane().getFenetre().revalidate();
			this.getContentpane().getFenetre().repaint();

			JOptionPane.showMessageDialog(new JFrame(),"Vous devrez redemarrer l'application pour valider les changements","Information",JOptionPane.INFORMATION_MESSAGE);

			this.dispose();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e){

	}
	@Override
	public void mouseExited(MouseEvent e){
	}
	@Override
	public void mousePressed(MouseEvent e){
		
	}
	@Override
	public void mouseReleased(MouseEvent e){
		
	}
} 