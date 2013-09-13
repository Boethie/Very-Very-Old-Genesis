package genesis.genesis.block;

import genesis.genesis.common.Genesis;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;

public class BlockGenesis extends Block {

	public BlockGenesis(int blockID, Material material) {
		super(blockID, material);
		
        setCreativeTab(Genesis.tabGenesis);
	}
	
	@Override
	public Block setUnlocalizedName(String unlocName)
	{
		GameRegistry.registerBlock(this, ItemBlock.class, unlocName);
		
		return super.setUnlocalizedName(unlocName);
	}
	
    public int idDropped(int metdata, Random random, int fortune)
    {
        return blockID;
    }
	
	public void registerIcons(IconRegister iconRegister)
    {
		this.blockIcon = iconRegister.registerIcon(Genesis.modid + ":" + getTextureName());
    }
	
}
