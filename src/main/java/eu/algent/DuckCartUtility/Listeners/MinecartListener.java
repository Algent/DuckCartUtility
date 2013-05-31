package eu.algent.DuckCartUtility.Listeners;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.*;
import org.bukkit.util.Vector;

import eu.algent.DuckCartUtility.DuckCartUtility;
import eu.algent.DuckCartUtility.Events.VehicleMoveBlockEvent;
import eu.algent.DuckCartUtility.Utils.CartUtil;
import eu.algent.DuckCartUtility.Utils.SignUtil;

public class MinecartListener implements Listener {
    private DuckCartUtility plugin;

    public MinecartListener(DuckCartUtility plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {
        if (!(event.getVehicle() instanceof Minecart)) return;
        if (CartUtil.hasMovedBlock(event.getFrom(), event.getTo())) {
            plugin.callEvent(new VehicleMoveBlockEvent(event.getVehicle(), event.getFrom(), event.getTo()));
        }
    }

    @EventHandler
    public void onVehicleMoveBlock(VehicleMoveBlockEvent event){   
        if (!(event.getVehicle() instanceof Minecart)) return;
        Minecart minecart = (Minecart) event.getVehicle();
        if (CartUtil.isOnTrack(minecart) && plugin.getPluginConfig().isSignControlEnabled()) {
            Block track = minecart.getLocation().getBlock();
            if (SignUtil.isSign(track.getRelative(0, -2, 0))) {
                Sign sign = SignUtil.getSignAt(track.getRelative(0, -2, 0));
                plugin.signControl.signEvent(sign, minecart);
            }
        }
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent event) {
        if (!(event.getEntered() instanceof Player)) {
            if (!plugin.getPluginConfig().canNonPlayerEnterCart()) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        plugin.getLogger().info("VehicleExitEvent");
        if (!(event.getVehicle() instanceof Minecart)) return;
        Minecart minecart = (Minecart) event.getVehicle();
        if (minecart.getDamage() > 40) return;
        if (plugin.getPluginConfig().isDropOnExit()) CartUtil.doDropCart(minecart);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onVehicleEntityCollision(VehicleEntityCollisionEvent event){
        if (event.getVehicle() instanceof Minecart){
            Minecart minecart = (Minecart) event.getVehicle();
            if (event.getEntity() instanceof LivingEntity){
                LivingEntity livingentity = (LivingEntity) event.getEntity();
                if (!(livingentity instanceof Player) && !(livingentity instanceof Tameable)){
                    if (plugin.getPluginConfig().isRemoveLivingEntityCollide()) {
                        final Vector velocity = minecart.getVelocity(); 
                        if (velocity.getX() != 0d || velocity.getZ() != 0d){
                            livingentity.remove();
                            event.setCancelled(true);
                            event.setCollisionCancelled(true);
                        }
                    }
                }
            }
        }

    }

}
