import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

class ServerThread extends Thread{  
		boolean onlyOne = true;
	    String line=null;
	    InputStream  is = null;
	    OutputStream os=null;
	    Socket s=null;

	    public ServerThread(Socket s){
	        this.s=s;
	    }

	    public void run() {
	    
	    try {
	    	while(true){
	    		
	    		
				if((Server.getArraySockets().size()==1)&onlyOne){
					OutputStream oss = Server.getArraySockets().get(0).getOutputStream();
	    			for(int ii=0;ii<4;ii++){
	    				oss.write("9".getBytes());
	    				System.out.print(9);
	    			}
					onlyOne=false;
				} 
	    		
	    		 InputStream is = s.getInputStream();
	    		
	    		 int b;
	    		 
	    		 while((b=is.read())!=-1){
	    				 
	    		 System.out.print((char)b);
	    		 
	    		for(int i = 0; i<Server.getArraySockets().size();i++){
	    			OutputStream os2 = Server.getArraySockets().get(i).getOutputStream();
	    			os2.write(b);
	    		}
	    		 }
	    	}
	    } catch (IOException e) {

	        line=this.getName(); 
	        System.out.println("Client "+line+" zamknal polaczenie");
	    }
	    catch(NullPointerException e){
	        line=this.getName(); 
	        System.out.println("Client "+line+" zamkniety");
	    }

	    finally{    
	    try{
	        System.out.println("Polaczenie zakonczone");
	        if (is!=null){
	            is.close(); 
	            System.out.println(" Socket Input Stream Closed");
	        }

	        if(os!=null){
	            os.close();
	            System.out.println("Socket Out Closed");
	        }
	        if (s!=null){
	        s.close();
	        System.out.println("Socket Zakonczony");
	        }

	        }
	    catch(IOException ie){
	        System.out.println("Socket Close Error");
	    }
	    }//end finally
	    }
	
	}