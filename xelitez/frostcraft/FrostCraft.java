package xelitez.frostcraft;

import java.io.File;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import xelitez.frostcraft.block.BlockThermalPipe;
import xelitez.frostcraft.client.render.RenderFrostTool;
import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.enchantment.FrostEnchantment;
import xelitez.frostcraft.energy.EnergyRequestRegistry;
import xelitez.frostcraft.network.NetworkManager;
import xelitez.frostcraft.network.PacketManagerClient;
import xelitez.frostcraft.network.PacketManagerServer;
import xelitez.frostcraft.registry.CommonProxy;
import xelitez.frostcraft.registry.IdMap;
import xelitez.frostcraft.registry.RecipeRegistry;
import xelitez.frostcraft.world.WorldAccess;
import xelitez.frostcraft.world.WorldTicker;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.NULL;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.FMLInjectionData;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.RelaunchClassLoader;
import cpw.mods.fml.relauncher.Side;

@Mod(	
		modid = "XEZFrostCraft",
		name = "FrostCraft", 
		acceptedMinecraftVersions = "[1.4.7]",
		version = "0.0.1.0")
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		versionBounds = "[0.0.0,0.1)",
		clientPacketHandlerSpec = @SidedPacketHandler(channels = {"XFCC"}, packetHandler = PacketManagerClient.class ),
		serverPacketHandlerSpec = @SidedPacketHandler(channels = {"XFCS"}, packetHandler = PacketManagerServer.class ))
public class FrostCraft 
{
	@Instance(value = "XEZFrostCraft")
	public static FrostCraft instance;
	
	private Configuration C;
	private IdMap map = new IdMap();
	private boolean checkForUpdates;
	
	@SidedProxy(clientSide = "xelitez.frostcraft.registry.ClientProxy", serverSide = "xelitez.frostcraft.registry.CommonProxy")
	public static CommonProxy proxy = new CommonProxy();
	
	public Version version = new Version();
	
	public static WorldAccess access = new WorldAccess();
	
