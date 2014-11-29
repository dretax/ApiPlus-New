package team.ApiPlus.Util;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Atlan1
 * @version 1.0
 */
public class TaskModel {

	
	private JavaPlugin p;
	private String taskType = "delayed";
	private long timeDiff = 0;
	private Class<?>[] defaultArgTypes;
	
	
	public TaskModel(JavaPlugin p, String taskType, long timeDiff, Class<?>... arguments)  {
		this.p = p;
		this.taskType = taskType;
		this.timeDiff = timeDiff;
		this.defaultArgTypes = arguments;
	}
	
	public Task createTask(Object...args){
		return new Task(p, this, args){
			public void run(){
				TaskModel.this.run(this);
			}
		};
	}
	
	public void run(Task task) {
	}

	public String getTaskType() {
		return taskType;
	}
	
	public JavaPlugin getPlugin() {
		return p;
	}

	public long getTimeDiff() {
		return timeDiff;
	}

	public Class<?>[] getDefaultArgTypes() {
		return defaultArgTypes;
	}
	
}
