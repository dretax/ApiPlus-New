package team.ApiPlus.API.Type;

import java.util.List;

import org.bukkit.plugin.Plugin;

import team.ApiPlus.API.EffectHolder;
import team.ApiPlus.API.Effect.Effect;


/**
 * @author Atlan1
 * @version 1.0
 */
public abstract class BlockTypeEffectPlusProperty extends BlockTypeProperty implements
		EffectHolder {

	public BlockTypeEffectPlusProperty(Plugin plugin, String name, boolean isOpaque) {
		super(plugin, name, isOpaque);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Effect> getEffects() {
		return (List<Effect>) getProperty("EFFECTS");
	}

	@Override
	public void setEffects(List<Effect> effects) {
		editProperty("EFFECTS", effects);
		addProperty("EFFECTS", effects);
	}

}
