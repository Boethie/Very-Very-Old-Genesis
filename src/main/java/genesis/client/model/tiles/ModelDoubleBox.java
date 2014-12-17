package genesis.client.model.tiles;

import net.minecraft.client.model.ModelRenderer;

public class ModelDoubleBox extends ModelBox{
	
	public ModelDoubleBox(){

		lid = new ModelRenderer(this, 0, 0).setTextureSize(128, 128);
		lid.addBox(0F, 0F, 0F, 15, 1, 32);
		lid.setRotationPoint(0.5F, 2.0F, 0F);
		box = new ModelRenderer(this, 0, 33).setTextureSize(128, 128);
		box.addBox(1F, 3F, 1F, 14, 14, 30);
		box.setRotationPoint(0F, 0F, 0F);
	}

}
