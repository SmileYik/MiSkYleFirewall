package com.github.miskyle.miskylepluginfirewall;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.miskyle.miskylepluginfirewall.cmd.CommandManager;
import com.github.miskyle.miskylepluginfirewall.data.ConfigManager;

public class MiSkYleFirewall extends JavaPlugin{
  private static MiSkYleFirewall plugin;
  @Override
  public void onEnable() {
    plugin = this;
    firstLoad();
    new ConfigManager(this);
    new CommandManager(this);
    getLogger().info("MiSkYleFirewall “—∆Ù”√!");
    super.onEnable();
  }
  
  private void firstLoad() {
    if (!getDataFolder().exists()) {
      getDataFolder().mkdir();
    }
    if(! new File(getDataFolder(), "config.yml").exists()) {
      saveDefaultConfig();
      reloadConfig();
    }
  }
  
  public static MiSkYleFirewall getPlugin() {
    return plugin;
  }
}
