package team.ApiPlus.Util;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author SirTyler, Atlan1
 * @version 1.0
 */
public class Task implements Runnable {
    private JavaPlugin plugin;
    protected Object[] arguments;
    private int taskID = 0;
    private TaskModel model = null;
    
    public static Task create(JavaPlugin plugin, TaskModel model, Object... arguments) {
        return new Task(plugin, model, arguments);
    }
    
    public Task(JavaPlugin plugin, TaskModel model, Object... arguments) {
        this.plugin = plugin;
        this.arguments = arguments;
        this.model = model;
    }
    
    public static Task create(JavaPlugin plugin, Object... arguments) {
        return new Task(plugin, arguments);
    }
    
    public Task(JavaPlugin plugin, Object... arguments) {
        this.plugin = plugin;
        this.arguments = arguments;
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }
    public TaskModel getModel() {
        return this.model;
    }
    public Server getServer() {
        return this.plugin.getServer();
    }
    public Object getArg(int index) {
        return arguments[index];
    }
    public int getIntArg(int index) {
        return (Integer) getArg(index);
    }
    public long getLongArg(int index) {
        return (Long) getArg(index);
    }
    public float getFloatArg(int index) {
        return (Float) getArg(index);
    }
    public double getDoubleArg(int index) {
        return (Double) getArg(index);
    }
    public String getStringArg(int index) {
        return (String) getArg(index);
    }
    
    public int getTaskID(){
    	return taskID;
    }

    public void run() {

    }
    
    public boolean checkDefault(){
    	if(arguments.length!=model.getDefaultArgTypes().length)
			return false;
		for(int index=0;index<model.getDefaultArgTypes().length;index++){
			if(arguments[index]!=null&&!model.getDefaultArgTypes()[index].isAssignableFrom(arguments[index].getClass())){
				return false;
			}
		}
		return true;
    }
    
    public boolean startTaskModel(){
    	if(model==null) return false;
    	if(!checkDefault()) return false;
    	
    	if(this.model.getTaskType().equalsIgnoreCase("delayed")){
    		this.startTaskDelayed(model.getTimeDiff());
    	}else if(this.model.getTaskType().equalsIgnoreCase("repeating")){
    		this.startTaskRepeating(model.getTimeDiff());
    	}else return false;
    	
    	return true;
    }

    public boolean isTaskQueued() {
        return this.getServer().getScheduler().isQueued(this.taskID);
    }
    public boolean isTaskRunning() {
        return this.getServer().getScheduler().isCurrentlyRunning(this.taskID);
    }
    public void stopTask() {
        this.getServer().getScheduler().cancelTask(this.taskID);
    }
    
    public void startTask() {
        startTask(false);
    }
    public void startTask(boolean Async) {
        startTaskDelayed(0, Async);
    }
    public void startTaskDelayed(long tickDelay) {
        startTaskDelayed(tickDelay, false);
    }
    public void startTaskDelayed(long tickDelay, boolean Async) {
        if (Async) {
            this.taskID = this.getServer().getScheduler().scheduleAsyncDelayedTask(this.plugin, this, tickDelay);
        } else {
            this.taskID = this.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, this, tickDelay);
        }
    }
    public void startTaskRepeating(long tickInterval) {
        startTaskRepeating(tickInterval, false);
    }
    public void startTaskRepeating(long tickInterval, boolean Async) {
        startTaskRepeating(0, tickInterval, Async);
    }
    public void startTaskRepeating(long tickDelay, long tickInterval, boolean Async) {
        if (Async) {
            this.taskID = this.getServer().getScheduler().scheduleAsyncRepeatingTask(this.plugin, this, tickDelay, tickInterval);
        } else {
            this.taskID = this.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, this, tickDelay, tickInterval);
        }
    }
}
