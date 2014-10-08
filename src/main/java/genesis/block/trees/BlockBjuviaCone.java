package genesis.block.trees;

import genesis.block.BlockGenesis;
import genesis.client.renderer.block.BlockBjuviaConeRenderer;
import genesis.lib.GenesisTabs;
import genesis.managers.GenesisModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * @author Arbiter
 */
public class BlockBjuviaCone extends BlockGenesis {
    public BlockBjuviaCone() {
        super(Material.wood);
        setHardness(0.2F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
        setCreativeTab(GenesisTabs.tabGenesisDecoration);
        setTickRandomly(true);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return BlockBjuviaConeRenderer.renderID;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        return meta == 2 ? 3 : 0;
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par3) {
        return GenesisModItems.bjuvia_seeds;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (random.nextInt(10) == 0 && canBlockStay(world, x, y, z)) {
            int meta = world.getBlockMetadata(x, y, z) + 1;
            if (meta > 2)
            {
            	meta = 2;
            }
            if (meta != world.getBlockMetadata(x, y, z))
            {
                world.setBlockMetadataWithNotify(x, y, z, meta, 2);
            }
        }
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
    	switch (world.getBlockMetadata(x, y, z))
    	{
    	case 0:
            setBlockBounds(0.3f, 0.0f, 0.3f, 0.7f, 0.8f, 0.7f);
            break;
    	case 1:
    		setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 0.9f, 0.8f);
    		break;
    	case 2:
    		setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, 1.0f, 0.9f);
    		break;
    		default:
    			break;
    	}
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
    	return canBlockStay(world, x, y, z);
    }
    
    protected void dropConeIfCannotStay(World world, int x, int y, int z)
    {
    	if (!canBlockStay(world, x, y, z))
    	{
    		dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 2);
    		world.setBlockToAir(x, y, z);
    	}
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
    	super.onNeighborBlockChange(world, x, y, z, block);
        dropConeIfCannotStay(world, x, y, z);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) 
    {
    	Block b = world.getBlock(x, y - 1, z);
    	int meta = world.getBlockMetadata(x, y - 1, z);
    	return b != null && b == GenesisTreeBlocks.logs[GenesisTreeBlocks.TreeType.BJUVIA.getGroup()]
    			&& meta == GenesisTreeBlocks.getLogMetadataForDirection(GenesisTreeBlocks.TreeType.BJUVIA.getGroup(),
    			ForgeDirection.UP);
   }
}