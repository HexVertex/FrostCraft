package xelitez.frostcraft.registry;

import xelitez.frostcraft.FrostCraft;
import xelitez.frostcraft.block.*;
import xelitez.frostcraft.item.*;
import xelitez.frostcraft.entity.*;
import xelitez.frostcraft.enums.*;
import xelitez.frostcraft.client.model.ModelFairy;
import xelitez.frostcraft.client.render.*;
import xelitez.frostcraft.tileentity.*;
import xelitez.frostcraft.world.*;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.*;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IdMap 
{
	public int defaultIdThermalPipe = 2200;
	public int defaultIdThermalMachines = 2201;
	public int defaultIdBlockIcicle = 2202;
	public int defaultIdBlockBlackFrost = 2203;
	public int defaultIdBlockBlackFrostStairSet = 2204;
	public int defaultIdBlockBlackFrostFenceSet = 2207;
	public int defaultIdBlockBlackFrostSlabSet = 2210;
	
	public int defaultIdFrostBow = 2300;
	public int defaultIdFrostGun = 2301;
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
	public int defaultIdIcicle = 2302;
	public int defaultIdSpear = 2319;
	public int defaultIdCrossbow = 2320;
	
	public int defaultEnforcerToolStartId = 2400;
	public int enforcerToolStartId;
	public Item[] EnforcedTools;
	
	public static int IdThermalPipe;
	public static int IdThermalMachines;
	public static int IdBlockIcicle;
	public static int IdBlockBlackFrost;
	public static int IdBlockBlackFrostStairSet;
	public static int IdBlockBlackFrostFenceSet;
	public static int IdBlockBlackFrostSlabSet;
	
	public static int IdFrostBow;
	public static int IdFrostGun;
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
	public static int IdSpear;
	public static int IdCrossbow;
	
	public static Block blockThermalPipe;
	public static Block blockThermalMachines;
	public static Block blockIcicle;
	public static Block blockBlackFrost;
	public static Block blockBlackFrostStair;
	public static Block blockBlackFrostStairCobble;
	public static Block blockBlackFrostStairBrick;
	public static Block blockBlackFrostFenceSet;
	public static BlockHalfSlab blockBlackFrostSingleSlabSet;
	public static BlockHalfSlab blockBlackFrostDoubleSlabSet;
	
	public static Item itemFrostBow;
	public static Item itemFrostGun;
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
	public static Item itemSpear;
	public static Item itemCrossbow;
	
	public static WorldType worldTypeWinterland;
	
	public static ISimpleBlockRenderingHandler thermalPipeRenderer;
	
	/**
	 * Initialise all FrostCraft Blocks and additions
	 */
	public void initialiseBlocks()
	{
		blockThermalPipe = new BlockThermalPipe(IdThermalPipe, Material.iron).setHardness(0.4F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("thermal_pipe").setTextureName("frostcraft:thermal_pipe");
		blockThermalMachines = new BlockThermalMachines(IdThermalMachines, Material.iron).setHardness(2.5F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("thermal_machine_block").setCreativeTab(CreativeTabs.FCMechanical).setTextureName("frostcraft:thermal_machine_block");
		blockIcicle = new BlockIcicle(IdBlockIcicle, Material.ice).setHardness(0.2F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("icicle").setTextureName("frostcraft:icicle");
		blockBlackFrost = new BlockBlackFrost(IdBlockBlackFrost).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blackFrost").setTextureName("frostcraft:blackFrost");
		blockBlackFrostStair = new BlockBlackFrostStairs(IdBlockBlackFrostStairSet, blockBlackFrost, 0).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blackFrostStair").setTextureName("frostcraft:blackFrostStair");
		blockBlackFrostStairCobble = new BlockBlackFrostStairs(IdBlockBlackFrostStairSet + 1, blockBlackFrost, 1).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blackFrostStairCoble").setTextureName("frostcraft:blackFrostStairCoble");
		blockBlackFrostStairBrick = new BlockBlackFrostStairs(IdBlockBlackFrostStairSet + 2, blockBlackFrost, 2).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blackFrostStairBrick").setTextureName("frostcraft:blackFrostStairBrick");
		blockBlackFrostFenceSet = new BlockBlackFrostFence(defaultIdBlockBlackFrostFenceSet, Material.ice).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blackFrostFenceSet").setTextureName("frostcraft:blackFrostFenceSet");
		blockBlackFrostSingleSlabSet = (BlockHalfSlab)new BlockBlackFrostSlab(defaultIdBlockBlackFrostSlabSet, false, Material.ice).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blackFrostSingleSlabSet").setTextureName("frostcraft:blackFrost");
		blockBlackFrostDoubleSlabSet = (BlockHalfSlab)new BlockBlackFrostSlab(defaultIdBlockBlackFrostSlabSet + 1, true, Material.ice).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("blackFrostDoubleSlabSet").setTextureName("frostcraft:blackFrost");
		GameRegistry.registerBlock(blockThermalPipe, "ThermalPipe");
		GameRegistry.registerBlock(blockThermalMachines, ItemThermalMachines.class, "ThermalPump");
		GameRegistry.registerBlock(blockIcicle, "Icicle");
		GameRegistry.registerBlock(blockBlackFrost, ItemBlackFrost.class, "Blackfrost");
		GameRegistry.registerBlock(blockBlackFrostStair, "BlackfrostStair");
		GameRegistry.registerBlock(blockBlackFrostStairCobble, "BlackfrostStairCobble");
		GameRegistry.registerBlock(blockBlackFrostStairBrick, "BlackfrostStairBrick");
		GameRegistry.registerBlock(blockBlackFrostFenceSet, ItemBlackFrostFences.class, "BlackfrostFences");
		GameRegistry.registerBlock(blockBlackFrostSingleSlabSet, ItemBlackFrostSlabSingle.class, "BlackfrostSingleSlabs");
		GameRegistry.registerBlock(blockBlackFrostDoubleSlabSet, ItemBlackFrostSlabDouble.class, "BlackfrostDoubleSlabs");
		LanguageRegistry.addName(blockThermalPipe, "Thermal Pipe");
		LanguageRegistry.addName(new ItemStack(blockThermalMachines, 1, 0), "Thermal Pump");
		LanguageRegistry.addName(new ItemStack(blockThermalMachines, 1, 1), "Frost Furnace");
		LanguageRegistry.addName(new ItemStack(blockThermalMachines, 1, 2), "Frost Generator");
		LanguageRegistry.addName(new ItemStack(blockThermalMachines, 1, 3), "Freezer");
		LanguageRegistry.addName(new ItemStack(blockThermalMachines, 1, 4), "Frost Enforcer");
		LanguageRegistry.addName(blockIcicle, "Icicle");
		LanguageRegistry.addName(new ItemStack(blockBlackFrost, 1, 0), "Blackfrost");
		LanguageRegistry.addName(new ItemStack(blockBlackFrost, 1, 1), "Cracked Blackfrost");
		LanguageRegistry.addName(new ItemStack(blockBlackFrost, 1, 2), "Blackfrost Brick");
		LanguageRegistry.addName(blockBlackFrostStair, "Blackfrost Stairs");
		LanguageRegistry.addName(blockBlackFrostStairCobble, "Cracked Blackfrost Stairs");
		LanguageRegistry.addName(blockBlackFrostStairBrick, "Blackfrost Brick Stairs");
		LanguageRegistry.addName(new ItemStack(blockBlackFrostFenceSet, 1, 0), "Blackfrost Fence");
		LanguageRegistry.addName(new ItemStack(blockBlackFrostFenceSet, 1, 1), "Cracked Blackfrost Fence");
		LanguageRegistry.addName(new ItemStack(blockBlackFrostFenceSet, 1, 2), "Blackfrost Brick Fence");
		LanguageRegistry.addName(new ItemStack(blockBlackFrostSingleSlabSet, 1, 0), "Blackfrost Slab");
		LanguageRegistry.addName(new ItemStack(blockBlackFrostSingleSlabSet, 1, 1), "Cracked Blackfrost Slab");
		LanguageRegistry.addName(new ItemStack(blockBlackFrostSingleSlabSet, 1, 2), "Blackfrost Brick Slab");
	}
	
	/**
	 * Initialise all FrostCraft Items and additions
	 */
	public void initialiseItems()
	{
		itemFrostBow = new ItemFrostBow(IdFrostBow).setUnlocalizedName("frost_bow").setTextureName("frostcraft:frost_bow");
		itemFrostGun = new ItemFrostGun(IdFrostGun).setUnlocalizedName("frost_gun").setTextureName("frostcraft:frost_gun");
		itemCraftingItems = new CraftingItems(IdCraftingItems);
		itemCompiledFrostBlade = new ItemFrostBlade(IdCompiledFrostBlade, FrostToolMaterial.FROST_COMPILED).setUnlocalizedName("compiled_frost_sword").setTextureName("frostcraft:compiled_frost_sword");
		itemCompiledFrostSpade = new ItemFrostSpade(IdCompiledFrostSpade, FrostToolMaterial.FROST_COMPILED).setUnlocalizedName("compiled_frost_spade").setTextureName("frostcraft:compiled_frost_spade");
		itemCompiledFrostPickaxe = new ItemFrostPickaxe(IdCompiledFrostPickaxe, FrostToolMaterial.FROST_COMPILED).setUnlocalizedName("compiled_frost_pickaxe").setTextureName("frostcraft:compiled_frost_pickaxe");
		itemCompiledFrostAxe = new ItemFrostAxe(IdCompiledFrostAxe, FrostToolMaterial.FROST_COMPILED).setUnlocalizedName("compiled_frost_axe").setTextureName("frostcraft:compiled_frost_axe");
		itemCompiledFrostHoe = new ItemFrostHoe(IdCompiledFrostHoe, FrostToolMaterial.FROST_COMPILED).setUnlocalizedName("compiled_frost_hoe").setTextureName("frostcraft:compiled_frost_hoe");
		itemFrozenFrostBlade = new ItemFrostBlade(IdFrozenFrostBlade, FrostToolMaterial.FROST_FROZEN).setUnlocalizedName("frost_sword").setTextureName("frostcraft:frost_sword");
		itemFrozenFrostSpade = new ItemFrostSpade(IdFrozenFrostSpade, FrostToolMaterial.FROST_FROZEN).setUnlocalizedName("frost_spade").setTextureName("frostcraft:frost_spade");
		itemFrozenFrostPickaxe = new ItemFrostPickaxe(IdFrozenFrostPickaxe, FrostToolMaterial.FROST_FROZEN).setUnlocalizedName("frost_pickaxe").setTextureName("frostcraft:frost_pickaxe");
		itemFrozenFrostAxe = new ItemFrostAxe(IdFrozenFrostAxe, FrostToolMaterial.FROST_FROZEN).setUnlocalizedName("frost_axe").setTextureName("frostcraft:frost_axe");
		itemFrozenFrostHoe = new ItemFrostHoe(IdFrozenFrostHoe, FrostToolMaterial.FROST_FROZEN).setUnlocalizedName("frost_hoe").setTextureName("frostcraft:frost_hoe");
		itemFrostBlade = new ItemFrostBlade(IdFrostBlade, FrostToolMaterial.FROST).setUnlocalizedName("enforced_frost_blade").setTextureName("frostcraft:enforced_frost_blade");
		itemFrostSpade = new ItemFrostSpade(IdFrostSpade, FrostToolMaterial.FROST).setUnlocalizedName("enforced_frost_spade").setTextureName("frostcraft:enforced_frost_spade");
		itemFrostPickaxe = new ItemFrostPickaxe(IdFrostPickaxe, FrostToolMaterial.FROST).setUnlocalizedName("enforced_frost_pickaxe").setTextureName("frostcraft:enforced_frost_pickaxe");
		itemFrostAxe = new ItemFrostAxe(IdFrostAxe, FrostToolMaterial.FROST).setUnlocalizedName("enforced_frost_axe").setTextureName("frostcraft:enforced_frost_axe");
		itemFrostHoe = new ItemFrostHoe(IdFrostHoe, FrostToolMaterial.FROST).setUnlocalizedName("enforced_frost_hoe").setTextureName("frostcraft:enforced_frost_hoe");
		itemIcicle = new ItemIcicle(IdIcicle).setUnlocalizedName("icicle").setTextureName("frostcraft:icicle");
		itemSpear = new ItemSpear(IdSpear).setUnlocalizedName("guardians_spear").setTextureName("frostcraft:guardians_spear");
		itemCrossbow = new ItemCrossbow(IdCrossbow).setUnlocalizedName("guardians_crossbow").setTextureName("frostcraft:guardians_crossbow");
		
		GameRegistry.registerItem(itemFrostBow, "FrostBow");
		GameRegistry.registerItem(itemFrostGun, "FrostGun");
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
		GameRegistry.registerItem(itemSpear, "ItemSpear");
		GameRegistry.registerItem(itemCrossbow, "ItemCrossbow");
		
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
		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 2), "CFU Handler");
		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 3), "Frost Transformer");
		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 4), "Compressor");
		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 5), "Frost Sprayer");
		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 6), "CFU Storage Handler");
		LanguageRegistry.addName(itemSpear, "Guardian's Spear");
		LanguageRegistry.addName(itemCrossbow, "Guardian's Crossbow");
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
		//EntityRegistry.registerGlobalEntityID(EntityFairy.class, "IceFairy", EntityRegistry.findGlobalUniqueEntityId(), 0x0000FF, 0x00FFFF);
		//EntityRegistry.registerModEntity(EntityFairy.class, "IceFairy", 2, FrostCraft.instance, 64, 20, true);
	}
	
	public void initialiseWorld()
	{
		GameRegistry.registerWorldGenerator(WorldGenIcicles.getInstance());
		worldTypeWinterland = new WorldTypeWinterLand(Settings.EndlessWinterID != -1 ? Settings.EndlessWinterID : WorldTypeBase.getFreeId(), "Endless Winter");
	}
	
	public void initialiseEnfrocerItems()
	{
		this.EnforcedTools = new Item[Settings.MaxEnfrocerItems];
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
				this.EnforcedTools[i] = new ItemFrostEnforced(this.enforcerToolStartId + i, item, type).setUnlocalizedName("Enforced" + item.getUnlocalizedName());
				GameRegistry.registerItem(this.EnforcedTools[i], "Enforced" + item.getUnlocalizedName());
				LanguageRegistry.addName(this.EnforcedTools[i], "Enforced " + StatCollector.translateToLocal(item.getStatName()));
				RecipeRegistry.addEnforcerRecipe(item, new ItemStack(this.EnforcedTools[i]), true);
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
		thermalPipeRenderer = new RendererThermalPipe(RenderingRegistry.getNextAvailableRenderId());
		RenderingRegistry.registerBlockHandler(thermalPipeRenderer);
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostArrow.class, new RenderFrostArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostShot.class, new RenderFrostShot(0.5f));
		MinecraftForgeClient.registerItemRenderer(IdMap.itemFrostBow.itemID, new RenderFrostBow());
		RenderingRegistry.registerEntityRenderingHandler(EntityFairy.class, new RenderFairy(new ModelFairy(0.0F, "frostcraft:textures/icefairywings.png"), 1.0F, 1.0F));
	}
}
