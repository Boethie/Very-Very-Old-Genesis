package genesis.block.plants;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.common.Genesis;
import genesis.common.GenesisTabs;

public class BlockCalamitesStorage extends BlockRotatedPillar {

	@SideOnly(Side.CLIENT) IIcon topIcon;

	public BlockCalamitesStorage() {
		super(new Material(MapColor.foliageColor));

	    setCreativeTab(GenesisTabs.tabGenesis);
		setHardness(2);
		setStepSound(soundTypeWood);
		// setBurnProperties(blockID, 4, 4);
		setHarvestLevel("axe", 0);
	}

	public void registerBlockIcons(IIconRegister iconRegister) {
		topIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_top");
		blockIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_side");
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
