package team.ApiPlus.API;

import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import team.ApiPlus.ApiPlus;
import team.ApiPlus.API.Type.BlockType;
import team.ApiPlus.API.Type.ItemType;
import team.ApiPlus.Manager.BlockManager;
import team.ApiPlus.Manager.ItemManager;
import team.ApiPlus.Manager.TypeManager;
import team.ApiPlus.Util.Metrics;
import team.ApiPlus.Util.Metrics.Graph;

/**
 * PluginPlus class for use with API+.
 * @author SirTyler, Atlan1
 * @version 1.0
 */
abstract public class PluginPlus extends JavaPlugin {
	
	/**
	 * Method used to register this plugin to API+.
	 */
	public void registerPluginPlus() {
		try {
			ApiPlus.addHook(this);
			Metrics met = new Metrics(ApiPlus.getInstance());
			Graph g = met.createGraph("Plugins using Api+");
			g.addPlotter(new Metrics.Plotter(this.getName()) {
				@Override
				public int getValue() {
					return 1;
				}
			});
			met.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Abstract Method overwritten for Loading given FileConfigurations.
	 * @param con FileConfiguration to read.
	 * @return boolean True if action completed succesfully, False if not.
	 */
	abstract public boolean loadConfig(FileConfiguration con);
	
	/**
	 * Method used for building an Item and Adding it to the ItemManager.
	 * @param name String name of new Item.
	 * @param texture String texture of new Item.
	 * @param baseType String name of Type to base Item off.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public boolean addItem(String name, String texture, String baseType) {
		try {
			ItemType it = ItemManager.getInstance().buildItem(this, name, texture, baseType);
			ItemManager.getInstance().addItem(it);
			return true;
		} catch (Exception e) {
			Bukkit.getLogger().log(Level.INFO, String.format("[%s][ERROR] %s %s", this.getName(), e.getMessage(), e.getCause()));
			return false;
		}
	}
	
	/**
	 * Method used for building an Block and Adding it to the BlockManager.
	 * @param name String name of new Block.
	 * @param texture String texture of new Block.
	 * @param baseType String name of Type to base Block off.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public boolean addBlock(String name, boolean isOpaque, String baseType) {
		try {
			BlockType it = BlockManager.getInstance().buildBlock(this, name, isOpaque, baseType);
			BlockManager.getInstance().addBlock(it);
			return true;
		} catch (Exception e) {
			Bukkit.getLogger().log(Level.INFO, String.format("[%s][ERROR] %s %s", this.getName(), e.getMessage(), e.getCause()));
			return false;
		}
	}
	
	/**
	 * Method used for registering new item types.
	 * @param Map containing the names and the classes to add as new types.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public boolean registerItemTypes(Map<String, Class<? extends ItemType>> types) {
		try{
			for(String s : new HashSet<String>(types.keySet()))
				TypeManager.getInstance().registerItemType(s, types.get(s));
		}catch(Exception e){
			Bukkit.getLogger().log(Level.INFO, String.format("[%s][ERROR] %s %s", this.getName(), e.getMessage(), e.getCause()));
			return false;
		}
		return true;
	}
	
	/**
	 * Method used for registering new block types.
	 * @param Map containing the names and the classes to add as new types.
	 * @return boolean True if action completed successfully, False if not.
	 */
	public boolean registerBlockTypes(Map<String, Class<? extends BlockType>> types) {
		try{
			for(String s : new HashSet<String>(types.keySet()))
				TypeManager.getInstance().registerBlockType(s, types.get(s));
		}catch(Exception e){
			Bukkit.getLogger().log(Level.INFO, String.format("[%s][ERROR] %s %s", this.getName(), e.getMessage(), e.getCause()));
			return false;
		}
		return true;
	}
}
