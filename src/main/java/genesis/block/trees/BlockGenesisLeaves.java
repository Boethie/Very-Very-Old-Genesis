package genesis.block.trees;

import genesis.Genesis;
import genesis.block.trees.GenesisTreeBlocks.TreeType;
import genesis.helper.GenesisHelper;
import genesis.item.itemblock.IItemBlockWithSubNames;
import genesis.lib.GenesisTabs;
import genesis.managers.GenesisModItems;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGenesisLeaves extends BlockLeaves {
	
	public static String[] blockNames;
	
	static{
		TreeType[] trees = TreeType.values();
		blockNames = new String[trees.length];
		for(TreeType tree : trees){
			blockNames[tree.ordinal()] = tree.name().toLowerCase();
		}
	}
	
	private IIcon[] icons;
	private TreeType treeType;
	
    public BlockGenesisLeaves(int type) {
    	super();
    	treeType = TreeType.get(type);
        setCreativeTab(GenesisTabs.tabGenesisDecoration);
    }

    public String getUnlocalizedName(){
        return super.getUnlocalizedName() + treeType.name().toLowerCase();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
    	icons = new IIcon[2];
    	String path = Genesis.ASSETS + "leaves_" + treeType.name().toLowerCase();
    	icons[0] = register.registerIcon(path);
    	icons[1] = register.registerIcon(path + "_opaque");
    }

    @Override
    public IIcon getIcon(int id, int metadata) {
    	boolean opaque = Genesis.proxy.areLeavesOpaque();
    	return icons[GenesisHelper.booleanToInt(opaque)];
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int unused) {
        return Item.getItemFromBlock(GenesisTreeBlocks.saplings[metadata]);
    }

    @Override
    public boolean isOpaqueCube() {
        return Genesis.proxy.areLeavesOpaque();
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        field_150121_P = Minecraft.getMinecraft().gameSettings.fancyGraphics;
        return super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }

    @Override
    public String[] func_150125_e() {
        return blockNames;
    }

    @Override
    protected void func_150124_c(World world, int x, int y, int z, int metadata, int chance) {
        if(treeType.equals(TreeType.ARAUCARIOXYLON)) {
            if (world.rand.nextInt(50) == 0) {
                dropBlockAsItem(world, x, y, z, new ItemStack(GenesisModItems.araucarioxylon_seeds));
            }
        }
        world.setBlockToAir(x, y, z);
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        //If it will harvest, delay deletion of the block
        return willHarvest || super.removedByPlayer(world, player, x, y, z, willHarvest);
    }
}
