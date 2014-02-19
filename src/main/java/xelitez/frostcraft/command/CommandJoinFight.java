package xelitez.frostcraft.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import xelitez.frostcraft.entity.EntityFrostWing;

public class CommandJoinFight extends CommandBase
{
	String[] completions = new String[] {"FrostWing"};
	
	@Override
	public String getCommandName() 
	{
		return "joinfight";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) 
	{
		return "/joinfight <creature>";
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) 
	{
		if(astring.length > 0)
		{
			if(astring[0].toLowerCase().equals("frostwing"))
			{
				EntityPlayerMP player = getCommandSenderAsPlayer(icommandsender);
				if(player.worldObj != null)
				{
					for(Object obj : player.worldObj.loadedEntityList)
					{
						if(obj instanceof EntityFrostWing)
						{
							EntityFrostWing entity = (EntityFrostWing)obj;
							double posX = entity.posX;
							double posZ = entity.posZ;
							double posY = this.getLowsetPossiblePosition(player.worldObj, posX, entity.posY, posZ);
			                player.mountEntity((Entity)null);
			                player.setPositionAndUpdate(posX, posY, posZ);
			                return;
						}
					}
					throw new SyntaxErrorException(astring[0] + " was not found!", new Object[0]);
				}
			}
		}
        throw new WrongUsageException("/joinfight <creature>", new Object[0]);
	}
	
	private double getLowsetPossiblePosition(World world, double x, double y, double z)
	{
		for(double ypos = y;ypos > 0.0D;ypos -= 1.0D)
		{
			if(world.getBlock((int)Math.floor(x), (int)Math.floor(ypos), (int)Math.floor(z)) != Blocks.air && world.getBlock((int)Math.floor(x), (int)Math.floor(ypos), (int)Math.floor(z)).getCollisionBoundingBoxFromPool(world, (int)Math.floor(x), (int)Math.floor(ypos), (int)Math.floor(z)) != null)
			{
				if((world.getBlock((int)Math.floor(x), (int)Math.floor(ypos + 1.0D), (int)Math.floor(z)) == Blocks.air || world.getBlock((int)Math.floor(x), (int)Math.floor(ypos + 1.0D), (int)Math.floor(z)).getCollisionBoundingBoxFromPool(world, (int)Math.floor(x), (int)Math.floor(ypos + 1.0D), (int)Math.floor(z)) == null) && (world.getBlock((int)Math.floor(x), (int)Math.floor(ypos + 2.0D), (int)Math.floor(z)) == Blocks.air || world.getBlock((int)Math.floor(x), (int)Math.floor(ypos + 2.0D), (int)Math.floor(z)).getCollisionBoundingBoxFromPool(world, (int)Math.floor(x), (int)Math.floor(ypos + 2.0D), (int)Math.floor(z)) == null))
				{
					return Math.floor(ypos + 1.0D);
				}
			}
		}
		return 0.0D;
	}
	
	@Override
	public List<?> addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
    	return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, completions) : null;
    }
}
