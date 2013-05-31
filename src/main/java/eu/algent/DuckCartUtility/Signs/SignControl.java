package eu.algent.DuckCartUtility.Signs;

import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;

import eu.algent.DuckCartUtility.DuckCartUtility;

public class SignControl {
    DuckCartUtility plugin;
    EjectSign ejectSign;

    public SignControl(DuckCartUtility plugin) {
        this.plugin = plugin;
        ejectSign = new EjectSign(plugin);
    }

    public void signEvent(Sign sign, Minecart minecart) {
        String firstLine = sign.getLine(0);
        if (firstLine.equalsIgnoreCase("[Eject]")) ejectSign.execute(sign, minecart);
    }
}
