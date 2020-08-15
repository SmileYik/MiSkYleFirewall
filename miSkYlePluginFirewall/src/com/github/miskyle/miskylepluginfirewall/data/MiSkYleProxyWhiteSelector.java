package com.github.miskyle.miskylepluginfirewall.data;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;

import org.bukkit.plugin.Plugin;

import com.github.miskyle.miskylepluginfirewall.MiSkYleFirewall;

public class MiSkYleProxyWhiteSelector extends ProxySelector{
  private static MiSkYleProxyWhiteSelector mps;
  private ProxySelector defualt;
  
  public MiSkYleProxyWhiteSelector() {
    if (mps != null) {
      reback();
    }
    mps = this;
    defualt = ProxySelector.getDefault();
    ProxySelector.setDefault(this);
  }
  
  public static void reback() {
    ProxySelector.setDefault(mps.defualt);
  }

  @Override
  public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
    this.defualt.connectFailed(uri, sa, ioe);
  }

  @Override
  public List<Proxy> select(URI uri) {
    Plugin plugin = ConfigManager.getPlugin(new Exception().getStackTrace());
    if (plugin == null) {
      if (ConfigManager.getHostList().contains(uri.getHost())) {
        return defualt.select(uri);
      } else {
        MiSkYleFirewall.getPlugin().getServer().getConsoleSender().sendMessage("§c已阻止插件连接至 §b" + uri.getHost());
        Report.add(null, uri);
        return null;
      }
    }
    
    if (ConfigManager.getPluginList().contains(plugin.getName())) {
      return defualt.select(uri);
    } else if (ConfigManager.getHostList().contains(uri.getHost())) {
      return defualt.select(uri);
    } else {
      MiSkYleFirewall.getPlugin().getServer().getConsoleSender().sendMessage("§c已阻止插件 §b"+plugin.getName()+" §c连接至 §b" + uri.getHost());
      Report.add(plugin.getName(), uri);
      return null;
    }
  }
}
