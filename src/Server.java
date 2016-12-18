import java.awt.List;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
	
	private static ServerSocket ss;
	private static ArrayList sockets;
	
	public static void main(String[] args) throws IOException{
	
		sockets = new ArrayList<Socket>(); //lista aktywnych gniazd;
		Socket s=null;
	    ServerSocket ss2=null;
	    int port =Integer.parseInt(args[0]);
		
		System.out.println("Server nasluchuje na porcie "+port+"......");
		ss2 = new ServerSocket(port);
		
		while(true){
	        try{
	            s= ss2.accept();
	            System.out.println("Polaczenie zlapane-> przekierowane");
	            sockets.add(s);
	            ServerThread st=new ServerThread(s);
	            st.start();

	        }catch(Exception e){
	            e.printStackTrace();
	            System.out.println("Connection Error");

	        }

	
		
		}
		
		
	}
	
	public static ArrayList<Socket> getArraySockets(){
		return sockets;
	}
}
	
