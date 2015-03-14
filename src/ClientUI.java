import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;


public class ClientUI 
{
	JFrame contentWindow;
	JTextArea messageField;
	JTextArea chatLog;
	
	JButton sendButton;
	JScrollPane chatScroll;
	
	DerpClient myClient;
	
	public ClientUI(DerpClient c)
	{
		myClient = c;
		
		contentWindow = new JFrame();
		chatLog = new JTextArea();
		messageField = new JTextArea();
		
		sendButton = new JButton();
		chatScroll = new JScrollPane(chatLog);
	}
	
	public void init()
	{
		contentWindow = new JFrame();
		contentWindow.setSize(400, 500);
		contentWindow.setTitle("DerpChat™ Client");
		contentWindow.setLayout(new BorderLayout());
		contentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentWindow.setVisible(true);
		
		messageField.setPreferredSize(new Dimension(contentWindow.getWidth(), 50));
		messageField.setLineWrap(true);
		messageField.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		messageField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLUE));
		
		sendButton.setPreferredSize(new Dimension(contentWindow.getWidth(), 50));
		sendButton.setText("Send");
		sendButton.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						myClient.sendMessage();
					} 
					catch (IOException e1) 
					{
						chatLog.append("ERROR: Failed to send message");
					}
				}
			});
		
		chatScroll.setPreferredSize(new Dimension(contentWindow.getWidth(), 350));
		
		chatLog.setEditable(false);
		chatLog.setLineWrap(true);
		
		contentWindow.add(chatScroll, BorderLayout.NORTH);
		contentWindow.add(messageField, BorderLayout.CENTER);
		contentWindow.add(sendButton, BorderLayout.SOUTH);
		contentWindow.pack();
	}
	
	public void appendToLog(String text)
	{
		chatLog.append(text);
	}
	
	public void clearLog()
	{
		chatLog.setText("");
	}
	
	public void clearMessageField()
	{
		messageField.setText("");
	}
	
	public String getMessageText()
	{
		return messageField.getText();
	}
}
