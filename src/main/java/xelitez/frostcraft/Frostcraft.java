package xelitez.frostcraft;

import java.io.File;
import java.lang.reflect.Method;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.apache.logging.log4j.Level;

import xelitez.frostcraft.client.render.RenderFrostTool;
import xelitez.frostcraft.command.CommandGenerate;
import xelitez.frostcraft.command.CommandJoinFight;
import xelitez.frostcraft.effect.EffectTicker;
import xelitez.frostcraft.effect.FCPotion;
import xelitez.frostcraft.enchantment.FrostEnchantment;
import xelitez.frostcraft.energy.EnergyRequestRegistry;
import xelitez.frostcraft.netty.Pipeline;
import xelitez.frostcraft.network.NetworkManager;
import xelitez.frostcraft.registry.CommonProxy;
import xelitez.frostcraft.registry.IdMap;
import xelitez.frostcraft.registry.RecipeRegistry;
import xelitez.frostcraft.registry.Settings;
import xelitez.frostcraft.world.WorldTicker;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.FMLInjectionData;

@Mod(	
		modid = "XEZFrostcraft",
		name = "Frostcraft", 
		version = Version.version)
public class Frostcraft 
{
	@Instance(value = "XEZFrostcraft")
	public static Frostcraft instance;
	
	private Configuration C;
	private IdMap map = new IdMap();
	
	@SidedProxy(clientSide = "xelitez.frostcraft.registry.ClientProxy", serverSide = "xelitez.frostcraft.registry.CommonProxy")
	public static CommonProxy proxy = new CommonProxy();
	
	public Version version = new Version();
	
	public static Pipeline PIPELINE = new Pipeline();
	
	@EventHandler
    public void preload(FMLPreInitializationEvent evt)
    {
		evt.getModMetadata().version = Version.getVersion() + " for " + Version.MC;
		
		C = new Configuration(new File((File)FMLInjectionData.data()[6], "XEliteZ/Frostcraft.cfg"));
		try
		{
			C.load();
			
			C.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, "IDs that are -1 will be given the next free id possible");
			
			Settings.MaxEnfrocerItems = C.get(Configuration.CATEGORY_GENERAL, "MaxEnforcerItems", Settings.defaultMaxEnforcedItems).getInt(Settings.defaultMaxEnforcedItems);
			
			// Start getting world configuration
			Settings.EndlessWinterID = C.get("World", "EndlessWinterId", Settings.EndlessWinterID).getInt();
			
			// Start getting potion configuration
			Settings.potionFreezeId = C.get("Potion", "PotionFreezeId", Settings.potionFreezeId).getInt();
			Settings.potionFrostburnId = C.get("Potion", "PotionFrostburnId", Settings.potionFrostburnId).getInt();
			
			// Start getting enchantments configuration
			Settings.enchantmentFreezeId = C.get("Enchantments", "EnchantmentFreezeId", Settings.enchantmentFreezeId).getInt();
			Settings.enchantmentFrostburnId = C.get("Enchantments", "EnchantmentFrostburnId", Settings.enchantmentFrostburnId).getInt();
			
			// Getting configuration about update checker
            Property update = C.get("Updates", "Check for updates", true);
            Property ignoreMinorBuilds = C.get("Updates", "Ignore minor builds", true);
            Property ignoreOtherMCVersions = C.get("Updates", "Ignore other MC versions", false);
            Property notify = C.get("Updates", "Notify about XEZUpdateUtility", true);
            Version.notify = notify.getBoolean(true);
            Settings.checkForUpdates = update.getBoolean(true);
            Settings.ignoremB = ignoreMinorBuilds.getBoolean(true);
            Settings.ignoreMC = ignoreOtherMCVersions.getBoolean(false);
		}
		catch(Exception e)
		{
			FCLog.log(Level.WARN, "Frostcraft failed to load the configuration file", e);
		}
		finally
		{
			C.save();
		}
    	map.initialiseBlocks();
    	map.initialiseItems();
    	map.initialiseEntities();
    	map.initialiseWorld();
    	if(evt.getSide().isClient())
    	{
    		map.initialiseRenderers();
    	}
		map.initialiseEnfrocerItems();
    }
	
	@EventHandler
    public void load(FMLInitializationEvent evt)
    {
		if(evt.getSide().isClient())
		{
	    	MinecraftForge.EVENT_BUS.register(new EventListenerClient());
		}
    	FCPotion.RegisterPotionEffects();
    	FrostEnchantment.registerFrostEnchantments();
    	NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
    	FMLCommonHandler.instance().bus().register(EffectTicker.instance());
    	FMLCommonHandler.instance().bus().register(EnergyRequestRegistry.getInstance());
    	FMLCommonHandler.instance().bus().register(new WorldTicker());
    	FMLCommonHandler.instance().bus().register(new NetworkManager());
    	RecipeRegistry.registry().registerRecipes();
    	MinecraftForge.EVENT_BUS.register(new EventListener());
    	PIPELINE.initalise();
        try
        {
            if (Settings.checkForUpdates)
            {
				Class<? extends Object> clazz = Class.forName("xelitez.updateutility.UpdateRegistry");
                Method registermod = clazz.getDeclaredMethod("addMod", Object.class, Object.class);
				Class<? extends Object> clazz2 = Class.forName("xelitez.frostcraft.plugins.Update");
                registermod.invoke(null, this, clazz2.newInstance());
                Version.registered = true;
            }
        }
        catch (Exception E)
        {
        	FCLog.log(Level.INFO, "Frostcraft failed to register to the XEZUpdateUtility");
        	FCLog.log(Level.INFO, "It isn't required but highly recommended");
            if (Settings.checkForUpdates)
            {
                version.checkForUpdatesNoUtility();
            }
        }
    }
    
	@EventHandler
    public void postload(FMLPostInitializationEvent evt)
    { 
//		try
//  		{
//			Class.forName("codechicken.nei.api.API");
// 			NEIFCLoader.register();
//  		} 
// 		catch(Exception e)
// 		{
// 			
// 		}

		if(evt.getSide().isClient())
		{
			for(int i = 0;i < map.EnforcedTools.length;i++)
			{
				if(map.EnforcedTools[i] != null)
				{
					MinecraftForgeClient.registerItemRenderer(map.EnforcedTools[i], new RenderFrostTool());
				}
			}
		}
        proxy.registerSidedElements();
        PIPELINE.postInitialise();
    }
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent evt)
	{
		evt.registerServerCommand(new CommandGenerate());
		evt.registerServerCommand(new CommandJoinFight());
	}
}
