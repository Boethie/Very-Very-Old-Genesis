package genesis.genesis.block.trees;

import genesis.genesis.block.BlockSounds;
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
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * This class is exactly the same as BlockGenesisLog except that it spawns mobs upon breaking
 */
public class BlockRottenLog extends BlockLog implements IBlockGenesisTrees {

    private static Icon[] woodSideIcons = null;

    private static Icon[] woodTopIcons = null;
    public int logSet;
    
	public BlockRottenLog(int blockID, int cat) {
		super(blockID);
		
		setCreativeTab(Genesis.tabGenesis);
		setStepSound(BlockSounds.soundRottenLogFootstep);
		setHardness(2);
		setBurnProperties(blockID, 4, 4);
		
		this.logSet = cat;
	}
	
	public int damageDropped(int par1)
    {
        return par1 & 3;
    }
	
	/**
	 * Method that allows block to spawn monsters on destruction.
	 * Change Silverfish to desired mob once coding for that mob is finished.
	 */
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            EntitySilverfish entitysilverfish = new EntitySilverfish(par1World);
            entitysilverfish.setLocationAndAngles((double)par2 + 0.5D, (double)par3, (double)par4 + 0.5D, 0.0F, 0.0F);
            par1World.spawnEntityInWorld(entitysilverfish);
            entitysilverfish.spawnExplosionParticle();
        }

        super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
    }
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata)
    {
        int k = metadata & 12;
        int l = (metadata & 3) + (logSet * TreeBlocks.setSize);
        return k == 0 && (side == 1 || side == 0) ? this.func_111049_d(l) : (k == 4 && (side == 5 || side == 4) ? this.func_111049_d(l) : (k == 8 && (side == 2 || side == 3) ? this.func_111049_d(l) : this.func_111048_c(l)));
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List itemList)
    {
		BlocksHelper.addTreeSubBlocksToCreative(blockID, creativeTabs, itemList, this.logSet);
    }
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
    {
        woodSideIcons = new Icon[TreeBlocks.woodTypeCount];
        woodTopIcons = new Icon[TreeBlocks.woodTypeCount];
        
        for (int i = 0; i < TreeBlocks.woodTypeCount; ++i)
        {
            woodSideIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":log_" + TreeBlocks.woodTypes.get(i).toLowerCase());
            woodTopIcons[i] = iconRegister.registerIcon(Genesis.MOD_ID + ":log_" + TreeBlocks.woodTypes.get(i).toLowerCase()  + "_top");
        }
    }
	
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return super.onBlockPlaced(par1World, par2, par3, par4, par5, par6, par7, par8, par9);
    }
	
	@SideOnly(Side.CLIENT)
	protected Icon func_111048_c(int i) {
		return this.woodSideIcons[i];
	}
	
	@SideOnly(Side.CLIENT)
    protected Icon func_111049_d(int par1)
    {
        return this.woodTopIcons[par1];
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
