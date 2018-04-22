package xyz.joestr.maintenancemode.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyz.joestr.maintenancemode.MaintenanceMode;
import xyz.joestr.maintenancemode.utils.ColorCode;

public class MMCommand implements CommandExecutor {
	
	private MaintenanceMode maintenanceMode;
	
	public MMCommand(MaintenanceMode maintenanceMode) {
		
		this.maintenanceMode = maintenanceMode;
	}
	
	CommandSender commandSender;
	Command command;
	String string;
	String[] parameters;
	
	public boolean onCommand(CommandSender commandSender, Command command, String string, String[] parameters) {
		
		this.commandSender = commandSender;
		this.command = command;
		this.string = string;
		this.parameters = parameters;
		
		if(commandSender instanceof Player) {
			
			Player player = (Player) commandSender;
				
			if(parameters.length > 0) {
				
				if(parameters[0].equalsIgnoreCase("active")) {
					
					if(player.hasPermission("maintenancemode.command.mm.active")) {
						
						active();
						return true;
					} else {
						
						player.sendMessage("§cYou are lacking the permission \"maintenancemode.command.mm.activate\".");
						return true;
					}
				} else if (parameters[0].equalsIgnoreCase("operator")) {
					
					if(player.hasPermission("maintenancemode.command.mm.operator")) {
						
						operator();
						return true;
					} else {
						
						player.sendMessage("§cYou are lacking the permission \"maintenancemode.command.mm.operator\".");
						return true;
					}
				} else if (parameters[0].equalsIgnoreCase("permission")) {
					
					if(player.hasPermission("maintenancemode.command.mm.permission")) {
						
						permission();
						return true;
					} else {
						
						player.sendMessage("§cYou are lacking the permission \"maintenancemode.command.mm.permission\".");
						return true;
					}
				} else if (parameters[0].equalsIgnoreCase("kickmessage")) {
					
					if(player.hasPermission("maintenancemode.command.mm.kickmessage")) {
						
						kickmessage();
						return true;
					} else {
						
						player.sendMessage("§cYou are lacking the permission \"maintenancemode.command.mm.kickmessage\".");
						return true;
					}
				} else if (parameters[0].equalsIgnoreCase("motd")) {
					
					if(player.hasPermission("maintenancemode.command.mm.motd")) {
						
						motd();
						return true;
					} else {
						
						player.sendMessage("§cYou are lacking the permission \"maintenancemode.command.mm.motd\".");
						return true;
					}
				} else if (parameters[0].equalsIgnoreCase("reload")) {
					
					if(player.hasPermission("maintenancemode.command.mm.reload")) {
						
						reload();
						return true;
					} else {
						
						player.sendMessage("§cYou are lacking the permission \"maintenancemode.command.mm.reload\".");
						return true;
					}
				} else if (parameters[0].equalsIgnoreCase("reset")) {
					
					if(player.hasPermission("maintenancemode.command.mm.reset")) {
						
						reset();
						return true;
					} else {
						
						player.sendMessage("§cYou are lacking the permission \"maintenancemode.command.mm.reset\".");
						return true;
					}
				} else {
					
					usage();
					return true;
				}
			} else {
				
				list(player);
				return true;
			}
		} else {
			
			if(parameters.length > 0) {
				
				if(parameters[0].equalsIgnoreCase("active")) {
					
					active();
					return true;
				} else if (parameters[0].equalsIgnoreCase("operator")) {
					
					operator();
					return true;
				} else if (parameters[0].equalsIgnoreCase("permission")) {
					
					permission();
					return true;
				} else if (parameters[0].equalsIgnoreCase("kickmessage")) {
					
					kickmessage();
					return true;
				} else if (parameters[0].equalsIgnoreCase("motd")) {
					
					motd();
					return true;
				} else if (parameters[0].equalsIgnoreCase("reload")) {
					
					reload();
					return true;
				} else if (parameters[0].equalsIgnoreCase("reset")) {
					
					reset();
					return true;
				} else {
					
					usage();
					return true;
				}
			} else {
				
				list();
				return true;
			}
		}
	}
	
