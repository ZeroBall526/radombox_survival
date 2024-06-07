package steaming.randombox;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;


public class radombox implements Listener {

    @EventHandler
    public void playercraftevent(CraftItemEvent event){
        event.setCancelled(true);
        Player p = (Player) event.getWhoClicked();

        p.sendMessage("크래프팅중!");
    }
}
