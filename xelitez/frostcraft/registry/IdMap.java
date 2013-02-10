package xelitez.frostcraft.registry;

import xelitez.frostcraft.FrostCraft;
import xelitez.frostcraft.block.*;
import xelitez.frostcraft.item.*;
import xelitez.frostcraft.entity.*;
import xelitez.frostcraft.client.render.*;
import xelitez.frostcraft.tileentity.*;
import xelitez.frostcraft.registry.CreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
	
	public int defaultIdFrostBow = 2300;
	public int defaultIdFrostGun = 2301;
	public int defaultIdParticleItem = 2302;
	public int defaultIdCraftingItems = 2303;
	
	public static int IdThermalPipe;
	public static int IdThermalMachines;
	
	public static int IdFrostBow;
	public static int IdFrostGun;
	public static int IdParticleItem;
	public static int IdCraftingItems;
	
	public static Block BlockThermalPipe;
	public static Block BlockThermalMachines;
	
	public static Item itemFrostBow;
	public static Item itemFrostGun;
	public static Item itemParticleItem;
	public static Item itemCraftingItems;
	
	public void initializeBlocks()
	{
		BlockThermalPipe = new BlockThermalPipe(IdThermalPipe, Material.iron).setHardness(0.4F).setStepSound(Block.soundMetalFootstep).setBlockName("ThermalPipe");
		BlockThermalMachines = new BlockThermalMachines(IdThermalMachines, Material.iron).setHardness(2.5F).setStepSound(Block.soundMetalFootstep).setBlockName("ThermalMachines").setCreativeTab(CreativeTabs.FCMechanical);;
		GameRegistry.registerBlock(BlockThermalPipe, "ThermalPipe");
		GameRegistry.registerBlock(BlockThermalMachines, ItemThermalMachines.class, "ThermalPump");
		LanguageRegistry.addName(BlockThermalPipe, "Thermal Pipe");
		LanguageRegistry.addName(new ItemStack(BlockThermalMachines, 1, 0), "Thermal Pump");
		LanguageRegistry.addName(new ItemStack(BlockThermalMachines, 1, 1), "Frost Furnace");
		LanguageRegistry.addName(new ItemStack(BlockThermalMachines, 1, 2), "Frost Generator");
		LanguageRegistry.addName(new ItemStack(BlockThermalMachines, 1, 3), "Freezer");
	}
	
	public void initializeItems()
	{
		itemFrostBow = new ItemFrostBow(IdFrostBow).setIconIndex(1).setItemName("FrostBow");
		itemFrostGun = new ItemFrostGun(IdFrostGun).setIconIndex(0).setItemName("FrostGun");
		itemParticleItem = new DummyParticleItem(IdParticleItem).setItemName("DummyParticleItem");
		itemCraftingItems = new CraftingItems(IdCraftingItems);
		GameRegistry.registerItem(itemFrostBow, "FrostBow");
		GameRegistry.registerItem(itemFrostGun, "FrostGun");
		GameRegistry.registerItem(itemParticleItem, "DummyParticleItem");
		GameRegistry.registerItem(itemCraftingItems, "CraftingItems");
		LanguageRegistry.addName(itemFrostBow, "Frost Bow");
		LanguageRegistry.addName(itemFrostGun, "Frost Gun");
		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 0), "Iceball");
		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 1), "Ice-Covered String");
	}

	public void initializeEntities() 
	{
		GameRegistry.registerTileEntity(TileEntityThermalPipe.class, "Thermal Pipe");
		GameRegistry.registerTileEntity(TileEntityThermalMachines.class, "Thermal Machines");
		GameRegistry.registerTileEntity(TileEntityThermalPump.class, "Thermal Pump");
		GameRegistry.registerTileEntity(TileEntityFrostFurnace.class, "Frost Furnace");
		GameRegistry.registerTileEntity(TileEntityFrostGenerator.class, "Frost Generator");
		GameRegistry.registerTileEntity(TileEntityFreezer.class, "Freezer");
		EntityRegistry.registerGlobalEntityID(EntityFrostArrow.class, "FrostArrow", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFrostArrow.class, "FrostArrow", 0, FrostCraft.instance, 64, 20, false);
		EntityRegistry.registerGlobalEntityID(EntityFrostShot.class, "FrostShot", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFrostShot.class, "FrostShot", 1, FrostCraft.instance, 64, 20, false);
	}
	
	@SideOnly(Side.CLIENT)
	public void initializeRenderers()
	{
		RenderingRegistry.registerBlockHandler(2200, new RendererThermalPipe());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostArrow.class, new RenderFrostArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostShot.class, new RenderFrostShot(0.5f));
	}
}
