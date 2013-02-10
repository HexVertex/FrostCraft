package xelitez.frostcraft.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeRegistry 
{
	private static final RecipeRegistry instance = new RecipeRegistry();
	
	public static HashMap<List<Integer>, ItemStack> freezerRecipes = new HashMap<List<Integer>, ItemStack>();
	public static HashMap<List<Integer>, Float> freezerExperiences = new HashMap<List<Integer>, Float>();
	
	public static List frostFuel = new ArrayList();
	
	public static RecipeRegistry registry()
	{
		return instance;
	}
	
	static
	{
		addFrostValue(Item.snowball, 25);
		addFrostValue(Item.bucketWater, 50);
		addFrostValue(Item.bucketMilk, 10);
		addFrostValue(Block.snow, 33);
		addFrostValue(Block.ice, 200);
		addFrostValue(Block.blockSnow, 100);
		addFrostValue(IdMap.itemCraftingItems, 1, 100);
		addFrostValue(IdMap.itemCraftingItems, 0, 125);
		addFreezerRecipe(Item.bucketWater, new ItemStack(Block.ice, 2), 0.25F);
		addFreezerRecipe(Item.snowball, new ItemStack(IdMap.itemCraftingItems, 1, 0), 0.40F);
		addFreezerRecipe(Item.silk, new ItemStack(IdMap.itemCraftingItems, 1, 1), 0.33F);
	}
	
	private static void addFrostValue(ItemStack item, int time)
	{
		frostFuel.add(new Object[] {item, time});
	}
	
	public static void addFrostValue(Block block, int time)
	{
		instance.addFrostValue(new ItemStack(block.blockID, 1, 0), time);
	}
	
	public static void addFrostValue(Block block, int meta, int time)
	{
		instance.addFrostValue(new ItemStack(block.blockID, 1, meta), time);
	}
	
	public static void addFrostValue(Item item, int time)
	{
		instance.addFrostValue(new ItemStack(item.itemID, 1, 0), time);
	}
	
	public static void addFrostValue(Item item, int meta, int time)
	{
		instance.addFrostValue(new ItemStack(item.itemID, 1, meta), time);
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
		instance.addFreezerRecipe(new ItemStack(block.blockID, 1, 0), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Block block, int meta, ItemStack result)
	{
		instance.addFreezerRecipe(new ItemStack(block.blockID, 1, meta), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Block block, ItemStack result, float exp)
	{
		instance.addFreezerRecipe(new ItemStack(block.blockID, 1, 0), result, exp);
	}
	
	public static void addFreezerRecipe(Block block, int meta, ItemStack result, float exp)
	{
		instance.addFreezerRecipe(new ItemStack(block.blockID, 1, meta), result, exp);
	}
	
	public static void addFreezerRecipe(Item item, ItemStack result)
	{
		instance.addFreezerRecipe(new ItemStack(item.itemID, 1, 0), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Item item, int meta, ItemStack result)
	{
		instance.addFreezerRecipe(new ItemStack(item.itemID, 1, meta), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Item item, ItemStack result, float exp)
	{
		instance.addFreezerRecipe(new ItemStack(item.itemID, 1, 0), result, exp);
	}
	
	public static void addFreezerRecipe(Item item, int meta, ItemStack result, float exp)
	{
		instance.addFreezerRecipe(new ItemStack(item.itemID, 1, meta), result, exp);
	}
	
	public ItemStack getFreezingResult(ItemStack item)
	{
		if(item == null)
		{
			return null;
		}
		if(this.freezerRecipes.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
		{
			return this.freezerRecipes.get(Arrays.asList(item.itemID, item.getItemDamage()));
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
		if(this.freezerExperiences.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
		{
			return this.freezerExperiences.get(Arrays.asList(item.itemID, item.getItemDamage()));
		}
		else
		{
			return 0.0f;
		}
	}
}
