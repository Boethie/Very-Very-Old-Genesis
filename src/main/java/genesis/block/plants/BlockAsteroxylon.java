package genesis.block.plants;

import genesis.lib.GenesisTabs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Arbiter
 */
public class BlockAsteroxylon extends BlockGenesisTerrestrialPlant implements IGrowable {
    @SideOnly(Side.CLIENT)
    public IIcon[] icons;
    private String unlocalizedName;

    public BlockAsteroxylon() {
        super();
        setCreativeTab(GenesisTabs.tabGenesisDecoration);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List itemList) {
        itemList.add(new ItemStack(item, 1, 0));
        itemList.add(new ItemStack(item, 1, 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1) {
        return ColorizerGrass.getGrassColor(0.5d, 1.0d);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        return world.getBiomeGenForCoords(x, z).getBiomeGrassColor(x, y, z);
    }

    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack stack) {
        if (stack.getItemDamage() == 0) {
            return super.canPlaceBlockAt(world, x, y, z);
        }
        return (world.isAirBlock(x, y + 1, z) || world.getBlock(x, y + 1, z) instanceof BlockGenesisPlantTop) && super.canPlaceBlockAt(world, x, y, z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        Block b = world.getBlock(x, y + 1, z);
        if (b != null && b instanceof BlockAsteroxylonTop) {
            world.setBlockToAir(x, y + 1, z);
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[2];
        icons[0] = register.registerIcon(getTextureName());
        icons[1] = register.registerIcon(getTextureName() + "_bottom");
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        list.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return list;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        if (par2 > 1) {
            par2 = 1;
        }
        return icons[par2];
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        if (world.isAirBlock(x, y + 1, z) && meta == 1) {
            world.setBlock(x, y + 1, z, GenesisPlantBlocks.asteroxylon_top);
        }
        return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
    }

    @Override
    public boolean func_149851_a(World var1, int var2, int var3, int var4, boolean var5) {
        return var1.getBlockMetadata(var2, var3, var4) == 0;
    }

    @Override
    public boolean func_149852_a(World var1, Random var2, int var3, int var4, int var5) {
        return true;
    }

    @Override
    public void func_149853_b(World var1, Random var2, int var3, int var4, int var5) {
        if (var1.isAirBlock(var3, var4 + 1, var5)) {
            var1.setBlock(var3, var4 + 1, var5, GenesisPlantBlocks.asteroxylon_top);
            var1.setBlockMetadataWithNotify(var3, var4, var5, 1, 2);
        }
    }

    /*public IIcon getIconForItemRender()
    {
        return icons[0];
    }*/
}