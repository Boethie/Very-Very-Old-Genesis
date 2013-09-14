package genesis.genesis.plants.blocks;

import java.util.Random;

import genesis.genesis.block.Blocks;
import genesis.genesis.common.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BlockGenesisPlant extends BlockFlower{

	protected BlockGenesisPlant(int id) {
		super(id);
		this.setCreativeTab(Genesis.tabGenesis);
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
    {
		this.blockIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + this.getTextureName());
    }
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random){}
	
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
		int id = world.getBlockId(x, y - 1, z);
		if(id == Block.dirt.blockID || 
		   id == Block.grass.blockID || 
		   id == Blocks.moss.blockID ||
		   id == PlantBlocks.blockCalamites.blockID)
		{
			return true;
		}
		return false;
		
    }
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return (this.canSustainPlant(world, x, y, z, ForgeDirection.UP, this));
    }
	
	@Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlockID)
    {
        this.checkBlockCoordValid(world, x, y, z);
    }
	
    protected final void checkBlockCoordValid(World world, int x, int y, int z)
    {
        if (!this.canBlockStay(world, x, y, z))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }
    
    @Override
    public boolean canBlockStay(World par1World, int x, int y, int z)
    {
        return this.canPlaceBlockAt(par1World, x, y, z);
    }
}
