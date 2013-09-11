package genesis.genesis.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.genesis.common.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMoss extends BlockGrass {
	
	protected Icon iconGrassTop;
	protected Icon iconSnowSide;
	protected Icon iconSideOverlay;

	protected BlockMoss(int blockID) {
		super(blockID);
		
		setCreativeTab(Genesis.tabGenesis);
		setHardness(0.6F);
		setStepSound(soundGrassFootstep);
	}
	
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if (!world.isRemote)
        {
            if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2)
            {
                world.setBlock(x, y, z, Block.dirt.blockID);
            }
            else if (world.getBlockLightValue(x, y + 1, z) >= 9)
            {
                for (int i = 0; i < 4; i++)
                {
                    int randX = x + random.nextInt(3) - 1;
                    int randY = y + random.nextInt(5) - 3;
                    int randZ = z + random.nextInt(3) - 1;

                    if (world.getBlockId(randX, randY, randZ) == Block.dirt.blockID &&
                    		world.getBlockLightValue(randX, randY + 1, randZ) >= 4 &&
                    		world.getBlockLightOpacity(randX, randY + 1, randZ) <= 2)
                    {
                        world.setBlock(randX, randY, randZ, blockID);
                    }
                }
            }
        }
    }
	
	@Override
	public int getRenderType()
	{
		if (BlockMossRenderer.renderingInventory)
			return 0;
		
		return BlockMossRenderer.renderID;
	}

	@Override
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(Genesis.modid + ":" + getTextureName() + "_side");
        this.iconGrassTop = iconRegister.registerIcon(Genesis.modid + ":" + getTextureName() + "_top");
        this.iconSnowSide = iconRegister.registerIcon(Genesis.modid + ":" + getTextureName() + "_side_snowed");
		this.iconSideOverlay = iconRegister.registerIcon(Genesis.modid + ":" + getTextureName() + "_side_overlay");
    }

	@Override
    public Icon getIcon(int side, int metdata)
    {
        return side == 1 ? this.iconGrassTop : (side == 0 ? Block.dirt.getBlockTextureFromSide(side) : this.blockIcon);
    }
	
	@Override
    public int getBlockColor()
    {
        return ColorizerGrass.getGrassColor(0.3, 0.3);
    }
	
	public static boolean isSnowed(IBlockAccess blockAccess, int x, int y, int z)
	{
        Material material = blockAccess.getBlockMaterial(x, y + 1, z);
        return material == Material.snow || material == Material.craftedSnow;
	}
	
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
    	if (isSnowed(blockAccess, x, y, z) || BlockMossRenderer.pass == 0 || BlockMossRenderer.renderingInventory)
    		return 16777215;
    	
    	return super.colorMultiplier(blockAccess, x, y, z);
    }

	@Override
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        boolean snow = isSnowed(blockAccess, x, y, z);
        
		if (!snow && BlockMossRenderer.pass == 0)
		{
			if (side == 0)
	            return Block.dirt.getBlockTextureFromSide(side);
			else
				return blockIcon;
		}
		else
		{
	        if (side == 1)
	        {
	            return this.iconGrassTop;
	        }
	        else if (side == 0)
	        {
	            return Block.dirt.getBlockTextureFromSide(side);
	        }
	        else
	        {
	            return snow ? this.iconSnowSide : this.iconSideOverlay;
	        }
		}
    }
	
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
    	if (BlockMossRenderer.pass == 1 && side == 0)
    		return false;
    	
        return super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }
	
	public Icon getPlainSideTexture()
	{
		return blockIcon;
	}
	
}
