package genesis.genesis.block;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.BlocksHelper;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.common.MinecraftForge;

public class BlockGenesisRock extends BlockGenesis {

	public BlockGenesisRock(int blockID, Material mat, int harvestLevel) {
		super(blockID, mat);
		
        setStepSound(soundStoneFootstep);
        
        MinecraftForge.setBlockHarvestLevel(this, "pickaxe", harvestLevel);
	}
	
}
