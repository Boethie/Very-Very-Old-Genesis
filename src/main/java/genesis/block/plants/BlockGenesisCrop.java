package genesis.block.plants;

import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.common.Genesis;

public class BlockGenesisCrop extends BlockCrops{

	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;
	@SideOnly(Side.CLIENT)
	private int Stages;

	private int soilID;
	private int seedID;
	private int droppedAmmount;
	private int fullGrownDroppedAmmount;

	/**
	 * 
	 * @param blockID
	 * @param seedID
	 * @param soilID
	 * @param stages
	 * @param fullGrownDroppedAmmountMax
	 */
	public BlockGenesisCrop(int blockID, int seedID, int soilID, int stages, int fullGrownDroppedAmmountMax)
	{
		super(blockID);

		this.fullGrownDroppedAmmount = fullGrownDroppedAmmountMax;
		this.soilID = soilID;
		this.seedID = seedID;
		Stages = stages;
		setCreativeTab((CreativeTabs)null);
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int par1)
	{
		return par1 == soilID;
	}

	@Override
	protected int getSeedItem()
	{
		return seedID + 256;
	}

	@Override
	protected int getCropItem()
	{
		return seedID + 256;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return droppedAmmount;
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

		if (!par1World.isRemote)
		{
			if (par5 >= Stages)
			{
				Random par1Random = new Random();
				droppedAmmount = par1Random.nextInt(fullGrownDroppedAmmount) + 1;
				this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this));
			}
			else
			{
				droppedAmmount = 1;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		if (par2 < 0 || par2 > Stages)
		{
			par2 = Stages;
		}

		return this.iconArray[par2];
	}

	@SideOnly(Side.CLIENT)

	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.iconArray = new IIcon[Stages];

		for (int i = 0; i < this.iconArray.length; ++i)
		{
			this.iconArray[i] = par1IconRegister.registerIcon(Genesis.MOD_ID + ":" + this.getTextureName() + "_stage_" + i);
		}
	}
}
