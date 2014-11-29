package team.ApiPlus.API.Effect.Default;

import org.bukkit.Location;

import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.API.Effect.LocationEffect;
import team.ApiPlus.Util.Utils;

/**
 * @author Atlan1
 * @version 1.0
 */
public class ExplosionEffect implements LocationEffect{

	private EffectTarget et;
	
	private int size = 0;
	

	public ExplosionEffect(Object...args) {
		this.size =  ((Number) args[0]).intValue();
	}

	@Override
	public void performEffect(Object... arguments) {
		Location l = (Location) arguments[0];
		if(Utils.tntIsAllowedInRegion(l))
			 l.getWorld().createExplosion(l, size);
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
