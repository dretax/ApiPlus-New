package team.ApiPlus.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

import team.ApiPlus.ApiPlus;
import team.ApiPlus.API.Type.BlockType;
import team.ApiPlus.API.Type.ItemType;
import team.ApiPlus.API.Type.MaterialType;
import team.ApiPlus.Manager.BlockManager;
import team.ApiPlus.Manager.ItemManager;

/**
 * Utility Class containing cross-class methods.
 * @author SirTyler (Tyler Martin)
 * @version 1.0
 */
public class Utils {
	private static boolean useDebug = true;
	
	
	public synchronized static void sendNotification(SpoutPlayer sp, String title, String text, ItemStack icon, int duration) {
			if(title.length()>26){
				warning("Too long notification. Check your item names.");
				title = title.replace(title.substring(25, title.length()-1),"");
			}
			if(text.length()>26){
				warning("Too long notification. Check your item names.");
				text = text.replace(text.substring(25, text.length()-1),"");
			}
			sp.sendNotification(title, text, icon, duration);
	}
	
	
	/**Method used to check weather a material was created by API+
	 * @param name The material name to check
	 * @return True if material belongs to API+, else False
	 */
	public synchronized static boolean isApiPlusMaterial(String name) {
		for(ItemType i : ItemManager.getInstance().getItems())
			if(i.getName().equals(name))
				return true;
		for(BlockType b : BlockManager.getInstance().getBlocks())
			if(b.getName().equals(name))
				return true;
		return false;
	}
	
	/** Method used to get a certain material created by API+ by the name
	 * @param name Material name to get
	 * @return The Material with the given name
	 */
	public synchronized static MaterialType getApiPlusMaterial(String name) {
		for(ItemType i : ItemManager.getInstance().getItems())
			if(i.getName().equals(name))
				return i;
		for(BlockType b : BlockManager.getInstance().getBlocks())
			if(b.getName().equals(name))
				return b;
		return null;
	}
	
	/**
	 * Method used for checking if a block is defined as transparent
	 * @param b Block to check
	 * @return boolean true if b was transparent, else false
	 */
	public  synchronized static boolean isTransparent(Block b){
		return ApiPlus.transparentMaterials.contains(b.getType());
	}
	
	/**
	 * Method used for getting the key in a map with a given value
	 * @param map The map to be used
	 * @param value The value to get the key for
	 * @return Object Matching key if contained, else null
	 */
	public  synchronized static <K, V> Object getKey(Map<K, V> map, Object value){
		for(Entry<K, V> entry : new HashSet<Entry<K, V>>(map.entrySet())){
			if(entry.getValue().equals(value)){
				return entry.getKey();
			}
		}
		return null;
	}
	
	/**
	 * Method used for writing a Info message to the Logger.
	 * @param message String message to write.
	 */
	public synchronized static void info(String message) {
		Bukkit.getLogger().log(Level.INFO, String.format("[API+] %s", message));
	}
	
	/**
	 * Method used for writing multiple Info messages to the Logger.
	 * @param message String Array of messages to write.
	 */
	public synchronized static void info(String[] message) {
		for(String s : message) {
			Bukkit.getLogger().log(Level.INFO, String.format("[API+] %s", s));
		}
	}
	
	/**
	 * Method used for writing a Warning message to the Logger.
	 * @param message String message to write.
	 */
	public synchronized static void warning(String message) {
		Bukkit.getLogger().log(Level.WARNING, String.format("[API+] %s", message));
	}
	
	/**
	 * Method used for writing multiple Warning messages to the Logger.
	 * @param message String Array of messages to write.
	 */
	public synchronized static void warning(String[] message) {
		for(String s : message) {
			Bukkit.getLogger().log(Level.WARNING, String.format("[API+] %s", s));
		}
	}
	
	/**
	 * Method used for setting Debug mode of API.
	 * @param b Boolean value to be set.
	 */
	public synchronized static void setDebug(boolean b) {
		useDebug = b;
	}
	
