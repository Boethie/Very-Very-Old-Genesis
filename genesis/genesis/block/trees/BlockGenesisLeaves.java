package genesis.genesis.block.trees;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.BlocksHelper;
import genesis.genesis.lib.IDs;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockGenesisLeaves extends BlockLeaves implements IBlockGenesisTrees {

	@SideOnly(Side.CLIENT)
    private static Icon[] leavesIcons = null;
    @SideOnly(Side.CLIENT)
    private static Icon[] leavesOpaqIcons = null;
    
    public int leavesSet;
    
	public BlockGenesisLeaves(int par1, int set) {
		super(par1);
		
		this.leavesSet = set;
		this.setCreativeTab(Genesis.tabGenesis);
		
		setHardness(0.2F);
		setLightOpacity(1);
		setStepSound(soundGrassFootstep);
		setBurnProperties(blockID, 2, 8);
	}
	
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
		int index = (par2 & 3) + (leavesSet * TreeBlocks.setSize);
        return Minecraft.getMinecraft().gameSettings.fancyGraphics ? leavesIcons[index] : leavesOpaqIcons[index];
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List itemList)
    {
		BlocksHelper.addTreeSubBlocksToCreative(blockID, creativeTabs, itemList, leavesSet);
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		leavesIcons = new Icon[TreeBlocks.woodTypeCount];
        leavesOpaqIcons = new Icon[TreeBlocks.woodTypeCount];
        
        for (int i = 0; i < TreeBlocks.woodTypeCount; ++i)
        {
            leavesIcons[i] = par1IconRegister.registerIcon(Genesis.MOD_ID + ":leaves_" + TreeBlocks.woodTypes.get(i).toLowerCase());
            leavesOpaqIcons[i] = par1IconRegister.registerIcon(Genesis.MOD_ID + ":leaves_" + TreeBlocks.woodTypes.get(i).toLowerCase()  + "_opaque");
        }
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return IDs.blockSaplingID.getID(leavesSet);
    }
	
	public int getBlockSet()
	{
		return leavesSet;
	}

}
