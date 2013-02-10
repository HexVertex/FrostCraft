package xelitez.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

import cpw.mods.fml.common.CertificateHelper;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.IDownloadDisplay;

public class Downloader 
{
	
    static IDownloadDisplay downloadMonitor;
    private static ByteBuffer downloadBuffer = ByteBuffer.allocateDirect(1 << 22);
    
    static Logger log;
    
    public Downloader()
    {
    	log = Logger.getLogger("XEZDownloader");
    	log.setParent(FMLLog.getLogger());
    }
    
    public static void downloadFile(File libFile, String rootUrl,String realFilePath, String hash)
    {
        try
        {
            URL libDownload = new URL(String.format(rootUrl,realFilePath));
            downloadMonitor.updateProgressString("Downloading file %s", libDownload.toString());
            log.info("Downloading file");
            URLConnection connection = libDownload.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", "FML Relaunch Downloader");
            int sizeGuess = connection.getContentLength();
            performDownload(connection.getInputStream(), sizeGuess, hash, libFile);
            downloadMonitor.updateProgressString("Download complete");
            log.info("Download complete");
        }
        catch (Exception e)
        {
            if (downloadMonitor.shouldStopIt())
            {
            	log.warning("You have stopped the downloading operation before it could complete");
                return;
            }
            if (e instanceof RuntimeException) throw (RuntimeException)e;
            log.severe("There was a problem downloading the file %s automatically. Perhaps you " +
            		"have an environment without internet access. You will need to download " +
            		"the file manually or restart and let it try again\n");
            libFile.delete();
            throw new RuntimeException("A download error occured", e);
        }
    }
    
    private static void performDownload(InputStream is, int sizeGuess, String validationHash, File target)
    {
        if (sizeGuess > downloadBuffer.capacity())
        {
            throw new RuntimeException(String.format("The file to download is too large", target.getName()));
        }
        downloadBuffer.clear();

        int bytesRead, fullLength = 0;

        downloadMonitor.resetProgress(sizeGuess);
        try
        {
            downloadMonitor.setPokeThread(Thread.currentThread());
            byte[] smallBuffer = new byte[1024];
            while ((bytesRead = is.read(smallBuffer)) >= 0) {
                downloadBuffer.put(smallBuffer, 0, bytesRead);
                fullLength += bytesRead;
                if (downloadMonitor.shouldStopIt())
                {
                    break;
                }
                downloadMonitor.updateProgress(fullLength);
            }
            is.close();
            downloadMonitor.setPokeThread(null);
            downloadBuffer.limit(fullLength);
            downloadBuffer.position(0);
        }
        catch (InterruptedIOException e)
        {
            // We were interrupted by the stop button. We're stopping now.. clear interruption flag.
            Thread.interrupted();
            return;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


        try
        {
            String cksum = generateChecksum(downloadBuffer);
            if (cksum.equals(validationHash))
            {
                downloadBuffer.position(0);
                FileOutputStream fos = new FileOutputStream(target);
                fos.getChannel().write(downloadBuffer);
                fos.close();
            }
            else
            {
                throw new RuntimeException(String.format("The downloaded file %s has an invalid checksum %s (expecting %s). The download did not succeed correctly and the file has been deleted. Please try launching again.", target.getName(), cksum, validationHash));
            }
        }
        catch (Exception e)
        {
            if (e instanceof RuntimeException) throw (RuntimeException)e;
            throw new RuntimeException(e);
        }
    }
    
    private static String generateChecksum(ByteBuffer buffer)
    {
        return CertificateHelper.getFingerprint(buffer);
    }
}
