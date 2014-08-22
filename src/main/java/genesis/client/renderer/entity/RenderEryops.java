package genesis.client.renderer.entity;

import genesis.client.model.ModelEryops;
import genesis.common.Genesis;
import genesis.entity.EntityEryops;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderEryops extends RenderLiving {

	protected ModelEryops model;
	private static final ResourceLocation texture = new ResourceLocation(Genesis.MOD_ID, "textures/entity/eryops.png");

	public RenderEryops(ModelBase model, float shadow) {
		super(model, shadow);
		this.model = ((ModelEryops) mainModel);
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
