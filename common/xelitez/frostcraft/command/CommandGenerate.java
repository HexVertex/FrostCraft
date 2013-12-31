package xelitez.frostcraft.command;

import java.util.List;
import java.util.Random;

import xelitez.frostcraft.registry.IdMap;
import xelitez.frostcraft.world.WorldGenFrostWingTower;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChunkCoordinates;

public class CommandGenerate extends CommandBase
{
	String[] completions = new String[] {"castle", "cylinder", "remove"};
	String[] completions2 = new String[] {"cylinder"};
	
	@Override
	public String getCommandName() 
	{
		return "generate";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) 
	{
		return "/generate <structure>";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) 
	{
		if(astring.length > 0)
		{
			boolean flag = false;
			for(String s : IdMap.testers)
			{
				if(s.toLowerCase().matches(icommandsender.getCommandSenderName().toLowerCase()))
				{
					flag = true;
				}
			}
			if(flag)
			{
				if(astring[0].equals("castle"))
				{
					ChunkCoordinates ck = icommandsender.getPlayerCoordinates();
					WorldGenFrostWingTower.generateAt(new Random(), ck.posX, ck.posY, ck.posZ, icommandsender.getEntityWorld());
					return;
				}
				if(astring[0].equals("cylinder"))
				{
					ChunkCoordinates ck = icommandsender.getPlayerCoordinates();
					WorldGenFrostWingTower.generateFrostWingCylinder(icommandsender.getEntityWorld(), ck.posX, ck.posZ);
					return;
				}
				if(astring[0].equals("remove"))
				{
					if(astring[1].equals("cylinder"))
					{
						ChunkCoordinates ck = icommandsender.getPlayerCoordinates();
						WorldGenFrostWingTower.removeCylinder(icommandsender.getEntityWorld(), ck.posX, ck.posZ);
						return;
					}
				}
			}
			else
			{
				throw new NoTestException(new Object[0]);
			}
		}
        throw new WrongUsageException("/generate <structure>", new Object[0]);
	}
	
	@Override
	public List<?> addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
		return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, completions) : (par2ArrayOfStr.length == 2 && par2ArrayOfStr[0].equals("remove") ? getListOfStringsMatchingLastWord(par2ArrayOfStr, completions2) : null);
    }

}