	@PreInit
    public void preload(FMLPreInitializationEvent evt)
    {
		evt.getModMetadata().version = Version.getVersion();
		
		if(evt.getSide().isClient())
		{
			MinecraftForgeClient.preloadTexture("/xelitez/frostcraft/textures/Blocks_0.png");
			MinecraftForgeClient.preloadTexture("/xelitez/frostcraft/textures/Items_0.png");
			MinecraftForgeClient.preloadTexture("/xelitez/frostcraft/textures/effects.png");
		}
		
		C = new Configuration(new File("XEliteZ/FrostCraft.cfg"));
		try
		{
			C.load();
			
			map.MaxEnfrocerItems = C.get(C.CATEGORY_GENERAL, "MaxEnforcerItems", map.defaultMaxEnforcedItems).getInt(map.defaultMaxEnforcedItems);
			map.enforcerToolStartId = C.get("Equipment", "EnforcerToolsStartId", map.defaultEnforcerToolStartId).getInt(map.defaultEnforcerToolStartId);

			// Start getting configuration settings of Item/Block Id's
			map.IdThermalPipe = C.get("Mechanical", "ThermalPipeId", map.defaultIdThermalPipe).getInt(map.defaultIdThermalPipe);
			map.IdThermalMachines = C.get("Mechanical", "ThermalPumpId", map.defaultIdThermalMachines).getInt(map.defaultIdThermalMachines);
			
			map.IdFrostBow = C.get("Equipment", "FrostBowId", map.defaultIdFrostBow).getInt(map.defaultIdFrostBow);
			map.IdFrostGun = C.get("Equipment", "FrostGunId", map.defaultIdFrostGun).getInt(map.defaultIdFrostGun);
			map.IdCompiledFrostBlade = C.get("Equipment", "CompiledFrostBladeId", map.defaultIdCompiledFrostBlade).getInt(map.defaultIdCompiledFrostBlade);
			map.IdCompiledFrostSpade = C.get("Equipment", "CompiledFrostSpadeId", map.defaultIdCompiledFrostSpade).getInt(map.defaultIdCompiledFrostSpade);
			map.IdCompiledFrostPickaxe = C.get("Equipment", "CompiledFrostPickaxeId", map.defaultIdCompiledFrostPickaxe).getInt(map.defaultIdCompiledFrostPickaxe);
			map.IdCompiledFrostAxe = C.get("Equipment", "CompiledFrostAxeId", map.defaultIdCompiledFrostAxe).getInt(map.defaultIdCompiledFrostAxe);
			map.IdCompiledFrostHoe = C.get("Equipment", "CompiledFrostHoeId", map.defaultIdCompiledFrostHoe).getInt(map.defaultIdCompiledFrostHoe);
			map.IdFrozenFrostBlade = C.get("Equipment", "FrozenFrostBladeId", map.defaultIdFrozenFrostBlade).getInt(map.defaultIdFrozenFrostBlade);
			map.IdFrozenFrostSpade = C.get("Equipment", "FrozenFrostSpadeId", map.defaultIdFrozenFrostSpade).getInt(map.defaultIdFrozenFrostSpade);
			map.IdFrozenFrostPickaxe = C.get("Equipment", "FrozenFrostPickaxeId", map.defaultIdFrozenFrostPickaxe).getInt(map.defaultIdFrozenFrostPickaxe);
			map.IdFrozenFrostAxe = C.get("Equipment", "FrozenFrostAxeId", map.defaultIdFrozenFrostAxe).getInt(map.defaultIdFrozenFrostAxe);
			map.IdFrozenFrostHoe = C.get("Equipment", "FrozenFrostHoeId", map.defaultIdFrozenFrostHoe).getInt(map.defaultIdFrozenFrostHoe);
			map.IdFrostBlade = C.get("Equipment", "FrostBladeId", map.defaultIdFrostBlade).getInt(map.defaultIdFrostBlade);
			map.IdFrostSpade = C.get("Equipment", "FrostSpadeId", map.defaultIdFrostSpade).getInt(map.defaultIdFrostSpade);
			map.IdFrostPickaxe = C.get("Equipment", "FrostPickaxeId", map.defaultIdFrostPickaxe).getInt(map.defaultIdFrostPickaxe);
			map.IdFrostAxe = C.get("Equipment", "FrostAxeId", map.defaultIdFrostAxe).getInt(map.defaultIdFrostAxe);
			map.IdFrostHoe = C.get("Equipment", "FrostHoeId", map.defaultIdFrostHoe).getInt(map.defaultIdFrostHoe);
			
			map.IdParticleItem = C.get("Misc", "DummyParticleItemId", map.defaultIdParticleItem).getInt(map.defaultIdParticleItem);
			map.IdCraftingItems = C.get("Misc", "CraftingItemsId", map.defaultIdCraftingItems).getInt(map.defaultIdCraftingItems);
			map.IdIcicle = C.get("Misc", "IcicleId", map.defaultIdIcicle).getInt(map.defaultIdIcicle);
			map.IdBlockIcicle = C.get("Misc", "BlockIcicleId", map.defaultIdBlockIcicle).getInt(map.defaultIdBlockIcicle);
			
			// Getting configuration about update checker
            Property update = C.get("Updates", "Check for updates", true);
            Property ignoreMinorBuilds = C.get("Updates", "Ignore minor builds", true);
            Property ignoreOtherMCVersions = C.get("Updates", "Ignore other MC versions", false);
            version.checkForUpdates = update.getBoolean(false);
            version.ignoremB = ignoreMinorBuilds.getBoolean(true);
            version.ignoreMC = ignoreOtherMCVersions.getBoolean(false);
		}
		catch(Exception e)
		{
			FCLog.log(Level.WARNING, "FrostCraft failed to load the configuration file", e);
		}
		finally
		{
			C.save();
		}
    }
	
    @Init
    public void load(FMLInitializationEvent evt)
    {
    	map.initialiseBlocks();
    	map.initialiseItems();
    	map.initialiseEntities();
    	map.initialiseWorld();
    	if(evt.getSide().isClient())
    	{
    		map.initialiseRenderers();
    	}
    	FCPotion.RegisterPotionEffects();
    	FrostEnchantment.registerFrostEnchantments();
    	NetworkRegistry.instance().registerGuiHandler(instance, proxy);
    	TickRegistry.registerTickHandler(EnergyRequestRegistry.getInstance(), Side.SERVER);
    	TickRegistry.registerTickHandler(EffectTicker.instance(), Side.SERVER);
    	TickRegistry.registerTickHandler(new WorldTicker(), Side.SERVER);
    	NetworkRegistry.instance().registerConnectionHandler(new NetworkManager());
    	RecipeRegistry.registry().registerRecipes();
    	MinecraftForge.EVENT_BUS.register(new EventListener());
    }
    
	@PostInit
    public void postload(FMLPostInitializationEvent evt)
    {
		map.initialiseEnfrocerItems();
		if(evt.getSide().isClient())
		{
			for(int i = 0;i < map.EnforcedTools.length;i++)
			{
				if(map.EnforcedTools[i] != null)
				{
					MinecraftForgeClient.registerItemRenderer(map.EnforcedTools[i].itemID , new RenderFrostTool());
				}
			}
		}
        try
        {
            if (checkForUpdates)
            {
                version.checkForUpdates();
            }
        }
        catch (Throwable E)
        {
        	FCLog.log(Level.SEVERE, "FrostCraft failed to check for updates", E);
        }
    }
}
