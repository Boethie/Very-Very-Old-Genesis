package genesis.item;

import net.minecraft.item.Item;

public interface IUpgradeableTool <E extends Item>
{
    public void setNextTier(E nextTier);
    public E getNextTier();
}
