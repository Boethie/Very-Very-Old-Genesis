package genesis.block.plants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.common.Genesis;
import genesis.common.GenesisSounds;
import genesis.common.GenesisTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockCalamitesStorage extends BlockRotatedPillar {

    @SideOnly(Side.CLIENT)
    IIcon topIcon;

    public BlockCalamitesStorage() {
        super(new Material(MapColor.foliageColor));
        setCreativeTab(GenesisTabs.tabGenesis);
        setHardness(2);
        setStepSound(GenesisSounds.soundTypeCalamites);
        setHarvestLevel("axe", 0);
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Genesis.ASSETS + textureName);
    }

    public void registerBlockIcons(IIconRegister iconRegister) {
        topIcon = iconRegister.registerIcon(getTextureName() + "_top");
        blockIcon = iconRegister.registerIcon(getTextureName() + "_side");
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int metadata) {
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int metadata) {
        return topIcon;
    }
}
