package genesis.block.plants;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.common.Genesis;

public class BlockCalamitesStorage extends BlockRotatedPillar {

	@SideOnly(Side.CLIENT)
	IIcon topIcon;
	
	public BlockCalamitesStorage(int blockID) {
		super(blockID, new Material(MapColor.foliageColor));
		
		setCreativeTab(Genesis.tabGenesis);
		setHardness(2);
		setStepSound(soundWoodFootstep);
		setBurnProperties(this.blockID, 4, 4);
		
		MinecraftForge.setBlockHarvestLevel(this, "axe", 0);
	}
	
	public void registerIcons(IIconRegister iconRegister)
    {
		this.topIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_top");
		this.blockIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_side");
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int metadata) {
		return this.blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getEndIcon(int metadata)
    {
		return this.topIcon;
    }
	
}
