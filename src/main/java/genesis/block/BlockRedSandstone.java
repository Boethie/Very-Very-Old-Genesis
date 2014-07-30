package genesis.block;

import genesis.common.Genesis;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockRedSandstone extends BlockGenesisRock
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockRedSandstone()
	{
		super(Material.rock, 1);
		setHardness(0.8F);
		setResistance(4.0F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 0 ? icons[2] : (side == 1 ? icons[0] : icons[1]);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		icons = new IIcon[3];
		icons[0] = register.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_top");
		icons[1] = register.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_side");
		icons[2] = register.registerIcon(Genesis.MOD_ID + ":" + getTextureName() + "_bottom");
	}
}