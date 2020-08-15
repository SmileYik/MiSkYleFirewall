package com.github.miskyle.miskylepluginfirewall.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.github.miskyle.miskylepluginfirewall.MiSkYleFirewall;
import com.github.miskyle.miskylepluginfirewall.data.ConfigManager;

public class Commands {
  @Cmd(subCmd = {"addPlugin"},
      args = {"","�����"},
      des = "���Ӳ�������б���",
      permission = "MiSkYleFirewall.Admin",
      needPlayer = false)
  public void addPlugin(CommandSender sender, String[] args) {
    ConfigManager.getPluginList().add(args[1]);
    ConfigManager.save();
    sender.sendMessage("��Ϊ: "+args[1]+" �Ĳ����������б���");
  }
  
  @Cmd(subCmd = {"addHost"},
      args = {"","������"},
      des = "�������������б���",
      permission = "MiSkYleFirewall.Admin",
      needPlayer = false)
  public void addHost(CommandSender sender, String[] args) {
    ConfigManager.getHostList().add(args[1]);
    ConfigManager.save();
    sender.sendMessage("��Ϊ: "+args[1]+" ����������������б���");
  }
  
  @Cmd(subCmd = {"reload"},
      args = {""},
      des = "����������",
      permission = "MiSkYleFirewall.Admin",
      needPlayer = false)
  public void reload(CommandSender sender, String[] args) {
    Bukkit.getPluginManager().disablePlugin(MiSkYleFirewall.getPlugin());
    Bukkit.getPluginManager().enablePlugin(MiSkYleFirewall.getPlugin());
    sender.sendMessage("�����������!");
  }
}
