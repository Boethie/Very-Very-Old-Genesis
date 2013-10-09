package genesis.genesis.world;

import genesis.genesis.block.trees.BlockGenesisSapling;
import genesis.genesis.block.trees.TreeBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

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
        
        Block soil = Block.blocksList[world.getBlockId(locX, locY, locZ)];
        if(soil == null || !soil.canSustainPlant(world, locX, locY, locZ, ForgeDirection.UP, 
				(BlockGenesisSapling)TreeBlocks.blocksSaplings[0]))return false;
        if(!isCubeClear(locX, locY+2, locZ, 2, 10))return false;
        
    	//generates the trunk
    	locY++;
    	int treeHeight = minHeight + random.nextInt(maxHeight);
    	
    	type = random.nextInt(3);
    	//tree type 1: single trunk
    	if(type == 0){
    		for(int i = 0; i < treeHeight; i++){
        		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
        	}
    		int currentHeight = locY + treeHeight - 3;
    		generateLeafLayerCircle(world, random, 1, locX, locZ, currentHeight);
    		generateLeafLayerCircle(world, random, 1, locX, locZ, currentHeight+1);
    		generateLeafLayerCircle(world, random, 1, locX, locZ, currentHeight+2);
    		generateLeafLayerCircle(world, random, 1, locX, locZ, currentHeight+3);
    		setBlockInWorld(locX, currentHeight+4, locZ, this.leavesID, this.leavesMeta);
    	}
    	
    	//tree type 2: split trunk
    	else if(type == 1){
    		for(int i = 0; i < treeHeight-5; i++){
        		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
        	}
        	int currentHeight = treeHeight-5;
        	int radius = 1;
        	
        	//trunks along x
        	if(random.nextInt(2) == 0){
        		setBlockInWorld(locX+radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
        		setBlockInWorld(locX-radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
        		radius++; currentHeight++;
        		setBlockInWorld(locX+radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
        		setBlockInWorld(locX-radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
        		currentHeight++;
        		setBlockInWorld(locX+radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
        		setBlockInWorld(locX-radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
        		
        		for(int a = 0; a < 2; a++){
	        		int randomHeight = random.nextInt(2);
	        		setBlockInWorld(locX+radius, locY + currentHeight + randomHeight, locZ, this.woodID, this.woodMeta);
	        		int h2 = locY + treeHeight - 3;
	        		generateLeafLayerCircle(world, random, 1, locX+radius, locZ, h2 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX+radius, locZ, h2+1 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX+radius, locZ, h2+2 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX+radius, locZ, h2+3 + randomHeight);
	        		setBlockInWorld(locX+radius, h2+4 + randomHeight, locZ, this.leavesID, this.leavesMeta);
	        		radius *= -1;
        		}
        	}
        	//trunks along y
        	else{
        		setBlockInWorld(locX, locY + currentHeight, locZ+radius, this.woodID, this.woodMeta);
        		setBlockInWorld(locX, locY + currentHeight, locZ-radius, this.woodID, this.woodMeta);
        		radius++; currentHeight++;
        		setBlockInWorld(locX, locY + currentHeight, locZ+radius, this.woodID, this.woodMeta);
        		setBlockInWorld(locX, locY + currentHeight, locZ-radius, this.woodID, this.woodMeta);
        		currentHeight++;
        		setBlockInWorld(locX, locY + currentHeight, locZ+radius, this.woodID, this.woodMeta);
        		setBlockInWorld(locX, locY + currentHeight, locZ-radius, this.woodID, this.woodMeta);
        		
        		for(int a = 0; a < 2; a++){
	        		int randomHeight = random.nextInt(2);
	        		setBlockInWorld(locX, locY + currentHeight + randomHeight, locZ+radius, this.woodID, this.woodMeta);
	        		int h2 = locY + treeHeight - 3;
	        		generateLeafLayerCircle(world, random, 1, locX, locZ+radius, h2 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX, locZ+radius, h2+1 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX, locZ+radius, h2+2 + randomHeight);
	        		generateLeafLayerCircle(world, random, 1, locX, locZ+radius, h2+3 + randomHeight);
	        		setBlockInWorld(locX, h2+4 + randomHeight, locZ+radius, this.leavesID, this.leavesMeta);
	        		radius *= -1;
        		}
        	}
    	}
    	//tree type 3: 4 trunks
    	else{
    		for(int i = 0; i < treeHeight-5; i++){
        		setBlockInWorld(locX, locY + i, locZ, this.woodID, this.woodMeta);
        	}
        	int currentHeight = treeHeight-5;
        	int radius = 1;
        	
    		setBlockInWorld(locX+radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
    		setBlockInWorld(locX-radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
    		setBlockInWorld(locX, locY + currentHeight, locZ+radius, this.woodID, this.woodMeta);
    		setBlockInWorld(locX, locY + currentHeight, locZ-radius, this.woodID, this.woodMeta);
    		radius++; currentHeight++;
    		setBlockInWorld(locX+radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
    		setBlockInWorld(locX-radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
    		setBlockInWorld(locX, locY + currentHeight, locZ+radius, this.woodID, this.woodMeta);
    		setBlockInWorld(locX, locY + currentHeight, locZ-radius, this.woodID, this.woodMeta);
    		currentHeight++;
    		setBlockInWorld(locX+radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
    		setBlockInWorld(locX-radius, locY + currentHeight, locZ, this.woodID, this.woodMeta);
    		setBlockInWorld(locX, locY + currentHeight, locZ+radius, this.woodID, this.woodMeta);
    		setBlockInWorld(locX, locY + currentHeight, locZ-radius, this.woodID, this.woodMeta);
    		
    		for(int a = 0; a < 2; a++){
        		int randomHeight = random.nextInt(2);
        		setBlockInWorld(locX+radius, locY + currentHeight + randomHeight, locZ, this.woodID, this.woodMeta);
        		int h2 = locY + treeHeight - 3;
        		generateLeafLayerCircle(world, random, 1, locX+radius, locZ, h2 + randomHeight);
        		generateLeafLayerCircle(world, random, 1, locX+radius, locZ, h2+1 + randomHeight);
        		generateLeafLayerCircle(world, random, 1, locX+radius, locZ, h2+2 + randomHeight);
        		generateLeafLayerCircle(world, random, 1, locX+radius, locZ, h2+3 + randomHeight);
        		setBlockInWorld(locX+radius, h2+4 + randomHeight, locZ, this.leavesID, this.leavesMeta);
        		radius *= -1;
    		}
    		for(int a = 0; a < 2; a++){
        		int randomHeight = random.nextInt(2);
        		setBlockInWorld(locX, locY + currentHeight + randomHeight, locZ+radius, this.woodID, this.woodMeta);
        		int h2 = locY + treeHeight - 3;
        		generateLeafLayerCircle(world, random, 1, locX, locZ+radius, h2 + randomHeight);
        		generateLeafLayerCircle(world, random, 1, locX, locZ+radius, h2+1 + randomHeight);
        		generateLeafLayerCircle(world, random, 1, locX, locZ+radius, h2+2 + randomHeight);
        		generateLeafLayerCircle(world, random, 1, locX, locZ+radius, h2+3 + randomHeight);
        		setBlockInWorld(locX, h2+4 + randomHeight, locZ+radius, this.leavesID, this.leavesMeta);
        		radius *= -1;
    		}
    	}
    	
    	return true;
    }
}













