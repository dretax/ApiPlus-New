package team.ApiPlus.API.Effect.Default;

import org.bukkit.entity.Entity;

import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.API.Effect.EntityEffect;

/**
 * @author Atlan1
 * @version 1.0
 */
public class BurnEffect implements EntityEffect{

	private EffectTarget et;
	private int fireTicks;

	public BurnEffect(Object...args){
		fireTicks = (Integer) args[0];
	}
	
	@Override
	public void performEffect(Object... arguments) {
		Entity e = (Entity) arguments[0];
		e.setFireTicks(fireTicks);
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
