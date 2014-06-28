package genesis.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import genesis.block.BlockAndMeta;
import genesis.block.trees.BlockGenesisSapling;
import genesis.block.trees.TreeBlocks;

public class WorldGenTreeCordaites extends WorldGenTreeBase {

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
	public WorldGenTreeCordaites(int minH, int maxH, boolean notify) {
		super(new BlockAndMeta(TreeBlocks.blocksLogs[0], 2), new BlockAndMeta(TreeBlocks.blocksLeaves[0], 2), notify);
		minHeight = minH;
		maxHeight = maxH;
	}

	@Override
	public boolean generate(World world, Random random, int locX, int locY, int locZ) {
		this.world = world;
		this.random = random;

		// finds top block for the given x,z position (excluding leaves and
		// grass)
		for (boolean var6 = false; world.getBlock(locX, locY, locZ) == Blocks.air || world.getBlock(locX, locY, locZ).getMaterial() == Material.water || world.getBlock(locX, locY, locZ).isLeaves(world, locX, locY, locZ) && locY > 0; --locY);
		// locY is now the highest solid terrain block

		Block soil = world.getBlock(locX, locY, locZ);
		if (soil == null || !soil.canSustainPlant(world, locX, locY, locZ, ForgeDirection.UP, (BlockGenesisSapling) TreeBlocks.blocksSaplings[0]))
			return false;
		if (!isCubeClearWater(locX, locY + 2, locZ, 1, 15))
			return false;

		// generates the trunk
		locY++;
		int treeHeight = minHeight + random.nextInt(maxHeight);
		for (int i = 2; i < treeHeight; i++)
			setBlockInWorld(locX, locY + i, locZ, wood.block, wood.metadata);

		// generate roots
		generateCordiatesRoots(world, random, locX, locY + 2, locZ);

		// generates leaves at top
		int currentHeight;
		
		for (currentHeight = locY + treeHeight - 3; currentHeight < locY + treeHeight; currentHeight++)
			generateLeafLayerCircleNoise(world, random, 3, locX + random.nextInt(2), locZ + random.nextInt(2), currentHeight);
		
		generateLeafLayerCircleNoise(world, random, 2.8, locX + random.nextInt(2), locZ + random.nextInt(2), currentHeight);
		currentHeight++;
		generateLeafLayerCircleNoise(world, random, 1.5, locX + random.nextInt(2), locZ + random.nextInt(2), currentHeight);
		currentHeight++;
		generateLeafLayerCircleNoise(world, random, 1, locX + random.nextInt(2), locZ + random.nextInt(2), currentHeight);

		// generates branches with leaves
		currentHeight -= 8;
		int firstDir = random.nextInt(4);
		
		for (int i = 0; i < 4; i++) {
			int[] xyz = generateStraightBranch(world, random, 3, locX, currentHeight + i, locZ, (firstDir + i) % 4);
			generateLeafLayerCircleNoise(world, random, 1.5, xyz[0], xyz[2], xyz[1] - 1);
			generateLeafLayerCircleNoise(world, random, 2, xyz[0], xyz[2], xyz[1]);
			generateLeafLayerCircleNoise(world, random, 1.5, xyz[0], xyz[2], xyz[1] + 1);
		}

		return true;
	}

	// Tree helper methods:

	protected void generateCordiatesRoots(World world, Random random, int x, int y, int z) {

		int[] i = { 1, 0, -1, -1, -1, 0, 1, 1 };
		int[] k = { 1, 1, 1, 0, -1, -1, -1, 0 };
		
		for (int a = 0; a < 8; a++) {
			int length = random.nextInt(3);
			
			for (int b = 0; b < length; b++)
				setBlockInWorld(x + i[a] * b, y, z + k[a] * b, wood.block, wood.metadata);
			
			for (int b = 0; b < 4; b++)
				setBlockInWorld(x + i[a] * (b + length), y - b, z + k[a] * (b + length), wood.block, wood.metadata);
		}
	}
}
