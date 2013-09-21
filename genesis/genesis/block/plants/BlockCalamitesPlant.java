package genesis.genesis.block.plants;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import genesis.genesis.block.Blocks;
import genesis.genesis.common.Genesis;
import genesis.genesis.lib.MiscHelpers;

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
import net.minecraft.world.*;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;

public class BlockCalamitesPlant extends BlockGenesisPlant {
	
	public static final int PLAIN_META_MASK = 7;
	public static final int EGGS_META = 8;

	@SideOnly(Side.CLIENT)
	Icon calamitesPlant;
	
	@SideOnly(Side.CLIENT)
	Icon calamitesPlantTop;

	@SideOnly(Side.CLIENT)
	Icon calamitesPlantEggs1;
	@SideOnly(Side.CLIENT)
	Icon calamitesPlantEggs2;
	
	@SideOnly(Side.CLIENT)
	Icon calamitesPlantTopEggs1;
	@SideOnly(Side.CLIENT)
	Icon calamitesPlantTopEggs2;
	
	public BlockCalamitesPlant(int par1) {
		super(par1);

		setHardness(1.5F);
		setStepSound(soundWoodFootstep);
		setBurnProperties(this.blockID, 4, 4);
		
		setPlantBoundsSize(0.25F);
		setStackable(10);
		setPlantableTypes(new EnumPlantType[] {EnumPlantType.Plains, EnumPlantType.Desert});
		
		MinecraftForge.setBlockHarvestLevel(this, "axe", 0);
	}
	
	public static class CalamitesProperties
	{
		public int height;
		public int position;
		public boolean hasEggs;
		public boolean top;
		public ArrayList<ChunkPosition> positions;
		
		public CalamitesProperties(int height, int position, boolean hasEggs, ArrayList<ChunkPosition> positions)
		{
			this.height = height;
			this.position = position;
			this.hasEggs = hasEggs;
			this.top = position == height - 1;
			this.positions = positions;
		}
	}
	
	public CalamitesProperties getProperties(IBlockAccess world, int x, int y, int z)
	{
		int atBlockID = world.getBlockId(x, y, z);
		Block block = blocksList[atBlockID];
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (atBlockID == this.blockID)
		{
			int height = 1;
			int position = 0;
			boolean hasEggs = hasEggs(metadata);
			
			int up = 2;
			int off = 1;
			ArrayList<ChunkPosition> downPositions = new ArrayList();
			ArrayList<ChunkPosition> upPositions = new ArrayList();
			
			do
			{
				if (up == 2)
					off = -1;
				else
					off = 1;
				
				do
				{
					atBlockID = world.getBlockId(x, y + off, z);
					block = blocksList[atBlockID];
					metadata = world.getBlockMetadata(x, y + off, z);
					
					if (atBlockID == this.blockID)
					{
						height++;
						
						if (up == 2)
						{
							position++;
							downPositions.add(new ChunkPosition(x, y + off, z));
						}
						else
						{
							upPositions.add(new ChunkPosition(x, y + off, z));
						}
					}
					
					if (!hasEggs && hasEggs(metadata))
						hasEggs = true;
					
					if (up == 2)
					{
						off--;
					}
					else
					{
						off++;
					}
				} while (atBlockID == this.blockID);
				
				up--;
			} while (up > 0);
			
			ArrayList<ChunkPosition> positions = new ArrayList();
			
			for (int i = downPositions.size() - 1; i >= 0; i--)
			{
				positions.add(downPositions.get(i));
			}
			
			positions.add(new ChunkPosition(x, y, z));
			
			for (int i = 0; i < upPositions.size(); i++)
			{
				positions.add(upPositions.get(i));
			}
			
			return new CalamitesProperties(height, position, hasEggs, positions);
		}
		
		return null;
	}
	
	private boolean isTop(IBlockAccess world, int x, int y, int z)
	{
		int atBlockID = world.getBlockId(x, y, z);
		
		if (atBlockID == this.blockID)
		{
			atBlockID = world.getBlockId(x, y + 1, z);
			
			if (atBlockID != this.blockID)
				return true;
		}
		
		return false;
	}
	
	private int setHasEggs(int metadata, boolean hasEggs)
	{
		return (metadata & PLAIN_META_MASK) | (hasEggs ? EGGS_META : 0);
	}
	
	private boolean hasEggs(int metadata)
	{
		return (metadata & EGGS_META) != 0;
	}
	
	protected int setAge(int metadata, int age)
	{
		return (metadata & EGGS_META) | Math.min(age, PLAIN_META_MASK);
	}
	
	protected int getAge(int metadata)
	{
		return metadata & PLAIN_META_MASK;
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
		int blockID = world.getBlockId(x, y - 1, z);
		Block block = Block.blocksList[blockID];
		
		if (block != null)
		{
			return (block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this) &&
					MiscHelpers.isWaterInRange(world, x, y, z, 2, 1)) ||
					canStayStacked(world, x, y, z, blockID);
		}
		
