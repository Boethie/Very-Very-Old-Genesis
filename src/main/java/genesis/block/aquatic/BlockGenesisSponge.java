package genesis.block.aquatic;

import genesis.common.Genesis;
import genesis.lib.PlantMetadata;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Arbiter
 */
public class BlockGenesisSponge extends BlockGenesisAquaticPlant {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockGenesisSponge() {
        super(Material.water);
        setStepSound(soundTypeCloth);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        int size = PlantMetadata.spongeTypes.size();
        for (int i = 0; i < size; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[PlantMetadata.spongeTypes.size()];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = register.registerIcon(Genesis.ASSETS + PlantMetadata.spongeTypes.get(i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int meta) {
        return icons[meta];
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
    	if (world.getBlockMetadata(x, y, z) == 5)
    	{
    		entity.attackEntityFrom(DamageSource.cactus, 1.0f);
    	}
    }
}