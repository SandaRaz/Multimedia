package lib.thread;

import lib.inc.*;
import lib.mysocket.*;
import lib.data.*;
import lib.main.*;

import java.net.*;
import java.io.*;

public class ClientProcess extends Thread{
	Myserver server;
	Socket client;
	int requetteNumber;
	Function myfunction = new Function();

	String mainfolder = "./Folders";

	public String getMainfolder(){
		return this.mainfolder;
	}
	public void setMainfolder(String mf){
		this.mainfolder = mf;
	}

		
	public ClientProcess(Myserver serv,Socket clientvenue,int counter){
		this.server = serv;
		this.client = clientvenue;
		this.requetteNumber = counter;
	}

	@Override
	public void run(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(this.client.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(this.client.getInputStream());
			DataOutputStream dos = new DataOutputStream(this.client.getOutputStream());

			Myclient mc = new Myclient();

			while(true){
				System.out.println("\n Traitement de requette pour un client: "+this.client.getInetAddress());

				FirstPacket clientFirstPack = (FirstPacket) ois.readObject();
				
				if(clientFirstPack.getDeconnect() == true){
					System.out.println(">>>>> Client "+clientFirstPack.getClient()+"("+this.client.getInetAddress()+") s'est deconnecte");
					break;
				}

				mc.showAction(clientFirstPack,this.requetteNumber);		// Afficher les messages dans le serveur

		// ---------------------------- VERIFIER LE CHOIX DU CLIENT -------------------------

				if(clientFirstPack.getMenu().equalsIgnoreCase("Picture")){
					oos.writeObject(this.server.ListerImage(clientFirstPack.getClient(),mainfolder));
					oos.flush();
					System.out.println("FirstPacket	envoyer a "+clientFirstPack.getClient());
				}

				if(clientFirstPack.getMenu().equalsIgnoreCase("Music")){
					oos.writeObject(this.server.ListerMusic(clientFirstPack.getClient(),mainfolder));
					oos.flush();
					System.out.println("FirstPacket	envoyer a "+clientFirstPack.getClient());
				}

			// ------- while pour le traitement de tous requettes dans un menu choisit ------
				while(true){
					System.out.println("\n	En attente de reception d'autre requette \n");

					SecondPacket clientSecondPack = (SecondPacket) ois.readObject();

					if(clientSecondPack.getDeconnect() == true){
						System.out.println(">>>>> Client "+clientSecondPack.getClient()+"("+this.client.getInetAddress()+") est revenue dans son menu principal");
						break;
					}

					mc.showAction2(clientSecondPack,this.requetteNumber);
					String nomfichier = clientSecondPack.getNomfichier();

					if(clientSecondPack.getMenu().equalsIgnoreCase("Picture")){
						String path = mainfolder+"/Picture/"+nomfichier;
						System.out.println(" Chemin du fichier : "+path);

						byte[] image = this.myfunction.streamToBytes(path);
						BytePacket bpack = new BytePacket(clientSecondPack.getClient(),image);

						oos.writeObject(bpack);
						oos.flush();

						System.out.println("Image envoyee a "+clientSecondPack.getClient());
					}
				}

			// ------------------------------------------------------------------------------

		// ---------------------------------------------------------------------------------------
			/*	

				Function searcher = new Function();
				File resultat = searcher.findTheMusic(clientPack.getArtist(),clientPack.getTitre());
				//System.out.println("Resultat path >>> "+resultat.getPath());

				if(resultat == null){
					System.out.println(" Resultat est null ");
					this.server.Envoyer_Vide(oos);
				}else{
					Response resp = new Response(clientPack.getClient(),this.tempPath,resultat.getName(),resultat);
					this.server.Envoyer_File_Response(dos,oos,resp);					// Envoyer la reponse en OutputStream a tout client
				}
			*/

				
			}

			ois.close();
			oos.close();
			dos.close();

			this.client.close();
			//System.out.println(">>>>> Client "+clientFirstPack.getClient()+"("+this.client.getInetAddress()+") s'est deconnecte");
		}catch(Exception e){
			//e = new Exception(">>>>> Client "+this.clientNumber+"("+this.client.getInetAddress()+") s'est deconnecte");
		
			e.printStackTrace();		// Avant de se deconnecter;
		}finally{
			//System.out.println(">>>>> Client "+this.clientNumber+" s'est deconnecte");
		}
	}
}