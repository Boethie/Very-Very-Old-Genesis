package genesis.genesis.block;

import genesis.genesis.common.Genesis;
import genesis.genesis.lib.Ids;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockGenesisLog extends BlockLog{

	public static final String[] gwoodType = new String[] {"Araucarioxylon", "Bjuvia", "Cordaites", "Fig", "Lepidodendron", "Sigillaria", "Tempskya"};
    @SideOnly(Side.CLIENT)
    private Icon[] woodSide;
    @SideOnly(Side.CLIENT)
    private Icon[] woodTop;
    public int LogCat;
	public BlockGenesisLog(int par1, int cat) {
		super(par1);
		this.setCreativeTab(Genesis.tabGenesis);
		this.LogCat = cat;
		this.setStepSound(Block.soundWoodFootstep);
		this.setHardness(1F);
	}
	@Override
	public int getRenderType()
	{
		return 31;
	}
	public int damageDropped(int par1)
    {
        return par1 & 3;
    }
	public Icon getIcon(int par1, int par2)
    {
        int k = par2 & 12;
        int l = (par2 & 3) + (LogCat * 4);
        return k == 0 && (par1 == 1 || par1 == 0) ? this.func_111049_d(l) : (k == 4 && (par1 == 5 || par1 == 4) ? this.func_111049_d(l) : (k == 8 && (par1 == 2 || par1 == 3) ? this.func_111049_d(l) : this.func_111048_c(l)));
        
    }
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		int count = 0;
		for(int i = (LogCat * 4); i < this.gwoodType.length; i++)
		{
			par3List.add(new ItemStack(par1, 1, count));
			count++;
			if(count == 4)
			{
				break;
			}
		}
    }
	public void registerIcons(IconRegister par1IconRegister)
    {
        this.woodSide = new Icon[this.gwoodType.length];
        this.woodTop = new Icon[this.gwoodType.length];
        
        for (int i = 0; i < this.gwoodType.length; ++i)
        {
            this.woodSide[i] = par1IconRegister.registerIcon(Genesis.modid + ":log_" + this.gwoodType[i].toLowerCase());
            this.woodTop[i] = par1IconRegister.registerIcon(Genesis.modid + ":log_" + this.gwoodType[i].toLowerCase()  + "_top");
        }
    }
	@SideOnly(Side.CLIENT)
	protected Icon func_111048_c(int i) {
		return this.woodSide[i];
	}
	@SideOnly(Side.CLIENT)
    protected Icon func_111049_d(int par1)
    {
        return this.woodTop[par1];
    }
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return Ids.blockLogGenesisID_actual + this.LogCat;
    }
	

}
