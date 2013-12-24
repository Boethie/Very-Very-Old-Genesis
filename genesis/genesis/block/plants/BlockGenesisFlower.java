package genesis.genesis.block.plants;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.genesis.common.Genesis;

public class BlockGenesisFlower extends BlockFlower implements IPlantRenderSpecials, IPlantInFlowerPot {
	
	public Icon[] blockIcons;
	
	public EnumPlantType defaultType = EnumPlantType.Plains;
	public EnumPlantType[] typesPlantable = {};
	private EnumPlantType testingType;
	
	protected BlockGenesisFlower(int id) {
		super(id);

		setCreativeTab(Genesis.tabGenesis);
		setPlantBoundsSize(0.375F);
		
		this.setStepSound(soundGrassFootstep);
	}
	
	public BlockGenesisFlower setPlantableTypes(EnumPlantType[] types) {
		typesPlantable = types;
		return this;
	}

	/*
	 * Overridden method to make it possible to plant plants on multiple land types.
	 */
    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z) {
    	if (testingType != null)
    		return testingType;
    	
    	EnumPlantType output = EnumPlantType.Plains;

    	for (EnumPlantType type : typesPlantable) {
    		testingType = type;
    		
    		if (blocksList[world.getBlockId(x, y - 1, z)].canSustainPlant(world, x, y, z, ForgeDirection.UP, this))
    			output = type;
    	}
    	
    	testingType = null;
    	
        return output;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List itemList) {
		for(int set = 0; set < PlantBlocks.flowerTypes.size(); set++){
			itemList.add(new ItemStack(blockID, 1, set));
		}
    }
    
    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }
	
	protected void setPlantBoundsSize(float size) {
        setBlockBounds(0.5F - size, 0, 0.5F - size, 0.5F + size, 1, 0.5F + size);
	}
	
	@Override
	public int getRenderType() {
		return 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		blockIcons = new Icon[PlantBlocks.flowerTypes.size()];
		int set = 0;
		
		for(String s : PlantBlocks.flowerTypes) {
			this.blockIcons[set] = iconRegister.registerIcon(Genesis.MOD_ID + ":" + s);
			set++;
		}
    }
	
	@Override
	public Icon getIcon(int par1, int par2) {
        return this.blockIcons[par2];
    }
	
	protected int getVerticalPosition(IBlockAccess world, int x, int y, int z) {
		int checkBlockID = this.blockID;
		int count = world.getBlockId(x, y, z) == this.blockID ? 1 : 0;
		int off = 1;
		
		while (checkBlockID == this.blockID) {
			checkBlockID = world.getBlockId(x, y - off, z);

			if (checkBlockID == this.blockID)
				count++;
			
			off++;
		}
		
		return count;
	}
	
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		dropIfCannotStay(world, x, y, z);
	}
	
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		boolean out = false;
		
		int blockID = world.getBlockId(x, y - 1, z);
		Block block = Block.blocksList[blockID];
		
		if (block != null)
			out = (block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this));
		
		return out;
    }
    
    @Override
    public boolean canBlockStay(World par1World, int x, int y, int z) {
        return canPlaceBlockAt(par1World, x, y, z);
    }
	
    protected void dropIfCannotStay(World world, int x, int y, int z) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }
	
	@Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourBlockID) {
        dropIfCannotStay(world, x, y, z);
    }

	@Override
	public boolean shouldReverseTex(IBlockAccess world, int x, int y, int z, int side) {
		return false;
	}
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    	/*
    	if (!world.isRemote && side != 1)
	    	updateTick(world, x, y, z, world.rand);
    	*/
    	
    	return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

	@Override
	public float renderScale(IBlockAccess world, int x, int y, int z) {
		return 1;
	}
	
	@Override
	public int getRenderColor(IBlockAccess world, int x, int y, int z) {
        return 16777215;
	}
	
	@Override
	public Icon getIconForFlowerPot(IBlockAccess world, int x, int y, int z, int plantMetadata) {
		return getBlockTexture(world, x, y, z, 0);
	}
	
	@Override
	public Block getBlockForRender(IBlockAccess world, int x, int y, int z) {
		return this;
	}
}
