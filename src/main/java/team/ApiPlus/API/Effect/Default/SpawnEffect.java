package team.ApiPlus.API.Effect.Default;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.API.Effect.LocationEffect;

/**
 * @author Atlan1
 * @version 1.0
 */
public class SpawnEffect implements LocationEffect{
	
	private EffectTarget et;
	
	private EntityType ent;

	public SpawnEffect(Object...args) {
		this.ent = EntityType.valueOf(((String) args[0]).toUpperCase());
	}

	@Override
	public void performEffect(Object... arguments) {
		Location l = (Location) arguments[0];
		l.add(0, 1, 0);
		l.getWorld().spawn(l, ent.getEntityClass());
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
