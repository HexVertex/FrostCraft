package xelitez.frostcraft.registry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.MinecraftForgeClient;
import xelitez.frostcraft.Frostcraft;
import xelitez.frostcraft.block.BlockBlackFrost;
import xelitez.frostcraft.block.BlockBlackFrostFence;
import xelitez.frostcraft.block.BlockBlackFrostSlab;
import xelitez.frostcraft.block.BlockBlackFrostStairs;
import xelitez.frostcraft.block.BlockIcicle;
import xelitez.frostcraft.block.BlockStatue;
import xelitez.frostcraft.block.BlockThermalMachines;
import xelitez.frostcraft.block.BlockThermalPipe;
import xelitez.frostcraft.client.model.ModelFrostWingLow;
import xelitez.frostcraft.client.render.RenderCrossbow;
import xelitez.frostcraft.client.render.RenderCrossbowBolt;
import xelitez.frostcraft.client.render.RenderForstWing;
import xelitez.frostcraft.client.render.RenderFrostArrow;
import xelitez.frostcraft.client.render.RenderFrostBall;
import xelitez.frostcraft.client.render.RenderFrostBow;
import xelitez.frostcraft.client.render.RenderFrostGuard;
import xelitez.frostcraft.client.render.RenderFrostShot;
import xelitez.frostcraft.client.render.RenderIcicle;
import xelitez.frostcraft.client.render.RendererThermalPipe;
import xelitez.frostcraft.client.render.TileEntityFrostStatueRenderer;
import xelitez.frostcraft.entity.EntityCrossbowBolt;
import xelitez.frostcraft.entity.EntityFrostArrow;
import xelitez.frostcraft.entity.EntityFrostBall;
import xelitez.frostcraft.entity.EntityFrostGuard;
import xelitez.frostcraft.entity.EntityFrostShot;
import xelitez.frostcraft.entity.EntityFrostWing;
import xelitez.frostcraft.entity.EntityFrostWingIcicleDropping;
import xelitez.frostcraft.enums.EnumRenderType;
import xelitez.frostcraft.enums.FrostToolMaterial;
import xelitez.frostcraft.item.CraftingItems;
import xelitez.frostcraft.item.ItemBlackFrost;
import xelitez.frostcraft.item.ItemBlackFrostFences;
import xelitez.frostcraft.item.ItemBlackFrostSlabDouble;
import xelitez.frostcraft.item.ItemBlackFrostSlabSingle;
import xelitez.frostcraft.item.ItemCrossbow;
import xelitez.frostcraft.item.ItemFrostAxe;
import xelitez.frostcraft.item.ItemFrostBlade;
import xelitez.frostcraft.item.ItemFrostBow;
import xelitez.frostcraft.item.ItemFrostEnforced;
import xelitez.frostcraft.item.ItemFrostGun;
import xelitez.frostcraft.item.ItemFrostHoe;
import xelitez.frostcraft.item.ItemFrostOrb;
import xelitez.frostcraft.item.ItemFrostPickaxe;
import xelitez.frostcraft.item.ItemFrostSpade;
import xelitez.frostcraft.item.ItemFrostTool;
import xelitez.frostcraft.item.ItemIcePop;
import xelitez.frostcraft.item.ItemIcicle;
import xelitez.frostcraft.item.ItemSpear;
import xelitez.frostcraft.item.ItemThermalMachines;
import xelitez.frostcraft.tileentity.TileEntityFreezer;
import xelitez.frostcraft.tileentity.TileEntityFrostEnforcer;
import xelitez.frostcraft.tileentity.TileEntityFrostFurnace;
import xelitez.frostcraft.tileentity.TileEntityFrostGenerator;
import xelitez.frostcraft.tileentity.TileEntityStatue;
import xelitez.frostcraft.tileentity.TileEntityThermalMachines;
import xelitez.frostcraft.tileentity.TileEntityThermalPipe;
import xelitez.frostcraft.tileentity.TileEntityThermalPump;
import xelitez.frostcraft.world.WorldGenFrostWingTower;
import xelitez.frostcraft.world.WorldGenIcicles;
import xelitez.frostcraft.world.WorldTypeWinterLand;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IdMap 
{
	public static Block blockThermalPipe;
	public static Block blockThermalMachines;
	public static Block blockIcicle;
	public static Block blockBlackFrost;
	public static Block blockBlackFrostStair;
	public static Block blockBlackFrostStairCobble;
	public static Block blockBlackFrostStairBrick;
	public static Block blockBlackFrostFenceSet;
	public static BlockSlab blockBlackFrostSingleSlabSet;
	public static BlockSlab blockBlackFrostDoubleSlabSet;
	public static Block blockStatue;
	
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
	public static Item itemFrostOrb;
	public static Item itemIcePop;
	
	public Item[] EnforcedTools;
	
	public static WorldType worldTypeWinterland;
	
	public static ISimpleBlockRenderingHandler thermalPipeRenderer;
	
	public static final float FrostWingSize = 2.5F;
	
	public static final String[] testers = new String[] {"lord_kalvin", "XEliteZSjors", "sea_hawk"};

	
	
	/**
	 * Initialise all Frostcraft Blocks and additions
	 */
	public void initialiseBlocks()
	{
		blockThermalPipe = new BlockThermalPipe(Material.iron).setHardness(0.4F).setStepSound(Block.soundTypeMetal).setBlockName("thermal_pipe").setBlockTextureName("frostcraft:thermal_pipe");
		blockThermalMachines = new BlockThermalMachines( Material.iron).setHardness(2.5F).setStepSound(Block.soundTypeMetal).setBlockName("thermal_machine_block").setCreativeTab(FrostcraftCreativeTabs.FCMechanical).setBlockTextureName("frostcraft:thermal_machine_block");
		blockIcicle = new BlockIcicle(Material.ice).setHardness(0.2F).setStepSound(Block.soundTypeGlass).setBlockName("icicle").setBlockTextureName("frostcraft:icicle");
		blockBlackFrost = new BlockBlackFrost().setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundTypeStone).setBlockName("blackFrost").setBlockTextureName("frostcraft:blackFrost");
		blockBlackFrostStair = new BlockBlackFrostStairs(blockBlackFrost, 0).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundTypeStone).setBlockName("blackFrostStair").setBlockTextureName("frostcraft:blackFrostStair");
		blockBlackFrostStairCobble = new BlockBlackFrostStairs(blockBlackFrost, 1).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundTypeStone).setBlockName("blackFrostStairCoble").setBlockTextureName("frostcraft:blackFrostStairCoble");
		blockBlackFrostStairBrick = new BlockBlackFrostStairs(blockBlackFrost, 2).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundTypeStone).setBlockName("blackFrostStairBrick").setBlockTextureName("frostcraft:blackFrostStairBrick");
		blockBlackFrostFenceSet = new BlockBlackFrostFence(Material.ice).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundTypeStone).setBlockName("blackFrostFenceSet").setBlockTextureName("frostcraft:blackFrostFenceSet");
		blockBlackFrostSingleSlabSet = (BlockSlab)new BlockBlackFrostSlab(false, Material.ice).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundTypeStone).setBlockName("blackFrostSingleSlabSet").setBlockTextureName("frostcraft:blackFrost");
		blockBlackFrostDoubleSlabSet = (BlockSlab)new BlockBlackFrostSlab(true, Material.ice).setHardness(1.0F).setLightOpacity(7).setStepSound(Block.soundTypeStone).setBlockName("blackFrostDoubleSlabSet").setBlockTextureName("frostcraft:blackFrost");
		blockStatue = new BlockStatue(Material.rock).setBlockUnbreakable().setResistance(6000000.0F).setStepSound(Block.soundTypeStone).setBlockName("statue").setBlockTextureName("stone");
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
		GameRegistry.registerBlock(blockStatue, "Statue");
