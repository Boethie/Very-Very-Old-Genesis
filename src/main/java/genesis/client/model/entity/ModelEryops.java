package genesis.client.model.entity;

import genesis.entity.EntityEryops;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEryops extends ModelBase {
	ModelRenderer torso1;
	ModelRenderer torso2;
	ModelRenderer neck;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer tail4;
	ModelRenderer head1;
	ModelRenderer head2;
	ModelRenderer snout1;
	ModelRenderer snout2;
	ModelRenderer upperjaw;
	ModelRenderer lowerjaw1;
	ModelRenderer lowerjaw2;
	ModelRenderer teethleft;
	ModelRenderer teethright;
	ModelRenderer frontleft1;
	ModelRenderer frontright1;
	ModelRenderer frontleft2;
	ModelRenderer frontright2;
	ModelRenderer backleft1;
	ModelRenderer backright1;
	ModelRenderer backleft2;
	ModelRenderer backright2;
	ModelRenderer toefrontleft1;
	ModelRenderer toefrontright1;
	ModelRenderer toefrontleft2;
	ModelRenderer toefrontright2;
	ModelRenderer toefrontleft3;
	ModelRenderer toefrontright3;
	ModelRenderer toebackleft1;
	ModelRenderer toebackright1;
	ModelRenderer toebackleft2;
	ModelRenderer toebackright2;
	ModelRenderer toebackleft3;
	ModelRenderer toebackright3;
	ModelRenderer toebackleft4;
	ModelRenderer toebackright4;

	public ModelEryops() {
		textureWidth = 256;
		textureHeight = 128;

		torso1 = new ModelRenderer(this, 0, 0);
		torso1.addBox(-6F, -2.5F, 0F, 12, 9, 14);
		torso1.setRotationPoint(0F, 16F, -7F);
		torso1.setTextureSize(256, 128);
		torso1.mirror = true;
		setRotation(torso1, -0.0371786F, 0F, 0F);
		torso2 = new ModelRenderer(this, 0, 25);
		torso2.addBox(-5F, -3.2F, 13.5F, 10, 8, 5);
		torso2.setRotationPoint(0F, 16F, -7F);
		torso2.setTextureSize(256, 128);
		torso2.mirror = true;
		setRotation(torso2, -0.1487144F, 0F, 0F);
		neck = new ModelRenderer(this, 31, 25);
		neck.addBox(-5.5F, -2.5F, -1.8F, 11, 9, 2);
		neck.setRotationPoint(0F, 16F, -7F);
		neck.setTextureSize(256, 128);
		neck.mirror = true;
		setRotation(neck, 0F, 0F, 0F);
		tail1 = new ModelRenderer(this, 0, 40);
		tail1.addBox(-3.5F, -2.5F, -0.2F, 7, 7, 4);
		tail1.setRotationPoint(0F, 19F, 11F);
		tail1.setTextureSize(256, 128);
		tail1.mirror = true;
		setRotation(tail1, -0.1858931F, 0F, 0F);
		tail2 = new ModelRenderer(this, 0, 53);
		tail2.addBox(-2.5F, -1.2F, 3.2F, 5, 6, 4);
		tail2.setRotationPoint(0F, 19F, 11F);
		tail2.setTextureSize(256, 128);
		tail2.mirror = true;
		setRotation(tail2, -0.0743572F, 0F, 0F);
		tail3 = new ModelRenderer(this, 0, 65);
		tail3.addBox(-1.5F, -0.5F, 7F, 3, 4, 4);
		tail3.setRotationPoint(0F, 19F, 11F);
		tail3.setTextureSize(256, 128);
		tail3.mirror = true;
		setRotation(tail3, -0.1487144F, 0F, 0F);
		tail4 = new ModelRenderer(this, 0, 75);
		tail4.addBox(-1F, 0F, 11F, 2, 3, 4);
		tail4.setRotationPoint(0F, 19F, 11F);
		tail4.setTextureSize(256, 128);
		tail4.mirror = true;
		setRotation(tail4, -0.1858931F, 0F, 0F);
		head1 = new ModelRenderer(this, 80, 0);
		head1.addBox(-5.0F, -2.1F, -5.8F, 10, 4, 6);
		head1.setRotationPoint(0F, 16F, -9F);
		head1.setTextureSize(256, 128);
		head1.mirror = true;
		setRotation(head1, 0.0371786F, 0F, 0F);
		head2 = new ModelRenderer(this, 114, 0);
		head2.addBox(-4.5F, 2.1F, -5.8F, 9, 4, 6);
		head2.setRotationPoint(0F, 16F, -9F);
		head2.setTextureSize(256, 128);
		head2.mirror = true;
		setRotation(head2, 0.0371786F, 0F, 0F);
		snout1 = new ModelRenderer(this, 80, 12);
		snout1.addBox(-4F, -2F, -15F, 8, 1, 10);
		snout1.setRotationPoint(0F, 16F, -9F);
		snout1.setTextureSize(256, 128);
		snout1.mirror = true;
		setRotation(snout1, 0.1487144F, 0F, 0F);
		snout2 = new ModelRenderer(this, 118, 12);
		snout2.addBox(-4.5F, -1F, -15.5F, 9, 2, 10);
		snout2.setRotationPoint(0F, 16F, -9F);
		snout2.setTextureSize(256, 128);
		snout2.mirror = true;
		setRotation(snout2, 0.1115296F, 0F, 0F);
		upperjaw = new ModelRenderer(this, 80, 26);
		upperjaw.addBox(-4.4F, 1.2F, -15.4F, 9, 1, 10);
		upperjaw.setRotationPoint(0F, 16F, -9F);
		upperjaw.setTextureSize(256, 128);
		upperjaw.mirror = true;
		setRotation(upperjaw, 0.0371724F, 0F, 0F);
		lowerjaw1 = new ModelRenderer(this, 80, 40);
		lowerjaw1.addBox(-4F, 2F, -15.3F, 8, 2, 10);
		lowerjaw1.setRotationPoint(0F, 16F, -9F);
		lowerjaw1.setTextureSize(256, 128);
		lowerjaw1.mirror = true;
		setRotation(lowerjaw1, 0.0371786F, 0F, 0F);
		lowerjaw2 = new ModelRenderer(this, 118, 40);
		lowerjaw2.addBox(-3.5F, 4.5F, -13.5F, 7, 2, 9);
		lowerjaw2.setRotationPoint(0F, 16F, -9F);
		lowerjaw2.setTextureSize(256, 128);
		lowerjaw2.mirror = true;
		setRotation(lowerjaw2, -0.1487144F, 0F, 0F);
		teethleft = new ModelRenderer(this, 80, 55);
		teethleft.addBox(4.2F, 1.7F, -15.4F, 0, 1, 10);
		teethleft.setRotationPoint(0F, 16F, -9F);
		teethleft.setTextureSize(256, 128);
		teethleft.mirror = true;
		setRotation(teethleft, 0.0371755F, 0F, 0F);
		teethright = new ModelRenderer(this, 101, 55);
		teethright.addBox(-4.2F, 1.7F, -15.4F, 0, 1, 10);
		teethright.setRotationPoint(0F, 16F, -9F);
		teethright.setTextureSize(256, 128);
		teethright.mirror = true;
		setRotation(teethright, 0.0371755F, 0F, 0F);
		frontleft1 = new ModelRenderer(this, 180, 0);
		frontleft1.addBox(-1F, -1F, -1.5F, 2, 5, 3);
		frontleft1.setRotationPoint(6F, 19F, -6F);
		frontleft1.setTextureSize(256, 128);
		frontleft1.mirror = true;
		setRotation(frontleft1, 0.8922867F, 0.4089647F, 0F);
		frontright1 = new ModelRenderer(this, 192, 0);
		frontright1.addBox(-1F, -1F, -1.5F, 2, 5, 3);
		frontright1.setRotationPoint(-6F, 19F, -6F);
		frontright1.setTextureSize(256, 128);
		frontright1.mirror = true;
		setRotation(frontright1, 0.8922867F, -0.4089656F, 0F);
		frontleft2 = new ModelRenderer(this, 180, 10);
		frontleft2.addBox(-0.5F, 3F, -2F, 2, 2, 5);
		frontleft2.setRotationPoint(6F, 19F, -6F);
		frontleft2.setTextureSize(256, 128);
		frontleft2.mirror = true;
		setRotation(frontleft2, 0.4089647F, 0.2602511F, 0F);
		frontright2 = new ModelRenderer(this, 196, 10);
		frontright2.addBox(-1.5F, 3F, -2F, 2, 2, 5);
		frontright2.setRotationPoint(-6F, 19F, -6F);
		frontright2.setTextureSize(256, 128);
		frontright2.mirror = true;
		setRotation(frontright2, 0.4089647F, -0.260246F, 0F);
		backleft1 = new ModelRenderer(this, 180, 19);
		backleft1.addBox(-1F, -1F, -1.5F, 2, 4, 3);
		backleft1.setRotationPoint(5F, 19F, 9F);
		backleft1.setTextureSize(256, 128);
		backleft1.mirror = true;
		setRotation(backleft1, -0.8922867F, -0.5948578F, 0F);
		backright1 = new ModelRenderer(this, 192, 19);
		backright1.addBox(-1F, -1F, -1.5F, 2, 4, 3);
		backright1.setRotationPoint(-5F, 19F, 9F);
		backright1.setTextureSize(256, 128);
		backright1.mirror = true;
		setRotation(backright1, -0.8922867F, 0.5948606F, 0F);
		backleft2 = new ModelRenderer(this, 180, 28);
		backleft2.addBox(-1F, 1.5F, -2.5F, 2, 4, 2);
		backleft2.setRotationPoint(5F, 19F, 9F);
		backleft2.setTextureSize(256, 128);
		backleft2.mirror = true;
		setRotation(backleft2, -0.2974289F, -0.5948606F, 0F);
		backright2 = new ModelRenderer(this, 189, 28);
		backright2.addBox(-1F, 1.5F, -2.5F, 2, 4, 2);
		backright2.setRotationPoint(-5F, 19F, 9F);
		backright2.setTextureSize(256, 128);
		backright2.mirror = true;
		setRotation(backright2, -0.2974216F, 0.5948606F, 0F);
		toefrontleft1 = new ModelRenderer(this, 3, 0);
		toefrontleft1.addBox(0.5F, 4F, -2.5F, 1, 1, 2);
		toefrontleft1.setRotationPoint(6F, 19F, -6F);
		toefrontleft1.setTextureSize(256, 128);
		toefrontleft1.mirror = true;
		setRotation(toefrontleft1, 0F, -0.4833219F, 0F);
		toefrontright1 = new ModelRenderer(this, 1, 0);
		toefrontright1.addBox(-1.5F, 4F, -2.5F, 1, 1, 2);
		toefrontright1.setRotationPoint(-6F, 19F, -6F);
		toefrontright1.setTextureSize(256, 128);
		toefrontright1.mirror = true;
		setRotation(toefrontright1, 0F, 0.4833166F, 0F);
		toefrontleft2 = new ModelRenderer(this, 5, 0);
		toefrontleft2.addBox(0F, 4F, -3F, 1, 1, 3);
		toefrontleft2.setRotationPoint(6F, 19F, -6F);
		toefrontleft2.setTextureSize(256, 128);
		toefrontleft2.mirror = true;
		setRotation(toefrontleft2, 0F, -0.1858931F, 0F);
		toefrontright2 = new ModelRenderer(this, 0, 3);
		toefrontright2.addBox(-1F, 4F, -3F, 1, 1, 3);
		toefrontright2.setRotationPoint(-6F, 19F, -6F);
		toefrontright2.setTextureSize(256, 128);
		toefrontright2.mirror = true;
		setRotation(toefrontright2, 0F, 0.185895F, 0F);
		toefrontleft3 = new ModelRenderer(this, 0, 7);
		toefrontleft3.addBox(-0.5F, 4F, -3F, 1, 1, 3);
		toefrontleft3.setRotationPoint(6F, 19F, -6F);
		toefrontleft3.setTextureSize(256, 128);
		toefrontleft3.mirror = true;
		setRotation(toefrontleft3, 0F, 0.1858931F, 0F);
		toefrontright3 = new ModelRenderer(this, 0, 2);
		toefrontright3.addBox(-0.5F, 4F, -3F, 1, 1, 3);
		toefrontright3.setRotationPoint(-6F, 19F, -6F);
		toefrontright3.setTextureSize(256, 128);
		toefrontright3.mirror = true;
		setRotation(toefrontright3, 0F, -0.185895F, 0F);
		toebackleft1 = new ModelRenderer(this, 2, 2);
		toebackleft1.addBox(-2F, 4F, -5F, 1, 1, 3);
		toebackleft1.setRotationPoint(5F, 19F, 9F);
		toebackleft1.setTextureSize(256, 128);
		toebackleft1.mirror = true;
		setRotation(toebackleft1, 0F, -1.375609F, 0F);
		toebackright1 = new ModelRenderer(this, 3, 1);
		toebackright1.addBox(1F, 4F, -5F, 1, 1, 3);
		toebackright1.setRotationPoint(-5F, 19F, 9F);
		toebackright1.setTextureSize(256, 128);
		toebackright1.mirror = true;
		setRotation(toebackright1, 0F, 1.375616F, 0F);
		toebackleft2 = new ModelRenderer(this, 1, 3);
		toebackleft2.addBox(-1.5F, 4F, -6F, 1, 1, 3);
		toebackleft2.setRotationPoint(5F, 19F, 9F);
		toebackleft2.setTextureSize(256, 128);
		toebackleft2.mirror = true;
		setRotation(toebackleft2, 0F, -0.9666439F, 0F);
		toebackright2 = new ModelRenderer(this, 2, 4);
		toebackright2.addBox(0.5F, 4F, -6F, 1, 1, 3);
		toebackright2.setRotationPoint(-5F, 19F, 9F);
		toebackright2.setTextureSize(256, 128);
		toebackright2.mirror = true;
		setRotation(toebackright2, 0F, 0.9666506F, 0F);
		toebackleft3 = new ModelRenderer(this, 1, 2);
		toebackleft3.addBox(-0.5F, 4F, -6F, 1, 1, 3);
		toebackleft3.setRotationPoint(5F, 19F, 9F);
		toebackleft3.setTextureSize(256, 128);
		toebackleft3.mirror = true;
		setRotation(toebackleft3, 0F, -0.4833219F, 0F);
		toebackright3 = new ModelRenderer(this, 0, 1);
		toebackright3.addBox(-0.5F, 4F, -6F, 1, 1, 3);
		toebackright3.setRotationPoint(-5F, 19F, 9F);
		toebackright3.setTextureSize(256, 128);
		toebackright3.mirror = true;
		setRotation(toebackright3, 0F, 0.4833166F, 0F);
		toebackleft4 = new ModelRenderer(this, 2, 3);
		toebackleft4.addBox(1.5F, 4F, -5F, 1, 1, 3);
		toebackleft4.setRotationPoint(5F, 19F, 9F);
		toebackleft4.setTextureSize(256, 128);
		toebackleft4.mirror = true;
		setRotation(toebackleft4, 0F, 0.2602503F, 0F);
		toebackright4 = new ModelRenderer(this, 4, 4);
		toebackright4.addBox(-2.5F, 4F, -5F, 1, 1, 3);
		toebackright4.setRotationPoint(-5F, 19F, 9F);
		toebackright4.setTextureSize(256, 128);
		toebackright4.mirror = true;
		setRotation(toebackright4, 0F, -0.260246F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		torso1.render(f5);
		torso2.render(f5);
		neck.render(f5);
		tail1.render(f5);
		tail2.render(f5);
		tail3.render(f5);
		tail4.render(f5);
		head1.render(f5);
		head2.render(f5);
		snout1.render(f5);
		snout2.render(f5);
		upperjaw.render(f5);
		lowerjaw1.render(f5);
		lowerjaw2.render(f5);
		teethleft.render(f5);
		teethright.render(f5);
		frontleft1.render(f5);
		frontright1.render(f5);
		frontleft2.render(f5);
		frontright2.render(f5);
		backleft1.render(f5);
		backright1.render(f5);
		backleft2.render(f5);
		backright2.render(f5);
		toefrontleft1.render(f5);
		toefrontright1.render(f5);
		toefrontleft2.render(f5);
		toefrontright2.render(f5);
		toefrontleft3.render(f5);
		toefrontright3.render(f5);
		toebackleft1.render(f5);
		toebackright1.render(f5);
		toebackleft2.render(f5);
		toebackright2.render(f5);
		toebackleft3.render(f5);
		toebackright3.render(f5);
		toebackleft4.render(f5);
		toebackright4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

		EntityEryops eryops = (EntityEryops) entity;

		float lookingMovement = 0.012F * par5;
		float naturalHeadMovement = 0.025F * (1.0F - MathHelper.sin(0.05F * par3));
		float naturalLowerJawMovement = 0.010F * (1.0F - 0.5F * MathHelper.sin(0.05F * par3));
		float angryJawMovement = 0.25F * eryops.getMouthAngle();
		
		head1.rotateAngleX = lookingMovement + naturalHeadMovement - angryJawMovement + 0.03718F;
		head2.rotateAngleX = lookingMovement + naturalHeadMovement - angryJawMovement + 0.03718F;
		snout1.rotateAngleX = lookingMovement + naturalHeadMovement - angryJawMovement + 0.14F;
		snout2.rotateAngleX = lookingMovement + naturalHeadMovement - angryJawMovement + 0.10F;
		upperjaw.rotateAngleX = lookingMovement + naturalHeadMovement - angryJawMovement + 0.04F;

		lowerjaw1.rotateAngleX = lookingMovement + naturalLowerJawMovement + 0.5F * angryJawMovement +  0.038F;
		lowerjaw2.rotateAngleX = lookingMovement + naturalLowerJawMovement + 0.5F * angryJawMovement - 0.14F;
		teethleft.rotateAngleX = lookingMovement + naturalHeadMovement + 0.5F * angryJawMovement;
		teethright.rotateAngleX = lookingMovement + naturalHeadMovement + 0.5F * angryJawMovement;
		
		lookingMovement = 0.02F * par6;
		head1.rotateAngleY = lookingMovement;
		head2.rotateAngleY = lookingMovement;
		snout1.rotateAngleY = lookingMovement;
		snout2.rotateAngleY = lookingMovement;
		upperjaw.rotateAngleY = lookingMovement;
		lowerjaw1.rotateAngleY = lookingMovement;
		lowerjaw2.rotateAngleY = lookingMovement;
		teethleft.rotateAngleY = lookingMovement;
		teethright.rotateAngleY = lookingMovement;

		tail4.rotateAngleX = 0.060F * MathHelper.sin(0.025F * par3);
		tail4.rotateAngleY = 0.150F * MathHelper.sin(0.05F * par3);
		tail3.rotateAngleX = 0.045F * MathHelper.sin(0.025F * par3 + 0.2F);
		tail3.rotateAngleY = 0.1125F * MathHelper.sin(0.05F * par3 + 0.2F);
		tail2.rotateAngleX = 0.030F * MathHelper.sin(0.025F * par3 + 0.4F);
		tail2.rotateAngleY = 0.1125F * MathHelper.sin(0.05F * par3 + 0.4F);
		tail1.rotateAngleX = 0.02F * MathHelper.sin(0.025F * par3 + 0.6F);
		tail1.rotateAngleY = 0.0325F * MathHelper.sin(0.05F * par3 + 0.6F);
	}
}
