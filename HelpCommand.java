package me.jayden.commands;

import java.util.concurrent.TimeUnit;

import me.jayden.staff.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.protocol.packet.Chat;

public class HelpCommand extends Command{

	  public static Main main;
	  
	  public HelpCommand(Main instance) {
	    super("helpop", "kadhras.send", new String[0]);
	    main = instance;
	  }
	  
	  public void execute(CommandSender sender, String[] args) {
	    if ((sender instanceof ProxiedPlayer)) {
	      final ProxiedPlayer player = (ProxiedPlayer)sender;
	      if (!main.cooldown.contains(player.getName())) {
	        if (args.length > 0) {
	          String message = "";
	          for (int i = 0; i < args.length; i++) {
	            message = message + "§f§l" + args[i].replace("\"", "\\\"") + " ";
	          }
	          int count = 0;
	          for (ProxiedPlayer p : BungeeCord.getInstance().getPlayers()) {
	            if (p.hasPermission("kadhras.receive")) {
	              p.unsafe().sendPacket(new Chat("[{\"text\":\"[KadhrasHelp] \",\"color\":\"green\",\"bold\":true},{\"text\":\"[" + player.getServer().getInfo().getName() + "] \",\"color\":\"dark_red\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/tp " + player.getName() + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Teleport to the player\"}},{\"text\":\"" + player.getName() + "\",\"color\":\"white\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/tp " + player.getName() + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Teleport to the player\"}},{\"text\":\": " + message + "\",\"color\":\"white\",\"bold\":true,\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/respond " + player.getName() + " \"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Respond to the player\"}}]"));
	              count++;
	            }
	          }
	          if (count > 0) {
	            player.sendMessage(message("[§aKadhras§fHelp§f] §aYour message has been sent."));
	            main.cooldown.add(player.getName());
	            
	            main.getProxy().getScheduler().schedule(main, new Runnable() {
	              public void run() {
	                if (HelpCommand.main.cooldown.contains(player.getName())) {
	                  HelpCommand.main.cooldown.remove(player.getName());
	                }
	              }
	            }, 30L, TimeUnit.SECONDS);
	          } else {
	            player.sendMessage(message("§f[§aKadhras §fOpen] §cSorry there is no one available to assist you right now."));
	          }
	        } else {
	          player.sendMessage(message("&§Usage: /helpop <message>"));
	        }
	      } else {
	        player.sendMessage(message("[§aKadhras §fOpen] §cPlease slow down! You may not use HelpOP that often."));
	      }
	    }
	  }
	  
	  private BaseComponent[] message(String text) {
	    return new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', text)).create();
	  }
	}
