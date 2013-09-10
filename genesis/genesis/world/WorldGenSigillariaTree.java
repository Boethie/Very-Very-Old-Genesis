package genesis.genesis.world;

import genesis.genesis.block.BlockGenesisSapling;
import genesis.genesis.block.Blocks;
import genesis.genesis.block.TreeBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenSigillariaTree extends WorldGenerator implements IWorldGenerator{

	/** The minimum height of a generated tree. */
	private final int minTreeHeight;
	/** The metadata value of the wood to use in tree generation. */
	private final int metaWood;
	/** The metadata value of the leaves to use in tree generation. */
	private final int metaLeaves;
	public WorldGenSigillariaTree(boolean par1)
	{
		this(par1, 8, 1, 1);
	}
	public WorldGenSigillariaTree(boolean par1, int par2, int par3, int par4)
	{
		super(par1);
		this.minTreeHeight = par2;
		this.metaWood = par3;
		this.metaLeaves = par4;
	}


	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for(int i = 0; i < 10; i++)
		{
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = random.nextInt(16);
			int zCoord = chunkZ + random.nextInt(16);

			//(new WorldGenSigillariaTree(false, 9, 1, 1)).generate(world, random, xCoord, yCoord, zCoord);
		}
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
		int l = par2Random.nextInt(3) + this.minTreeHeight;
		boolean flag = true;
		if (par4 >= 1 && par4 + l + 1 <= 256)
		{
			int i1;
			byte b0;
			int j1;
			int k1;
			for (i1 = par4; i1 <= par4 + 1 + l; ++i1)
			{
				b0 = 1;
				if (i1 == par4)
				{
					b0 = 0;
				}
				
				if (i1 >= par4 + 1 + l - 2)
				{
					b0 = 2;
				}
				for (int l1 = par3 - b0; l1 <= par3 + b0 && flag; ++l1)
				{
					for (j1 = par5 - b0; j1 <= par5 + b0 && flag; ++j1)
					{
						if (i1 >= 0 && i1 < 256)
						{
							k1 = par1World.getBlockId(l1, i1 -1, j1);
							Block block = Block.grass;
							if (k1 != 0 && !block.isLeaves(par1World, l1, i1, j1)
							&& k1 != Block.grass.blockID
							&& k1 != Block.dirt.blockID
							&& !block.isWood(par1World, l1, i1, j1))
							{
								flag = false;
							}

						}else
						{
							flag = false;
						}
					}
				}
			}
			if(!flag)
			{
				return false;
			}
			else
			{
				i1 = par1World.getBlockId(par3, par4 - 1, par5);
				Block soil = Block.grass;
				boolean isSoil = (soil != null && soil.canSustainPlant(par1World, par3, par4, par5, ForgeDirection.UP, 
						(BlockGenesisSapling)TreeBlocks.blockSaplingGenesis[1]));
				if(isSoil && par4 < 256 - l - 1)
				{
					soil.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
					b0 = 3;
					byte b1 = 0;
					int i2;
					int j2;
					int k2;
					
					for (j1 = 0; j1 < l; ++j1)
					{
						k1 = par1World.getBlockId(par3, par4 + j1, par5);
						Block block = Block.blocksList[k1];
						if (k1 == 0 || block == null || block.isLeaves(par1World, par3, par4 + j1, par5))
						{
							this.setBlockAndMetadata(par1World, par3, par4 + j1, par5, TreeBlocks.blockLogGenesis[1].blockID, this.metaWood);
						}
					}
					
					for(int i4 = 2; i4 > -3; i4 -= 4)
					{
						for (j1 = par4 + l + 1; j1 <= par4 + l + 5; ++j1)
						{
							for(int k4 = 1; k4 > -3; k4 -= 2)
							{
								int j3 = par1World.getBlockId(par3 - i4, j1, par5 + 1);
								Block block = Block.blocksList[j3];
								if (block == null || block.canBeReplacedByLeaves(par1World, j1, j1, par5 + 1))
								{
									this.setBlockAndMetadata(par1World, par3 - i4, j1, par5 - k4, TreeBlocks.blockLeavesGenesis[1].blockID, this.metaLeaves);
								}
							}
							for(int k4 = 1; k4 > -3; k4 -= 2)
							{
								int j3 = par1World.getBlockId(par3 - i4, j1, par5 + 1);
								Block block = Block.blocksList[j3];
								if (block == null || block.canBeReplacedByLeaves(par1World, j1, j1, par5 + 1))
								{
									this.setBlockAndMetadata(par1World, par3 - i4 - k4, j1, par5, TreeBlocks.blockLeavesGenesis[1].blockID, this.metaLeaves);
								}
							}
							
							
							
							k1 = par1World.getBlockId(par3 + 2, j1, par5);
							Block block = Block.blocksList[k1];
							int id = TreeBlocks.blockLogGenesis[1].blockID;
							if(j1 + 1 > par4 + l + 5)
							{
								id = TreeBlocks.blockLeavesGenesis[1].blockID;
							}
							if (k1 == 0 || block == null || block.isLeaves(par1World, par3, j1, par5))
							{
								this.setBlockAndMetadata(par1World, par3 - i4, j1, par5, id, this.metaWood);
							}
						
						}
						k1 = par1World.getBlockId(par3 + (i4 / 2), j1 - 6, par5);
						Block block = Block.blocksList[k1];
						if (k1 == 0 || block == null || block.isLeaves(par1World, par3, j1 - 6, par5))
						{
							this.setBlockAndMetadata(par1World, par3 + (i4 / 2), j1 - 6, par5, TreeBlocks.blockLogGenesis[1].blockID, this.metaWood);
						}
						int j3 = par1World.getBlockId(par3 - i4, j1, par5);
						block = Block.blocksList[j3];
						if (block == null || block.canBeReplacedByLeaves(par1World, j1, j1, par5))
						{
							this.setBlockAndMetadata(par1World, par3 - i4, j1, par5, TreeBlocks.blockLeavesGenesis[1].blockID, this.metaLeaves);
						}
					}
					
					
					
				}
				return true;
			}
		}
		return flag;
	}
}
