package com.snowpegeon.farmnbt;

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

    @Override
    public void onEnable() {
        // storageSignNBT.configのセットアップ
        StorageSignNBTConfig ssConfigClass = new StorageSignNBTConfig(this);
        FileConfiguration keyConfig = ssConfigClass.getConfig();
        keyConfig.options().copyDefaults(true);
        // 特性上、何度も上書き処理を走らせたくないため、上書き制御付きのdefaultConfigを呼ぶ
        // このファイル自体は子のプラグインでは利用せず、StorageSignでのみ使用
        ssConfigClass.saveDefaultConfig();
    }

    private static Optional<String> extractNbtForVersion(List<Map<?, ?>> rawList, String version) {
        for (Map<?, ?> rawMap : rawList) {
            // 安全なキー・値のチェック
            if (rawMap.size() != 1) continue;

            Map.Entry<?, ?> entry = rawMap.entrySet().iterator().next();

            if (!(entry.getKey() instanceof String) || !(entry.getValue() instanceof String)) continue;

            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            if (version.equals(key)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

}
