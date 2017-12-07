package me.jayden.staff;

import java.util.ArrayList;

import me.jayden.commands.HelpCommand;
import me.jayden.commands.MessageCommand;
import me.jayden.commands.RepsondCommand;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
	
	  public ArrayList<String> cooldown = new ArrayList<String>();
	  
	public void onEnable() {
		getProxy().getPluginManager().registerCommand(this, new MessageCommand());
		getProxy().getPluginManager().registerCommand(this, new HelpCommand(this));
		getProxy().getPluginManager().registerCommand(this, new RepsondCommand());
	}

	@Override
	public void onDisable() {
		
	}
}
