package genesis.genesis.block.trees;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.BlocksHelper;
import genesis.genesis.lib.IDs;
import genesis.genesis.world.WorldGenTreeCordaites;
import genesis.genesis.world.WorldGenTreeLepidodendron;
import genesis.genesis.world.WorldGenTreeSigillaria;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSapling;
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

public class BlockGenesisSapling extends BlockSapling implements IBlockGenesisTrees {
	
	public int saplingSet;
	private static Icon[] saplingIcons = null;
	
	public BlockGenesisSapling(int blockID, int set) {
		super(blockID);
		
        setCreativeTab(Genesis.tabGenesis);
        setStepSound(soundGrassFootstep);
        
		this.saplingSet = set;
	}
	
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        int k = (par2 & 3) + (saplingSet * 4);
        return this.saplingIcons[k];
    }
	
	public int damageDropped(int par1)
    {
        return par1 & 3;
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List itemList)
    {
		BlocksHelper.addTreeSubBlocksToCreative(blockID, creativeTabs, itemList, this.saplingSet);
    }
	
	public void registerIcons(IconRegister iconRegister)
    {
        saplingIcons = new Icon[TreeBlocks.woodTypeCount];
        
        for (int i = 0; i < TreeBlocks.woodTypeCount; ++i)
        {
            saplingIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":sapling_" + TreeBlocks.woodTypes.get(i).toLowerCase());
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
		int k = (par1 & 3) + (saplingSet * 4);
        return this.saplingIcons[k];
    }
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return blockID;
    }
	
	public void growTree(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!TerrainGen.saplingGrowTree(par1World, par5Random, par2, par3, par4)) return;

        int l = par1World.getBlockMetadata(par2, par3, par4) & 3;
        Object object = null;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;

        if (l == 0)
        {
        	/*
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
        	*/
        	object = new WorldGenTreeSigillaria(7, 3, true);
        }
        else if (l == 1)
        {
            object = new WorldGenTreeLepidodendron(9, 5, true);
        }
        else if (l == 2)
        {
        	object = new WorldGenTreeCordaites(15, 5, true);
        }
        else
        {
            object = new WorldGenTrees(true);
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
	
	public int getBlockSet()
	{
		return saplingSet;
	}

}
