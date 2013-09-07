package genesis.genesis.block;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.Ids;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockGenesisLeaves extends BlockLeaves{

	public static final String[] gwoodType = new String[] {"Araucarioxylon", "Bjuvia", "Cordaites", "Fig", "Lepidodendron", "Sigillaria", "Tempskya"};
	@SideOnly(Side.CLIENT)
    private Icon[] leavesIcons;
    @SideOnly(Side.CLIENT)
    private Icon[] leavesOpaqIcons;
    public int leavesCat;
	public BlockGenesisLeaves(int par1, int cat) {
		super(par1);
		leavesCat = cat;
		this.setCreativeTab(Genesis.tabGenesis);
	}
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
		int k = (par2 & 3) + (leavesCat * 4);
        return Minecraft.getMinecraft().gameSettings.fancyGraphics ? leavesIcons[k] : leavesOpaqIcons[k];
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		int count = 0;
		for(int i = (leavesCat * 4); i < this.gwoodType.length; i++)
		{
			par3List.add(new ItemStack(par1, 1, count));
			count++;
			if(count == 4)
			{
				break;
			}
		}
    }
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		this.leavesIcons = new Icon[this.gwoodType.length];
        this.leavesOpaqIcons = new Icon[this.gwoodType.length];
        
        for (int i = 0; i < this.gwoodType.length; ++i)
        {
            this.leavesIcons[i] = par1IconRegister.registerIcon(Genesis.modid + ":leaves_" + this.gwoodType[i].toLowerCase());
            this.leavesOpaqIcons[i] = par1IconRegister.registerIcon(Genesis.modid + ":leaves_" + this.gwoodType[i].toLowerCase()  + "_opaque");
        }
    }
	public boolean isOpaqueCube()
    {
        return false;
    }
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return Ids.blockLeavesGenesisID_actual + this.leavesCat;
    }

}
