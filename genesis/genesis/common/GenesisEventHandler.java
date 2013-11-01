package genesis.genesis.common;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.BlockProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.genesis.block.Blocks;
import genesis.genesis.block.plants.BlockCalamitesPlant;
import genesis.genesis.block.plants.BlockCalamitesPlant.CalamitesProperties;
import genesis.genesis.block.plants.BlockGenesisFlowerPot;
import genesis.genesis.block.trees.BlockGenesisSapling;
import genesis.genesis.client.ClientTickHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class GenesisEventHandler {
	
	@ForgeSubscribe
	public void bonemealUsed(BonemealEvent event)
	{
		if (!event.world.isRemote)
		{
			int blockID = event.world.getBlockId(event.X, event.Y, event.Z);
			Block block = Block.blocksList[blockID];
			
			if (block instanceof BlockGenesisSapling)
			{
				if (event.world.rand.nextFloat() < 0.45)
				{
					BlockGenesisSapling sapling = (BlockGenesisSapling)block;
					sapling.markOrGrowMarked(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
				
				event.setResult(Result.ALLOW);
			}
		}
	}
	
}
