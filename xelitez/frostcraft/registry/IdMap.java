package xelitez.frostcraft.registry;

import xelitez.frostcraft.FrostCraft;
import xelitez.frostcraft.block.*;
import xelitez.frostcraft.item.*;
import xelitez.frostcraft.entity.*;
import xelitez.frostcraft.enums.*;
import xelitez.frostcraft.client.render.*;
import xelitez.frostcraft.tileentity.*;
import xelitez.frostcraft.world.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.client.registry.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IdMap 
{
	public int defaultIdThermalPipe = 2200;
	public int defaultIdThermalMachines = 2201;
	public int defaultIdBlockIcicle = 2202;
	
	public int defaultIdFrostBow = 2300;
	public int defaultIdFrostGun = 2301;
	public int defaultIdParticleItem = 2302;
	public int defaultIdCraftingItems = 2303;
	public int defaultIdCompiledFrostBlade = 2304;
	public int defaultIdCompiledFrostSpade = 2305;
	public int defaultIdCompiledFrostPickaxe = 2306;
	public int defaultIdCompiledFrostAxe = 2307;
	public int defaultIdCompiledFrostHoe = 2308;
	public int defaultIdFrozenFrostBlade = 2309;
	public int defaultIdFrozenFrostSpade = 2310;
	public int defaultIdFrozenFrostPickaxe = 2311;
	public int defaultIdFrozenFrostAxe = 2312;
	public int defaultIdFrozenFrostHoe = 2313;
	public int defaultIdFrostBlade = 2314;
	public int defaultIdFrostSpade = 2315;
	public int defaultIdFrostPickaxe = 2316;
	public int defaultIdFrostAxe = 2317;
	public int defaultIdFrostHoe = 2318;
	public int defaultIdIcicle = 2319;
	
	public int defaultEnforcerToolStartId = 2400;
	public int defaultMaxEnforcedItems = 25;
	public int enforcerToolStartId;
	public int MaxEnfrocerItems;
	public Item[] EnforcedTools;
	
	public static int IdThermalPipe;
	public static int IdThermalMachines;
	public static int IdBlockIcicle;
	
	public static int IdFrostBow;
	public static int IdFrostGun;
	public static int IdParticleItem;
	public static int IdCraftingItems;
	public static int IdCompiledFrostBlade;
	public static int IdCompiledFrostSpade;
	public static int IdCompiledFrostPickaxe;
	public static int IdCompiledFrostAxe;
	public static int IdCompiledFrostHoe;
	public static int IdFrozenFrostBlade;
	public static int IdFrozenFrostSpade;
	public static int IdFrozenFrostPickaxe;
	public static int IdFrozenFrostAxe;
	public static int IdFrozenFrostHoe;
	public static int IdFrostBlade;
	public static int IdFrostSpade;
	public static int IdFrostPickaxe;
	public static int IdFrostAxe;
	public static int IdFrostHoe;
	public static int IdIcicle;
	
	public static Block BlockThermalPipe;
	public static Block BlockThermalMachines;
	public static Block BlockIcicle;
	
	public static Item itemFrostBow;
	public static Item itemFrostGun;
	public static Item itemParticleItem;
	public static Item itemCraftingItems;
	public static Item itemCompiledFrostBlade;
	public static Item itemCompiledFrostSpade;
	public static Item itemCompiledFrostPickaxe;
	public static Item itemCompiledFrostAxe;
	public static Item itemCompiledFrostHoe;
	public static Item itemFrozenFrostBlade;
	public static Item itemFrozenFrostSpade;
	public static Item itemFrozenFrostPickaxe;
	public static Item itemFrozenFrostAxe;
	public static Item itemFrozenFrostHoe;
	public static Item itemFrostBlade;
	public static Item itemFrostSpade;
	public static Item itemFrostPickaxe;
	public static Item itemFrostAxe;
	public static Item itemFrostHoe;
	public static Item itemIcicle;
	
	/**
	 * Initialise all FrostCraft Blocks and additions
	 */
	public void initialiseBlocks()
	{
		BlockThermalPipe = new BlockThermalPipe(IdThermalPipe, Material.iron).setHardness(0.4F).setStepSound(Block.soundMetalFootstep).setBlockName("ThermalPipe");
		BlockThermalMachines = new BlockThermalMachines(IdThermalMachines, Material.iron).setHardness(2.5F).setStepSound(Block.soundMetalFootstep).setBlockName("ThermalMachines").setCreativeTab(CreativeTabs.FCMechanical);
		BlockIcicle = new BlockIcicle(IdBlockIcicle, Material.ice).setHardness(0.2F).setStepSound(Block.soundGlassFootstep).setBlockName("Icicle");
		GameRegistry.registerBlock(BlockThermalPipe, "ThermalPipe");
		GameRegistry.registerBlock(BlockThermalMachines, ItemThermalMachines.class, "ThermalPump");
		GameRegistry.registerBlock(BlockIcicle, "Icicle");
		LanguageRegistry.addName(BlockThermalPipe, "Thermal Pipe");
		LanguageRegistry.addName(new ItemStack(BlockThermalMachines, 1, 0), "Thermal Pump");
		LanguageRegistry.addName(new ItemStack(BlockThermalMachines, 1, 1), "Frost Furnace");
		LanguageRegistry.addName(new ItemStack(BlockThermalMachines, 1, 2), "Frost Generator");
		LanguageRegistry.addName(new ItemStack(BlockThermalMachines, 1, 3), "Freezer");
		LanguageRegistry.addName(new ItemStack(BlockThermalMachines, 1, 4), "Frost Enforcer");
		LanguageRegistry.addName(BlockIcicle, "BlockIcicle");
	}
	
	/**
	 * Initialise all FrostCraft Items and additions
	 */
	public void initialiseItems()
	{
		itemFrostBow = new ItemFrostBow(IdFrostBow).setIconIndex(1).setItemName("FrostBow");
		itemFrostGun = new ItemFrostGun(IdFrostGun).setIconIndex(0).setItemName("FrostGun");
		itemParticleItem = new DummyParticleItem(IdParticleItem).setItemName("DummyParticleItem");
		itemCraftingItems = new CraftingItems(IdCraftingItems);
		itemCompiledFrostBlade = new ItemFrostBlade(IdCompiledFrostBlade, FrostToolMaterial.FROST_COMPILED).setIconIndex(4).setItemName("CompieledFrostBlade");
		itemCompiledFrostSpade = new ItemFrostSpade(IdCompiledFrostSpade, FrostToolMaterial.FROST_COMPILED).setIconIndex(20).setItemName("CompiledFrostSpade");
		itemCompiledFrostPickaxe = new ItemFrostPickaxe(IdCompiledFrostPickaxe, FrostToolMaterial.FROST_COMPILED).setIconIndex(36).setItemName("CompiledFrostPickaxe");
		itemCompiledFrostAxe = new ItemFrostAxe(IdCompiledFrostAxe, FrostToolMaterial.FROST_COMPILED).setIconIndex(52).setItemName("CompiledFrostAxe");
		itemCompiledFrostHoe = new ItemFrostHoe(IdCompiledFrostHoe, FrostToolMaterial.FROST_COMPILED).setIconIndex(68).setItemName("CompiledFrostHoe");
		itemFrozenFrostBlade = new ItemFrostBlade(IdFrozenFrostBlade, FrostToolMaterial.FROST_FROZEN).setIconIndex(3).setItemName("FrozenFrostBlade");
		itemFrozenFrostSpade = new ItemFrostSpade(IdFrozenFrostSpade, FrostToolMaterial.FROST_FROZEN).setIconIndex(19).setItemName("FrozenFrostSpade");
		itemFrozenFrostPickaxe = new ItemFrostPickaxe(IdFrozenFrostPickaxe, FrostToolMaterial.FROST_FROZEN).setIconIndex(35).setItemName("FrozenFrostPickaxe");
		itemFrozenFrostAxe = new ItemFrostAxe(IdFrozenFrostAxe, FrostToolMaterial.FROST_FROZEN).setIconIndex(51).setItemName("FrozenFrostAxe");
		itemFrozenFrostHoe = new ItemFrostHoe(IdFrozenFrostHoe, FrostToolMaterial.FROST_FROZEN).setIconIndex(67).setItemName("FrozenFrostHoe");
		itemFrostBlade = new ItemFrostBlade(IdFrostBlade, FrostToolMaterial.FROST).setIconIndex(2).setItemName("FrostBlade");
		itemFrostSpade = new ItemFrostSpade(IdFrostSpade, FrostToolMaterial.FROST).setIconIndex(18).setItemName("FrostSpade");
		itemFrostPickaxe = new ItemFrostPickaxe(IdFrostPickaxe, FrostToolMaterial.FROST).setIconIndex(34).setItemName("FrostPickaxe");
		itemFrostAxe = new ItemFrostAxe(IdFrostAxe, FrostToolMaterial.FROST).setIconIndex(50).setItemName("FrostAxe");
		itemFrostHoe = new ItemFrostHoe(IdFrostHoe, FrostToolMaterial.FROST).setIconIndex(66).setItemName("FrostHoe");
		itemIcicle = new ItemIcicle(IdIcicle).setIconIndex(5).setItemName("Icicle");
		
		GameRegistry.registerItem(itemFrostBow, "FrostBow");
		GameRegistry.registerItem(itemFrostGun, "FrostGun");
		GameRegistry.registerItem(itemParticleItem, "DummyParticleItem");
		GameRegistry.registerItem(itemCraftingItems, "CraftingItems");
		GameRegistry.registerItem(itemCompiledFrostBlade, "CompiledFrostBlade");
		GameRegistry.registerItem(itemCompiledFrostSpade, "CompiledFrostSpade");
		GameRegistry.registerItem(itemCompiledFrostPickaxe, "CompiledFrostPickaxe");
		GameRegistry.registerItem(itemCompiledFrostAxe, "CompiledFrostAxe");
		GameRegistry.registerItem(itemCompiledFrostHoe, "CompiledFrostHoe");
		GameRegistry.registerItem(itemFrozenFrostBlade, "FrozenFrostBlade");
		GameRegistry.registerItem(itemFrozenFrostSpade, "FrozenFrostSpade");
		GameRegistry.registerItem(itemFrozenFrostPickaxe, "FrozenFrostPickaxe");
		GameRegistry.registerItem(itemFrozenFrostAxe, "FrozenFrostAxe");
		GameRegistry.registerItem(itemFrozenFrostHoe, "FrozenFrostHoe");
		GameRegistry.registerItem(itemFrostBlade, "FrostBlade");
		GameRegistry.registerItem(itemFrostSpade, "FrostSpade");
		GameRegistry.registerItem(itemFrostPickaxe, "FrostPickaxe");
		GameRegistry.registerItem(itemFrostAxe, "FrostAxe");
		GameRegistry.registerItem(itemFrostHoe, "FrostHoe");
		GameRegistry.registerItem(itemIcicle, "ItemIcicle");
		
		LanguageRegistry.addName(itemFrostBow, "Frost Bow");
		LanguageRegistry.addName(itemFrostGun, "Frost Gun");
		LanguageRegistry.addName(itemCompiledFrostBlade, "Compiled Frost Sword");
		LanguageRegistry.addName(itemCompiledFrostSpade, "Compiled Frost Shovel");
		LanguageRegistry.addName(itemCompiledFrostPickaxe, "Compiled Frost Pickaxe");
		LanguageRegistry.addName(itemCompiledFrostAxe, "Compiled Frost Axe");
		LanguageRegistry.addName(itemCompiledFrostHoe, "Compiled Frost Hoe");
		LanguageRegistry.addName(itemFrozenFrostBlade, "Frost Sword");
		LanguageRegistry.addName(itemFrozenFrostSpade, "Frost Shovel");
		LanguageRegistry.addName(itemFrozenFrostPickaxe, "Frost Pickaxe");
		LanguageRegistry.addName(itemFrozenFrostAxe, "Frost Axe");
		LanguageRegistry.addName(itemFrozenFrostHoe, "Frost Hoe");
		LanguageRegistry.addName(itemFrostBlade, "Enforced Frost Blade");
		LanguageRegistry.addName(itemFrostSpade, "Enforced Frost Shovel");
		LanguageRegistry.addName(itemFrostPickaxe, "Enforced Frost Pickaxe");
		LanguageRegistry.addName(itemFrostAxe, "Enforced Frost Axe");
		LanguageRegistry.addName(itemFrostHoe, "Enforced Frost Hoe");
		LanguageRegistry.addName(itemIcicle, "Icicle");
		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 0), "Iceball");
		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 1), "Ice-Covered String");
	}

	/**
	 * Initialise all FrostCraft entities and additions
	 */
	public void initialiseEntities() 
	{
		GameRegistry.registerTileEntity(TileEntityThermalPipe.class, "Thermal Pipe");
		GameRegistry.registerTileEntity(TileEntityThermalMachines.class, "Thermal Machines");
		GameRegistry.registerTileEntity(TileEntityThermalPump.class, "Thermal Pump");
		GameRegistry.registerTileEntity(TileEntityFrostFurnace.class, "Frost Furnace");
		GameRegistry.registerTileEntity(TileEntityFrostGenerator.class, "Frost Generator");
		GameRegistry.registerTileEntity(TileEntityFreezer.class, "Freezer");
		GameRegistry.registerTileEntity(TileEntityFrostEnforcer.class, "Frost Enforcer");
		EntityRegistry.registerGlobalEntityID(EntityFrostArrow.class, "FrostArrow", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFrostArrow.class, "FrostArrow", 0, FrostCraft.instance, 64, 20, false);
		EntityRegistry.registerGlobalEntityID(EntityFrostShot.class, "FrostShot", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFrostShot.class, "FrostShot", 1, FrostCraft.instance, 64, 20, false);
	}
	
	public void initialiseWorld()
	{
		GameRegistry.registerWorldGenerator(new WorldGenIcicles());
	}
	
	public void initialiseEnfrocerItems()
	{
		this.EnforcedTools = new Item[this.MaxEnfrocerItems];
		boolean isLeft = true;
		for(int i = 0;i < Item.itemsList.length;i++)
		{
			Item item = Item.itemsList[i];
			if(item != null && (item instanceof ItemTool || item instanceof ItemSword || item instanceof ItemHoe) && !(item instanceof ItemFrostTool || item instanceof ItemFrostBlade || item instanceof ItemFrostHoe))
			{
				this.registerEnforcerTool(item);
			}
			for(int c = 0;c < this.EnforcedTools.length;c++)
			{
				if(this.EnforcedTools[c] == null)
				{
					isLeft = true;
					break;
				}
				isLeft = false;
			}
			if(!isLeft)
			{
				break;
			}
		}
	}
	
	private void registerEnforcerTool(Item item)
	{
		for(int i = 0;i < this.EnforcedTools.length;i++)
		{
			if(this.EnforcedTools[i] == null)
			{
				EnumRenderType type = EnumRenderType.SWORD;
				if(item instanceof ItemSword)
				{
					type = EnumRenderType.SWORD;
				}
				else if(item instanceof ItemSpade)
				{
					type = EnumRenderType.SHOVEL;
				}
				else if(item instanceof ItemPickaxe)
				{
					type = EnumRenderType.PICKAXE;
				}
				else if(item instanceof ItemAxe)
				{
					type = EnumRenderType.AXE;
				}			
				else if(item instanceof ItemHoe)
				{
					type = EnumRenderType.HOE;
				}
				this.EnforcedTools[i] = new ItemFrostEnforced(this.enforcerToolStartId + i, item, type).setItemName("Enforced" + item.getItemName());
				GameRegistry.registerItem(this.EnforcedTools[i], "Enforced" + item.getItemName());
				LanguageRegistry.addName(this.EnforcedTools[i], "Enforced " + StatCollector.translateToLocal(item.getStatName()));
				RecipeRegistry.addEnforcerRecipe(item, new ItemStack(this.EnforcedTools[i]));
				break;
			}
		}
	}
	
	/**
	 * Initialise all FrostCraft renderers
	 */
	@SideOnly(Side.CLIENT)
	public void initialiseRenderers()
	{
		RenderingRegistry.registerBlockHandler(2200, new RendererThermalPipe());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostArrow.class, new RenderFrostArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostShot.class, new RenderFrostShot(0.5f));
		MinecraftForgeClient.registerItemRenderer(this.itemFrostBow.itemID, new RenderFrostBow());
	}
}
