package genesis.block.plants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import genesis.client.renderer.block.BlockGenesisPlantRenderer;
import genesis.Genesis;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGenesisCrop extends BlockCrops implements IPlantRenderSpecials {

    protected Item seedItem;
    protected Item cropItem;
    protected Block soilBlock;
    protected int stages;
    protected int droppedAmountMax;
    protected int droppedAmount;
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    /**
     * @param itemCrop                Crop
     * @param itemSeed                Seed
     * @param blockSoil               Soil
     * @param stageAmount             Stages
     * @param maxAmountDropped        grown maximal seeds dropped amount
     */
    public BlockGenesisCrop(Item itemCrop, Item itemSeed, Block blockSoil, int stageAmount, int maxAmountDropped) {
        cropItem = itemCrop;
        seedItem = itemSeed;
        soilBlock = blockSoil;
        stages = stageAmount;
        droppedAmountMax = maxAmountDropped;
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        return block == soilBlock;
    }

    @Override
    // protected int getSeedItem() {
    protected Item func_149866_i() {
        return seedItem;
    }

    @Override
    // protected int getCropItem() {
    protected Item func_149865_P() {
        return cropItem;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return droppedAmount;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int par1, int par2) {
        if (par2 < 0 || par2 > stages) {
            par2 = stages;
        }

        return iconArray[par2];
    }

    @Override
    public Block setBlockTextureName(String textureName) {
        return super.setBlockTextureName(Genesis.ASSETS + textureName);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        iconArray = new IIcon[stages];

        for (int i = 0; i < iconArray.length; ++i) {
            iconArray[i] = par1IconRegister.registerIcon(getTextureName() + "_stage_" + i);
        }
    }

    public boolean isTall() {
        return false;
    }

    @Override
    public int getRenderType() {
        return BlockGenesisPlantRenderer.renderID;
    }

    @Override
    public double randomPos(IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return 0.3;
    }

    @Override
    public double randomYPos(IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean shouldReverseTex(IBlockAccess world, int x, int y, int z, int side) {
        // TODO Auto-generated method stub
        return false;
    }
}
