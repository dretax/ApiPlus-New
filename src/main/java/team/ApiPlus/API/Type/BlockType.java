package team.ApiPlus.API.Type;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.block.GenericCustomBlock;

/**
 * @author Atlan1
 * @version 1.0
 */
public class BlockType extends GenericCustomBlock implements MaterialType{

	public BlockType(Plugin plugin, String name, boolean isOpaque) {
		super(plugin, name, isOpaque);
	}
	
	public BlockType(GenericCustomBlock b) {
		super(b.getPlugin(), b.getName(), b.isOpaque());
	}
	
}
