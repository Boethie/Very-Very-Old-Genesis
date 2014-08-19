package genesis.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPolissoir extends ModelBase {
    public ModelRenderer grooveBase;
    public ModelRenderer grooveBaseBend;
    public ModelRenderer grooveBaseSmall;
    public ModelRenderer base1;
    public ModelRenderer base2;
    public ModelRenderer base3;
    public ModelRenderer base4;
    public ModelRenderer baseBend1;
    public ModelRenderer baseBend2;
    public ModelRenderer baseBend3;
    public ModelRenderer baseBend4;
    public ModelRenderer water;

    public ModelPolissoir() {
        textureWidth = 64;
        textureHeight = 64;

        grooveBase = new ModelRenderer(this, 0, 0);
        grooveBase.addBox(0F, 0F, 0F, 10, 9, 13);
        grooveBase.setRotationPoint(-8F, 15F, -5F);
        grooveBase.mirror = true;
        setRotation(grooveBase, 0F, 0F, 0F);
        grooveBaseBend = new ModelRenderer(this, 38, 24);
        grooveBaseBend.addBox(0F, 0F, 0F, 10, 3, 3);
        grooveBaseBend.setRotationPoint(-7.9F, 14F, -6F);
        grooveBaseBend.mirror = true;
        setRotation(grooveBaseBend, -0.7330383F, 0F, 0F);
        grooveBaseSmall = new ModelRenderer(this, 38, 31);
        grooveBaseSmall.addBox(0F, 0F, 0F, 9, 8, 3);
        grooveBaseSmall.setRotationPoint(-8F, 16F, -8F);
        grooveBaseSmall.mirror = true;
        setRotation(grooveBaseSmall, 0F, 0F, 0F);
        base1 = new ModelRenderer(this, 0, 24);
        base1.addBox(0F, 0F, 0F, 7, 9, 10);
        base1.setRotationPoint(1F, 15F, -8F);
        base1.mirror = true;
        setRotation(base1, 0F, 0F, 0F);
        base2 = new ModelRenderer(this, 0, 43);
        base2.addBox(0F, 0F, 0F, 6, 7, 6);
        base2.setRotationPoint(2F, 17F, 2F);
        base2.mirror = true;
        setRotation(base2, 0F, 0F, 0F);
        base3 = new ModelRenderer(this, 0, 57);
        base3.addBox(0F, 0F, 0F, 6, 2, 1);
        base3.setRotationPoint(1.9F, 15F, 7F);
        base3.mirror = true;
        setRotation(base3, 0F, 0F, 0.0523599F);
        base4 = new ModelRenderer(this, 14, 57);
        base4.addBox(0F, 0F, 0F, 1, 2, 5);
        base4.setRotationPoint(6.9F, 15F, 2F);
        base4.mirror = true;
        setRotation(base4, -0.0523599F, 0F, 0F);
        baseBend1 = new ModelRenderer(this, 27, 56);
        baseBend1.addBox(0F, 0F, 0F, 1, 3, 5);
        baseBend1.setRotationPoint(1F, 15.2F, 2F);
        baseBend1.mirror = true;
        setRotation(baseBend1, 0F, 0F, -0.6108652F);
        baseBend2 = new ModelRenderer(this, 0, 60);
        baseBend2.addBox(0F, 0F, -1F, 5, 3, 1);
        baseBend2.setRotationPoint(2F, 15F, 2F);
        baseBend2.mirror = true;
        setRotation(baseBend2, 0.6981317F, 0F, 0F);
        baseBend3 = new ModelRenderer(this, 27, 51);
        baseBend3.addBox(0F, 0F, 0F, 5, 3, 1);
        baseBend3.setRotationPoint(2F, 15F, 7F);
        baseBend3.mirror = true;
        setRotation(baseBend3, -0.6108652F, 0F, 0.0523599F);
        baseBend4 = new ModelRenderer(this, 40, 43);
        baseBend4.addBox(0F, 0F, 0F, 1, 3, 5);
        baseBend4.setRotationPoint(7F, 15F, 2F);
        baseBend4.mirror = true;
        setRotation(baseBend4, -0.0872665F, 0.0523599F, 0.715585F);
        water = new ModelRenderer(this, 40, 59);
        water.addBox(0F, 0F, 0F, 5, 1, 5);
        water.setRotationPoint(2F, 15.5F, 2F);
        water.mirror = true;
        setRotation(water, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        grooveBase.render(f5);
        grooveBaseBend.render(f5);
        grooveBaseSmall.render(f5);
        base1.render(f5);
        base2.render(f5);
        base3.render(f5);
        base4.render(f5);
        baseBend1.render(f5);
        baseBend2.render(f5);
        baseBend3.render(f5);
        baseBend4.render(f5);
        water.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}