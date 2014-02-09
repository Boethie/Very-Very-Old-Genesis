package genesis.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.common.Genesis;

public class BlockGenesis extends Block {

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
	
}
