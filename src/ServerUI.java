import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class ServerUI 
{
	JFrame contentWindow;
	
	JTextArea chatLog = new JTextArea();
	
	public ServerUI()
	{
		
	}
	
	public void init()
	{
		contentWindow = new JFrame();
		contentWindow.setLayout(new BorderLayout());
		contentWindow.setTitle("DerpServer 1.0");
		contentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentWindow.setVisible(true);
		
		chatLog.setEditable(false);
		chatLog.setPreferredSize(new Dimension(400, 200));
		
		contentWindow.add(chatLog);
		contentWindow.pack();
	}
	
	public void appendToLog(String text)
	{
		chatLog.append(text);
	}
}
