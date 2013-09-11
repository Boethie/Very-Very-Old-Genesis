package genesis.genesis.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockMossRenderer implements ISimpleBlockRenderingHandler
{
	
	public static final int renderID = RenderingRegistry.getNextAvailableRenderId();
	public static boolean renderingInventory = false;
	public static int pass = 0;
	
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
    	renderingInventory = true;
    	renderer.renderBlockAsItem(block, metadata, 1);
    	renderingInventory = false;
    }

    public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block,
    		int modelID, RenderBlocks renderer)
    {
    	BlockMoss moss = (BlockMoss)block;
    	
    	pass = 0;

        boolean snow = BlockMoss.isSnowed(blockAccess, x, y, z);
        
        if (!snow && renderer.fancyGrass)
        {
        	renderer.renderStandardBlock(block, x, y, z);
        }
        
        pass = 1;
    	
    	renderer.renderStandardBlock(block, x, y, z);
    	
    	pass = -1;
    	
    	return true;
    }

    public boolean shouldRender3DInInventory()
    {
         return true;
    }

    public int getRenderId()
    {
         return renderID;
    }
}