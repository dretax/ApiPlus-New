package team.ApiPlus.API.Type;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import team.ApiPlus.ApiPlus;

import com.griefcraft.lwc.LWC;
import com.griefcraft.lwc.LWCPlugin;
import com.griefcraft.util.ProtectionFinder;
import com.griefcraft.util.matchers.DoorMatcher;
import com.griefcraft.util.matchers.DoubleChestMatcher;

/**
 * @author Atlan1
 * @version 1.0
 */
public class ItemType extends GenericCustomItem implements MaterialType{

	public ItemType(Plugin plugin, String name, String texture) {
		super(plugin, name, texture);
	}
	
	public ItemType(GenericCustomItem i) {
		super(i.getPlugin(), i.getName(), i.getTexture());
	}
	
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block,
			org.bukkit.block.BlockFace face) {
		try {
			if(!ApiPlus.hooks.containsKey("LWC")) return true;
			LWC lwc = ((LWCPlugin) ApiPlus.hooks.get("LWC")).getLWC();
			if (lwc != null) {
				lwc.wrapPlayer(player.getPlayer());
				Block b;
				if (block != null)
					b = block;
				else
					return true;
				DoubleChestMatcher chestMatcher = new DoubleChestMatcher();
				DoorMatcher doorMatcher = new DoorMatcher();
				if (lwc.getProtectionSet(b.getWorld(), b.getX(),
						b.getY(), b.getZ()).size() >= 2) {
					ProtectionFinder pf = new ProtectionFinder(lwc);
					pf.addBlock(b);
					if (chestMatcher.matches(pf) || doorMatcher.matches(pf)) {
						List<Block> list = pf.getBlocks();
						for (Block bl : list) {
							if (!lwc.canAccessProtection(player, bl)) {
								return false;
							}
						}
					}
				}
				if (!lwc.canAccessProtection(player, b)) {
					return false;
				} else
					return true;
			} else
				return true;
		} catch (Exception e) {
			return false;
		}
	}

}
