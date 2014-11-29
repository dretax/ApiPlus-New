package team.ApiPlus.API.Trigger;

import org.bukkit.plugin.java.JavaPlugin;

import team.ApiPlus.API.Trigger.Trigger;
import team.ApiPlus.Util.Task;

/**
 * @author Atlan1
 * @version 1.0
 */
public class TriggerTask extends Task {

	private Trigger trigger;
	
	public TriggerTask(JavaPlugin plugin, Trigger t, TriggerTaskModel ttm, Object...args)  {
		super(plugin,ttm, args);
		this.trigger = t;
	}
	
	public Trigger getTrigger() {
		return trigger;
	}
}
