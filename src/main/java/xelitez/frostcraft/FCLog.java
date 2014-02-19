package xelitez.frostcraft;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class FCLog 
{
    public static void log(Level level, String format, Object... data)
    {
        FMLLog.log("Frostcraft", level, String.format(format, data));
    }

    public static void log(Level level, Throwable ex, String format, Object... data)
    {
    	FMLLog.log("Frostcraft", level, String.format(format, data), ex);
    }

    public static void severe(String format, Object... data)
    {
        log(Level.ERROR, format, data);
    }

    public static void warning(String format, Object... data)
    {
        log(Level.WARN, format, data);
    }

    public static void info(String format, Object... data)
    {
        log(Level.INFO, format, data);
    }

    public static void fine(String format, Object... data)
    {
        log(Level.DEBUG, format, data);
    }

    public static void finer(String format, Object... data)
    {
        log(Level.TRACE, format, data);
    }
}
