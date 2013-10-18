package genesis.genesis.hooks;

import genesis.genesis.block.plants.BlockGenesisFlowerPot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class FlowerPotHooks {
	
	public static boolean checkSwitchFlowerPotBlock(World world, int x, int y, int z, EntityPlayer player,
			int side, float hitX, float hitY, float hitZ)
	{
        int oldMetadata = world.getBlockMetadata(x, y, z);
        
        int worldBlockID = world.getBlockId(x, y, z);
        
        for (BlockFlowerPot pot : BlockGenesisFlowerPot.potBlocks)
        {
	        if (pot != Block.flowerPot && pot.blockID != worldBlockID &&
	        		pot.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ))
	        {
	        	world.setBlock(x, y, z, pot.blockID, world.getBlockMetadata(x, y, z), 2);
	        	return true;
	        }
        }
        
        world.setBlock(x, y, z, Block.flowerPot.blockID, oldMetadata, 2);
	    
	    return false;
	}
}
