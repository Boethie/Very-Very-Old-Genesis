package genesis.client.renderer;

import cpw.mods.fml.client.FMLClientHandler;
import genesis.client.model.tiles.ModelPolissoir;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemPolissoirRender implements IItemRenderer {
    private ModelPolissoir render;
    public ItemPolissoirRender() { render = new ModelPolissoir(); }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
        {
        case ENTITY:
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("genesis:textures/entity/polissoir.png"));

            GL11.glPushMatrix(); //start
            GL11.glTranslatef(.5F, .8F, .5F); //size
            GL11.glScalef(1F, 1F, 1F);
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glRotatef(-90, 0, 1, 0);
            render.renderAll();
            GL11.glPopMatrix(); //end
            break;
        }
        case EQUIPPED:
        {
            renderPolissoir(1.0F, 1.0F, 1.0F, item.getItemDamage());
            break;
        }
        case EQUIPPED_FIRST_PERSON:
        {
            renderPolissoir(1.0F, 1.0F, 1.0F, item.getItemDamage());
            break;
        }
        case INVENTORY:
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("genesis:textures/entity/polissoir.png"));

            GL11.glPushMatrix(); //start
            GL11.glTranslatef(1, 2, 1); //size
            GL11.glScalef(1F, 1F, 1F);
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glRotatef(-90, 0, 1, 0);
            render.renderAll();
            GL11.glPopMatrix(); //end
            break;
        }
        default:
            break;
        }
    }

    public void renderPolissoir(float x, float y, float z, int metaData)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("genesis:textures/entity/polissoir.png"));

        GL11.glPushMatrix(); //start
        GL11.glTranslatef(x - .5F, y, z - .5F); //size
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glRotatef(-90, 0, 1, 0);
        render.renderAll();
        GL11.glPopMatrix(); //end
    }
}