package genesis.client.model.tiles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBox extends ModelBase{
	
	public ModelRenderer lid;
	public ModelRenderer box;

	public ModelBox(){

		lid = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
		lid.addBox(0F, 0F, 0F, 15, 1, 16);
		lid.setRotationPoint(0.5F, 2.0F, 0F);
		box = new ModelRenderer(this, 0, 17).setTextureSize(64, 64);
		box.addBox(1F, 3F, 1F, 14, 14, 14);
		box.setRotationPoint(0F, 0F, 0F);
	}
	
	public void renderAll(){
		float f = 0.0625F;
		lid.render(f);
		box.render(f);
	}
}
