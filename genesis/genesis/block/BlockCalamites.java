package genesis.genesis.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.genesis.common.Genesis;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockCalamites extends BlockRotatedPillar{

	@SideOnly(Side.CLIENT)
	Icon topIcon;
	@SideOnly(Side.CLIENT)
	Icon sideIcon;
	
	public BlockCalamites(int blockID) {
		super(blockID, Material.plants);
		this.setCreativeTab(Genesis.tabGenesis);
	}
	
	public void registerIcons(IconRegister iconRegister)
    {
		this.topIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_block_top");
		this.sideIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_block_side");
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getSideIcon(int i) {
		return this.sideIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getEndIcon(int par1)
    {
		return this.topIcon;
    }

}
