package me.jayden.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class RepsondCommand extends Command {

	  public RepsondCommand() {
	    super("respond", "kadhras.respond", new String[0]);
	    
	  }
	  
	  public void execute(CommandSender sender, String[] args) {
	    if ((sender instanceof ProxiedPlayer)) {
	      ProxiedPlayer player = (ProxiedPlayer)sender;
	      if (args.length > 1) {
	        String message = "";
	        for (int i = 1; i < args.length; i++) {
	          message = message + "§f§l" + args[i] + " ";
	        }
	        boolean playerFound = false;
	        for (ProxiedPlayer p : BungeeCord.getInstance().getPlayers()) {
	          if (p.getName().equalsIgnoreCase(args[0])) {
	            p.sendMessage(message("[§aKadhras§fHelp] §f§lResponse from §c§l" + player.getName() + "§f§l: " + message));
	            for (ProxiedPlayer p2 : BungeeCord.getInstance().getPlayers()) {
	              if (p2.hasPermission("kadhras.receive")) {
	                p2.sendMessage(message("[&aKadhras&fHelp] §c§l" + player.getName() + " §7§l-> §f§;" + p.getName() + "§f§l: " + message));
	              }
	            }
	            playerFound = true;
	          }
	        }
	        if (!playerFound) {
	          player.sendMessage(message("[§aKadhras §fOpen] §7The player §a" + args[0] + " §7doesn't seem to be online."));
	        }
	      } else {
	        player.sendMessage(message("§cUsage: /respond <player> <message>"));
	      }
	    }
	  }
	  
	  private BaseComponent[] message(String text) {
	    return new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', text)).create();
	    
	  }
	}

