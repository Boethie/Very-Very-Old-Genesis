package genesis.genesis.block;

import java.util.Random;

import genesis.genesis.client.ClientProxy;
import genesis.genesis.common.Genesis;
import net.minecraft.block.BlockIce;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockFrozenItem extends BlockIce{

	public static Item[] items;
	
	public BlockFrozenItem(int blockID, Item[] item) {
		super(blockID);
		items = item;
		this.setCreativeTab(Genesis.tabGenesis);
	}
	public int getRenderType()
	{
		return BlockFrozenItemRenderer.renderID;
	}
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	public boolean isOpaqueCube()
	{
		return false;
	}
	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}
    
	@Override
	public boolean canRenderInPass(int pass)
	{
		BlockFrozenItemRenderer.renderPass = pass;
		return true;
	}
	
	public void registerIcons(IconRegister iconRegister)
    {
		this.blockIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName());
    }
	
	public void onBlockAdded(World world, int x, int y, int z)
    {
		Random rand = new Random();
		world.setBlockMetadataWithNotify(x, y, z, rand.nextInt(items.length), 2);
    }
	
	public int quantityDropped(Random par1Random)
    {
		return 1;
    }
	
	public int idDropped(int metadata, Random random, int fortune)
	{
		return getItemFromMetadata(metadata).itemID;
	}
	
	public int damageDropped(int metadata)
	{
		return getItemFromMetadata(metadata).itemID;
		
	}
	public static Item getItemFromMetadata(int metadata)
	{
		Item item = items[metadata];
		return item != null ? item : items[0];
	}

}
