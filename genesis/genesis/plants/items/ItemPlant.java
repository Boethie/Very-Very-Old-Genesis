package genesis.genesis.plants.items;

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
	 public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	    {
	        int i1 = world.getBlockId(x, y, z);

	        if (i1 == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1)
	        {
	            par7 = 1;
	        }
	        else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID)
	        {
	            if (par7 == 0)
	            {
	                --y;
	            }

	            if (par7 == 1)
	            {
	                ++y;
	            }

	            if (par7 == 2)
	            {
	                --z;
	            }

	            if (par7 == 3)
	            {
	                ++z;
	            }

	            if (par7 == 4)
	            {
	                --x;
	            }

	            if (par7 == 5)
	            {
	                ++x;
	            }
	        }

	        if (!entityPlayer.canPlayerEdit(x, y, z, par7, itemStack))
	        {
	            return false;
	        }
	        else if (itemStack.stackSize == 0)
	        {
	            return false;
	        }
	        else
	        {
	            if (world.canPlaceEntityOnSide(this.blockPlantId, x, y, z, false, par7, (Entity)null, itemStack))
	            {
	                Block block = Block.blocksList[this.blockPlantId];
	                int j1 = block.onBlockPlaced(world, x, y, z, par7, par8, par9, par10, 0);

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