//		LanguageRegistry.addName(blockThermalPipe, "Thermal Pipe");
//		LanguageRegistry.addName(new ItemStack(blockThermalMachines, 1, 0), "Thermal Pump");
//		LanguageRegistry.addName(new ItemStack(blockThermalMachines, 1, 1), "Frost Furnace");
//		LanguageRegistry.addName(new ItemStack(blockThermalMachines, 1, 2), "Frost Generator");
//		LanguageRegistry.addName(new ItemStack(blockThermalMachines, 1, 3), "Freezer");
//		LanguageRegistry.addName(new ItemStack(blockThermalMachines, 1, 4), "Frost Enforcer");
//		LanguageRegistry.addName(blockIcicle, "Icicle");
//		LanguageRegistry.addName(new ItemStack(blockBlackFrost, 1, 0), "Blackfrost");
//		LanguageRegistry.addName(new ItemStack(blockBlackFrost, 1, 1), "Cracked Blackfrost");
//		LanguageRegistry.addName(new ItemStack(blockBlackFrost, 1, 2), "Blackfrost Brick");
//		LanguageRegistry.addName(blockBlackFrostStair, "Blackfrost Stairs");
//		LanguageRegistry.addName(blockBlackFrostStairCobble, "Cracked Blackfrost Stairs");
//		LanguageRegistry.addName(blockBlackFrostStairBrick, "Blackfrost Brick Stairs");
//		LanguageRegistry.addName(new ItemStack(blockBlackFrostFenceSet, 1, 0), "Blackfrost Fence");
//		LanguageRegistry.addName(new ItemStack(blockBlackFrostFenceSet, 1, 1), "Cracked Blackfrost Fence");
//		LanguageRegistry.addName(new ItemStack(blockBlackFrostFenceSet, 1, 2), "Blackfrost Brick Fence");
//		LanguageRegistry.addName(new ItemStack(blockBlackFrostSingleSlabSet, 1, 0), "Blackfrost Slab");
//		LanguageRegistry.addName(new ItemStack(blockBlackFrostSingleSlabSet, 1, 1), "Cracked Blackfrost Slab");
//		LanguageRegistry.addName(new ItemStack(blockBlackFrostSingleSlabSet, 1, 2), "Blackfrost Brick Slab");
//		LanguageRegistry.addName(blockStatue, "Frost Wing Statue");
	}
	
	/**
	 * Initialise all Frostcraft Items and additions
	 */
	public void initialiseItems()
	{
		itemFrostBow = new ItemFrostBow().setUnlocalizedName("frost_bow").setTextureName("frostcraft:frost_bow");
		itemFrostGun = new ItemFrostGun().setUnlocalizedName("frost_gun").setTextureName("frostcraft:frost_gun");
		itemCraftingItems = new CraftingItems();
		itemCompiledFrostBlade = new ItemFrostBlade(FrostToolMaterial.FROST_COMPILED).setUnlocalizedName("compiled_frost_sword").setTextureName("frostcraft:compiled_frost_sword");
		itemCompiledFrostSpade = new ItemFrostSpade(FrostToolMaterial.FROST_COMPILED).setUnlocalizedName("compiled_frost_spade").setTextureName("frostcraft:compiled_frost_spade");
		itemCompiledFrostPickaxe = new ItemFrostPickaxe(FrostToolMaterial.FROST_COMPILED).setUnlocalizedName("compiled_frost_pickaxe").setTextureName("frostcraft:compiled_frost_pickaxe");
		itemCompiledFrostAxe = new ItemFrostAxe(FrostToolMaterial.FROST_COMPILED).setUnlocalizedName("compiled_frost_axe").setTextureName("frostcraft:compiled_frost_axe");
		itemCompiledFrostHoe = new ItemFrostHoe(FrostToolMaterial.FROST_COMPILED).setUnlocalizedName("compiled_frost_hoe").setTextureName("frostcraft:compiled_frost_hoe");
		itemFrozenFrostBlade = new ItemFrostBlade(FrostToolMaterial.FROST_FROZEN).setUnlocalizedName("frost_sword").setTextureName("frostcraft:frost_sword");
		itemFrozenFrostSpade = new ItemFrostSpade(FrostToolMaterial.FROST_FROZEN).setUnlocalizedName("frost_spade").setTextureName("frostcraft:frost_spade");
		itemFrozenFrostPickaxe = new ItemFrostPickaxe(FrostToolMaterial.FROST_FROZEN).setUnlocalizedName("frost_pickaxe").setTextureName("frostcraft:frost_pickaxe");
		itemFrozenFrostAxe = new ItemFrostAxe(FrostToolMaterial.FROST_FROZEN).setUnlocalizedName("frost_axe").setTextureName("frostcraft:frost_axe");
		itemFrozenFrostHoe = new ItemFrostHoe(FrostToolMaterial.FROST_FROZEN).setUnlocalizedName("frost_hoe").setTextureName("frostcraft:frost_hoe");
		itemFrostBlade = new ItemFrostBlade(FrostToolMaterial.FROST).setUnlocalizedName("enforced_frost_blade").setTextureName("frostcraft:enforced_frost_blade");
		itemFrostSpade = new ItemFrostSpade(FrostToolMaterial.FROST).setUnlocalizedName("enforced_frost_spade").setTextureName("frostcraft:enforced_frost_spade");
		itemFrostPickaxe = new ItemFrostPickaxe(FrostToolMaterial.FROST).setUnlocalizedName("enforced_frost_pickaxe").setTextureName("frostcraft:enforced_frost_pickaxe");
		itemFrostAxe = new ItemFrostAxe(FrostToolMaterial.FROST).setUnlocalizedName("enforced_frost_axe").setTextureName("frostcraft:enforced_frost_axe");
		itemFrostHoe = new ItemFrostHoe(FrostToolMaterial.FROST).setUnlocalizedName("enforced_frost_hoe").setTextureName("frostcraft:enforced_frost_hoe");
		itemIcicle = new ItemIcicle().setUnlocalizedName("icicle").setTextureName("frostcraft:icicle");
		itemSpear = new ItemSpear().setUnlocalizedName("guardians_spear").setTextureName("frostcraft:guardians_spear");
		itemCrossbow = new ItemCrossbow().setUnlocalizedName("guardians_crossbow").setTextureName("frostcraft:guardians_crossbow");
		itemFrostOrb = new ItemFrostOrb().setUnlocalizedName("frost_orb").setTextureName("frostcraft:frost_orb");
		itemIcePop = new ItemIcePop();
		
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
		GameRegistry.registerItem(itemFrostOrb, "FrostOrb");
		GameRegistry.registerItem(itemIcePop, "IcePop");
		
//		LanguageRegistry.addName(itemFrostBow, "Frost Bow");
//		LanguageRegistry.addName(itemFrostGun, "Frost Gun");
//		LanguageRegistry.addName(itemCompiledFrostBlade, "Compiled Frost Sword");
//		LanguageRegistry.addName(itemCompiledFrostSpade, "Compiled Frost Shovel");
//		LanguageRegistry.addName(itemCompiledFrostPickaxe, "Compiled Frost Pickaxe");
//		LanguageRegistry.addName(itemCompiledFrostAxe, "Compiled Frost Axe");
//		LanguageRegistry.addName(itemCompiledFrostHoe, "Compiled Frost Hoe");
//		LanguageRegistry.addName(itemFrozenFrostBlade, "Frost Sword");
//		LanguageRegistry.addName(itemFrozenFrostSpade, "Frost Shovel");
//		LanguageRegistry.addName(itemFrozenFrostPickaxe, "Frost Pickaxe");
//		LanguageRegistry.addName(itemFrozenFrostAxe, "Frost Axe");
//		LanguageRegistry.addName(itemFrozenFrostHoe, "Frost Hoe");
//		LanguageRegistry.addName(itemFrostBlade, "Enforced Frost Blade");
//		LanguageRegistry.addName(itemFrostSpade, "Enforced Frost Shovel");
//		LanguageRegistry.addName(itemFrostPickaxe, "Enforced Frost Pickaxe");
//		LanguageRegistry.addName(itemFrostAxe, "Enforced Frost Axe");
//		LanguageRegistry.addName(itemFrostHoe, "Enforced Frost Hoe");
//		LanguageRegistry.addName(itemIcicle, "Icicle");
//		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 0), "Iceball");
//		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 1), "Ice-Covered String");
//		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 2), "CFU Handler");
//		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 3), "Frost Transformer");
//		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 4), "Compressor");
//		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 5), "Frost Sprayer");
//		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 6), "CFU Storage Handler");
//		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 7), "Stick in Water");
//		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 8), "Stick in Apple Juice");
//		LanguageRegistry.addName(new ItemStack(itemCraftingItems.itemID, 1, 9), "Stick in Chocolate");
//		LanguageRegistry.addName(itemSpear, "Guardian's Spear");
//		LanguageRegistry.addName(itemCrossbow, "Guardian's Crossbow");
//		LanguageRegistry.addName(itemFrostOrb, "Frost Orb");
//		LanguageRegistry.addName(new ItemStack(itemIcePop.itemID, 1, 0), "Ice Pop");
//		LanguageRegistry.addName(new ItemStack(itemIcePop.itemID, 1, 1), "Apple Ice Pop");
//		LanguageRegistry.addName(new ItemStack(itemIcePop.itemID, 1, 2), "Chocolate Ice Pop");
	}

	/**
	 * Initialise all Frostcraft entities and additions
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
		GameRegistry.registerTileEntity(TileEntityStatue.class, "Frost Wing Statue");
		EntityRegistry.registerGlobalEntityID(EntityFrostArrow.class, "FrostArrow", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFrostArrow.class, "FrostArrow", 0, Frostcraft.instance, 64, 100, false);
		EntityRegistry.registerGlobalEntityID(EntityFrostShot.class, "FrostShot", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFrostShot.class, "FrostShot", 1, Frostcraft.instance, 64, 20, false);
		EntityRegistry.registerGlobalEntityID(EntityFrostWing.class, "FrostWing", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFrostWing.class, "FrostWing", 2, Frostcraft.instance, 80, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityFrostWingIcicleDropping.class, "IcicleDropping", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFrostWingIcicleDropping.class, "IcicleDropping", 3, Frostcraft.instance, 64, 20, false);
		EntityRegistry.registerGlobalEntityID(EntityFrostBall.class, "FrostBall", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFrostBall.class, "FrostBall", 4, Frostcraft.instance, 64, 1, false);
		EntityRegistry.registerGlobalEntityID(EntityCrossbowBolt.class, "CrossbowBolt", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityCrossbowBolt.class, "CrossbowBolt", 5, Frostcraft.instance, 64, 100, false);
		EntityRegistry.registerGlobalEntityID(EntityFrostGuard.class, "FrostGuard", EntityRegistry.findGlobalUniqueEntityId(), 0x333333, 0x00CCFF);
		EntityRegistry.registerModEntity(EntityFrostGuard.class, "FrostGuard", 6, Frostcraft.instance, 80, 1, true);
        List<BiomeGenBase> listBiomes = new ArrayList<BiomeGenBase>();
		for (BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
        {
			if(biome != null)
			{
				listBiomes.add(biome);
			}
        }
		EntityRegistry.addSpawn(EntityFrostGuard.class, 150, 2, 3, EnumCreatureType.monster, listBiomes.toArray(new BiomeGenBase[listBiomes.size()]));
		
//		LanguageRegistry.instance().addStringLocalization(new EntityFrostWing().getCommandSenderName(), "Frost Wing");
	}
	
	public void initialiseWorld()
	{
		GameRegistry.registerWorldGenerator(WorldGenIcicles.getInstance(), 1);
		GameRegistry.registerWorldGenerator(WorldGenFrostWingTower.getInstance(), 1);
		worldTypeWinterland = new WorldTypeWinterLand("Endless Winter");
	}
	
	public void initialiseEnfrocerItems()
	{
		this.EnforcedTools = new Item[Settings.MaxEnfrocerItems];
		List<Item> items = new ArrayList<Item>();
		Iterator<?> iterator = GameData.itemRegistry.iterator();
		boolean isLeft = true;
		while(iterator.hasNext())
		{
			Item item = (Item) iterator.next();
			if(item != null && (item instanceof ItemTool || item instanceof ItemSword || item instanceof ItemHoe) && !(item instanceof ItemFrostTool || item instanceof ItemFrostBlade || item instanceof ItemFrostHoe))
			{
				items.add(item);
			}
		}
		for(Item item : items)
		{
			this.registerEnforcerTool(item);
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
				this.EnforcedTools[i] = new ItemFrostEnforced(item, type).setUnlocalizedName("Enforced" + item.getUnlocalizedName());
				GameRegistry.registerItem(this.EnforcedTools[i], "Enforced" + item.getUnlocalizedName());
//				LanguageRegistry.addName(this.EnforcedTools[i], "Enforced " + StatCollector.translateToLocal(item.getUnlocalizedName()));
				RecipeRegistry.addEnforcerRecipe(item, new ItemStack(this.EnforcedTools[i]), true);
				break;
			}
		}
	}
	
	/**
	 * Initialise all Frostcraft renderers
	 */
	@SideOnly(Side.CLIENT)
	public void initialiseRenderers()
	{
		thermalPipeRenderer = new RendererThermalPipe(RenderingRegistry.getNextAvailableRenderId());
		RenderingRegistry.registerBlockHandler(thermalPipeRenderer);
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostArrow.class, new RenderFrostArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostShot.class, new RenderFrostShot(0.5f));
		MinecraftForgeClient.registerItemRenderer(IdMap.itemFrostBow, new RenderFrostBow());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostWing.class, new RenderForstWing(new ModelFrostWingLow(), 1.0F, FrostWingSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostWingIcicleDropping.class, new RenderIcicle());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostBall.class, new RenderFrostBall());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStatue.class, new TileEntityFrostStatueRenderer());
		MinecraftForgeClient.registerItemRenderer(IdMap.itemCrossbow, new RenderCrossbow());
		RenderingRegistry.registerEntityRenderingHandler(EntityCrossbowBolt.class, new RenderCrossbowBolt());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostGuard.class, new RenderFrostGuard(new ModelBiped(), 0.5F));
	}
}
