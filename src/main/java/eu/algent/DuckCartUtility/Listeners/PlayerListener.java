package eu.algent.DuckCartUtility.Listeners;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import eu.algent.DuckCartUtility.DuckCartUtility;
import eu.algent.DuckCartUtility.Utils.CartUtil;
import eu.algent.DuckCartUtility.Utils.PlayerUtil;
import eu.algent.DuckCartUtility.Utils.WandUtil;

public class PlayerListener implements Listener {
    private DuckCartUtility plugin;

    public PlayerListener(DuckCartUtility plugin) {
        this.plugin = plugin;
    }

    @EventHandler // RIGHT CLICK
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Minecart)) return;

        Player player = event.getPlayer();
        Minecart minecart = (Minecart) event.getRightClicked();

        // Check if Sneaking
        if (!player.isSneaking()) return;

        if (PlayerUtil.isWandEnabled(player)) {
            if (!plugin.getPluginConfig().isSpecialCartsAllowed() &&
                    !CartUtil.isNormalCart(minecart))
                return;
            event.setCancelled(true);
            String mode = PlayerUtil.getWandState(player);
            if(mode.equals("set") && plugin.getPluginConfig().isWandSetEnabled()) {
                WandUtil.wandSet(minecart, player);
            }
            else if(mode.equals("offset") && plugin.getPluginConfig().isWandOffsetEnabled()) {
                WandUtil.wandOffset(minecart, player);
            }
            else if(mode.equals("toggle") && plugin.getPluginConfig().isWandToggleEnabled()) {
                WandUtil.wandToggle(minecart, player);
            }
            else if(mode.equals("reset") && plugin.getPluginConfig().isWandResetEnabled()) {
                WandUtil.wandReset(minecart, player);
            }
            else if(mode.equals("info") && plugin.getPluginConfig().isWandInfoEnabled()) {
                WandUtil.wandInfo(minecart, player);
            }
        }
    }
}
