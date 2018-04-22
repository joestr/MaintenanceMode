package xyz.joestr.maintenancemode.eventlisteners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import xyz.joestr.maintenancemode.MaintenanceMode;
import xyz.joestr.maintenancemode.utils.ColorCode;

public class ServerListPing implements Listener {
	
	private MaintenanceMode maintenanceMode;

	public ServerListPing(MaintenanceMode maintenanceMode) {
		
		this.maintenanceMode = maintenanceMode;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onServerListPing(ServerListPingEvent event) {
		
		if(((Boolean) maintenanceMode.config.getMap().get("active"))) {
			
			event.setMotd(ColorCode.toValid(String.format(maintenanceMode.config.getMap().get("motd").toString().replaceAll("\\\\", "%")).intern(), "&"));
		}
	}
}
