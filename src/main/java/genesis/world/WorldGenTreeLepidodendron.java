package genesis.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;

import genesis.block.trees.BlockGenesisSapling;
import genesis.block.trees.TreeBlocks;

public class WorldGenTreeLepidodendron extends WorldGenTreeBase
{

    /** Constructor
     * @param minH minimum height of tree trunk
     * @param maxH max possible height above minH the tree trunk could grow
     * @param notify whether or not to notify blocks of the tree being grown.
     *  Generally false for world generation, true for saplings.
     */
    public WorldGenTreeLepidodendron(int minH, int maxH, boolean notify)
    {
    	super(1, 1, TreeBlocks.blocksLogs[0].blockID, TreeBlocks.blocksLeaves[0].blockID, notify);
        minHeight = minH;
        maxHeight = maxH;
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	

    	//finds top block for the given x,z position (excluding leaves and grass)
        for (boolean var6 = false; (world.getBlockId(locX, locY, locZ) == 0 ||
        		Block.blocksList[world.getBlockId(locX, locY, locZ)].isLeaves(world, locX, locY, locZ) && locY > 0); --locY);
        //locY is now the highest solid terrain block
        
        Block soil = Block.blocksList[world.getBlockId(locX, locY, locZ)];
        if(soil == null || !soil.canSustainPlant(world, locX, locY, locZ, ForgeDirection.UP, 
				(BlockGenesisSapling)TreeBlocks.blocksSaplings[0]))return false;
        if(!isCubeClear(locX, locY+2, locZ, 1, 15))return false;
        
    	//generates the trunk
    	locY++;
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	
    	//Generate trunk
		for(int i = 0; i < treeHeight; i++){
    		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
    	}
		//Generate leaves
		int currentHeight = locY + treeHeight - 3;
		generateLeafLayerCircle(world, random, 1, locX, locZ, currentHeight);
		generateLeafLayerCircle(world, random, 3.5, locX, locZ, currentHeight+1);
		generateLeafLayerCircle(world, random, 2.5, locX, locZ, currentHeight+2);
		generateLeafLayerCircle(world, random, 1.5, locX, locZ, currentHeight+3);
		setBlockInWorld(locX, currentHeight+4, locZ, this.leavesID, this.leavesMeta);
    	
    	return true;
    }
}













