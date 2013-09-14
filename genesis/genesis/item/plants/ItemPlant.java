package genesis.genesis.item.plants;

import genesis.genesis.common.Genesis;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPlant extends Item{

	int blockPlantId = 0;
	public ItemPlant(int par1, Block block) {
		super(par1);
		this.blockPlantId = block.blockID;
		this.setCreativeTab(Genesis.tabGenesis);
	}
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(Genesis.MOD_ID + ":" + iconString);
    }
	 public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float par8, float par9, float par10)
	    {
	        int id = world.getBlockId(x, y, z);

	        if (id == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1)
	        {
	            side = 1;
	        }
	        else if (id != Block.vine.blockID && id != Block.tallGrass.blockID && id != Block.deadBush.blockID)
	        {
	            if (side == 0)
	            {
	                --y;
	            }

	            if (side == 1)
	            {
	                ++y;
	            }

	            if (side == 2)
	            {
	                --z;
	            }

	            if (side == 3)
	            {
	                ++z;
	            }

	            if (side == 4)
	            {
	                --x;
	            }

	            if (side == 5)
	            {
	                ++x;
	            }
	        }

	        if (!entityPlayer.canPlayerEdit(x, y, z, side, itemStack))
	        {
	            return false;
	        }
	        else if (itemStack.stackSize == 0)
	        {
	            return false;
	        }
	        else
	        {
	            if (world.canPlaceEntityOnSide(this.blockPlantId, x, y, z, false, side, (Entity)null, itemStack))
	            {
	                Block block = Block.blocksList[this.blockPlantId];
	                int j1 = block.onBlockPlaced(world, x, y, z, side, par8, par9, par10, 0);

	                if (world.setBlock(x, y, z, this.blockPlantId, j1, 3))
	                {
	                    if (world.getBlockId(x, y, z) == this.blockPlantId)
	                    {
	                        Block.blocksList[this.blockPlantId].onBlockPlacedBy(world, x, y, z, entityPlayer, itemStack);
	                        Block.blocksList[this.blockPlantId].onPostBlockPlaced(world, x, y, z, j1);
	                    }

	                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), 
	                    		(double)((float)z + 0.5F), block.stepSound.getPlaceSound(),
	                    		(block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
	                    --itemStack.stackSize;
	                }
	            }

	            return true;
	        }
	    }

}
