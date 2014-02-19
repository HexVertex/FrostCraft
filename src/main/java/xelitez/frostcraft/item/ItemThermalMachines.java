package xelitez.frostcraft.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import xelitez.frostcraft.registry.IdMap;

public class ItemThermalMachines extends ItemBlock
{
	String[] name = new String[] {"ThermalPump", "FrostFurnace", "FrostGenerator", "Freezer", "FrostEnforcer"};
	
	public ItemThermalMachines(Block par1Block)
	{
		super(par1Block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
	}
	
	@Override
    public IIcon getIconFromDamage(int par1)
    {
        return IdMap.blockThermalMachines.getIcon(2, par1);
    }
    
    @Override
    public int getMetadata(int par1)
    {
        return par1;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	if(par1ItemStack.getItem() == Item.getItemFromBlock(IdMap.blockThermalMachines))
    	{
    		switch(par1ItemStack.getItemDamage())
    		{
    		case 0:
    			par3List.add("\u00a7oThis pump generates Compressed");
    			par3List.add("\u00a7oFrost Units based on biome,");
    			par3List.add("\u00a7otime and weather.");
    			break;
    		case 1:
    			par3List.add("\u00a7oThis machine is told te be a");
    			par3List.add("\u00a7othread to the laws of nature.");
    			par3List.add("\u00a7oIt creates temperatures so low");
    			par3List.add("\u00a7othat it burns!!!");
    			break;
    		case 2:
    			par3List.add("\u00a7oIn this machine you simply put");
    			par3List.add("\u00a7oitems, it compresses the items,");
    			par3List.add("\u00a7ofilters out the CFU and destroys");
    			par3List.add("\u00a7othe remaining stuff.");
    			break;
    		case 3:
    			par3List.add("\u00a7oIt's a FREEZER!!!");
    			par3List.add("\u00a7oDo you really need info about this?");
    			break;
    		case 4:
    			par3List.add("\u00a7oA Frost Enforcer enforces certain tools");
    			par3List.add("\u00a7oto become more efficient and durable");
    		}
    	}
    }
    
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return "XFC." + this.name[par1ItemStack.getItemDamage()];
    }
}
