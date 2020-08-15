package com.github.miskyle.miskylepluginfirewall.cmd;

import com.github.miskyle.miskylepluginfirewall.MiSkYleFirewall;

public class CommandManager {
	public CommandManager(MiSkYleFirewall plugin){
		regesitCommands(plugin);
	}
	
	private void regesitCommands(MiSkYleFirewall plugin) {
        RSCommand cmdRecipe = new RSCommand();
        cmdRecipe.initialization(
                Commands.class.getDeclaredMethods(), 
                new Commands(),
                "mfirewall");
        plugin.getCommand("mfirewall").setExecutor(cmdRecipe);
        plugin.getCommand("mfirewall").setTabCompleter(cmdRecipe);
	}
	
	public static boolean compareSubCommand(String[] args,String[] subCmd) {
		for(int i=0;i<subCmd.length;i++) {
			if(!subCmd[i].equalsIgnoreCase(args[i]))
				return false;
		}
		return true;
	}
}
