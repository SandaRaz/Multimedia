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
	int firstReq = 0;
	Function myfunction = new Function();

	public Thread streamSound;

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
			//MyObjectOutputStream moos = new MyObjectOutputStream(this.client.getOutputStream());

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

				if(clientFirstPack.getMenu().equalsIgnoreCase("Video")){
					oos.writeObject(this.server.ListerVideo(clientFirstPack.getClient(),mainfolder));
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

						//System.out.println("Image envoyee a "+clientSecondPack.getClient());
						//image = new byte[0];
					}

					if(clientSecondPack.getMenu().equalsIgnoreCase("Music")){
						String path = "";
						if(clientSecondPack.getArtist().equalsIgnoreCase("woArtistDir")){
							path = mainfolder+"/Music/"+clientSecondPack.getTitre();
						}else{
							path = mainfolder+"/Music/"+clientSecondPack.getArtist()+"/"+clientSecondPack.getTitre();
						}
						System.out.println(" Chemin du fichier : "+path);

						File song = new File(path);
						// --------- envoyer Metadata -----------
							Metadata metadata = this.server.retrieveMetaData(song.getName(),song);
							
							oos.writeObject(metadata);
							oos.flush();
							System.out.println("Metadata envoyee a "+clientSecondPack.getClient());
						// --------------------------------------

						// --------- Play maintenant ------------
							this.server.sendFile(dos,path);
							System.out.println("--- File has been send");
						// --------------------------------------
					}
				}
				
			}

			ois.close();
			oos.close();
			dos.close();

			this.client.close();
		}catch(Exception e){
			if(e instanceof java.net.SocketException){
				System.err.println("******	"+e.getMessage()+" ******");
			}else if(e instanceof java.io.EOFException){
				System.err.println("******	"+e.getMessage()+" ******");
			}else{
				e.printStackTrace();
			}
		}finally{
			//System.out.println(">>>>> Client "+this.clientNumber+" s'est deconnecte");
		}
	}

	Runnable runnableStream = new Runnable() {
        @Override
        public void run() {
        	try{
              	
            }catch(Exception e){
            	e.printStackTrace();
            }
        }
    };
}