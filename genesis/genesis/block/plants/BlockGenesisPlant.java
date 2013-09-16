package genesis.genesis.block.plants;

import java.util.Random;

import genesis.genesis.block.Blocks;
import genesis.genesis.client.BlockGenesisPlantRenderer;
import genesis.genesis.common.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BlockGenesisPlant extends BlockFlower {
	
	public boolean stackable = false;
	public int stackedLimit = 1;
	
	public EnumPlantType defaultType = EnumPlantType.Plains;
	public EnumPlantType[] typesPlantable = {};
	private EnumPlantType testingType;
	
	protected BlockGenesisPlant(int id) {
		super(id);
		
		setCreativeTab(Genesis.tabGenesis);
	}
	
	public BlockGenesisPlant setPlantableTypes(EnumPlantType[] types)
	{
		typesPlantable = types;
		return this;
	}

	/*
	 * Overridden method to make it possible to plant plants on multiple land types.
	 */
    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
    	if (testingType != null)
    		return testingType;
    	
    	EnumPlantType output = EnumPlantType.Plains;

    	for (EnumPlantType type : typesPlantable)
    	{
    		testingType = type;
    		
    		if (blocksList[world.getBlockId(x, y - 1, z)].canSustainPlant(world, x, y, z, ForgeDirection.UP, this))
    		{
    			output = type;
    		}
    	}
    	
    	testingType = null;
    	
        return output;
    }
	
	protected BlockGenesisPlant setStackable(int stackedLimit)
	{
		if (stackedLimit >= 0)
		{
			this.stackable = true;
			this.stackedLimit = stackedLimit;
		}
		else
		{
			this.stackable = false;
			stackedLimit = 0;
		}
		
		return this;
	}
	
	protected void setPlantBoundsSize(float size)
	{
        setBlockBounds(0.5F - size, 0, 0.5F - size, 0.5F + size, 1, 0.5F + size);
	}
	
	@Override
	public int getRenderType()
	{
		return BlockGenesisPlantRenderer.renderID;
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
    {
		this.blockIcon = iconRegister.registerIcon(Genesis.MOD_ID + ":" + getTextureName());
    }
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
	}
	
	protected boolean canStayStacked(World world, int x, int y, int z, int underBlockID)
	{
		if (stackable && underBlockID == this.blockID)
		{
			if (stackedLimit == 0)
				return true;
			
			int checkBlockID = 0;
			int count = 0;
			int off = 1;
			
			while (checkBlockID == this.blockID)
			{
				checkBlockID = world.getBlockId(x, y - off, z);

				if (checkBlockID == this.blockID)
				{
					count++;
				}
				
				off++;
			}
			
			off = 1;
			
			while (checkBlockID == this.blockID)
			{
				checkBlockID = world.getBlockId(x, y + off, z);
				
				if (checkBlockID == this.blockID)
				{
					count++;
				}
				
				off++;
			}
			
			if (count < stackedLimit)
				return true;
		}
		
		return false;
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
		int blockID = world.getBlockId(x, y - 1, z);
		Block block = Block.blocksList[blockID];
		
		if (block != null)
		{
			if (block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this))
				return true;
			
			return canStayStacked(world, x, y, z, blockID);
		}
		
		return false;
    }
    
    @Override
    public boolean canBlockStay(World par1World, int x, int y, int z)
    {
        return canPlaceBlockAt(par1World, x, y, z);
    }
	
    protected void dropIfCannotStay(World world, int x, int y, int z)
    {
        if (!canBlockStay(world, x, y, z))
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }
	
	@Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlockID)
    {
        this.dropIfCannotStay(world, x, y, z);
    }
	
}
