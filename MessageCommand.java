package me.jayden.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MessageCommand extends Command {
	
	public MessageCommand() {
		super("staffchat", "staffchat.message", "sc");
		
	}
	
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			
			if (args.length == 0 || args[0] == null ) {
				player.sendMessage(new TextComponent(ChatColor.RED + "Invalid Arguments. /sc <message>"));
				return;
			}
			
			String message = "";
			for (int x = 0; x < args.length; x++) {
				if (message.equals("")) {
					message = args[x];
					
				}else {
					message = message + "" + args[x];
				}
			}
			
			for (ProxiedPlayer staffmember : ProxyServer.getInstance().getPlayers()) {
				if (staffmember.hasPermission("staffchat.receive")) {
					staffmember.sendMessage(new TextComponent(ChatColor.RED + "[" + player.getServer().getInfo().getName() + "] " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + ":" + ChatColor.WHITE + message));
				}
			}
		}
	}

}
