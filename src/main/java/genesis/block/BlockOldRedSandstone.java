package genesis.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Arbiter
 */
public class BlockOldRedSandstone extends BlockGenesisRock {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockOldRedSandstone() {
        super(Material.rock, 1);
        setHardness(0.8F);
        setResistance(4.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 0 ? icons[2] : (side == 1 ? icons[0] : icons[1]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[3];
        icons[0] = register.registerIcon(getTextureName() + "_top");
        icons[1] = register.registerIcon(getTextureName() + "_side");
        icons[2] = register.registerIcon(getTextureName() + "_bottom");
    }
}