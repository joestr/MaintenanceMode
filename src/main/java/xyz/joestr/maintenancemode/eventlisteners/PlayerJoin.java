package xyz.joestr.maintenancemode.eventlisteners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import xyz.joestr.maintenancemode.MaintenanceMode;
import xyz.joestr.maintenancemode.utils.ColorCode;

public class PlayerJoin implements Listener {
	
	private MaintenanceMode maintenanceMode;

	public PlayerJoin(MaintenanceMode maintenanceMode) {
		
		this.maintenanceMode = maintenanceMode;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent event) {
				
		if(((Boolean) maintenanceMode.config.getMap().get("active"))) {
			
			boolean allow = false;
			
			if(((Boolean) maintenanceMode.config.getMap().get("operator"))) {
				
				if(event.getPlayer().isOp()) {
					
					allow = true;
				}
			}
			
			if(!allow && ((Boolean) maintenanceMode.config.getMap().get("permission"))) {
				
				if(event.getPlayer().hasPermission("maintenancemode.permission.bypass")) {
					
					allow = true;
				}
			}
			
			if(allow) {
				
				 event.allow();
			} else {
				
				event.disallow(null, ColorCode.toValid(String.format(maintenanceMode.config.getMap().get("kickmessage").toString().replaceAll("\\\\", "%")).intern(), "&"));
			}
		}
	}
}
