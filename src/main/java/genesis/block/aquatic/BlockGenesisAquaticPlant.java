package genesis.block.aquatic;

import genesis.managers.GenesisModBlocks;
import genesis.block.plants.IPlantRenderSpecials;
import genesis.client.renderer.block.BlockGenesisPlantRenderer;
import genesis.Genesis;
import genesis.lib.GenesisTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Arbiter
 */
public abstract class BlockGenesisAquaticPlant extends Block implements IPlantRenderSpecials {
    public BlockGenesisAquaticPlant(Material material) {
        super(material);
        setResistance(0.0F);
        setHardness(0.6F);
        setStepSound(soundTypeCloth);
        setCreativeTab(GenesisTabs.tabGenesisDecoration);
    }

    @Override
    public int getRenderType() {
        return BlockGenesisPlantRenderer.renderID;
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
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        boolean flag = false;
        Block[] blocks = new Block[]{world.getBlock(x + 1, y, z), world.getBlock(x - 1, y, z), world.getBlock(x, y + 1, z), world.getBlock(x, y, z + 1), world.getBlock(x, y, z - 1)};
        int index = 0, waterCount = 0;
        for (Block b : blocks) {
            if (index == 2) {
                continue;
            } else if (b == null) {
                flag = false;
                break;
            } else if (b == Blocks.water) {
                waterCount++;
            } else if (b instanceof BlockGenesisAquaticPlant) {
                waterCount++;
            }
            index++;
        }
        if (waterCount >= 1 && ((blocks[2] == Blocks.water) || (blocks[2] instanceof BlockCharniaTop))) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return canBlockStay(world, x, y, z);
    }

    protected boolean canPlaceBlockOn(Block block) {
        boolean flag = false;
        final Block[] blockList = new Block[]{Blocks.dirt, Blocks.gravel, Blocks.sand, Blocks.clay, GenesisAquaticBlocks.coral, GenesisModBlocks.granite, Blocks.stone, GenesisModBlocks.limestone, GenesisModBlocks.gneiss, GenesisModBlocks.quartzite, GenesisModBlocks.rhyolite, GenesisModBlocks.shale};
        for (Block b : blockList) {
            if (block == b) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return canPlaceBlockOn(world.getBlock(x, y - 1, z)) && canSustainPlant(world, x, y, z, ForgeDirection.DOWN, null);
    }

    protected void dropPlantIfCannotStay(World world, int x, int y, int z) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 2);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        if (!canBlockStay(world, x, y, z)) {
            dropPlantIfCannotStay(world, x, y, z);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        if (!canBlockStay(world, x, y, z)) {
            dropPlantIfCannotStay(world, x, y, z);
        }
    }

    @Override
    public double randomPos(IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return 0.4;
    }

    @Override
    public double randomYPos(IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return 0.2;
    }

    @Override
    public boolean shouldReverseTex(IBlockAccess world, int x, int y, int z, int side) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Genesis.ASSETS + textureName);
    }
}