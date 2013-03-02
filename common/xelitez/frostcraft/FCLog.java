package xelitez.frostcraft;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class FCLog 
{
	public static Logger log;
	private static boolean configured = false;
	
	public static void registerLogger()
	{
		log = Logger.getLogger("FrostCraft");
		log.setParent(FMLLog.getLogger());
		configured = true;
	}
	
    public static void log(Level level, String format, Object... data)
    {
        if (!configured)
        {
        	registerLogger();
        }
        log.log(level, String.format(format, data));
    }

    public static void log(Level level, Throwable ex, String format, Object... data)
    {
        if (!configured)
        {
        	registerLogger();
        }
        log.log(level, String.format(format, data), ex);
    }

    public static void severe(String format, Object... data)
    {
        log(Level.SEVERE, format, data);
    }

    public static void warning(String format, Object... data)
    {
        log(Level.WARNING, format, data);
    }

    public static void info(String format, Object... data)
    {
        log(Level.INFO, format, data);
    }

    public static void fine(String format, Object... data)
    {
        log(Level.FINE, format, data);
    }

    public static void finer(String format, Object... data)
    {
        log(Level.FINER, format, data);
    }

    public static void finest(String format, Object... data)
    {
        log(Level.FINEST, format, data);
    }
}
