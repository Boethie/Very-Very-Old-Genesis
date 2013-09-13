package genesis.genesis.block.trees;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.BlocksHelper;
import genesis.genesis.lib.IDs;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockGenesisLog extends BlockLog implements IBlockGenesisTrees {

    @SideOnly(Side.CLIENT)
    private Icon[] woodSide;
    @SideOnly(Side.CLIENT)
    private Icon[] woodTop;
    public int logSet;
    
	public BlockGenesisLog(int blockID, int cat) {
		super(blockID);
		
		setCreativeTab(Genesis.tabGenesis);
		setStepSound(Block.soundWoodFootstep);
		setHardness(1F);
		setBurnProperties(blockID, 4, 4);
		
		this.logSet = cat;
	}
	
	@Override
	public int getRenderType()
	{
		return 31;
	}
	
	public int damageDropped(int par1)
    {
        return par1 & 3;
    }
	
	public Icon getIcon(int side, int metadata)
    {
        int k = metadata & 12;
        int l = (metadata & 3) + (logSet * 4);
        return k == 0 && (side == 1 || side == 0) ? this.func_111049_d(l) : (k == 4 && (side == 5 || side == 4) ? this.func_111049_d(l) : (k == 8 && (side == 2 || side == 3) ? this.func_111049_d(l) : this.func_111048_c(l)));
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List itemList)
    {
		BlocksHelper.addTreeSubBlocksToCreative(blockID, creativeTabs, itemList, this.logSet);
    }
	
	public void registerIcons(IconRegister iconRegister)
    {
        this.woodSide = new Icon[TreeBlocks.woodTypes.length];
        this.woodTop = new Icon[TreeBlocks.woodTypes.length];
        
        for (int i = 0; i < TreeBlocks.woodTypes.length; ++i)
        {
            this.woodSide[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":log_" + TreeBlocks.woodTypes[i].toLowerCase());
            this.woodTop[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":log_" + TreeBlocks.woodTypes[i].toLowerCase()  + "_top");
        }
    }
	
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return super.onBlockPlaced(par1World, par2, par3, par4, par5, par6, par7, par8, par9);
    }
	
	@SideOnly(Side.CLIENT)
	protected Icon func_111048_c(int i) {
		return this.woodSide[i];
	}
	
	@SideOnly(Side.CLIENT)
    protected Icon func_111049_d(int par1)
    {
        return this.woodTop[par1];
    }
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return blockID;
    }
	
	public int getBlockSet()
	{
		return logSet;
	}
	
}
