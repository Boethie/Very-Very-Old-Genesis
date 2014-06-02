package genesis.block.plants;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.IGrowable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 
 * @author Arbiter
 *
 */
public class BlockAsteroxylon extends BlockGenesisTerrestrialPlant implements IGrowable
{
	public BlockAsteroxylon()
	{
		super();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List itemList) 
	{
		itemList.add(new ItemStack(item, 1, 0));
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return 0;
	}

	@Override
	public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5)
	{
		return true;
	}

	@Override
	public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5)
	{
		return true;
	}

	@Override
	public void func_149853_b(World var1, Random var2, int var3, int var4, int var5) 
	{
		
	}
}