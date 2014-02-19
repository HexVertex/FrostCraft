package xelitez.frostcraft.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegistry 
{
	private static final RecipeRegistry instance = new RecipeRegistry();
	
	public static HashMap<List<Object>, ItemStack> freezerRecipes = new HashMap<List<Object>, ItemStack>();
	public static HashMap<List<Object>, Float> freezerExperiences = new HashMap<List<Object>, Float>();
	
	public static HashMap<List<Object>, ItemStack> enforcerRecipes = new HashMap<List<Object>, ItemStack>();
	public static HashMap<Item, ItemStack> enforcerIdOnlyRecipes = new HashMap<Item, ItemStack>();
	
	public static List<Object[]> frostFuel = new ArrayList<Object[]>();
	
	public static RecipeRegistry registry()
	{
		return instance;
	}
	
	public void registerRecipes()
	{
		RecipeRegistry.addFrostValue(Items.snowball, 25);
		RecipeRegistry.addFrostValue(Items.water_bucket, 50);
		RecipeRegistry.addFrostValue(Items.milk_bucket, 10);
		RecipeRegistry.addFrostValue(Blocks.snow_layer, 33);
		RecipeRegistry.addFrostValue(Blocks.ice, 200);
		RecipeRegistry.addFrostValue(Blocks.snow, 100);
		RecipeRegistry.addFrostValue(IdMap.itemCraftingItems, 0, 125);
		RecipeRegistry.addFrostValue(IdMap.itemIcicle, 150);
		
		RecipeRegistry.addFreezerRecipe(Items.water_bucket, new ItemStack(Blocks.ice, 2), 0.25F);
		RecipeRegistry.addFreezerRecipe(Items.snowball, new ItemStack(IdMap.itemCraftingItems, 1, 0), 0.40F);
		RecipeRegistry.addFreezerRecipe(Items.string, new ItemStack(IdMap.itemCraftingItems, 1, 1), 0.33F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCompiledFrostBlade, new ItemStack(IdMap.itemFrozenFrostBlade), 1.0F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCompiledFrostSpade, new ItemStack(IdMap.itemFrozenFrostSpade), 1.0F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCompiledFrostPickaxe, new ItemStack(IdMap.itemFrozenFrostPickaxe), 1.0F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCompiledFrostAxe, new ItemStack(IdMap.itemFrozenFrostAxe), 1.0F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCompiledFrostHoe, new ItemStack(IdMap.itemFrozenFrostHoe), 1.0F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCraftingItems, 7, new ItemStack(IdMap.itemIcePop, 1, 0), 0.33F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCraftingItems, 8, new ItemStack(IdMap.itemIcePop, 1, 1), 0.33F);
		RecipeRegistry.addFreezerRecipe(IdMap.itemCraftingItems, 9, new ItemStack(IdMap.itemIcePop, 1, 2), 0.33F);
		
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
		
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostBlade), new Object[] {"I", "C", "X", 'I', IdMap.itemIcicle, 'C', Blocks.ice, 'X', Items.stick} );
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostAxe), new Object[] {"BC", "IX", " X", 'B', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'C', Blocks.ice, 'I', IdMap.itemIcicle, 'X', Items.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostAxe), new Object[] {"CB", "XI", "X ", 'B', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'C', Blocks.ice, 'I', IdMap.itemIcicle, 'X', Items.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostPickaxe), new Object[] {"ICI", " X ", " X ", 'I', IdMap.itemIcicle, 'C', Blocks.ice, 'X', Items.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostSpade), new Object[] {"B", "X", "X", 'B', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'X', Items.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostHoe), new Object [] {"IC", " X", " X", 'I', IdMap.itemIcicle, 'C', Blocks.ice, 'X', Items.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCompiledFrostHoe), new Object [] {"CI", "X ", "X ", 'I', IdMap.itemIcicle, 'C', Blocks.ice, 'X', Items.stick});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 2), new Object[] {"XSX", "BRB", "XSX", 'X', Items.iron_ingot, 'S', Items.snowball, 'B', Blocks.snow, 'R', Items.redstone});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 3), new Object[] {"XCX", "#D#", "XIX", 'X', Items.iron_ingot, 'C', Items.coal, '#', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'D', Items.diamond, 'I', Blocks.ice});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 4), new Object[] {"XSX", "P P", "XSX", 'X', Items.iron_ingot, 'S', Items.snowball, 'P', Blocks.piston});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 5), new Object[] {"X X", "BSB", "XPX", 'X', Items.iron_ingot, 'S', Items.snowball, 'B', Items.water_bucket, 'P', Blocks.piston});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 6), new Object[] {"XIX", "CEC", "XBX", 'X', Items.iron_ingot, 'I', IdMap.itemIcicle, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'B', Blocks.ice, 'E', Blocks.chest});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemFrostBow), new Object[] {" IS", "B S", " IS", 'I', IdMap.itemIcicle, 'B', Blocks.ice, 'S', new ItemStack(IdMap.itemCraftingItems, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemFrostBow), new Object[] {"SI ", "S B", "SI ", 'I', IdMap.itemIcicle, 'B', Blocks.ice, 'S', new ItemStack(IdMap.itemCraftingItems, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemFrostGun, 1, 1024), new Object[] {"XCS", "IBX", "XLX", 'X', Items.iron_ingot, 'B', Blocks.ice, 'L', Items.leather, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'S', new ItemStack(IdMap.itemCraftingItems, 1, 6), 'I', IdMap.itemIcicle});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalPipe, 6), new Object[] {"XXX", "S S", "XXX", 'X', Items.iron_ingot, 'S', Items.snowball});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 0), new Object[] {"XBX", "SCO", "XFX", 'X', Items.iron_ingot, 'B', new ItemStack(IdMap.itemCraftingItems, 1, 0), 'S', new ItemStack(IdMap.itemCraftingItems, 1, 6), 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'O', new ItemStack(IdMap.itemCraftingItems, 1, 4), 'F', Blocks.furnace});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 1), new Object[] {"XXX", "TCT", "XFX", 'X', Items.iron_ingot, 'T', new ItemStack(IdMap.itemCraftingItems, 1, 3), 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'F', Blocks.furnace});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 2), new Object[] {"XXX", "CEO", "XFX", 'X', Items.iron_ingot, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'E', new ItemStack(IdMap.itemCraftingItems, 1, 6), 'O', new ItemStack(IdMap.itemCraftingItems, 1, 4), 'F', Blocks.furnace});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 2), new Object[] {"XXX", "CEO", "XFX", 'X', Items.iron_ingot, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'E', Blocks.chest, 'O', new ItemStack(IdMap.itemCraftingItems, 1, 4), 'F', Blocks.furnace});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 3), new Object[] {"XXX", "C S", "XPX", 'X', Items.iron_ingot, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'S', new ItemStack(IdMap.itemCraftingItems, 1, 6), 'P', new ItemStack(IdMap.itemCraftingItems, 1, 5)});
		GameRegistry.addRecipe(new ItemStack(IdMap.blockThermalMachines, 1, 4), new Object[] {"XPX", "COS", "XFX", 'X', Items.iron_ingot, 'C', new ItemStack(IdMap.itemCraftingItems, 1, 2), 'O', new ItemStack(IdMap.itemCraftingItems, 1, 4), 'S', new ItemStack(IdMap.itemCraftingItems, 1, 6), 'F', Blocks.furnace, 'P', new ItemStack(IdMap.itemCraftingItems, 1, 5)});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 7), new Object[] {"S", "B", 'S', Items.stick, 'B', Items.water_bucket});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 8), new Object[] {"S", "B", 'S', Items.apple, 'B', new ItemStack(IdMap.itemCraftingItems, 1, 7)});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 8), new Object[] {"A", "S", "B", 'A', Items.apple, 'S', Items.stick, 'B', Items.water_bucket});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 9), new Object[] {"S", "B", 'S', new ItemStack(Items.dye, 1, 3), 'B', new ItemStack(IdMap.itemCraftingItems, 1, 7)});
		GameRegistry.addRecipe(new ItemStack(IdMap.itemCraftingItems, 1, 9), new Object[] {"C", "S", "B", 'C', new ItemStack(Items.dye, 1, 3), 'S', Items.stick, 'B', Items.water_bucket});
	}
	
	private static void addFrostValue(ItemStack item, int time)
	{
		frostFuel.add(new Object[] {item, time});
	}
	
	public static void addFrostValue(Block block, int time)
	{
		RecipeRegistry.addFrostValue(new ItemStack(block, 1, 0), time);
	}
	
	public static void addFrostValue(Block block, int meta, int time)
	{
		RecipeRegistry.addFrostValue(new ItemStack(block, 1, meta), time);
	}
	
	public static void addFrostValue(Item item, int time)
	{
		RecipeRegistry.addFrostValue(new ItemStack(item, 1, 0), time);
	}
	
	public static void addFrostValue(Item item, int meta, int time)
	{
		RecipeRegistry.addFrostValue(new ItemStack(item, 1, meta), time);
	}
	
	public int getFrostTime(ItemStack par1)
	{
		for(int i = 0;i < frostFuel.size();i++)
		{
			Object[] obj = (Object[])frostFuel.get(i);
			ItemStack item = (ItemStack)obj[0];
			if(par1 != null && par1.getItem() == item.getItem() && par1.getItemDamage() == item.getItemDamage())
			{
				return (Integer)obj[1];
			}
		}
		return 0;
	}
	
	private static void addFreezerRecipe(ItemStack item, ItemStack result, float exp)
	{
		freezerRecipes.put(Arrays.asList(item.getItem(), item.getItemDamage()), result);
		freezerExperiences.put(Arrays.asList(result.getItem(), result.getItemDamage()), exp);
	}
	
	public static void addFreezerRecipe(Block block, ItemStack result)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(block, 1, 0), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Block block, int meta, ItemStack result)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(block, 1, meta), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Block block, ItemStack result, float exp)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(block, 1, 0), result, exp);
	}
	
	public static void addFreezerRecipe(Block block, int meta, ItemStack result, float exp)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(block, 1, meta), result, exp);
	}
	
	public static void addFreezerRecipe(Item item, ItemStack result)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(item, 1, 0), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Item item, int meta, ItemStack result)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(item, 1, meta), result, 0.0F);
	}
	
	public static void addFreezerRecipe(Item item, ItemStack result, float exp)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(item, 1, 0), result, exp);
	}
	
	public static void addFreezerRecipe(Item item, int meta, ItemStack result, float exp)
	{
		RecipeRegistry.addFreezerRecipe(new ItemStack(item, 1, meta), result, exp);
	}
	
	public ItemStack getFreezingResult(ItemStack item)
	{
		if(item == null)
		{
			return null;
		}
		if(RecipeRegistry.freezerRecipes.containsKey(Arrays.asList(item.getItem(), item.getItemDamage())))
		{
			return RecipeRegistry.freezerRecipes.get(Arrays.asList(item.getItem(), item.getItemDamage()));
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
		if(RecipeRegistry.freezerExperiences.containsKey(Arrays.asList(item.getItem(), item.getItemDamage())))
		{
			return RecipeRegistry.freezerExperiences.get(Arrays.asList(item.getItem(), item.getItemDamage()));
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
			enforcerRecipes.put(Arrays.asList(item.getItem(), item.getItemDamage()), result);
		}
		else
		{
			enforcerIdOnlyRecipes.put(item.getItem(), result);
		}
	}
	
	public static void addEnforcerRecipe(Block block, ItemStack result, boolean idOnly)
	{
		RecipeRegistry.addEnforcerRecipe(new ItemStack(block, 1, 0), result, idOnly);
	}
	
	public static void addEnforcerRecipe(Block block, int meta, ItemStack result, boolean idOnly)
	{
		RecipeRegistry.addEnforcerRecipe(new ItemStack(block, 1, meta), result, idOnly);
	}
	
	public static void addEnforcerRecipe(Item item, ItemStack result, boolean idOnly)
	{
		RecipeRegistry.addEnforcerRecipe(new ItemStack(item, 1, 0), result, idOnly);
	}
	
	public static void addEnforcerRecipe(Item item, int meta, ItemStack result, boolean idOnly)
	{
		RecipeRegistry.addEnforcerRecipe(new ItemStack(item, 1, meta), result, idOnly);
	}
	
	public ItemStack getEnforcingResult(ItemStack item)
	{
		if(item == null)
		{
			return null;
		}
		if(RecipeRegistry.enforcerRecipes.containsKey(Arrays.asList(item.getItem(), item.getItemDamage())))
		{
			return RecipeRegistry.enforcerRecipes.get(Arrays.asList(item.getItem(), item.getItemDamage()));
		}
		else if(RecipeRegistry.enforcerIdOnlyRecipes.containsKey(item.getItem()))
		{
			return RecipeRegistry.enforcerIdOnlyRecipes.get(item.getItem());
		}
		else
		{
			return null;
		}
	}
}
