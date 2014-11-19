package xelitez.frostcraft.command;

import xelitez.frostcraft.registry.IdMap;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;

public class ClientCommandDev extends CommandBase{

	@Override
	public String getCommandName() 
	{
		return "FC";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) 
	{
		return "Dev command only";
	}
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
    {
		boolean flag = false;
		for(String s : IdMap.testers)
		{
			if(s.toLowerCase().matches(icommandsender.getCommandSenderName().toLowerCase()))
			{
				flag = true;
			}
		}
		return flag;
    }

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) 
	{
		if(astring[0].equals("getBlock"))
		{
			if(icommandsender.getEntityWorld().isRemote)
			{
				if(Minecraft.getMinecraft().objectMouseOver != null)
				{
					MovingObjectPosition obj = Minecraft.getMinecraft().objectMouseOver;
					Block block = icommandsender.getEntityWorld().getBlock(obj.blockX, obj.blockY, obj.blockZ);
					int meta = icommandsender.getEntityWorld().getBlockMetadata(obj.blockX, obj.blockY, obj.blockZ);
					icommandsender.addChatMessage(new ChatComponentText(new StringBuilder().append(block.getUnlocalizedName()).append(", ").append(meta).toString()));
				}
			}
			return;
		}
	}

}
