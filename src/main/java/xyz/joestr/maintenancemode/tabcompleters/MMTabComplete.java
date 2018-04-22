package xyz.joestr.maintenancemode.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class MMTabComplete implements TabCompleter {

	public List<String> onTabComplete(CommandSender commandSender, Command command, String string, String[] parameters) {
		
		List<String> l = new ArrayList<String>();
		List<String> le = new ArrayList<String>();
		List<String> lc = new ArrayList<String>();
		
		if(commandSender instanceof Player) {
			
			Player player = (Player) commandSender;
			
			if(player.hasPermission("maintenancemode.command.mm")) {
				
				if(parameters.length == 0) {
					
					return list(player);
				}
				
				if(parameters.length == 1) {
					
					l = list(player);
					
					for(String s : l) {
						
						if(s.startsWith(parameters[0])) {
							
							lc.add(s);
						}
					}
					
					l = lc;
					
					return l;
				}
			}
			
			if(parameters.length == 2) {
				
				if(parameters[0].equalsIgnoreCase("active")) {
					
					if(player.hasPermission("maintenancemode.command.mm.active")) {
						
						l.add("true");
						l.add("false");
						
						for(String s : l) {
								
							if(s.startsWith(parameters[1])) {
								lc.add(s);
							}
						}
							
						l = lc;
						
						return l;
					}
				} else if (parameters[0].equalsIgnoreCase("operator")) {
					
					if(player.hasPermission("maintenancemode.command.mm.operator")) {
						
						l.add("true");
						l.add("false");
						
						for(String s : l) {
								
							if(s.startsWith(parameters[1])) {
								lc.add(s);
							}
						}
							
						l = lc;
						
						return l;
					}
				} else if (parameters[0].equalsIgnoreCase("permission")) {
					
					if(player.hasPermission("maintenancemode.command.mm.permission")) {
						
						l.add("true");
						l.add("false");
						
						for(String s : l) {
								
							if(s.startsWith(parameters[1])) {
								lc.add(s);
							}
						}
							
						l = lc;
						
						return l;
					}
				} else if (parameters[0].equalsIgnoreCase("kickmessage")) {
					
					return le;
				} else if (parameters[0].equalsIgnoreCase("motd")) {
					
					return le;
				} else if (parameters[0].equalsIgnoreCase("reload")) {
					
					return le;
				} else if (parameters[0].equalsIgnoreCase("reset")) {
					
					return le;
				} else {
					
					return le;
				}
			}
				
			return le;
		} else {
			
			if(parameters.length == 0) {
				
				return list();
			}
			
			if(parameters.length == 1) {
				
				l = list();
				
				for(String s : l) {
					
					if(s.startsWith(parameters[0])) {
						
						lc.add(s);
					}
				}
				
				l = lc;
				
				return l;
			}
			
			if(parameters.length == 2) {
				
				if(parameters[0].equalsIgnoreCase("active")) {
					
					l.add("true");
					l.add("false");
					
					for(String s : l) {
							
						if(s.startsWith(parameters[1])) {
							lc.add(s);
						}
					}
						
					l = lc;
					
					return l;
				} else if (parameters[0].equalsIgnoreCase("operator")) {
					
					l.add("true");
					l.add("false");
					
					for(String s : l) {
							
						if(s.startsWith(parameters[1])) {
							lc.add(s);
						}
					}
						
					l = lc;
					
					return l;
				} else if (parameters[0].equalsIgnoreCase("permission")) {
					
					l.add("true");
					l.add("false");
					
					for(String s : l) {
							
						if(s.startsWith(parameters[1])) {
							lc.add(s);
						}
					}
						
					l = lc;
					
					return l;
				} else if (parameters[0].equalsIgnoreCase("kickmessage")) {
					
					return le;
				} else if (parameters[0].equalsIgnoreCase("motd")) {
					
					return le;
				} else if (parameters[0].equalsIgnoreCase("reload")) {
					
					return le;
				} else if (parameters[0].equalsIgnoreCase("reset")) {
					
					return le;
				} else {
					
					return le;
				}
			}
				
			return le;
		}
	}
	
	public List<String> list() {
		
		List<String> returnVariable = new ArrayList<String>();
		
		returnVariable.add("active");
		returnVariable.add("operator");
		returnVariable.add("permission");
		returnVariable.add("kickmessage");
		returnVariable.add("motd");
		returnVariable.add("reload");
		returnVariable.add("reset");
		
		return returnVariable;
	}
	
	public List<String> list(Player player) {
		
		List<String> returnVariable = new ArrayList<String>();
		
		if(player.hasPermission("maintenance.command.mm")) {
			
			/*
			if(player.hasPermission("maintenancemode.command.mm")) {
				
				
			}
			*/
			
			if(player.hasPermission("maintenancemode.command.mm.active")) {
				
				returnVariable.add("active");
			}
			
			if(player.hasPermission("maintenancemode.command.mm.operator")) {
				
				returnVariable.add("operator");
			}
			
			if(player.hasPermission("maintenancemode.command.mm.permission")) {
				
				returnVariable.add("permission");
			}
			
			if(player.hasPermission("maintenancemode.command.mm.kickmessage")) {
				
				returnVariable.add("kickmessage");
			}
			
			if(player.hasPermission("maintenancemode.command.mm.motd")) {
				
				returnVariable.add("motd");
			}
			
			if(player.hasPermission("maintenancemode.command.mm.reload")) {
				
				returnVariable.add("reload");
			}
			
			if(player.hasPermission("maintenancemode.command.mm.reset")) {
				
				returnVariable.add("reset");
			}
		}

		return returnVariable;
	}
}
