
public class ClientCommands 
{
	Command[] cmds;
	
	public ClientCommands()
	{
		cmds = new Command[7];
		
		cmds[0] = new Command("connect");
		cmds[1] = new Command("disconnect");
		cmds[2] = new Command("ping");
		cmds[3] = new Command("save");
		cmds[4] = new Command("clear");
		cmds[5] = new Command("redact");
		cmds[6] = new Command("quote");
	}
	
	public String checkCommand(String text)
	{
		String value = ".";
		
		if (text.startsWith("+"))
		{
			value = "Invalid command.";
			
			String[] ss = text.split(" ");
			String attemptedCommand = ss[0].substring(1);
			
			for (Command c : cmds)
			{
				if (c.getID().equals(attemptedCommand))
				{
					c.execute();
					value = "Command accepted.";
				}
			}
		}
		
		return value;
	}

}