		return false;
    }
	
	private void resetAll(World world, ArrayList<ChunkPosition> positions, int exceptY, boolean skipTop)
	{
        ChunkPosition lastPos = null;
        
        if (skipTop)
        	lastPos = positions.get(positions.size() - 1);
        
        for (ChunkPosition pos : positions)
        {
        	if (pos != lastPos && pos.y != exceptY)
        	{
            	int resetMetadata = world.getBlockMetadata(pos.x, pos.y, pos.z);
            	world.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, setAge(resetMetadata, 0), 3);
        	}
        }
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (world.isRemote)
			return;
		
		CalamitesProperties props = getProperties(world, x, y, z);
		
		int metadata = world.getBlockMetadata(x, y, z);
		int age = getAge(metadata);
		boolean resetAges = false;
		
		if (hasEggs(metadata) && age >= 4)
		{
			metadata = setHasEggs(metadata, false);
			resetAges = true;
		}
		else if (props.top && props.height < stackedLimit)
		{
			if (age >= PLAIN_META_MASK && world.getBlockMaterial(x, y + 1, z).isReplaceable() && rand.nextBoolean())
			{
				world.setBlock(x, y + 1, z, blockID);
			}
		}
		else if (!props.hasEggs && age >= PLAIN_META_MASK &&
				rand.nextBoolean())
		{
            metadata = setHasEggs(metadata, true);
            resetAges = true;
		}
		
		if (resetAges)
		{
			age = -1;
            resetAll(world, props.positions, y, props.height < stackedLimit);
		}
		
		world.setBlockMetadataWithNotify(x, y, z, setAge(metadata, age + 1), 3);
	}
	
	private static boolean secondSide;
	private static boolean reverseTex;
	
	@Override
    public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
    {
		if (side == 0)
		{
			Random rand = new Random((x + z) * y * y * x * z);
			rand.nextFloat();	// Just to (hopefully) help the booleans be more random
			secondSide = rand.nextBoolean();
			reverseTex = rand.nextBoolean();
		}
		
    	int metadata = world.getBlockMetadata(x, y, z);
    	boolean isTop = isTop(world, x, y, z);
    	
    	if (hasEggs(metadata))
    	{
    		if (secondSide ? (side == 1 || side == 3) : (side == 0 || side == 2))
    		{
    			return isTop ? this.calamitesPlantTopEggs1 : this.calamitesPlantEggs1;
    		}
    		else
    		{
    			switch (side)
    			{
    			case 0:
    				if (secondSide && !reverseTex)
    	    			return isTop ? this.calamitesPlantTopEggs2 : this.calamitesPlantEggs2;
    				break;
    			case 1:
    				if (!secondSide && reverseTex)
    	    			return isTop ? this.calamitesPlantTopEggs2 : this.calamitesPlantEggs2;
    				break;
    			case 2:
    				if (secondSide && reverseTex)
    	    			return isTop ? this.calamitesPlantTopEggs2 : this.calamitesPlantEggs2;
    				break;
    			case 3:
    				if (!secondSide && !reverseTex)
    	    			return isTop ? this.calamitesPlantTopEggs2 : this.calamitesPlantEggs2;
    				break;
    			}
    		}
    	}
    	
    	return isTop ? this.calamitesPlantTop : this.calamitesPlant;
    }
	
	@Override
	public boolean shouldReverseTex(IBlockAccess world, int x, int y, int z, int side)
	{
		return reverseTex;
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
    {
		String texName = getTextureName();
		String texStart = Genesis.MOD_ID + ":" + texName;
		
		this.calamitesPlant = iconRegister.registerIcon(texStart);
		this.calamitesPlantTop = iconRegister.registerIcon(texStart + "_top");

		this.calamitesPlantEggs1 = iconRegister.registerIcon(texStart + "_eggs_1");
		this.calamitesPlantEggs2 = iconRegister.registerIcon(texStart + "_eggs_2");
		this.calamitesPlantTopEggs1 = iconRegister.registerIcon(texStart + "_eggs_top_1");
		this.calamitesPlantTopEggs2 = iconRegister.registerIcon(texStart + "_eggs_top_2");
		
		this.blockIcon = this.calamitesPlant;
    }
	
	@Override
	public String getItemIconName()
	{
		return Genesis.MOD_ID + ":" + getTextureName();
	}
	
	private boolean dropEggs(World world, int x, int y, int z, int metadata)
	{
        if (hasEggs(metadata))
        {
            if (world.isRemote)
                return true;

            if (world.getBlockId(x, y, z) == this.blockID)
            	world.setBlockMetadataWithNotify(x, y, z, setHasEggs(metadata, false), 3);
            
            EntityItem itemDrop = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack(Item.egg));
            double div = Math.sqrt(itemDrop.motionX * itemDrop.motionX +
            		itemDrop.motionY * itemDrop.motionY +
            		itemDrop.motionZ * itemDrop.motionZ) * 2;
            double dirX = itemDrop.motionX / div;
            double dirY = itemDrop.motionY / div;
            double dirZ = itemDrop.motionZ / div;
            itemDrop.setPosition(itemDrop.posX + dirX, itemDrop.posY + dirY, itemDrop.posZ + dirZ);
            
            world.spawnEntityInWorld(itemDrop);
            
            return true;
        }
        
		return false;
	}
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	if (dropEggs(world, x, y, z, world.getBlockMetadata(x, y, z)))
    		return true;
    	
    	/*if (!world.isRemote)
    	{
	    	updateTick(world, x, y, z, world.rand);
    	}*/
    	
    	return false;
    }
    
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune)
    {
    	super.dropBlockAsItemWithChance(world, x, y, z, metadata, chance, fortune);

        if (!world.isRemote && world.rand.nextFloat() <= chance)
        {
            dropEggs(world, x, y, z, metadata);
        }
    }
    
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
    	return metadata;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
    	float size = 0.15F;
    	return AxisAlignedBB.getAABBPool().getAABB(x + 0.5 - size, y, z + 0.5 - size,
    			x + 0.5 + size, y + 1, z + 0.5 + size);
    }
	
}
