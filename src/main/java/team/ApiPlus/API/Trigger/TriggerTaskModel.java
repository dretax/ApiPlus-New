package team.ApiPlus.API.Trigger;

import org.bukkit.plugin.java.JavaPlugin;

import team.ApiPlus.Util.TaskModel;

/**
 * @author Atlan1
 * @version 1.0
 */
public class TriggerTaskModel extends TaskModel{

	public TriggerTaskModel(JavaPlugin p, String taskType, long timeDiff, Class<?>... arguments) {
		super(p, taskType, timeDiff, arguments);
	}
	
	public TriggerTask createTask(Trigger t, Object...args){
		return new TriggerTask(getPlugin(), t, this, args){
			public void run(){
				TriggerTaskModel.this.run(this);
			}
		};
	}
}
