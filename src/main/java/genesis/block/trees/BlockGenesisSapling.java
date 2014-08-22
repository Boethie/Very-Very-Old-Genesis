package genesis.block.trees;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.block.GenesisModBlocks;
import genesis.block.plants.IPlantInFlowerPot;
import genesis.block.trees.GenesisTreeBlocks.TreeType;
import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import genesis.item.itemblock.IItemBlockWithSubNames;
import genesis.world.gen.feature.WorldGenTreeBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.List;
import java.util.Random;

public class BlockGenesisSapling extends BlockSapling implements IPlantInFlowerPot, IItemBlockWithSubNames {

    protected String[] blockNames;
    protected IIcon[] blockIcons;

    public BlockGenesisSapling(int group) {
        if (TreeType.values().length - (group * TreeType.GROUP_SIZE) >= TreeType.GROUP_SIZE)
            blockNames = new String[TreeType.GROUP_SIZE];
        else
            blockNames = new String[TreeType.values().length - (group * TreeType.GROUP_SIZE)];

        for (int i = 0; i < blockNames.length; i++)
            blockNames[i] = TreeType.values()[(group * TreeType.GROUP_SIZE) + i].getName();

        blockIcons = new IIcon[blockNames.length];

        setCreativeTab(GenesisTabs.tabGenesisDecoration);
        setStepSound(soundTypeGrass);
        setHardness(0.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        for (int i = 0; i < blockIcons.length; i++)
            blockIcons[i] = iconRegister.registerIcon(Genesis.ASSETS + "sapling_" + blockNames[i]);
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return blockIcons[metadata & 3];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for (int metadata = 0; metadata < blockNames.length; metadata++)
            list.add(new ItemStack(item, 1, metadata));
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int unused) {
        return Item.getItemFromBlock(GenesisTreeBlocks.saplings[TreeType.valueOf(getSubName(metadata).toUpperCase()).getGroup()]);
    }

    @Override
    // public void growTree(World world, int x, int y, int z, Random random) {
    public void func_149878_d(World world, int x, int y, int z, Random random) {
        if (!TerrainGen.saplingGrowTree(world, random, x, y, z))
            return;

        int metadata = world.getBlockMetadata(x, y, z) & 3;
        WorldGenTreeBase gen = GenesisTreeBlocks.getTreeGenerator(blockNames[metadata]);

        if (gen == null)
            return;

        world.setBlock(x, y, z, Blocks.air, 0, 4);

        if (!gen.generate(world, random, x, y, z))
            world.setBlock(x, y, z, this, metadata, 4);
    }

	/* IPlantInFlowerPot methods */

    @Override
    public float renderScale(IBlockAccess world, int x, int y, int z) {
        return 0.75F;
    }

    @Override
    public int getRenderColor(IBlockAccess world, int x, int y, int z) {
        return 16777215;
    }

    @Override
    public IIcon getIconForFlowerPot(IBlockAccess world, int x, int y, int z, int plantMetadata) {
        // return getIcon(0, world.getBlockMetadata(x, y, z);
        return null;
    }

    protected boolean canPlaceBlockOn(Block block) {
        return super.canPlaceBlockOn(block) || block == GenesisModBlocks.moss;
    }

    @Override
    public Block getBlockForRender(IBlockAccess world, int x, int y, int z) {
        return null;
    }

	/* IItemBlockWithSubNames methods */

    @Override
    public String getSubName(int metadata) {
        return blockNames[metadata & 3];
    }
}
