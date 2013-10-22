package xelitez.frostcraft.plugins;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import xelitez.frostcraft.client.gui.GuiFreezer;
import xelitez.frostcraft.registry.RecipeRegistry;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import codechicken.core.ReflectionManager;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class FreezerRecipeHandler extends TemplateRecipeHandler
{
	public class FreezingPair extends CachedRecipe
	{
		public FreezingPair(ItemStack ingred, ItemStack result, ItemStack containerItem)
		{
			ingred.stackSize = 1;
			this.ingred = new PositionedStack(ingred, 48, 6);
			this.result = new PositionedStack(result, 102, 6);
			if(containerItem != null)
			{
				this.result2 = new PositionedStack(containerItem, 102, 42);
			}
		}
		
		public PositionedStack getIngredient()
		{
			int cycle = cycleticks / 48;
			if(ingred.item.getItemDamage() == -1)
			{
				PositionedStack stack = ingred.copy();
				int maxDamage = 0;
				do
				{
					maxDamage++;
					stack.item.setItemDamage(maxDamage);
				}
				while(NEIClientUtils.isValidItem(stack.item));
				
				stack.item.setItemDamage(cycle % maxDamage);
				return stack;
			}
			return ingred;
		}
		
		public PositionedStack getResult()
		{
			return result;
		}
		
		public PositionedStack getOtherStack()
		{
			return result2;
		}
		
		PositionedStack ingred;
		PositionedStack result;
		PositionedStack result2;
	}
	
	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return GuiFreezer.class;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if(outputId.equals("freezing") && getClass() == FreezerRecipeHandler.class)//don't want subclasses getting a hold of this
		{
			HashMap<List<Integer>, ItemStack> recipes = null;
			try
			{
				try
				{
					recipes = ReflectionManager.getField(RecipeRegistry.class, HashMap.class, RecipeRegistry.registry(), 1);
				}
				catch (ArrayIndexOutOfBoundsException e) {}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}
			if(recipes == null)return;
			for(Entry<List<Integer>, ItemStack> recipe : recipes.entrySet())
			{
				ItemStack item = recipe.getValue();
				arecipes.add(new FreezingPair(new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)), item, new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)).getItem().getContainerItemStack(new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)))));
			}
		}
		else
		{
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		HashMap<List<Integer>, ItemStack> recipes = null;
		try
		{
			try
			{
				recipes = ReflectionManager.getField(RecipeRegistry.class, HashMap.class, RecipeRegistry.registry(), 1);
			}
			catch (ArrayIndexOutOfBoundsException e) {}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		if(recipes == null)return;
		for(Entry<List<Integer>, ItemStack> recipe : recipes.entrySet())
		{
			ItemStack item = recipe.getValue();
			if(NEIClientUtils.areStacksSameType(item, result))
			{
				arecipes.add(new FreezingPair(new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)), item, new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)).getItem().getContainerItemStack(new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)))));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{
		HashMap<List<Integer>, ItemStack> recipes = null;
		try
		{
			try
			{
				recipes = (HashMap<List<Integer>, ItemStack>) ReflectionManager.getField(RecipeRegistry.class, HashMap.class, RecipeRegistry.registry(), 1);
			}
			catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		if(recipes == null)return;
		for(Entry<List<Integer>, ItemStack> recipe : recipes.entrySet())
		{
			ItemStack item = recipe.getValue();
			if(ingredient.itemID == recipe.getKey().get(0) && ingredient.getItemDamage() == recipe.getKey().get(1))
			{
				arecipes.add(new FreezingPair(ingredient, item, ingredient.getItem().getContainerItemStack(ingredient)));
			}
		}
	}
	
	@Override
	public void loadTransferRects()
	{
		transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "freezing"));
	}

	@Override
	public String getRecipeName() 
	{
		return "Freezing";
	}
	
	@Override
	public String getOverlayIdentifier()
	{
		return "freezing";
	}

	@Override
	public String getGuiTexture() 
	{
		return "frostcraft:textures/freezer.png";
	}
	
	public void drawExtras(GuiContainerManager gui, int recipe)
	{
		drawProgressBar(49, 25, 176, 0, 14, 14, 48, 7);
		drawProgressBar(71, 23, 176, 14, 24, 16, 48, 0);
	}

}
