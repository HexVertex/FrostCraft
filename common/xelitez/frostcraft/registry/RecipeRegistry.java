package xelitez.frostcraft.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeRegistry 
{
	private static final RecipeRegistry instance = new RecipeRegistry();
	
	public static HashMap<List<Integer>, ItemStack> freezerRecipes = new HashMap<List<Integer>, ItemStack>();
	public static HashMap<List<Integer>, Float> freezerExperiences = new HashMap<List<Integer>, Float>();
	
	public static HashMap<List<Integer>, ItemStack> enforcerRecipes = new HashMap<List<Integer>, ItemStack>();
	public static HashMap<Integer, ItemStack> enforcerIdOnlyRecipes = new HashMap<Integer, ItemStack>();
	
	public static List<Object[]> frostFuel = new ArrayList<Object[]>();
	
	public static RecipeRegistry registry()
	{
		return instance;
	}
	
	public void registerRecipes()
	{
		RecipeRegistry.addFrostValue(Item.snowball, 25);
		RecipeRegistry.addFrostValue(Item.bucketWater, 50);
		RecipeRegistry.addFrostValue(Item.bucketMilk, 10);
		RecipeRegistry.addFrostValue(Block.snow, 33);
		RecipeRegistry.addFrostValue(Block.ice, 200);
		RecipeRegistry.addFrostValue(Block.blockSnow, 100);
		RecipeRegistry.addFrostValue(IdMap.itemCraftingItems, 0, 125);
		RecipeRegistry.addFrostValue(IdMap.itemIcicle, 150);
		
		RecipeRegistry.addFreezerRecipe(Item.bucketWater, new ItemStack(Block.ice, 2), 0.25F);
		RecipeRegistry.addFreezerRecipe(Item.snowball, new ItemStack(IdMap.itemCraftingItems, 1, 0), 0.40F);
		RecipeRegistry.addFreezerRecipe(Item.silk, new ItemStack(IdMap.itemCraftingItems, 1, 1), 0.33F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCompiledFrostBlade, new ItemStack(IdMap.itemFrozenFrostBlade), 1.0F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCompiledFrostSpade, new ItemStack(IdMap.itemFrozenFrostSpade), 1.0F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCompiledFrostPickaxe, new ItemStack(IdMap.itemFrozenFrostPickaxe), 1.0F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCompiledFrostAxe, new ItemStack(IdMap.itemFrozenFrostAxe), 1.0F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCompiledFrostHoe, new ItemStack(IdMap.itemFrozenFrostHoe), 1.0F);
		
		RecipeRegistry.addEnforcerRecipe(IdMap.itemCompiledFrostBlade, new ItemStack(IdMap.itemFrostBlade), true);
		RecipeRegistry.addEnforcerRecipe(IdMap.itemCompiledFrostSpade, new ItemStack(IdMap.itemFrostSpade), true);
		RecipeRegistry.addEnforcerRecipe(IdMap.itemCompiledFrostPickaxe, new ItemStack(IdMap.itemFrostPickaxe), true);
		RecipeRegistry.addEnforcerRecipe(IdMap.itemCompiledFrostAxe, new ItemStack(IdMap.itemFrostAxe), true);
		RecipeRegistry.addEnforcerRecipe(IdMap.itemCompiledFrostHoe, new ItemStack(IdMap.itemFrostHoe), true);
		RecipeRegistry.addEnforcerRecipe(IdMap.itemFrozenFrostBlade, new ItemStack(IdMap.itemFrostBlade), true);
		RecipeRegistry.addEnforcerRecipe(IdMap.itemFrozenFrostSpade, new ItemStack(IdMap.itemFrostSpade), true);
		RecipeRegistry.addEnforcerRecipe(IdMap.itemFrozenFrostPickaxe, new ItemStack(IdMap.itemFrostPickaxe), true);
		RecipeRegistry.addEnforcerRecipe(IdMap.itemFrozenFrostAxe, new ItemStack(IdMap.itemFrostAxe), true);
		RecipeRegistry.addEnforcerRecipe(IdMap.itemFrozenFrostHoe, new ItemStack(IdMap.itemFrostHoe), true);
		
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostBlade), new Object[] {"I", "C", "X", 'I', IdMap.itemIcicle, 'C', Block.ice, 'X', Item.stick} );
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostAxe), new Object[] {"BC", "IX", " X", 'B', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'C', Block.ice, 'I', IdMap.itemIcicle, 'X', Item.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostAxe), new Object[] {"CB", "XI", "X ", 'B', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'C', Block.ice, 'I', IdMap.itemIcicle, 'X', Item.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostPickaxe), new Object[] {"ICI", " X ", " X ", 'I', IdMap.itemIcicle, 'C', Block.ice, 'X', Item.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostSpade), new Object[] {"B", "X", "X", 'B', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'X', Item.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostHoe), new Object [] {"IC", " X", " X", 'I', IdMap.itemIcicle, 'C', Block.ice, 'X', Item.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostHoe), new Object [] {"CI", "X ", "X ", 'I', IdMap.itemIcicle, 'C', Block.ice, 'X', Item.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 2), new Object[] {"XSX", "BRB", "XSX", 'X', Item.ingotIron, 'S', Item.snowball, 'B', Block.blockSnow, 'R', Item.redstone});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 3), new Object[] {"XCX", "#D#", "XIX", 'X', Item.ingotIron, 'C', Item.coal, '#', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'D', Item.diamond, 'I', Block.ice});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 4), new Object[] {"XSX", "P P", "XSX", 'X', Item.ingotIron, 'S', Item.snowball, 'P', Block.pistonBase});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 5), new Object[] {"X X", "BSB", "XPX", 'X', Item.ingotIron, 'S', Item.snowball, 'B', Item.bucketWater, 'P', Block.pistonBase});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 6), new Object[] {"XIX", "CEC", "XBX", 'X', Item.ingotIron, 'I', IdMap.itemIcicle, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'B', Block.ice, 'E', Block.chest});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemFrostBow), new Object[] {" IS", "B S", " IS", 'I', IdMap.itemIcicle, 'B', Block.ice, 'S', new ItemStack(IdMap.itemCraftingItems, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemFrostBow), new Object[] {"SI ", "S B", "SI ", 'I', IdMap.itemIcicle, 'B', Block.ice, 'S', new ItemStack(IdMap.itemCraftingItems, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemFrostGun, 1, 1024), new Object[] {"XCS", "IBX", "XLX", 'X', Item.ingotIron, 'B', Block.ice, 'L', Item.leather, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'S', new ItemStack(IdMap.itemCraftingItems, 1, 6), 'I', IdMap.itemIcicle});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalPipe, 6), new Object[] {"XXX", "S S", "XXX", 'X', Item.ingotIron, 'S', Item.snowball});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 0), new Object[] {"XBX", "SCO", "XFX", 'X', Item.ingotIron, 'B', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'S', new ItemStack(IdMap.itemCraftingItems, 1, 6), 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'O', new ItemStack(IdMap.itemCraftingItems, 1, 4), 'F', Block.stoneOvenIdle});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 1), new Object[] {"XXX", "TCT", "XFX", 'X', Item.ingotIron, 'T', new ItemStack(IdMap.itemCraftingItems, 1, 3), 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'F', Block.stoneOvenIdle});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 2), new Object[] {"XXX", "CEO", "XFX", 'X', Item.ingotIron, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'E', new ItemStack(IdMap.itemCraftingItems, 1, 6), 'O', new ItemStack(IdMap.itemCraftingItems, 1, 4), 'F', Block.stoneOvenIdle});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 2), new Object[] {"XXX", "CEO", "XFX", 'X', Item.ingotIron, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'E', Block.chest, 'O', new ItemStack(IdMap.itemCraftingItems, 1, 4), 'F', Block.stoneOvenIdle});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 3), new Object[] {"XXX", "C S", "XPX", 'X', Item.ingotIron, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'S', new ItemStack(IdMap.itemCraftingItems, 1, 6), 'P', new ItemStack(IdMap.itemCraftingItems, 1, 5)});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 4), new Object[] {"XPX", "COS", "XFX", 'X', Item.ingotIron, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'O', new ItemStack(IdMap.itemCraftingItems, 1, 4), 'S', new ItemStack(IdMap.itemCraftingItems, 1, 6), 'F', Block.stoneOvenIdle, 'P', new ItemStack(IdMap.itemCraftingItems, 1, 5)});
	}
	
	private static void addFrostValue(ItemStack item, int time)
	{
		frostFuel.add(new Object[] {item, time});
	}
	
	public static void addFrostValue(Block block, int time)
	{
		RecipeRegistry.addFrostValue(new ItemStack(block.blockID, 1, 0), time);
	}
	
	public static void addFrostValue(Block block, int meta, int time)
	{
		RecipeRegistry.addFrostValue(new ItemStack(block.blockID, 1, meta), time);
	}
	
	public static void addFrostValue(Item item, int time)
	{
		RecipeRegistry.addFrostValue(new ItemStack(item.itemID, 1, 0), time);
	}
	
	public static void addFrostValue(Item item, int meta, int time)
	{
		RecipeRegistry.addFrostValue(new ItemStack(item.itemID, 1, meta), time);
	}
	
	public int getFrostTime(ItemStack par1)
	{
		for(int i = 0;i < frostFuel.size();i++)
		{
			Object[] obj = (Object[])frostFuel.get(i);
			ItemStack item = (ItemStack)obj[0];
			if(par1 != null && par1.itemID == item.itemID && par1.getItemDamage() == item.getItemDamage())
			{
				return (Integer)obj[1];
			}
		}
		return 0;
	}
	
	private static void addFreezerRecipe(ItemStack item, ItemStack result, float exp)
	{
		freezerRecipes.put(Arrays.asList(item.itemID, item.getItemDamage()), result);
		freezerExperiences.put(Arrays.asList(result.itemID, result.getItemDamage()), exp);
	}
	
	public static void addFreezerRecipe(Block block, ItemStack result)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(block.blockID, 1, 0), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Block block, int meta, ItemStack result)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(block.blockID, 1, meta), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Block block, ItemStack result, float exp)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(block.blockID, 1, 0), result, exp);
	}
	
	public static void addFreezerRecipe(Block block, int meta, ItemStack result, float exp)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(block.blockID, 1, meta), result, exp);
	}
	
	public static void addFreezerRecipe(Item item, ItemStack result)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(item.itemID, 1, 0), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Item item, int meta, ItemStack result)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(item.itemID, 1, meta), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Item item, ItemStack result, float exp)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(item.itemID, 1, 0), result, exp);
	}
	
	public static void addFreezerRecipe(Item item, int meta, ItemStack result, float exp)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(item.itemID, 1, meta), result, exp);
	}
	
	public ItemStack getFreezingResult(ItemStack item)
	{
		if(item == null)
		{
			return null;
		}
		if(RecipeRegistry.freezerRecipes.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
		{
			return RecipeRegistry.freezerRecipes.get(Arrays.asList(item.itemID, item.getItemDamage()));
		}
		else
		{
			return null;
		}
	}
	
	public float getFreezerExperience(ItemStack item)
	{
		if(item == null)
		{
			return 0.0f;
		}
		if(RecipeRegistry.freezerExperiences.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
		{
			return RecipeRegistry.freezerExperiences.get(Arrays.asList(item.itemID, item.getItemDamage()));
		}
		else
		{
			return 0.0f;
		}
	}
	
	private static void addEnforcerRecipe(ItemStack item, ItemStack result, boolean idOnly)
	{
		if(!idOnly)
		{
			enforcerRecipes.put(Arrays.asList(item.itemID, item.getItemDamage()), result);
		}
		else
		{
			enforcerIdOnlyRecipes.put(item.itemID, result);
		}
	}
	
	public static void addEnforcerRecipe(Block block, ItemStack result, boolean idOnly)
	{
		RecipeRegistry.addEnforcerRecipe(new ItemStack(block.blockID, 1, 0), result, idOnly);
	}
	
	public static void addEnforcerRecipe(Block block, int meta, ItemStack result, boolean idOnly)
	{
		RecipeRegistry.addEnforcerRecipe(new ItemStack(block.blockID, 1, meta), result, idOnly);
	}
	
	public static void addEnforcerRecipe(Item item, ItemStack result, boolean idOnly)
	{
		RecipeRegistry.addEnforcerRecipe(new ItemStack(item.itemID, 1, 0), result, idOnly);
	}
	
	public static void addEnforcerRecipe(Item item, int meta, ItemStack result, boolean idOnly)
	{
		RecipeRegistry.addEnforcerRecipe(new ItemStack(item.itemID, 1, meta), result, idOnly);
	}
	
	public ItemStack getEnforcingResult(ItemStack item)
	{
		if(item == null)
		{
			return null;
		}
		if(RecipeRegistry.enforcerRecipes.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
		{
			return RecipeRegistry.enforcerRecipes.get(Arrays.asList(item.itemID, item.getItemDamage()));
		}
		else if(RecipeRegistry.enforcerIdOnlyRecipes.containsKey(item.itemID))
		{
			return RecipeRegistry.enforcerIdOnlyRecipes.get(item.itemID);
		}
		else
		{
			return null;
		}
	}
}
