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

public class BlockCalamites extends Block implements IPlantable{

	@SideOnly(Side.CLIENT)
	Icon calamitesPlant;
	@SideOnly(Side.CLIENT)
	Icon eggCalamitesPlant;
	public BlockCalamites(int par1) {
		super(par1, Material.plants);
		this.setTickRandomly(true);
		float f = 0.375F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
		if (par1World.isAirBlock(par2, par3 + 1, par4))
        {
            int l;

            for (l = 1; par1World.getBlockId(par2, par3 - l, par4) == this.blockID; ++l)
            {
                ;
            }

            if (l < 10)
            {
                int i1 = par1World.getBlockMetadata(par2, par3, par4);

                if (i1 == 4)
                {
                    par1World.setBlock(par2, par3 + 1, par4, this.blockID);
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 4);
                    this.onNeighborBlockChange(par1World, par2, par3 + 1, par4, this.blockID);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 + 1, 4);
                }
            }
            else
            {
            	int i1 = par1World.getBlockMetadata(par2, par3, par4);
            	if(i1 != 10)
            	{
            		par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
            	}
            }
        }
    }
	@Override
	public int getRenderType()
	{
		return 1;
	}
	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) {
		return EnumPlantType.Plains;
	}
	@Override
	public int getPlantID(World world, int x, int y, int z) {
		return this.blockID;
	}
	@Override
	public Icon getIcon(int side, int metadata)
    {
		return metadata < 5 || metadata == 10 ? this.calamitesPlant : this.eggCalamitesPlant;
    }
	
	@Override
	public int getPlantMetadata(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
	@Override
	public void registerIcons(IconRegister iconRegister)
    {
		this.calamitesPlant = iconRegister.registerIcon(Genesis.MOD_ID + ":" + this.getTextureName());
		this.eggCalamitesPlant = iconRegister.registerIcon(Genesis.MOD_ID + ":" + this.getTextureName() + "_egg");
    }
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
		return PlantItems.calamites.itemID;
		
    }
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        Block block = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
        return (block != null && block.blockID == Blocks.moss.blockID || block != null && block.blockID == this.blockID);
    }
	@Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        this.checkBlockCoordValid(par1World, par2, par3, par4);
    }
    protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return this.canPlaceBlockAt(par1World, par2, par3, par4);
    }
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return PlantItems.calamites.itemID;
    }
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
    	par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 10);
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
}
