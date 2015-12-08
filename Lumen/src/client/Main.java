/**
 *	**	**	**	**	**	**	**	**
 * 	Socket.java
 * 	client
 *	**	**	**	**	**	**	**	**
 *
 * 	Author		:		Alvin
 * 	Year		:		2014
 * 	Time		:		12:40:40
 * 
 *	**	**	**	**	**	**	**	**
 */
package client;

/**
 * @author Alvin
 */


import java.net.*; 
import java.awt.image.BufferedImage;
import java.io.*; 

import javax.imageio.ImageIO;
import org.newdawn.slick.util.ResourceLoader;
 
// A client for our Multithreaded SocketServer. 
public class Main
{
	public static String ipAddress;
	
	static Socket s = null; 
    static BufferedReader in = null; 
    static PrintWriter out = null;
    public static boolean changed = false;
    static String command = "none";
    public static int connect(String _ipAddress) {
    	ipAddress = _ipAddress;
    	int status = 0;
        try
        { 
            s = new Socket(ipAddress, 10114);
        }        
        catch(UnknownHostException uhe) 
        { 
            // Server Host unreachable 
            System.out.println("Unknown Host :" + ipAddress); 
            s = null; 
            status = 1;
        } 
        catch(IOException ioe) 
        { 
            // Cannot connect to port on given server host 
            System.out.println("Cant connect to server at 11111. Make sure it is running."); 
            s = null; 
            status = 2;
        }
        Thread t = new Thread(new MessageLoop());
        t.start();
        URL url = ResourceLoader.getResource("res/grass.png");
        try {
			client.Protocol.server_icon = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
    }
    
    static int identity = 0;
    public static String say(String _command) {
    	command = identity + _command;
    	if (identity == 0)
    		identity = 1;
    	else
    		identity = 0;
    	//changed = true;
		return command;
    }
    
    public static class MessageLoop implements Runnable {

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		//@Override
		public static boolean end = false;
		public void run() {
			// TODO Auto-generated method stub
			boolean thread = !end;
		    BufferedReader in = null; 
		    PrintWriter out = null;
			while (thread) {
				thread = !end;
				try {
					in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					out = new PrintWriter(new OutputStreamWriter(s.getOutputStream())); 
			        while (thread) {
						out.println(command);
						out.flush();
						
						//System.out.println(command);
						//changed = false;
						//String response = in.readLine();
						String response = in.readLine();
						System.out.println(response);
						client.Protocol.process(response);
			        }
					
					//Thread.sleep(16);
				} catch(IOException ioe) { 
		            System.out.println("Exception during communication. Server probably closed connection."); 
		        }/* catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/ finally { 
		            try
		            { 
		                // Close the input and output streams 
		                //out.close(); 
		                //in.close(); 
		                // Close the socket before quitting 
		                //s.close();
		                thread = false;
		            } 
		            catch(Exception e) 
		            { 
		                e.printStackTrace(); 
		            }
		        }
			}
		}
    	
    }
    
    //public static String say(String command)
    //{
    //	String _return = null;
        // First parameter has to be Server host name or IP address 
        /*if(args.length == 0) 
        { 
            System.out.println("Usage : SocketClient <serverName>"); 
            return; 
        }*/
        
        //Socket s = null; 
         
        // Create the socket connection to the MultiThreadedSocketServer port 11111 
         
        /*if(s == null) 
            System.exit(-1);*/
         
        /*BufferedReader in = null; 
        PrintWriter out = null;*/
        //try
        //{
            // Create the streams to send and receive information 
            /*in = new BufferedReader(new InputStreamReader(s.getInputStream())); 
            out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));*/
             
            // Since this is the client, we will initiate the talking. 
            // Send a string data and flush 
            /*out.println("hej"); 
            out.flush();
            out.println("quit"); 
            out.flush(); 
            // Receive the reply. 
            System.out.println(in.readLine()); 
            _return = in.readLine();
             
            // Send the special string to tell server to quit. 
            /*out.println("Quit"); 
            out.flush();*/
        /*} 
        catch(IOException ioe) 
        { 
            System.out.println("Exception during communication. Server probably closed connection."); 
        } 
        finally
        { 
            try
            { 
                // Close the input and output streams 
                out.close(); 
                in.close(); 
                // Close the socket before quitting 
                s.close();
            } 
            catch(Exception e) 
            { 
                e.printStackTrace(); 
            }
        }*/
        
	//	return _return;     
    //} 
}