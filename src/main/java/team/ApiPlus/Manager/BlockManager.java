package team.ApiPlus.Manager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import team.ApiPlus.API.PluginPlus;
import team.ApiPlus.API.Type.BlockType;
import team.ApiPlus.Util.Utils;

/**
 * API+ Custom Block Manager - used for managing and building custom items
 * @author SirTyler, Atlan1
 * @version 1.0
 */
public class BlockManager {
	
	private static BlockManager instance;
	private List<BlockType> list = new ArrayList<BlockType>();
	
	private BlockManager() {
		if(instance != null) Utils.info("Cannot have multiple Instances of the Block Manager.");
	}
	
	public boolean addBlock(BlockType i) {
		if(list.contains(i)) return false;
		else {
			list.add(i);
			return true;
		}
	}
	
	/**
	 * Method used for Removing Block from Manager.
	 * @param i Block to be removed.
	 * @return boolean True if action was completed, False if not.
	 */
	public boolean removeBlock(BlockType i) {
		if(list.contains(i)) {
			list.remove(i);
			return true;
		} else return false;
	}
	
	/**
	 * Method used for Removing Block from Manager based on Name.
	 * @param name Name of Block to be removed.
	 * @return boolean True if action was completed, False if not.
	 */
	public boolean removeItem(String name) {
		BlockType i = getBlock(name);
		if(i == null) return false;
		return removeBlock(i);
	}
	
	/**
	 * Method used for getting Block from Manager based on Name.
	 * @param name Name of Block to look for.
	 * @return Block Block found, returns null if nothing.
	 */
	public BlockType getBlock(String name) {
		for(BlockType i : list) {
			if(i.getName().equalsIgnoreCase(name)) return i;
		}
		return null;
	}
	
	/**
	 * Method used for building an Block based on supplied data.
	 * @param p Plugin+ to be used.
	 * @param name String name of new Block.
	 * @param texture String URL for Texture.
	 * @param baseType  String name of Type to Base off.
	 * @return Block Block created.
	 * @throws InvocationTargetException InvocationTargetException is a checked exception that wraps an exception thrown by an invoked method or constructor.
	 * @throws IllegalAccessException An IllegalAccessException is thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
	 * @throws InstantiationException Thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the specified class object cannot be instantiated.
	 * @throws IllegalArgumentException Thrown to indicate that a method has been passed an illegal or inappropriate argument.
	 * @throws NoSuchMethodException Thrown when a particular method cannot be found.
	 * @throws SecurityException Thrown by the security manager to indicate a security violation.
	 */
	public BlockType buildBlock(PluginPlus p, String name, boolean isOpaque, String baseType) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		Class<? extends BlockType> type = TypeManager.getInstance().getBlockType(baseType);
		BlockType block;
		Constructor<? extends BlockType> con = type.getConstructor(Plugin.class, String.class, boolean.class);
		block = con.newInstance(p,name,isOpaque);
		addBlock(block);
		return block;
	}
	
	/**Method used to get all blocks as list
	 * @return A list of all added Block Types
	 */
	public List<BlockType> getBlocks() {
		return new ArrayList<BlockType>(list);
	}
	
	/**
	 * Method used for getting an Instance of the API's ItemManager, Only one instance is allowed.
	 * @return ItemManager Instance of the API's ItemManager.
	 */
	public static BlockManager getInstance() {
		if(instance == null) instance = new BlockManager();
		return instance;
	}
}
