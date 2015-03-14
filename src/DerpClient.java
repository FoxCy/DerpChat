import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class DerpClient 
{
	Socket clientSocket;
	
	String serverIP;
	
	Scanner in;
	DataOutputStream out;
	
	ClientUI myUI;
	
	ClientCommands cmd;
	
	public void setUpClient()
	{
		myUI = new ClientUI(this);
		myUI.init();
		cmd = new ClientCommands();
		
		serverIP = "127.0.0.1";
		
		try 
		{
			clientSocket = new Socket(serverIP, 4446);
			myUI.appendToLog("Connected to " + serverIP);
			
			in = new Scanner(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			
			listen();
		} 
		
		catch (IOException e) 
		{
			myUI.appendToLog("ERROR: Failed to connect client.\n");
		}	
	}
	
	public void sendMessage() throws IOException
	{
		if (clientSocket != null)
		{
			String message = myUI.getMessageText();
			
			String isCmd = cmd.checkCommand(message);
			
			if (isCmd.equals("."))
			{
				out.writeUTF(message + "\n");
			}
			
			else
			{
				message = isCmd;
			}
		
			myUI.clearMessageField();
			myUI.appendToLog(message + "\n");
		}
		
		else
		{
			myUI.clearMessageField();
			myUI.appendToLog("ERROR: Server unavailiable to process request.\n");
		}
	}
	
	public void listen()
	{
		while (true)
		{
			String incomingMessage = in.nextLine();
			myUI.appendToLog(incomingMessage.substring(2, incomingMessage.length()) + "\n");
		}
	}
	
	public static void main(String[] args)
	{
		DerpClient dc = new DerpClient();
		dc.setUpClient();
	}
}
