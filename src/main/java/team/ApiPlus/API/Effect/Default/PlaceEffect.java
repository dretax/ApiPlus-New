package team.ApiPlus.API.Effect.Default;

import org.bukkit.Location;
import org.bukkit.Material;

import team.ApiPlus.API.Effect.EffectTarget;
import team.ApiPlus.API.Effect.LocationEffect;
import team.ApiPlus.Util.Utils;

/**
 * @author Atlan1
 * @version 1.0
 */
public class PlaceEffect implements LocationEffect{

	private EffectTarget et;
	
	private Material m;

	public PlaceEffect(Object...args) {
		this.m =  (Material)args[0];
	}
	
	@Override
	public void performEffect(Object... arguments) {
		Location l = (Location) arguments[0];
		if(Utils.isTransparent(l.getBlock()))
			l.getBlock().setType(m);
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
