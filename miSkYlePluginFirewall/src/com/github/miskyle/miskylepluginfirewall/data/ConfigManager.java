package com.github.miskyle.miskylepluginfirewall.data;

import java.util.HashMap;
import java.util.List;

import org.bukkit.plugin.Plugin;

import com.github.miskyle.miskylepluginfirewall.MiSkYleFirewall;

public class ConfigManager {
  private static ConfigManager cm;
  private MiSkYleFirewall plugin;
  private HashMap<ClassLoader, Plugin> plugins;
  
  private boolean white;
  private List<String> pluginList;
  private List<String> hostList;
  
  public ConfigManager(MiSkYleFirewall plugin) {
    cm = this;
    this.plugin = plugin;
    plugins = new HashMap<>();
    white = plugin.getConfig().getBoolean("white-list-mode");
    pluginList = plugin.getConfig().getStringList("plugins");
    hostList = plugin.getConfig().getStringList("hosts");
    if (white) {
      new MiSkYleProxyWhiteSelector();
      plugin.getServer().getConsoleSender().sendMessage("§b防火墙模式: §c白名单");
    } else {
      plugin.getServer().getConsoleSender().sendMessage("§b防火墙模式: §c黑名单");
      new MiSkYleProxyBlackSelector();
    }
  }
  
  public static void loadPlugin() {
    if (cm.plugins.size() < cm.plugin.getServer().getPluginManager().getPlugins().length) {
      for (Plugin p : cm.plugin.getServer().getPluginManager().getPlugins()) {
        if (cm.plugins.values().contains(p)) {
          continue;
        }
        cm.plugins.put(p.getClass().getClassLoader(), p);
      }      
    }
    cm.plugins.remove(cm.plugin.getClass().getClassLoader());
  }
  
  public static Plugin getPlugin(StackTraceElement[] elements) {
    loadPlugin();
    for (StackTraceElement element : elements) {
      try {
        ClassLoader loader = ConfigManager.class.getClassLoader().loadClass(element.getClassName()).getClassLoader();
        if (loader != null && cm.plugins.containsKey(loader)) {
          return cm.plugins.get(loader);
        }
      } catch (Exception e) {
        
      }
    }
    return null;
  }
  
  public static void save() {
    cm.plugin.getConfig().set("hosts", cm.hostList);
    cm.plugin.getConfig().set("plugins", cm.pluginList);
    cm.plugin.getConfig().set("white-list-mode", cm.white);
    cm.plugin.saveConfig();
  }
  
  public static boolean isWhiteMode() {
    return cm.white;
  }
  
  public static List<String> getPluginList(){
    return cm.pluginList;
  }
  
  public static List<String> getHostList(){
    return cm.hostList;
  }
}
