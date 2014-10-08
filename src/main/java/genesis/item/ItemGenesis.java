package genesis.item;

import genesis.Genesis;
import genesis.lib.GenesisTabs;
import net.minecraft.item.Item;

public class ItemGenesis extends Item {
    public ItemGenesis() {
        setCreativeTab(GenesisTabs.tabGenesisMaterials);
    }

    @Override
    public Item setTextureName(String textureName) {
        return super.setTextureName(Genesis.ASSETS + textureName);
    }
}
