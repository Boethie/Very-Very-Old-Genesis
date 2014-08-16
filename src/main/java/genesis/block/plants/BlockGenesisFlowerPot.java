package genesis.block.plants;

import genesis.block.BlockAndMeta;
import genesis.client.renderer.BlockGenesisFlowerPotRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class BlockGenesisFlowerPot extends BlockFlowerPot {

    public static final HashSet<BlockFlowerPot> potBlocks = new HashSet();
    private final HashMap<ItemStack, Integer> metadataMap = new HashMap();
    private final HashMap<Integer, BlockAndMeta> plantBlockCache = new HashMap();
    private int currentID = 1;

    public BlockGenesisFlowerPot() {
        super();

        if (potBlocks.isEmpty())
            potBlocks.add((BlockFlowerPot) Blocks.flower_pot);

        potBlocks.add(this);
    }

    public static boolean tryRegisterPlant(ItemStack plantStack) {
        if (Block.getBlockFromItem(plantStack.getItem()) instanceof IPlantInFlowerPot)
            for (BlockFlowerPot potBlock : potBlocks)
                if (potBlock instanceof BlockGenesisFlowerPot)
                    if (((BlockGenesisFlowerPot) potBlock).registerPlant(plantStack))
                        return true;

        return false;
    }

    @Override
    public int getRenderType() {
        return BlockGenesisFlowerPotRenderer.renderID;
    }

    public boolean registerPlant(ItemStack plantStack) {
        if (metadataMap.size() < 14) {
            metadataMap.put(plantStack, currentID);
            currentID++;
            return true;
        }

        return false;
    }

    public int getPlantMetadata(ItemStack stack) {
        for (Entry<ItemStack, Integer> entry : metadataMap.entrySet()) {
            ItemStack keyStack = entry.getKey();

            if (keyStack.getItem() == stack.getItem() && keyStack.getItemDamage() == stack.getItemDamage())
                return entry.getValue();
        }

        return -1;
    }

    public ItemStack getPlantStack(int metadata) {
        for (Entry<ItemStack, Integer> entry : metadataMap.entrySet())
            if (entry.getValue().intValue() == metadata)
                return entry.getKey();

        return null;
    }

    public BlockAndMeta getPlantBlock(int metadata) {
        BlockAndMeta blockAndMeta = plantBlockCache.get(metadata);

        if (blockAndMeta == null) {
            ItemStack stack = getPlantStack(metadata);

            if (stack != null) {
                ItemBlock itemBlock = (ItemBlock) stack.getItem();

                int plantMetadata = itemBlock.getMetadata(stack.getItemDamage());

                blockAndMeta = new BlockAndMeta(Block.getBlockFromItem(stack.getItem()), plantMetadata);
                plantBlockCache.put(metadata, blockAndMeta);
            }
        }

        return blockAndMeta;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.getBlockMetadata(x, y, z) == 0) {
            ItemStack stack = player.getHeldItem();

            if (stack != null) {
                int metadata = getPlantMetadata(stack);

                if (metadata > 0) {
                    world.setBlockMetadataWithNotify(x, y, z, metadata, 2);

                    if (!player.capabilities.isCreativeMode && --stack.stackSize <= 0)
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

                    return true;
                }
            }

            int oldMetadata = world.getBlockMetadata(x, y, z);
            Block worldBlock = world.getBlock(x, y, z);

            for (BlockFlowerPot pot : potBlocks)
                if (pot != this && pot != worldBlock && pot.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ)) {
                    world.setBlock(x, y, z, pot, world.getBlockMetadata(x, y, z), 2);
                    return true;
                }

            world.setBlock(x, y, z, this, oldMetadata, 2);
        }

        return false;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        ItemStack stack = getPlantStack(world.getBlockMetadata(x, y, z));

        return stack == null ? new ItemStack(Items.flower_pot) : stack;
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z) {
        ItemStack stack = getPlantStack(world.getBlockMetadata(x, y, z));

        return stack == null ? 0 : stack.getItemDamage();
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune) {
        if (!world.isRemote) {
            ArrayList<ItemStack> items = getDrops(world, x, y, z, metadata, fortune);
            chance = ForgeEventFactory.fireBlockHarvesting(items, world, this, x, y, z, metadata, fortune, chance, false, null);

            for (ItemStack item : items)
                if (world.rand.nextFloat() <= chance)
                    dropBlockAsItem(world, x, y, z, item);

            ItemStack stack = getPlantStack(metadata);

            if (stack != null)
                dropBlockAsItem(world, x, y, z, stack);
        }
    }

}
