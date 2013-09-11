package genesis.genesis.block;

import genesis.genesis.common.Genesis;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockGenesis extends Block {

	public BlockGenesis(int blockID, Material material) {
		super(blockID, material);
		
        setCreativeTab(Genesis.tabGenesis);
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
