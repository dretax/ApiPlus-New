package team.ApiPlus.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * API+ File Utility Class.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class FileUtil {

	/**
	 * Method used for Copying one InputStream to a File.
	 * @param in InputStream to be Copied from.
	 * @param file File to be saved to.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public synchronized static boolean copy(InputStream in, File file){
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			Utils.debug(e);
			return false;
		}
		return true;
	}
	
	/**
	 * Method used for Copying one File to another File.
	 * @param in File to be copied.
	 * @param out File to be saved to.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public synchronized static boolean copy(File in, File out) {
		try {
			InputStream is = new FileInputStream(in);
			return copy(is,out);
		} catch (Exception e) {
			Utils.debug(e);
			return false;
		}
	}
	
	/**
	 * Method used for creating a File based on supplied path.
	 * @param path String path to be used.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public synchronized static boolean create(String path){
		File f = new File(path);
		return create(f);
	}
	
	/**
	 * Method used for creating a File based on File Object.
	 * @param f File to be used.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public synchronized static boolean create(File f){
		if(!f.exists()){
			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e) {
				Utils.debug(e);
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	
	/**
	 * Method used for deleting a File based on supplied path.
	 * @param path String path to be used.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public synchronized static boolean delete(String path) {
		File f = new File(path);
		return delete(f);
	}
	
	/**
	 * Method used for deleting a File based on File Object.
	 * @param f File to be used.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public synchronized static boolean delete(File f){
		return f.delete();
	}
	
	/**
	 * Method used for Copying one File to another File but leaves InputStream Open.
	 * @param in File to be copied.
	 * @param out File to be saved to.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public synchronized static boolean copyOpen(InputStream in, File file){
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
		} catch (Exception e) {
			Utils.debug(e);
			return false;
		}
		return true;
	}
}