	public void kick() {
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			
			if((Boolean) maintenanceMode.config.getMap().get("operator")) {
				
				if(!player.isOp()) {
					
					player.kickPlayer(ColorCode.toValid((String) maintenanceMode.config.getMap().get("kickmessage"), "&"));
				}
			} else if ((Boolean) maintenanceMode.config.getMap().get("permission")) {
				
				if(!player.hasPermission("maintenancemode.permission.bypass")) {
					
					player.kickPlayer(ColorCode.toValid((String) maintenanceMode.config.getMap().get("kickmessage"), "&"));
				}
			} else {
				
				player.kickPlayer(ColorCode.toValid((String) maintenanceMode.config.getMap().get("kickmessage"), "&"));
			}
		}
	}
	public void list() {
		
		String returnVariable = "";
		
		returnVariable += "§b/mm §7(Shows a list of all (sub-)commands.)";
		returnVariable += "\n§b/mm active [<§atrue§b|§cfalse§b>] §7(Show [Change] the activation mode.)";
		returnVariable += "\n§b/mm operator [<§atrue§b|§cfalse§b>] §7(Show [Change] the operator bypass mode.)";
		returnVariable += "\n§b/mm permission [<§atrue§b|§cfalse§b>] §7(Show [Change] the permission bypass mode.)";
		returnVariable += "\n§b/mm kickmessage [<text ...>] §7(Show [Change] the kick message.)";
		returnVariable += "\n§b/mm motd [<text ...>] §7(Show [Change] the MOTD.)";
		returnVariable += "\n§b/mm reload §7(Reload the config file.)";
		returnVariable += "\n§b/mm reset §7(Reset the config file.)";
		
		commandSender.sendMessage(returnVariable);
	}
	
	public void active() {
		
		if(parameters.length == 2) {
			
			if(parameters[1].equalsIgnoreCase("true")) {
				
				if(!(Boolean) maintenanceMode.config.getMap().get("active")) {
					
					maintenanceMode.config.getMap().put("active", true);
					
					maintenanceMode.config.Save();
					
					commandSender.sendMessage("§aActivation mode changed to true.");
				} else {
					
					commandSender.sendMessage("§cThe activation mode was already set to §atrue§c.");
				}
			} else if(parameters[1].equalsIgnoreCase("false")) {
				
				if((Boolean) maintenanceMode.config.getMap().get("active")) {
					
					maintenanceMode.config.getMap().put("active", false);
					
					maintenanceMode.config.Save();
					
					commandSender.sendMessage("§aActivation mode changed to §cfalse§a.");
				} else {
					
					commandSender.sendMessage("§cThe activation mode was already set to false.");
				}
			} else {
				
				commandSender.sendMessage("§b/mm active [<§atrue§b|§cfalse§b>] §7(Show [Change] the activation mode.)");
			}
		} else {
			
			commandSender.sendMessage("§aActivation mode is set to " + ((Boolean) maintenanceMode.config.getMap().get("active") ? "true" : " §cfalse") + "§a.");
		}
	}
	
	public void operator() {
		
		if(parameters.length == 2) {
			
			if(parameters[1].equalsIgnoreCase("true")) {
				
				if(!(Boolean) maintenanceMode.config.getMap().get("operator")) {
					
					maintenanceMode.config.getMap().put("operator", true);
					
					maintenanceMode.config.Save();
					
					commandSender.sendMessage("§aOperator bypass mode changed to true.");
				} else {
					
					commandSender.sendMessage("§cThe operator bypass mode was already set to §atrue§c.");
				}
			} else if(parameters[1].equalsIgnoreCase("false")) {
				
				if((Boolean) maintenanceMode.config.getMap().get("operator")) {
					
					maintenanceMode.config.getMap().put("operator", false);
					
					maintenanceMode.config.Save();
					
					commandSender.sendMessage("§aOperator bypass mode changed to §cfalse§a.");
				} else {
					
					commandSender.sendMessage("§cThe operator bypass mode was already set to false.");
				}
			} else {
				
				commandSender.sendMessage("§b/mm operator [<§atrue§b|§cfalse§b>] §7(Show [Change] the operator bypass mode.)");
			}
		} else {
			
			commandSender.sendMessage("§aOperator bypass mode is set to " + ((Boolean) maintenanceMode.config.getMap().get("operator") ? "true" : "§cfalse") + "§a.");
		}
	}
	
	public void permission() {
		if(parameters.length == 2) {
			
			if(parameters[1].equalsIgnoreCase("true")) {
				
				if(!(Boolean) maintenanceMode.config.getMap().get("permission")) {
					
					maintenanceMode.config.getMap().put("permission", true);
					
					maintenanceMode.config.Save();
					
					commandSender.sendMessage("§aPermission bypass mode changed to true.");
				} else {
					
					commandSender.sendMessage("§cThe permission bypass mode was already set to §atrue§c.");
				}
			} else if(parameters[1].equalsIgnoreCase("false")) {
				
				if((Boolean) maintenanceMode.config.getMap().get("permission")) {
					
					maintenanceMode.config.getMap().put("permission", false);
					
					maintenanceMode.config.Save();
					
					commandSender.sendMessage("§aPermission bypass mode changed to §cfalse§a.");
				} else {
					
					commandSender.sendMessage("§cThe permission bypass mode was already set to false.");
				}
			} else {
				
				commandSender.sendMessage("§b/mm permission [<§atrue§b|§cfalse§b>] §7(Show [Change] the permission bypass mode.)");
			}
		} else {
			
			commandSender.sendMessage("§aPermission bypass mode is set to " + ((Boolean) maintenanceMode.config.getMap().get("permission") ? "true" : " §cfalse") + "§a.");
		}
	}
	
	public void kickmessage() {
		
		if(parameters.length > 1) {
			
			String motd = "";
			
			for (int i = 1; i < parameters.length; i++) {
				
				motd = motd + parameters[i] + " ";
			}
			
			maintenanceMode.config.getMap().put("motd", motd);
			
			maintenanceMode.config.Save();
	          
			commandSender.sendMessage("§aYou changed the kick message.");
		} else {
			
			commandSender.sendMessage("§aKick Message text: " + ColorCode.toValid((String) maintenanceMode.config.getMap().get("kickmessage"), "&"));
		}
	}
	
	public void motd() {
		
		if(parameters.length > 1) {
			
			String motd = "";
			
			for (int i = 1; i < parameters.length; i++) {
				
				motd = motd + parameters[i] + " ";
			}
			
			maintenanceMode.config.getMap().put("motd", motd);
			
			maintenanceMode.config.Save();
	          
			commandSender.sendMessage("§aYou changed the MOTD.");
		} else {
			
			commandSender.sendMessage("§aMOTD text: " + ColorCode.toValid((String) maintenanceMode.config.getMap().get("motd"), "&"));
		}
	}
	
	public void reload() {
		
		if(parameters.length == 1) {
			
			maintenanceMode.config.Load();
			commandSender.sendMessage("§aYou reloaded the config file.");
		} else {
			
			commandSender.sendMessage("§b/mm reload §7(Reload the config file.)");
		}
	}
	
	public void reset() {
		
		if(parameters.length == 1) {
			
			maintenanceMode.config.Reset();
			commandSender.sendMessage("§aYou reseted the config file.");
		} else {
			
			commandSender.sendMessage("§b/mm reload §7(Reset the config file.)");
		}
	}
	
	public void usage() {
		
		commandSender.sendMessage("§cPlease use \"/mm\".");
	}
	
	public void list(Player player) {
		
		if(player.hasPermission("maintenancemode.command.mm")) {
			
			String returnVariable = "";
			
			if(player.hasPermission("maintenancemode.command.mm")) {
				
				returnVariable += "§b/mm §7(Show a list of all (sub-)commands.)";
			}
			
			if(player.hasPermission("maintenancemode.command.mm.active")) {
				
				returnVariable += "\n§b/mm active [<§atrue§b|§cfalse§b>] §7(Show [Change] the maintenance mode activation.)";
			}
			
			if(player.hasPermission("maintenancemode.command.mm.operator")) {
				
				returnVariable += "\n§b/mm operator [<§atrue§b|§cfalse§b>] §7(Show [Change] the operator bypass mode.)";
			}
			
			if(player.hasPermission("maintenancemode.command.mm.permission")) {
				
				returnVariable += "\n§b/mm permission [<§atrue§b|§cfalse§b>] §7(Show [Change] the permission bypass mode.)";
			}
			
			if(player.hasPermission("maintenancemode.command.mm.kickmessage")) {
				
				returnVariable += "\n§b/mm kickmessage [<text ...>] §7(Show [Change] the kick message.)";
			}
			
			if(player.hasPermission("maintenancemode.command.mm.motd")) {
				
				returnVariable += "\n§b/mm motd [<text ...>] §7(Show [Change] the MOTD.)";
			}
			
			if(player.hasPermission("maintenancemode.command.mm.reload")) {
				
				returnVariable += "\n§b/mm reload §7(Reload the config file.)";
			}
			
			if(player.hasPermission("maintenancemode.command.mm.reset")) {
				
				returnVariable += "\n§b/mm reset §7(Reset the config file.)";
			}
			
			player.sendMessage(returnVariable);
		} else {
			
			player.sendMessage("§cYou are lacking the permission \"maintenancemode.command.mm\".");
		}
	}
}
