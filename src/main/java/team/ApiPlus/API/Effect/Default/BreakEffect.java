package team.ApiPlus.API.Effect.Default;

import org.bukkit.Location;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.block.SpoutBlock;

import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.API.Effect.LocationEffect;

/**
 * @author Atlan1
 * @version 1.0
 */
public class BreakEffect implements LocationEffect{

	private EffectTarget et;
	
	private float potency = .0f;
	
	public BreakEffect(Object...args) {
		this.potency = ((Number) args[0]).floatValue();
	}

	@Override
	public void performEffect(Object... arguments) {
		Location l = (Location) arguments[0];
		if(MaterialData.getBlock(l.getBlock().getTypeId()).getHardness()<Float.valueOf(potency)){
			((SpoutBlock)l.getBlock()).breakNaturally();
		}
	}

	@Override
	public EffectTarget getEffectTarget() {
		return et;
	}

	@Override
	public void setEffectTarget(EffectTarget et) {
		this.et = et;
	}

}
