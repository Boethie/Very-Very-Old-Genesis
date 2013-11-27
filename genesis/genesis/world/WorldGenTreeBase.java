package genesis.genesis.world;

import genesis.genesis.block.trees.BlockGenesisSapling;
import genesis.genesis.block.trees.TreeBlocks;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

public abstract class WorldGenTreeBase extends WorldGenerator
{
	protected int woodID;
	protected int leavesID;
    protected int woodMeta;
    protected int leavesMeta;
    protected int type;
    
    protected int minHeight;
    protected int maxHeight;
    
    protected boolean notifyFlag;
    
    protected World world;
    protected Random random;
    
    //this array is the 8 directions of x and y, used for palm trees.
    protected int[][]directions = {{1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}};

    /** Constructor - sets up tree variables
     * @param lmd leaf meta data
     * @param wmd wood meta data
     * @param wb wood block id
     * @param lb leaf block id
     * @param minH minimum height of tree trunk
     * @param maxH max possible height above minH the tree trunk could grow
     * @param notify whether or not to notify blocks of the tree being grown.
     *  Generally false for world generation, true for saplings.
     */
    public WorldGenTreeBase(int lmd, int wmd, int wb, int lb, boolean notify)
    {
        this.woodID = wb;
        this.leavesID = lb;
        this.woodMeta = wmd;
        this.leavesMeta = lmd;
        this.notifyFlag = notify;
    }

    public abstract boolean generate(World world, Random random, int locX, int locY, int locZ);

    
	//UTILITY GENERATORS - LEAVES, BRANCHES, TRUNKS
    
    //generates a circular disk of leaves around a coordinate block, only overwriting air blocks.
    protected void generateLeafLayerCircle(World world, Random random, double radius, int xo, int zo, int h){
    	for(int x = (int)Math.ceil(xo - radius); x <= (int)Math.ceil(xo + radius); x++){
			for(int z = (int)Math.ceil(zo - radius); z <= (int)Math.ceil(zo + radius); z++){
				double xfr = z - zo;
				double zfr = x- xo;
				
				if(xfr * xfr + zfr * zfr <= radius * radius){
					setBlockInWorld(x, h, z, this.leavesID, this.leavesMeta);
				}
			}
		}
    }
    
    //generates a circular disk of leaves around a coordinate block, only overwriting air blocks.
    //noise means the outer block has a 50% chance of generating
    protected void generateLeafLayerCircleNoise(World world, Random random, double radius, int xo, int zo, int h){
    	for(int x = (int)Math.ceil(xo - radius); x <= (int)Math.ceil(xo + radius); x++){
			for(int z = (int)Math.ceil(zo - radius); z <= (int)Math.ceil(zo + radius); z++){
				double xfr = z - zo;
				double zfr = x- xo;
				
				if(xfr * xfr + zfr * zfr <= radius * radius){
					if(xfr * xfr + zfr * zfr <= (radius - 1) * (radius - 1) || random.nextInt(2) == 0){
						setBlockInWorld(x, h, z, this.leavesID, this.leavesMeta);
					}
				}
			}
		}
    }
    
    //generates a circular disk of wood around a coordinate block, only overwriting air and leaf blocks.
    protected void generateWoodLayerCircle(World world, Random random, double radius, int xo, int zo, int h){
    	for(int x = (int)Math.ceil(xo - radius); x <= (int)Math.ceil(xo + radius); x++){
			for(int z = (int)Math.ceil(zo - radius); z <= (int)Math.ceil(zo + radius); z++){
				double xfr = z - zo;
				double zfr = x- xo;
				
				if(xfr * xfr + zfr * zfr <= radius * radius){
					setBlockInWorld(x, h, z, this.woodID, this.woodMeta);
				}
			}
		}
    }
    
