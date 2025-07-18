package com.snowpegeon.farmnbt.config;


import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * config.ymlを読み取るローダー.
 */
public class ConfigLoader {

  /**
   * config.ymlを参照するFileConfigurationの実体.
   */
  public static FileConfiguration fileConfig;

  /**
   * ローダーの初期化処理. JavaPlugin.onEnableクラスでのみ呼ばれることを想定.
   */
  public static void setup(JavaPlugin plugin) {
    fileConfig = plugin.getConfig();
    fileConfig.options().copyDefaults(true);
    fileConfig.options().setHeader(List.of("FarmNBT Configuration"));
    plugin.saveConfig();
  }

  /**
   * log-levelの値を取得する.
   *
   * @return String
   */
  public static String getLogLevel() {
    return fileConfig.getString("log-level");
  }
}