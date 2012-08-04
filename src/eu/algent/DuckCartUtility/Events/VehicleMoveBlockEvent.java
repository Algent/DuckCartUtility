package eu.algent.DuckCartUtility.Events;

import org.bukkit.Location;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.HandlerList;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class VehicleMoveBlockEvent extends VehicleMoveEvent{
    private static final HandlerList handlers = new HandlerList();

    public VehicleMoveBlockEvent(final Vehicle vehicle, final Location from, final Location to) {
        super(vehicle, from, to);
    }
    
    public HandlerList getHandlers() {
        return handlers;
    }
     
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
