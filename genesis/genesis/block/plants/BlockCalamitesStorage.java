package genesis.genesis.block.plants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.genesis.common.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;

public class BlockCalamitesStorage extends BlockRotatedPillar {

	@SideOnly(Side.CLIENT)
	Icon topIcon;
	
	public BlockCalamitesStorage(int blockID) {
		super(blockID, new Material(MapColor.foliageColor));
		
		setCreativeTab(Genesis.tabGenesis);
		setHardness(2);
		setStepSound(soundWoodFootstep);
		setBurnProperties(this.blockID, 4, 4);
		
		MinecraftForge.setBlockHarvestLevel(this, "axe", 0);
	}
	
	public void registerIcons(IconRegister iconRegister)
    {
		this.topIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_top");
		this.blockIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_side");
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getSideIcon(int metadata) {
		return this.blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getEndIcon(int metadata)
    {
		return this.topIcon;
    }
	
}
