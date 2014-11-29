package team.ApiPlus;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.CraftItemEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

import team.ApiPlus.Util.Utils;

public class ApiPlusListener implements Listener{

	private ApiPlus plugin;
	
	public ApiPlusListener(ApiPlus p) {
		plugin = p;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCraftItem(CraftItemEvent event) {
		SpoutItemStack sis = new SpoutItemStack(event.getRecipe().getResult());
		org.getspout.spoutapi.material.Material m = sis.getMaterial();
		if(Utils.isApiPlusMaterial(m.getName())){
			if(!event.getWhoClicked().hasPermission("apiplus.craft.all")) {
				if(!event.getWhoClicked().hasPermission("apiplus.craft." + ( m.getName().toLowerCase().replace(" ", "_"))))
					event.setResult(Result.DENY);
					Utils.sendNotification((SpoutPlayer)event.getWhoClicked(), "Permission", ChatColor.RED+"Denied", new SpoutItemStack(sis), 1500);
			}
		}
	}
	
}
