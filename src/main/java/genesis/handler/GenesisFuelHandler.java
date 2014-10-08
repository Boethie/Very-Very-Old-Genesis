package genesis.handler;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class GenesisFuelHandler implements IFuelHandler {
    private static final GenesisFuelHandler INSTANCE = new GenesisFuelHandler();
    private HashMap<ItemStack, Integer> fuelItems = new HashMap<ItemStack, Integer>();

    private GenesisFuelHandler() {}

    public static GenesisFuelHandler instance() {
        return INSTANCE;
    }

    public void addFuel(ItemStack fuel, int burnTime) {
        fuelItems.put(fuel, burnTime);
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        for (Map.Entry<ItemStack, Integer> entry : fuelItems.entrySet()) {
            if (entry.getKey().isItemEqual(fuel)) {
                return entry.getValue();
            }
        }
        return 0;
    }
}
