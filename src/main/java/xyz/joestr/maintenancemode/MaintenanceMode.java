package xyz.joestr.maintenancemode;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.joestr.maintenancemode.commands.MMCommand;
import xyz.joestr.maintenancemode.delegate.YMLDelegate;
import xyz.joestr.maintenancemode.eventlisteners.PlayerJoin;
import xyz.joestr.maintenancemode.eventlisteners.ServerListPing;
import xyz.joestr.maintenancemode.tabcompleters.MMTabComplete;

public class MaintenanceMode extends JavaPlugin {
	
	public YMLDelegate config = new YMLDelegate(this, "config", "config.yml");

	@Override
	public void onEnable() {
				
		if(!config.Exist()) {
			config.Create();
		}
			
		config.Load();
		
		if(!config.Check()) {
			Bukkit.getLogger().log(Level.WARNING, "[" + this.getName() +"] The file \"config.yml\" is not valid.");
			Bukkit.getPluginManager().disablePlugin(this);
		} else {
			Bukkit.getPluginManager().registerEvents(new PlayerJoin(this), this);
			Bukkit.getPluginManager().registerEvents(new ServerListPing(this), this);
			PluginCommand mm = Bukkit.getPluginCommand("mm");
			mm.setExecutor(new MMCommand(this));
			mm.setTabCompleter(new MMTabComplete());
		}
	}
	
	@Override
	public void onDisable() {
		
	}
}
