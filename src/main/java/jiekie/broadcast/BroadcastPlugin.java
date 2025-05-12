package jiekie.broadcast;

import jiekie.broadcast.command.BroadcastCommand;
import jiekie.broadcast.completer.BroadcastTabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class BroadcastPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // 명령어 등록
        getCommand("공지사항").setExecutor(new BroadcastCommand());

        // 자동완성 등록
        getCommand("공지사항").setTabCompleter(new BroadcastTabCompleter());

        getLogger().info("공지사항 플러그인 by Jiekie");
        getLogger().info("Copyright © 2025 Jiekie. All rights reserved.");
    }

    @Override
    public void onDisable() {}
}
