package genesis.block.trees;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.client.renderer.BlockBjuviaConeRenderer;
import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import genesis.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author Arbiter
 */
public class BlockBjuviaCone extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon coneIcon;

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
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int meta) {
        return coneIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        coneIcon = register.registerIcon(Genesis.MOD_ID + ":bjuvia_cone");
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
        return meta == 2 ? 3 : 0;
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par3) {
        return ModItems.bjuviaSeeds;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (random.nextInt(10) == 0 && canBlockStay(world, x, y, z)) {
            int newMeta = world.getBlockMetadata(x, y, z) + 1;
            if (newMeta > 2) {
                newMeta = 2;
            }
            world.setBlockMetadataWithNotify(x, y, z, newMeta, 2);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, 0, 2);
            world.setBlockToAir(x, y, z);
        }
    }

	/*@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		Block b = world.getBlock(x, y - 1, z);
		if (b != null)
		{
			return b == TreeBlocks.blocksLogs[0];
		}
		return false;
	}*/
}