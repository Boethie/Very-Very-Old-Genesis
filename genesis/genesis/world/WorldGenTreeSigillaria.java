package genesis.genesis.world;

import genesis.genesis.block.trees.TreeBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeSigillaria extends WorldGenTreeBase
{


    /** Constructor - gets the generator for the correct highlands tree
     * @param lmd leaf meta data
     * @param wmd wood meta data
     * @param wb wood block id
     * @param lb leaf block id
     * @param minH minimum height of tree trunk
     * @param maxH max possible height above minH the tree trunk could grow
     * @param notify whether or not to notify blocks of the tree being grown.
     *  Generally false for world generation, true for saplings.
     */
    public WorldGenTreeSigillaria(int minH, int maxH, boolean notify)
    {
    	super(0, 0, TreeBlocks.blocksLogs[0].blockID, TreeBlocks.blocksLeaves[0].blockID, notify);
        minHeight = minH;
        maxHeight = maxH;
    }

    public boolean generate(World world, Random random, int locX, int locY, int locZ)
    {
    	this.world = world;
    	this.random = random;
    	

    	//finds top block for the given x,z position (excluding leaves and grass)
        for (boolean var6 = false; (world.getBlockId(locX, locY, locZ) == 0 || Block.blocksList[world.getBlockId(locX, locY, locZ)].isLeaves(world, locX, locY, locZ) && locY > 0); --locY);
        //locY is now the highest solid terrain block
        
        if(!(world.getBlockId(locX, locY, locZ) == Block.grass.blockID || world.getBlockId(locX, locY, locZ) == Block.dirt.blockID))return false;
        if(!isCubeClear(locX, locY+2, locZ, 2, 10))return false;
        
    	//generates the trunk
    	locY++;
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	
    	//tree type 1: single trunk
    	if(random.nextInt(2) == 0){
    		for(int i = 0; i < treeHeight; i++){
        		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
        	}
    		int h = locY + treeHeight - 3;
    		generateLeafLayerCircle(world, random, 1, locX, locZ, h);
    		generateLeafLayerCircle(world, random, 1, locX, locZ, h+1);
    		generateLeafLayerCircle(world, random, 1, locX, locZ, h+2);
    		generateLeafLayerCircle(world, random, 1, locX, locZ, h+3);
    		setBlockInWorld(locX, h+4, locZ, this.leavesID, this.leavesMeta);
    	}
    	
    	//tree type 2: split trunk
    	else{
    		for(int i = 0; i < treeHeight-5; i++){
        		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
        	}
        	int h = treeHeight-5;
        	int r = 1;
        	
        	//trunks along x
        	if(random.nextInt(2) == 0){
        		setBlockInWorld(locX+r, locY + h, locZ, this.woodID, this.woodMeta);
        		setBlockInWorld(locX-r, locY + h, locZ, this.woodID, this.woodMeta);
        		r++; h++;
        		setBlockInWorld(locX+r, locY + h, locZ, this.woodID, this.woodMeta);
        		setBlockInWorld(locX-r, locY + h, locZ, this.woodID, this.woodMeta);
        		h++;
        		setBlockInWorld(locX+r, locY + h, locZ, this.woodID, this.woodMeta);
        		setBlockInWorld(locX-r, locY + h, locZ, this.woodID, this.woodMeta);
        		
        		for(int a = 0; a < 2; a++){
	        		int randomHeight = random.nextInt(2);
	        		setBlockInWorld(locX+r, locY + h + randomHeight, locZ, this.woodID, this.woodMeta);
	        		int h2 = locY + treeHeight - 3;
	        		generateLeafLayerCircle(world, random, 1, locX+r, locZ, h2 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX+r, locZ, h2+1 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX+r, locZ, h2+2 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX+r, locZ, h2+3 + randomHeight);
	        		setBlockInWorld(locX+r, h2+4 + randomHeight, locZ, this.leavesID, this.leavesMeta);
	        		r *= -1;
        		}
        	}
        	//trunks along y
        	else{
        		setBlockInWorld(locX, locY + h, locZ+r, this.woodID, this.woodMeta);
        		setBlockInWorld(locX, locY + h, locZ-r, this.woodID, this.woodMeta);
        		r++; h++;
        		setBlockInWorld(locX, locY + h, locZ+r, this.woodID, this.woodMeta);
        		setBlockInWorld(locX, locY + h, locZ-r, this.woodID, this.woodMeta);
        		h++;
        		setBlockInWorld(locX, locY + h, locZ+r, this.woodID, this.woodMeta);
        		setBlockInWorld(locX, locY + h, locZ-r, this.woodID, this.woodMeta);
        		
        		for(int a = 0; a < 2; a++){
	        		int randomHeight = random.nextInt(2);
	        		setBlockInWorld(locX, locY + h + randomHeight, locZ+r, this.woodID, this.woodMeta);
	        		int h2 = locY + treeHeight - 3;
	        		generateLeafLayerCircle(world, random, 1, locX, locZ+r, h2 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX, locZ+r, h2+1 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX, locZ+r, h2+2 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX, locZ+r, h2+3 + randomHeight);
	        		setBlockInWorld(locX, h2+4 + randomHeight, locZ+r, this.leavesID, this.leavesMeta);
	        		r *= -1;
        		}
        	}
    	}
    	
    	
    	return true;
    }
}