	/**
	 * Method used for a writing Debug message to the Logger.
	 * @param message String message to write.
	 */
	public synchronized static void debug(String message) {
		if(useDebug == false) return;
		Bukkit.getLogger().log(Level.INFO, String.format("[API+][Debug] %s", message));
	}
	
	public synchronized static void debug(Exception e) {
		if(useDebug == false) return;
		Bukkit.getLogger().log(Level.INFO, String.format("[API+][Debug] %s %s", e.getLocalizedMessage(), e.getCause()));
	}
	
	/**
	 * Method used for writing multiple Debug messages to the Logger.
	 * @param message String Array of messages to write. 
	 */
	public synchronized static void debug(String[] message) {
		if(useDebug == false) return;
		for(String s : message) {
			Bukkit.getLogger().log(Level.INFO, String.format("[API+][Debug] %s", s));
		}
	}
	
	/**
	 * Method used for checking if List contains CustomItems.
	 * @param items List<ItemStack> to check.
	 * @return boolean True if contains CustomItems, False if not.
	 */
	public synchronized static boolean containsCustomItems(List<ItemStack> items){
		for(ItemStack i : items){
			if(isCustomItem(i)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method used for checking if ItemStack is CustomItem.
	 * @param item ItemStack to check.
	 * @return boolean True if is CustomItem, False if not.
	 */
	public synchronized static boolean isCustomItem(ItemStack item){
		return new SpoutItemStack(item).isCustomItem();
	}
	
	/**
	 * Method used for getting List<Entity> of Nearby Entities of Location.
	 * @param loc Location to use as reference.
	 * @param radiusX Double value to use as max radius on X axis.
	 * @param radiusY Double value to use as max radius on Y axis.
	 * @param radiusZ Double value to use as max radius on Z axis.
	 * @return List<Entity> List of Entities within range.
	 */
	public synchronized static List<Entity> getNearbyEntities(Location loc, double radiusX, double radiusY, double radiusZ) {
		Entity e = loc.getWorld().spawn(loc, ExperienceOrb.class);
		@SuppressWarnings("unchecked")
		List<Entity> entities = (List<Entity>) ((ArrayList<Entity>) e.getNearbyEntities(radiusX, radiusY, radiusZ)).clone();
		e.remove();
		return entities;
	}
	
	/**
	 * Method used for getting RandomInteger based on start and stop positions.
	 * @param start Integer to start with.
	 * @param end Integer to end with.
	 * @return Integer Random Integer created.
	 */
	public synchronized static int getRandomInteger(int start, int end) {
		Random rand = new Random();
		return start + rand.nextInt(end + 1);
	}
	
	/**
	 * Method used for getting the Direction of Location as Vector.
	 * @param l Location to be referenced.
	 * @return Vector Vector of Direction.
	 */
	public synchronized static Vector getDirection(Location l) {
		Vector vector = new Vector();

		double rotX = l.getYaw();
		double rotY = l.getPitch();

		vector.setY(-Math.sin(Math.toRadians(rotY)));

		double h = Math.cos(Math.toRadians(rotY));

		vector.setX(-h * Math.sin(Math.toRadians(rotX)));
		vector.setZ(h * Math.cos(Math.toRadians(rotX)));

		return vector;
	}
	
	/**
	 * Method used for setting the Location to look at another Location.
	 * @param loc Location to be referenced.
	 * @param lookat Location to look at.
	 * @return Location Location facing lookat Location.
	 */
	public synchronized static Location setLookingAt(final Location loc, final Location lookat) {
		Location location = loc.clone();
		double dx = lookat.getX() - location.getX();
		double dy = lookat.getY() - location.getY();
		double dz = lookat.getZ() - location.getZ();

		if (dx != 0) {
			if (dx < 0) {
				location.setYaw((float) (1.5 * Math.PI));
			} else {
				location.setYaw((float) (0.5 * Math.PI));
			}
			location.setYaw((float) location.getYaw() - (float) Math.atan(dz / dx));
		} else if (dz < 0) {
			location.setYaw((float) Math.PI);
		}
		double dxz = Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2));
		location.setPitch((float) - Math.atan(dy / dxz));
		location.setYaw(-location.getYaw() * 180f / (float) Math.PI);
		location.setPitch(location.getPitch() * 180f / (float) Math.PI);

		return location;
	}
	
	/**
	 * Method used for getting location of Player's Hand.
	 * @param p Player to be referenced.
	 * @return Location of Player's Hand.
	 */
	public synchronized static Location getHandLocation(Player p) {
		Location loc = p.getLocation().clone();

		double a = loc.getYaw() / 180D * Math.PI + Math.PI / 2;
		double l = Math.sqrt(0.8D * 0.8D + 0.4D * 0.4D);

		loc.setX(loc.getX() + l * Math.cos(a) - 0.8D * Math.sin(a));
		loc.setY(loc.getY() + p.getEyeHeight() - 0.2D);
		loc.setZ(loc.getZ() + l * Math.sin(a) + 0.8D * Math.cos(a));
		return loc;
	}
	
	/**
	 * Method used for getting a Sphere around given Location with given radius.
	 * @param center Location to use as center of sphere.
	 * @param radius Double to use as radius around center.
	 * @return Set<Block> Set of blocks that make of Sphere.
	 */
	public static Set<Block> getSphere(final Location pos, final int radius) {
		Set<Block> blocks = new HashSet<Block>();
        double zsq, xsq, bsq = Math.pow(radius, 2);

        for (int z = -radius; z <= radius; z++) {
        	zsq = Math.pow(z, 2);
            for (int x = -radius; x <= radius; x++) {
            	xsq = Math.pow(x, 2);
                for (int y = -radius; y <= radius; y++) {
                    if ((xsq + Math.pow(y, 2) + zsq) <= bsq) {
                        blocks.add(new Location(pos.getWorld(), pos.getBlockX() + x, pos.getBlockY() + y, pos.getBlockZ() + z).getBlock());
                        blocks.add(new Location(pos.getWorld(), pos.getBlockX() + x, pos.getBlockY() + y, pos.getBlockZ() - z).getBlock());
                        blocks.add(new Location(pos.getWorld(), pos.getBlockX() + x, pos.getBlockY() - y, pos.getBlockZ() - z).getBlock());
                        blocks.add(new Location(pos.getWorld(), pos.getBlockX() - x, pos.getBlockY() - y, pos.getBlockZ() - z).getBlock());
                        blocks.add(new Location(pos.getWorld(), pos.getBlockX() - x, pos.getBlockY() + y, pos.getBlockZ() + z).getBlock());
                        blocks.add(new Location(pos.getWorld(), pos.getBlockX() - x, pos.getBlockY() - y, pos.getBlockZ() + z).getBlock());
                        blocks.add(new Location(pos.getWorld(), pos.getBlockX() - x, pos.getBlockY() + y, pos.getBlockZ() - z).getBlock());
                        blocks.add(new Location(pos.getWorld(), pos.getBlockX() + x, pos.getBlockY() - y, pos.getBlockZ() + z).getBlock());
                    }
                }
            }
        }
        return blocks;
    }
    
	/**
	 * Method used for checking if TNT is allowed in given Region.
	 * @param loc Location to be referenced.
	 * @return boolean True if allowed, False if not.
	 */
	public synchronized static boolean tntIsAllowedInRegion(Location loc) {
		if (ApiPlus.hooks.containsKey("WorldGuard")) {
			WorldGuardPlugin wg = (WorldGuardPlugin) ApiPlus.hooks.get("WorldGuard");
			if (!wg.getGlobalRegionManager().allows(DefaultFlag.TNT, loc)) {
				return false;
			} else
				return true;
		} else
			return true;
	}
	
	/**
	 * Method used for Converting List<ItemStack> into ItemStack[]
	 * @param list List of ItemStack to be converted
	 * @return ItemStack[] ItemStack Array returned.
	 */
	public synchronized static ItemStack[] convertToArray(List<ItemStack> list) {
	    ItemStack[] items = new ItemStack[list.size()];
	    for (int i = 0; i < list.size(); i++) {
	    	items[i] = list.get(i);
	    }
		return items;
	}
}

