package genesis.genesis.block;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.Ids;
import genesis.genesis.world.WorldGenSigillariaTree;
import genesis.genesis.world.WorldGenSigillariaTree1;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockGenesisSapling extends BlockFlower{

	public static final String[] gwoodType = new String[] {"Araucarioxylon", "Bjuvia", "Cordaites", "Fig", "Lepidodendron", "Sigillaria", "Tempskya"};
	public int SapCat;
	private Icon[] saplingIcon;
	
	public BlockGenesisSapling(int par1, int cat) {
		super(par1);
		this.SapCat = cat;
		float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(Genesis.tabGenesis);
	}
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        int k = (par2 & 3) + (SapCat * 4);
        return this.saplingIcon[k];
    }
	public int damageDropped(int par1)
    {
        return par1 & 3;
    }
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		int count = 0;
		for(int i = (SapCat * 4); i < this.gwoodType.length; i++)
		{
			par3List.add(new ItemStack(par1, 1, count));
			count++;
			if(count == 4)
			{
				break;
			}
		}
    }
	public void registerIcons(IconRegister par1IconRegister)
    {
        this.saplingIcon = new Icon[this.gwoodType.length];
        
        for (int i = 0; i < this.gwoodType.length; ++i)
        {
            this.saplingIcon[i] = par1IconRegister.registerIcon(Genesis.modid + ":sapling_" + this.gwoodType[i].toLowerCase());
        }
    }
	protected ItemStack createStackedBlock(int par1)
	{
		return new ItemStack(this.blockID, 1, this.func_111050_e(par1));
	}
	public int func_111050_e(int par1)
	{
		return par1 & 3;
	}
	@SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
		int k = (par1 & 3) + (SapCat * 4);
        return this.saplingIcon[k];
    }
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return Ids.blockSaplingGenesisID_actual + this.SapCat;
    }
	public void growTree(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!TerrainGen.saplingGrowTree(par1World, par5Random, par2, par3, par4)) return;

        int l = par1World.getBlockMetadata(par2, par3, par4) & 3;
        Object object = null;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;

        if (l == 1)
        {
        	int r = par5Random.nextInt(3);
        	switch(r){
        	case 0:
        	case 1:
        		object = new WorldGenSigillariaTree(true);
        		break;
        	case 2:
        		object = new WorldGenSigillariaTree1();
        		break;
        	}
            
        }
        else if (l == 2)
        {
            //object = new WorldGenTaiga1();
        }
        else if (l == 3)
        {
        	//object = new WorldGenTrees(true);
        }
        else
        {
            //object = new WorldGenTrees(true);
        }

        if (flag)
        {
            par1World.setBlock(par2 + i1, par3, par4 + j1, 0, 0, 4);
            par1World.setBlock(par2 + i1 + 1, par3, par4 + j1, 0, 0, 4);
            par1World.setBlock(par2 + i1, par3, par4 + j1 + 1, 0, 0, 4);
            par1World.setBlock(par2 + i1 + 1, par3, par4 + j1 + 1, 0, 0, 4);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, 0, 0, 4);
        }

        if (!((WorldGenerator)object).generate(par1World, par5Random, par2 + i1, par3, par4 + j1))
        {
            par1World.setBlock(par2, par3, par4, this.blockID, l, 4);
        }
    }


}
