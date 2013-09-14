package genesis.genesis.plants.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.genesis.block.Blocks;
import genesis.genesis.common.Genesis;
import genesis.genesis.plants.items.PlantItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BlockCalamitesPlant extends BlockGenesisPlant{

	@SideOnly(Side.CLIENT)
	Icon calamitesPlant;
	@SideOnly(Side.CLIENT)
	Icon eggCalamitesPlant;
	public BlockCalamitesPlant(int par1) {
		super(par1);
		float f = 0.375F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
    {
		if (world.isAirBlock(x, y + 1, z))
        {
            int height;

            for (height = 1; world.getBlockId(x, y - height, z) == this.blockID; ++height)
            {
                ;
            }

            if (height < 10)
            {
                int meta = world.getBlockMetadata(x, y, z);

                if (meta == 4)
                {
                    world.setBlock(x, y + 1, z, this.blockID);
                    world.setBlockMetadataWithNotify(x, y, z, 0, 4);
                    this.onNeighborBlockChange(world, x, y + 1, z, this.blockID);
                }
                else
                {
                    world.setBlockMetadataWithNotify(x, y, z, meta + 1, 4);
                }
            }
            else
            {
            	int meta = world.getBlockMetadata(x, y, z);
            	if(meta != 10)
            	{
            		world.setBlockMetadataWithNotify(x, y, z, 5, 2);
            	}
            }
        }
    }
	
	@Override
	public Icon getIcon(int side, int metadata)
    {
		return metadata < 5 || metadata == 10 ? this.calamitesPlant : this.eggCalamitesPlant;
    }
	
	@Override
	public void registerIcons(IconRegister iconRegister)
    {
		this.calamitesPlant = iconRegister.registerIcon(Genesis.MOD_ID + ":" + this.getTextureName());
		this.eggCalamitesPlant = iconRegister.registerIcon(Genesis.MOD_ID + ":" + this.getTextureName() + "_egg");
    }
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
		return PlantItems.calamites.itemID;
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return PlantItems.calamites.itemID;
    }
    
    @Override
    public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == 5)
        {
            if (world.isRemote)
                return true;

            world.setBlock(x, y, z, blockID, 10, 2);
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Item.egg, 1)));
            return true;
        }

        return false;
    }
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
    	world.scheduleBlockUpdate(x, y, z, this.blockID, 10);
    	return metadata;
    }
}
