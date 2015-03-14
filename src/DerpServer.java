import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class DerpServer 
{
	ServerSocket serverSocket;
	Socket clientSocket;
	
	Scanner in;
	DataOutputStream out;
	
	ServerUI myUI;
	
	ArrayList<Socket> connectedClients = new ArrayList<Socket>();
	
	public void setUpServer()
	{	
		myUI = new ServerUI();
		myUI.init();
		
		try 
		{
			serverSocket = new ServerSocket(4446);
			myUI.appendToLog("Listening...\n");
			
			clientSocket = serverSocket.accept();
			myUI.appendToLog("SERVER: Client Connected!\n");
			
			in = new Scanner(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			
			while(true)
			{
				String incomingMessage = in.nextLine();
				myUI.appendToLog(incomingMessage.substring(2, incomingMessage.length()) + "\n");
				
				out.writeUTF("SERVER: I recieved your message!\n");
			}
		}
		
		catch (IOException e) 
		{
			myUI.appendToLog("ERROR: Failed to start server.\n");
		}
	}
	
	public static void main(String[] args)
	{
		DerpServer ds = new DerpServer();
		ds.setUpServer();
	}
}
