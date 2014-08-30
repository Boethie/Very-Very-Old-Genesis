package genesis.block.trees;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.block.trees.GenesisTreeBlocks.TreeType;
import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import genesis.item.GenesisModItems;
import genesis.item.itemblock.IItemBlockWithSubNames;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockGenesisLeaves extends BlockLeaves implements IItemBlockWithSubNames {

    protected String[] blockNames;
    protected IIcon[] blockIcons;

    public BlockGenesisLeaves(int group) {
        if (TreeType.values().length - (group * TreeType.GROUP_SIZE) >= TreeType.GROUP_SIZE) {
            blockNames = new String[TreeType.GROUP_SIZE];
        } else {
            blockNames = new String[TreeType.values().length - (group * TreeType.GROUP_SIZE)];
        }

        for (int i = 0; i < blockNames.length; i++) {
            blockNames[i] = TreeType.values()[(group * TreeType.GROUP_SIZE) + i].getName();
        }

        blockIcons = new IIcon[blockNames.length * 2];

        setCreativeTab(GenesisTabs.tabGenesisDecoration);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        for (int i = 0; i < blockIcons.length; i += 2) {
            blockIcons[i] = iconRegister.registerIcon(Genesis.ASSETS + "leaves_" + blockNames[i / 2]);                        // Fancy graphics texture
            blockIcons[i + 1] = iconRegister.registerIcon(Genesis.ASSETS + "leaves_" + blockNames[i / 2] + "_opaque");        // Fast graphics texture
        }
    }

    @Override
    public IIcon getIcon(int id, int metadata) {
        metadata &= 3;

        if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
            return blockIcons[metadata * 2];
        } else {
            return blockIcons[((metadata + 1) * 2) - 1];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for (int metadata = 0; metadata < blockNames.length; metadata++) {
            list.add(new ItemStack(item, 1, metadata));
        }
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int unused) {
        return Item.getItemFromBlock(GenesisTreeBlocks.saplings[TreeType.valueOf(this, metadata).getGroup()]);
    }

    @Override
    public boolean isOpaqueCube() {
        return Genesis.proxy.areLeavesOpaque();
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        // graphicsLevel = Minecraft.getMinecraft().gameSettings.fancyGraphics;
        field_150121_P = Minecraft.getMinecraft().gameSettings.fancyGraphics;
        return super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }

    @Override
    public String[] func_150125_e() {
        return blockNames;
    }

    @Override
    protected void func_150124_c(World world, int x, int y, int z, int metadata, int chance) {
        TreeType type = TreeType.valueOf(this, world.getBlockMetadata(x, y, z));
        if (type.equals(TreeType.ARAUCARIOXYLON)) {
            if (world.rand.nextInt(50) == 0) {
                dropBlockAsItem(world, x, y, z, new ItemStack(GenesisModItems.araucarioxylon_seeds));
            }
        }
        world.setBlockToAir(x, y, z);
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        //If it will harvest, delay deletion of the block
        return willHarvest || super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    /* IItemBlockWithSubNames methods */

    @Override
    public String getSubName(int metadata) {
        return blockNames[metadata & 3];
    }
}
