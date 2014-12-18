package genesis.client.renderer.entity;

import genesis.Genesis;
import genesis.client.model.entity.ModelEryops;
import genesis.entity.EntityEryops;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderEryops extends RenderLiving {
    private static final ResourceLocation texture = new ResourceLocation(Genesis.ASSETS + "textures/entity/eryops.png");
    protected ModelEryops model;

    public RenderEryops(ModelBase modelBase, float shadow) {
        super(modelBase, shadow);
        model = ((ModelEryops) mainModel);
    }

    public void renderModel(EntityEryops entity, double x, double y, double z, float u, float v) {
        super.doRender(entity, x, y, z, u, v);
    }

    public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float u, float v) {
        renderModel((EntityEryops) entityLiving, x, y, z, u, v);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float u, float v) {
        renderModel((EntityEryops) entity, x, y, z, u, v);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }
}