    //generate a branch, can be any direction
    //startHeight is absolute, not relative to the tree.
    //dir = direction: 0 = north (+z) 1 = east (+x) 2 = south 3 = west
    protected int[] generateStraightBranch(World world, Random random, int length, int locX, int locY, int locZ, int dir){
    	int direction = -1;
    	if(dir < 2)
    		 direction = 1;
    	if(dir % 2 == 0){
    		//generates branch
    		for(int i = 1; i <= length; i++){
	    		setBlockInWorld(locX + i*direction, locY+i, locZ, this.woodID,
	    				TreeBlocks.getLogMetadataForDirection(this.woodMeta, ForgeDirection.NORTH));
    		}
    		return new int[]{locX+length*direction, locY+length, locZ};
    	}
    	else{
    		for(int i = 1; i <= length; i++){
	    		setBlockInWorld(locX, locY+i, locZ + i*direction, this.woodID,
	    				TreeBlocks.getLogMetadataForDirection(this.woodMeta, ForgeDirection.EAST));
    		}
    		return new int[]{locX, locY+length, locZ+length*direction};
    	}
    }
    
    //same as GenerateStraightBranch but downward (for Arau tree)
    protected int[] generateStraightBranchDown(World world, Random random, int length, int locX, int locY, int locZ, int dir){
    	int direction = -1;
    	if(dir < 2)
    		 direction = 1;
    	if(dir % 2 == 0){
    		//generates branch
    		for(int i = 1; i <= length; i++){
	    		setBlockInWorld(locX + i*direction, locY-i, locZ, this.woodID,
	    				TreeBlocks.getLogMetadataForDirection(this.woodMeta, ForgeDirection.NORTH));
    		}
    		return new int[]{locX+length*direction, locY-length, locZ};
    	}
    	else{
    		for(int i = 1; i <= length; i++){
	    		setBlockInWorld(locX, locY-i, locZ + i*direction, this.woodID,
	    				TreeBlocks.getLogMetadataForDirection(this.woodMeta, ForgeDirection.EAST));
    		}
    		return new int[]{locX, locY-length, locZ+length*direction};
    	}
    }
    
    
    protected void setBlockInWorld(int x, int y, int z, int id, int meta){
    	try{
			if(id == this.woodID && (world.isAirBlock(x,y,z) || Block.blocksList[world.getBlockId(x, y, z)].blockMaterial.isReplaceable() ||
					Block.blocksList[world.getBlockId(x, y, z)].isLeaves(world, x, y, z))){
				if(notifyFlag) world.setBlock(x, y, z, id, meta, 3);
		    	else world.setBlock(x, y, z, id, meta, 2);
			}
			else if(id == this.leavesID && world.isAirBlock(x,y,z)){
				if(notifyFlag) world.setBlock(x, y, z, id, meta, 3);
		    	else world.setBlock(x, y, z, id, meta, 2);
			}
    	}
    	catch(RuntimeException e){
    		if(e.getMessage().equals("Already decorating!!")){
    			System.out.println("Error: Tree block couldn't generate!");
    		}
    		//e.printStackTrace();
    	}
    }
    
    
    
    protected boolean isCubeClear (int x, int y, int z, int radius, int height){
    	for(int i = x-radius; i <= x+radius; i++){
    		for(int j = z-radius; j <= z+radius; j++){
    			for(int k = y; k <= y+height; k++){
    				//System.out.println(world.getBlockId(i, k, j));
    				//System.out.println(Block.blocksList[world.getBlockId(i, k, j)].isLeaves(world, i, j, k));
    				if(!(world.getBlockId(i, k, j) == 0 || Block.blocksList[world.getBlockId(i, k, j)].isLeaves(world, i, k, j)))return false;
    			}
    		}
    	}
    	//System.out.println("end");
    	return true;
    }
    
    //finds top block for the given x,z position (excluding leaves)
    protected int findTopBlock(int x, int z){
    	int y = 256;
        for (boolean var6 = false; (world.getBlockId(x, y, z) == 0 || Block.blocksList[world.getBlockId(x, y, z)].isLeaves(world, x, y, z)) && y > 0; --y);
        return y;
    }
}













