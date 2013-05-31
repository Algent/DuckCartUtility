package eu.algent.DuckCartUtility.Utils;

import net.minecraft.server.v1_5_R3.EntityMinecartAbstract;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftEntity;
import org.bukkit.entity.Minecart;

public class CartUtilCB {

    public static Boolean hasCustomBlock(Minecart minecart) {
        EntityMinecartAbstract minecartAbs = (EntityMinecartAbstract) ((CraftEntity) minecart).getHandle();
        return minecartAbs.s();
    }

    public static Integer getBlockId(Minecart minecart) {
        EntityMinecartAbstract minecartAbs = (EntityMinecartAbstract) ((CraftEntity) minecart).getHandle();
        return (minecartAbs.m() == null) ? 0 : minecartAbs.m().id;
    }

    public static Integer getBlockData(Minecart minecart) {
        EntityMinecartAbstract minecartAbs = (EntityMinecartAbstract) ((CraftEntity) minecart).getHandle();
        return minecartAbs.o();
    }

    public static Integer getBlockOffset(Minecart minecart) {
        EntityMinecartAbstract minecartAbs = (EntityMinecartAbstract) ((CraftEntity) minecart).getHandle();
        return minecartAbs.q();
    }

    public static void toggleCustomBlock(Minecart minecart) {
        EntityMinecartAbstract minecartAbs = (EntityMinecartAbstract) ((CraftEntity) minecart).getHandle();
        if (minecartAbs.s())
            minecartAbs.a(false);
        else
            minecartAbs.a(true);
    }

    public static void disableCustomBlock(Minecart minecart) {
        EntityMinecartAbstract minecartAbs = (EntityMinecartAbstract) ((CraftEntity) minecart).getHandle();
        if (minecartAbs.s())
            minecartAbs.a(false);
    }

    public static void setBlock(Minecart minecart, Integer id, Integer data) {
        EntityMinecartAbstract minecartAbs = (EntityMinecartAbstract) ((CraftEntity) minecart).getHandle();
        minecartAbs.k(id);
        minecartAbs.l(data);
    }

    public static void setData(Minecart minecart, Integer data) {
        EntityMinecartAbstract minecartAbs = (EntityMinecartAbstract) ((CraftEntity) minecart).getHandle();
        minecartAbs.l(data);
    }

    public static void setBlockOffset(Minecart minecart, Integer offset) {
        EntityMinecartAbstract minecartAbs = (EntityMinecartAbstract) ((CraftEntity) minecart).getHandle();
        minecartAbs.m(offset);
    }

    public static void resetCustomization(Minecart minecart) {
        setBlock(minecart, 0, 0);
        resetOffset(minecart);
        disableCustomBlock(minecart);
    }

    public static void resetOffset(Minecart minecart) {
        int offset = 6;
        switch (minecart.getType()) {
        case MINECART:
        case MINECART_FURNACE:
        case MINECART_TNT:
        case MINECART_MOB_SPAWNER:
            offset = 6;
            break;
        case MINECART_CHEST:
            offset = 8;
            break;
        case MINECART_HOPPER:
            offset = 1;
            break;
        default:
            break;
        }
        setBlockOffset(minecart, offset);
    }
}
