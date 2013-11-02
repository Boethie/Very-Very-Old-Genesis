package genesis.genesis.block.trees;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.genesis.common.Genesis;
import genesis.genesis.lib.BlocksHelper;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockGenesisWood extends BlockWood implements IBlockGenesisTrees
{
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    
    private int woodSet;

    public BlockGenesisWood(int blockID, int cat)
    {
        super(blockID);
        setCreativeTab(Genesis.tabGenesis);
		setStepSound(Block.soundWoodFootstep);
		setHardness(2);
		setBurnProperties(blockID, 5, 5);
		
		
		this.woodSet = cat;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List itemList)
    {
		BlocksHelper.addTreeSubBlocksToCreative(blockID, creativeTabs, itemList, this.woodSet);
    }
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
    {
        iconArray = new Icon[TreeBlocks.woodTypeCount];
        
        for (int i = 0; i < TreeBlocks.woodTypeCount; ++i)
        {
            iconArray[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":planks_" + TreeBlocks.woodTypes.get(i).toLowerCase());
        }
    }
	
    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 >= this.iconArray.length)
        {
            par2 = 0;
        }

        return this.iconArray[woodSet*TreeBlocks.setSize + par2];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

	@Override
	public int getBlockSet() {
		
		return woodSet;
	}
}
