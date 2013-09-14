package genesis.genesis.block.plants;

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
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random){}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
		Block block = Block.blocksList[world.getBlockId(x, y - 1, z)];
		if(block != null)	
			return block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this) ||
				   block.blockID == this.blockID;
		return false;
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
