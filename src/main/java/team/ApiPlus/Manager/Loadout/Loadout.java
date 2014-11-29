package team.ApiPlus.Manager.Loadout;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import team.ApiPlus.Util.FileUtil;
import team.ApiPlus.Util.Utils;

/**
 * 
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class Loadout {
	private Map<String,String> info = new HashMap<String,String>();
	private List<FileConfiguration> configs = new ArrayList<FileConfiguration>();
	
	/**
	 * Static Method used for creating Loadout.
	 * @param location File representing the Loadout to create.
	 * @return Loadout Returns instance of Loadout.
	 */
	public static Loadout create(File location) {
		return new Loadout(location);
	}
	
	private Loadout(File location) {
		if(!location.getPath().endsWith(".zip")) return;
		try {
			ZipInputStream in =  new ZipInputStream(new FileInputStream(location));
			info.put("NAME",location.getName());
			ZipEntry ent;
			while((ent = in.getNextEntry()) != null) {
				if(!ent.toString().endsWith(".yml")) {
					Utils.debug("Not YAML: " + ent.toString());
					return;
				} else {
					FileConfiguration con = new YamlConfiguration();
					File child = new File(location.getParent() + File.separator + ent.getName());
					FileUtil.copyOpen(in, child);
					con.load(child);
					if(child.getName().equalsIgnoreCase("info.yml")) {
						info.put("NAME",con.getString("loadout-name"));
						info.put("VERSION", con.getString("loadout-version"));
						info.put("AUTHORS", con.getString("loadout-authors"));
						info.put("PLUGIN", con.getString("loadout-plugin"));
						info.put("PVERSION", con.getString("loadout-plugin-version"));
						Utils.info(String.format("Loadout: %s has now been loaded.", getName()));
					}
					FileUtil.delete(child);
					configs.add(con);
				}
			}
			in.close();
			LoadoutManager.getInstance().addLoadout(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used for getting list of FileConfiguration used in this Loadout.
	 * @return List<FileConfiguration> List of FileConfigurations.
	 */
	public List<FileConfiguration> getConfigs() {
		return configs;
	}
	
	/**
	 * Method used for getting FileConfigratuon used in this Loadout by name.
	 * @param name String name to look for.
	 * @return FileConfiguration FileConfiguration used in this Loadout.
	 */
	public FileConfiguration getConfig(String name) {
		for(FileConfiguration f : configs) {
			if(f.getName().equalsIgnoreCase(name)) return f;
		}
		return null;
	}
	
	/**
	 * Method used for getting Name of Loadout.
	 * @return String Name of Loadout.
	 */
	public String getName() {
		return info.get("NAME");
	}
	
	/**
	 * Method used for getting Version of Loadout.
	 * @return String Version of Loadout, null if none found.
	 */
	public String getVersion() {
		if(info.containsKey("VERSION")) {
			return info.get("VERSION");
		} else return null;
	}
	
	/**
	 * Method used for getting Authors of Loadout.
	 * @return String[] Array of Aurhtos of Loadout, null if none found.
	 */
	public String[] getAuthors() {
		if(info.containsKey("AUTHORS")) {
			return info.get("AUTHORS").split(",");
		} else return null;
	}
	
	/**
	 * Method used for getting Plugin Name for Loadout.
	 * @return String Plugin associated with Loadout, null if none found.
	 */
	public String getPlugin() {
		if(info.containsKey("PLUGIN")) {
			return info.get("PLUGIN");
		} else return null;
	}
	
	/**
	 * Method used for getting Plugin Version for Loadout.
	 * @return String Plugin Version associated with Loadout, null if none found.
	 */
	public String getPluginVersion() {
		if(info.containsKey("PVERSION")) {
			return info.get("PVERSION");
		} else return null;
	}
}
