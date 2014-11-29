package team.ApiPlus.Manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import team.ApiPlus.Util.Utils;

/**
 * Configuration Manager for API+.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class ConfigManager {
	private static ConfigManager instance;
	private List<FileConfiguration> configs = new ArrayList<FileConfiguration>();
	
	private ConfigManager() {
		if(instance != null) Utils.info("Cannot have multiple Instances of the Item Manager.");
	}
	
	/**
	 * Method used for Adding Configuration to ConfigManager.
	 * @param con FileConfiguration to be Added.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public boolean add(FileConfiguration con) {
		if(check(con)) return false;
		else {
			configs.add(con);
			return true;
		}
	}
	
	
	/**
	 * Method used for Converting and Adding File to ConfigManager.
	 * @param f File to be Added.
	 * @return boolean True if action completed successfully, false if not.
	 */
	public boolean add(File f) {
		try {
			FileConfiguration con = new YamlConfiguration();
			con.load(f);
			return add(con);
		} catch (Exception e) {
			Utils.debug(e);
			return false;
		}
	}
	
	/**
	 * Method used for removing FileConfiguration from ConfigManager.
	 * @param con FileConfiguration to remove.
	 * @return boolean True if action was completed successfully, false if not.
	 */
	public boolean remove(FileConfiguration con) {
		if(check(con)) {
			configs.remove(con);
			return true;
		} else return false;
	}
	
	/**
	 * Method used for Removing FileConfiguration of File from ConfigManager.
	 * @param f File to remove.
	 * @return boolean True if action was completed successfully, false if not.
	 */
	public boolean remove(File f) {
		try {
			FileConfiguration con = new YamlConfiguration();
			con.load(f);
			return remove(con);
		} catch (Exception e) {
			Utils.debug(e);
			return false;
		}
	}
	
	/**
	 * Method used for checking if ConfigManager has FileConfiguration.
	 * @param con FileConfiguration to check.
	 * @return boolean True if contains, False if not.
	 */
	public boolean check(FileConfiguration con) {
		if(configs.contains(con)) return true;
		else return false;
	}
	
	/**
	 * Method used for checking if ConfigManager has File as FileConfiguration.
	 * @param f File to check.
	 * @return boolean True if contains, False if not.
	 */
	public boolean check(File f) {
		try {
			FileConfiguration con = new YamlConfiguration();
			con.load(f);
			return check(con);
		} catch (Exception e) {
			Utils.debug(e);
			return false;
		}
	}
	
	/**
	 * Method used for getting FileConfiguration from ConfigManager.
	 * @param f File to retrieve.
	 * @return FileConfiguration FileConfiguration found, null if none found.
	 */
	public FileConfiguration get(File f) {
		try {
			FileConfiguration con = new YamlConfiguration();
			con.load(f);
			if(check(con)) return configs.get(configs.indexOf(con));
			else return null;
		} catch (Exception e) {
			Utils.debug(e);
			return null;
		}
	}
	
	/**
	 * Method used for getting an Instance of the API's ConfigManager, Only one instance is allowed.
	 * @return ConfigManager Instance of the API's ConfigManager.
	 */
	public static ConfigManager getInstance() {
		if(instance == null) instance = new ConfigManager();
		return instance;
	}
}
