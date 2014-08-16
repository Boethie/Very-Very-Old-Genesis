package genesis.block.aquatic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.common.Genesis;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * @author Arbiter
 */
public class BlockPteridinium extends BlockGenesisAquaticPlant {
    public BlockPteridinium() {
        super(Material.water);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon(Genesis.MOD_ID + ":" + getTextureName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.blockIcon;
    }
}