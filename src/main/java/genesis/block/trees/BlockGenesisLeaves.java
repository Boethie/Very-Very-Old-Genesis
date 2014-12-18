package genesis.block.trees;

import genesis.Genesis;
import genesis.block.trees.GenesisTreeBlocks.TreeType;
import genesis.helper.GenesisHelper;
import genesis.lib.GenesisTabs;
import genesis.managers.GenesisModItems;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
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
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
    	
    	BlockGenesisLeaves theBlock=(BlockGenesisLeaves)world.getBlock(x, y, z);
    	int type=theBlock.treeType.ordinal();
    	ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int chance = this.func_150123_b(metadata);
        
        if (fortune > 0)
        {
            chance -= 2 << fortune;
            if (chance < 10) chance = 10;
        }

        if (world.rand.nextInt(chance) == 0)
            ret.add(new ItemStack(this.getItemDropped(type, world.rand, fortune), 1, this.damageDropped(metadata)));

        chance = 200;
        if (fortune > 0)
        {
            chance -= 10 << fortune;
            if (chance < 40) chance = 40;
        }

        this.captureDrops(true);
        this.func_150124_c(world, x, y, z, metadata, chance); // Dammet mojang
        ret.addAll(this.captureDrops(false));
        return ret;
    }
    
    @Override
    public int damageDropped(int meta)
    {
        return 0;
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
    	BlockGenesisLeaves theBlock=(BlockGenesisLeaves)world.getBlock(x, y, z);
    	if(theBlock.treeType.equals(TreeType.ARAUCARIOXYLON)) {
            if (world.rand.nextInt(50) < 4) {
                dropBlockAsItem(world, x, y, z, new ItemStack(GenesisModItems.araucarioxylon_cone));
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
