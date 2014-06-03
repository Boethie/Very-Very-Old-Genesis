package genesis.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import net.minecraftforge.common.util.ForgeDirection;

import genesis.block.BlockAndMeta;
import genesis.block.trees.BlockGenesisSapling;
import genesis.block.trees.TreeBlocks;

public class WorldGenTreeSigillaria extends WorldGenTreeBase {

	/**
	 * Constructor
	 * 
	 * @param minH
	 *            minimum height of tree trunk
	 * @param maxH
	 *            max possible height above minH the tree trunk could grow
	 * @param notify
	 *            whether or not to notify blocks of the tree being grown.
	 *            Generally false for world generation, true for saplings.
	 */
	public WorldGenTreeSigillaria(int minH, int maxH, boolean notify) {
		super(new BlockAndMeta(TreeBlocks.blocksLogs[0], 0), new BlockAndMeta(TreeBlocks.blocksLeaves[0], 0), notify);
		minHeight = minH;
		maxHeight = maxH;
	}

	@Override
	public boolean generate(World world, Random random, int locX, int locY, int locZ) {
		this.world = world;
		this.random = random;

		// finds top block for the given x,z position (excluding leaves and
		// grass)
		for (boolean var6 = false; world.getBlock(locX, locY, locZ) == Blocks.air || world.getBlock(locX, locY, locZ).isLeaves(world, locX, locY, locZ) && locY > 0; --locY);
		// locY is now the highest solid terrain block

		Block soil = world.getBlock(locX, locY, locZ);
		if (soil == null || !soil.canSustainPlant(world, locX, locY, locZ, ForgeDirection.UP, (BlockGenesisSapling) TreeBlocks.blocksSaplings[0]))
			return false;
		if (!isCubeClear(locX, locY + 2, locZ, 2, 10))
			return false;

		// generates the trunk
		locY++;
		int treeHeight = minHeight + random.nextInt(maxHeight);

		type = random.nextInt(2);
		// tree type 1: single trunk
		if (type == 0) {
			for (int i = 0; i < treeHeight; i++)
				setBlockInWorld(locX, locY + i, locZ, wood.block, wood.metadata);
			int currentHeight = locY + treeHeight - 3;
			generateLeafLayerCircle(world, random, 1, locX, locZ, currentHeight);
			generateLeafLayerCircle(world, random, 1, locX, locZ, currentHeight + 1);
			generateLeafLayerCircle(world, random, 1, locX, locZ, currentHeight + 2);
			generateLeafLayerCircle(world, random, 1, locX, locZ, currentHeight + 3);
			setBlockInWorld(locX, currentHeight + 4, locZ, leaves.block, leaves.metadata);
		}

		// tree type 2: split trunk
		else if (type == 1) {
			for (int i = 0; i < treeHeight - 5; i++)
				setBlockInWorld(locX, locY + i, locZ, wood.block, wood.metadata);
			int currentHeight = treeHeight - 5;
			int radius = 1;

			// trunks along x
			if (random.nextInt(2) == 0) {
				setBlockInWorld(locX + radius, locY + currentHeight, locZ, wood.block, wood.metadata);
				setBlockInWorld(locX - radius, locY + currentHeight, locZ, wood.block, wood.metadata);
				radius++;
				currentHeight++;
				setBlockInWorld(locX + radius, locY + currentHeight, locZ, wood.block, wood.metadata);
				setBlockInWorld(locX - radius, locY + currentHeight, locZ, wood.block, wood.metadata);
				currentHeight++;
				setBlockInWorld(locX + radius, locY + currentHeight, locZ, wood.block, wood.metadata);
				setBlockInWorld(locX - radius, locY + currentHeight, locZ, wood.block, wood.metadata);

				for (int a = 0; a < 2; a++) {
					int randomHeight = random.nextInt(2);
					setBlockInWorld(locX + radius, locY + currentHeight + randomHeight, locZ, wood.block, wood.metadata);
					int h2 = locY + treeHeight - 3;
					generateLeafLayerCircle(world, random, 1, locX + radius, locZ, h2 + randomHeight);
					generateLeafLayerCircle(world, random, 1, locX + radius, locZ, h2 + 1 + randomHeight);
					generateLeafLayerCircle(world, random, 1, locX + radius, locZ, h2 + 2 + randomHeight);
					generateLeafLayerCircle(world, random, 1, locX + radius, locZ, h2 + 3 + randomHeight);
					setBlockInWorld(locX + radius, h2 + 4 + randomHeight, locZ, leaves.block, leaves.metadata);
					radius *= -1;
				}
			}
			// trunks along y
			else {
				setBlockInWorld(locX, locY + currentHeight, locZ + radius, wood.block, wood.metadata);
				setBlockInWorld(locX, locY + currentHeight, locZ - radius, wood.block, wood.metadata);
				radius++;
				currentHeight++;
				setBlockInWorld(locX, locY + currentHeight, locZ + radius, wood.block, wood.metadata);
				setBlockInWorld(locX, locY + currentHeight, locZ - radius, wood.block, wood.metadata);
				currentHeight++;
				setBlockInWorld(locX, locY + currentHeight, locZ + radius, wood.block, wood.metadata);
				setBlockInWorld(locX, locY + currentHeight, locZ - radius, wood.block, wood.metadata);

				for (int a = 0; a < 2; a++) {
					int randomHeight = random.nextInt(2);
					setBlockInWorld(locX, locY + currentHeight + randomHeight, locZ + radius, wood.block, wood.metadata);
					int h2 = locY + treeHeight - 3;
					generateLeafLayerCircle(world, random, 1, locX, locZ + radius, h2 + randomHeight);
					generateLeafLayerCircle(world, random, 1, locX, locZ + radius, h2 + 1 + randomHeight);
					generateLeafLayerCircle(world, random, 1, locX, locZ + radius, h2 + 2 + randomHeight);
					generateLeafLayerCircle(world, random, 1, locX, locZ + radius, h2 + 3 + randomHeight);
					setBlockInWorld(locX, h2 + 4 + randomHeight, locZ + radius, leaves.block, leaves.metadata);
					radius *= -1;
				}
			}
		}
		
		return true;
	}
}
