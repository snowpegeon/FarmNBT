package com.snowpegeon.farmnbt;

import com.github.teruteru128.logger.Logger;
import com.snowpegeon.farmnbt.config.ConfigLoader;
import com.snowpegeon.farmnbt.config.StorageSignNBTConfig;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

public class FarmNBT extends JavaPlugin implements Listener {
    public Logger logger;

    @Override
    public void onEnable() {
        // ConfigLoader初期化
        ConfigLoader.setup(this);

        // ロガーの初期設定
        String logLevel = ConfigLoader.getLogLevel();
        // ログ取得
        Logger.register(this, logLevel);
        logger = Logger.getInstance(this);

        logger.debug("★onEnable:Start");
        logger.debug("★StorageSignNBTConfig:Start");
        // storageSignNBT.configのセットアップ
        StorageSignNBTConfig ssConfigClass = new StorageSignNBTConfig(this);
        FileConfiguration keyConfig = ssConfigClass.getConfig();
        keyConfig.options().copyDefaults(false);
        // 特性上、何度も上書き処理を走らせたくないため、上書き制御付きのdefaultConfigを呼ぶ
        // このファイル自体は子のプラグインでは利用せず、StorageSignでのみ使用
        ssConfigClass.saveDefaultConfig();
        logger.debug("★StorageSignNBTConfig:End");
        logger.debug("★onEnable:End");
    }
}
