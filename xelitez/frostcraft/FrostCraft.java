package xelitez.frostcraft;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import xelitez.frostcraft.block.BlockThermalPipe;
import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.energy.EnergyRequestRegistry;
import xelitez.frostcraft.network.NetworkManager;
import xelitez.frostcraft.network.PacketManagerClient;
import xelitez.frostcraft.network.PacketManagerServer;
import xelitez.frostcraft.registry.CommonProxy;
import xelitez.frostcraft.registry.IdMap;
import xelitez.frostcraft.registry.RecipeRegistry;
import xelitez.updateutility.UpdateRegistry;
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
	
	public Logger fcLog;
	
	@PreInit
    public void preload(FMLPreInitializationEvent evt)
    {
		fcLog = Logger.getLogger("FrostCraft");
		fcLog.setParent(FMLLog.getLogger());
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
			map.IdThermalPipe = C.get("Mechanical", "ThermalPipeId", map.defaultIdThermalPipe).getInt(map.defaultIdThermalPipe);
			map.IdThermalMachines = C.get("Mechanical", "ThermalPumpId", map.defaultIdThermalMachines).getInt(map.defaultIdThermalMachines);
			
			map.IdFrostBow = C.get("Equipment", "FrostBowId", map.defaultIdFrostBow).getInt(map.defaultIdFrostBow);
			map.IdFrostGun = C.get("Equipment", "FrostGunId", map.defaultIdFrostGun).getInt(map.defaultIdFrostGun);
			
			map.IdParticleItem = C.get("MiscItems", "DummyParticleItemId", map.defaultIdParticleItem).getInt(map.defaultIdParticleItem);
			map.IdCraftingItems = C.get("MiscItems", "CraftingItemsId", map.defaultIdCraftingItems).getInt(map.defaultIdCraftingItems);
			
            Property update = C.get("Updates", "Check for updates", true);
            Property ignoreMinorBuilds = C.get("Updates", "Ignore minor builds", true);
            Property ignoreOtherMCVersions = C.get("Updates", "Ignore other MC versions", false);
            version.checkForUpdates = update.getBoolean(false);
            version.ignoremB = ignoreMinorBuilds.getBoolean(true);
            version.ignoreMC = ignoreOtherMCVersions.getBoolean(false);
		}
		catch(Exception e)
		{
			fcLog.log(Level.WARNING, "FrostCraft failed to load the configuration file", e);
		}
		finally
		{
			C.save();
		}
    }
	
    @Init
    public void load(FMLInitializationEvent evt)
    {
    	map.initializeBlocks();
    	map.initializeItems();
    	map.initializeEntities();
    	if(evt.getSide().isClient())
    	{
    		map.initializeRenderers();
    	}
    	FCPotion.RegisterPotionEffects();
    	NetworkRegistry.instance().registerGuiHandler(instance, proxy);
    	TickRegistry.registerTickHandler(EnergyRequestRegistry.getInstance(), Side.SERVER);
    	TickRegistry.registerTickHandler(EffectTicker.instance(), Side.SERVER);
    	NetworkRegistry.instance().registerConnectionHandler(new NetworkManager());
    	RecipeRegistry.registry();
    }
    
	@PostInit
    public void postload(FMLPostInitializationEvent evt)
    {
		UpdateRegistry.addMod(this.instance, this.version);
        try
        {
            if (checkForUpdates)
            {
                UpdateRegistry.addMod(this.instance, this.version);
            }
        }
        catch (Exception E)
        {
        	fcLog.log(Level.SEVERE, "FrostCraft failed to check for updates", E);
        }
    }
}
