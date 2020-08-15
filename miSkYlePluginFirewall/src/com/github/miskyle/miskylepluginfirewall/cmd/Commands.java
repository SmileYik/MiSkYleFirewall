package com.github.miskyle.miskylepluginfirewall.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.github.miskyle.miskylepluginfirewall.MiSkYleFirewall;
import com.github.miskyle.miskylepluginfirewall.data.ConfigManager;

public class Commands {
  @Cmd(subCmd = {"addPlugin"},
      args = {"","插件名"},
      des = "增加插件名到列表中",
      permission = "MiSkYleFirewall.Admin",
      needPlayer = false)
  public void addPlugin(CommandSender sender, String[] args) {
    ConfigManager.getPluginList().add(args[1]);
    ConfigManager.save();
    sender.sendMessage("名为: "+args[1]+" 的插件已添加至列表中");
  }
  
  @Cmd(subCmd = {"addHost"},
      args = {"","主机名"},
      des = "增加主机名到列表中",
      permission = "MiSkYleFirewall.Admin",
      needPlayer = false)
  public void addHost(CommandSender sender, String[] args) {
    ConfigManager.getHostList().add(args[1]);
    ConfigManager.save();
    sender.sendMessage("名为: "+args[1]+" 的主机名已添加至列表中");
  }
  
  @Cmd(subCmd = {"reload"},
      args = {""},
      des = "重新载入插件",
      permission = "MiSkYleFirewall.Admin",
      needPlayer = false)
  public void reload(CommandSender sender, String[] args) {
    Bukkit.getPluginManager().disablePlugin(MiSkYleFirewall.getPlugin());
    Bukkit.getPluginManager().enablePlugin(MiSkYleFirewall.getPlugin());
    sender.sendMessage("重新载入完成!");
  }
}
