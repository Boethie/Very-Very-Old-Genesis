package genesis.block.gui;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCampfireTE extends ModelBase {

    public ModelRenderer stick;

    public ModelCampfireTE() {
        stick = new ModelRenderer(this, "stick");
        stick.setTextureSize(32, 32);
        stick.addBox(-0.5F, -10, -0.5F, 1, 20, 1);
    }

    public void renderAll() {
        stick.render(0.0625F);
    }
}
