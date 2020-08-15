package com.github.miskyle.miskylepluginfirewall.data;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import com.github.miskyle.miskylepluginfirewall.MiSkYleFirewall;

public class Report {
  private static List<String> blockHosts = new ArrayList<String>();
  
  public static void add(String pluginName, URI uri) {
    StringBuilder sb = new StringBuilder();
    sb.append(new Date().toString());
    sb.append(" 已阻止插件 ");
    sb.append(pluginName==null?"":pluginName);
    sb.append(" 连接至 ");
    sb.append(uri.getHost());
    blockHosts.add(sb.toString());
    save();
  }
  
  private static void save() {
    File file = new File(MiSkYleFirewall.getPlugin().getDataFolder(), "log.yml");
    YamlConfiguration log = YamlConfiguration.loadConfiguration(file);
    log.set("log", blockHosts);
    try {
      log.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
