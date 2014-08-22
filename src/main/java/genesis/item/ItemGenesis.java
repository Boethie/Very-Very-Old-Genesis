package genesis.item;

import genesis.common.Genesis;
import genesis.common.GenesisTabs;
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
