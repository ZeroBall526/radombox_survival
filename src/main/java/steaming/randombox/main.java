package steaming.randombox;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("randombox survival 플러그인이 활성화 되었어요!");
        getServer().getPluginManager().registerEvents(new radombox() , this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        getLogger().info("randombox survival 플러그인이 비활성화 되었어요!");
        // Plugin shutdown logic
    }
}
