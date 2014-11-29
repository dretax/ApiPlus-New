package team.ApiPlus.API.Type;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import team.ApiPlus.API.EffectHolder;
import team.ApiPlus.API.Effect.Effect;

/**
 * @author Atlan1
 * @version 1.0
 */
public abstract class ItemTypeEffect extends ItemType implements EffectHolder {

	public ItemTypeEffect(Plugin plugin, String name, String texture) {
		super(plugin, name, texture);
	}

	private List<Effect> effects = new ArrayList<Effect>();
	
	@Override
	public List<Effect> getEffects() {
		return new ArrayList<Effect>(effects);
	}

	@Override
	public void setEffects(List<Effect> effects) {
		effects = new ArrayList<Effect>(effects);
	}

}
