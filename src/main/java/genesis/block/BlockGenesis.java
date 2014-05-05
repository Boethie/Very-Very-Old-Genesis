package genesis.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.common.Genesis;
import genesis.item.ModItems;

public class BlockGenesis extends Block {
	protected Drop drop;
	
	public BlockGenesis(Material material) {
		super(material);
        setCreativeTab(Genesis.tabGenesis);
	}
	
	public void registerBlock(String name) {
		GameRegistry.registerBlock(this, ItemBlock.class, name);
	}
	
	@Override
	public Block setBlockName(String name) {
		registerBlock(name);
		return super.setBlockName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName());
    }
	
	//block drops
	public BlockGenesis setDrop(ItemStack drop) {
		this.drop = new Drop(drop);

		return this;
	}
	
	public BlockGenesis setDrop(ItemStack drop, int rangeStart, int rangeEnd) {
		this.drop = new Drop(drop, rangeStart, rangeEnd);

		return this;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		
		int count = drop == null ? 1 : world.rand.nextInt(drop.getRangeEnd() - drop.getRangeStart() + 1) + drop.getRangeStart();
		for (int i = 0; i < count; i++){
			ret.add(drop == null ? new ItemStack(this, 1, metadata) : drop.getDrop().copy());
		}
		
		return ret;
	}
	
	private class Drop{
		private final ItemStack drop;
		private final int rangeStart, rangeEnd;
		
		public Drop(ItemStack drop){
			this(drop, 1, 1);
		}
		
		public Drop(ItemStack drop, int rangeStart, int rangeEnd){
			this.drop = drop;
			this.rangeStart = rangeStart;
			this.rangeEnd = rangeEnd;
		}
		
		public ItemStack getDrop() {
			return drop;
		}
		
		public int getRangeStart() {
			return rangeStart;
		}
		
		public int getRangeEnd() {
			return rangeEnd;
		}
	}
}
