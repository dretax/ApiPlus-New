package team.ApiPlus.API.Effect.Default;

import org.bukkit.util.Vector;
import org.bukkit.Location;
import org.getspout.spoutapi.particle.Particle;
import org.getspout.spoutapi.particle.Particle.ParticleType;

import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.API.Effect.LocationEffect;

/**
 * @author Atlan1
 * @version 1.0
 */
public class ParticleEffect implements LocationEffect{

	private EffectTarget et;
	
	private int amount = 0;
	private float gravity = 0;
	private int maxAge = 0;
	private float scale = 0;
	private ParticleType pt;

	public ParticleEffect(Object...args) {
		this.pt = ParticleType.valueOf(((String) args[0]).toUpperCase());
		this.amount = (Integer) args[1];
		this.gravity = (Integer) args[2];
		this.maxAge = (Integer) args[3];
		this.scale = (Integer) args[4];
	}
	
	@Override
	public void performEffect(Object... arguments) {
		Location loc = (Location) arguments[0];
		Particle p = new Particle(pt, loc, new Vector(0,0,0));
		p.setAmount(amount);
		p.setGravity(gravity);
		p.setMaxAge(maxAge);
		p.setScale(scale);
		p.spawn();
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
