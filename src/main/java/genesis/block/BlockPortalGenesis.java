package genesis.block;

import genesis.Genesis;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPortalGenesis extends BlockBreakable {
    public static final int[][] field_150001_a = new int[][]{new int[0], {3, 1}, {2, 0}};

    public BlockPortalGenesis() {
        super(Material.portal, false);
        setTickRandomly(true);
    }

    public static int getMetadata(int metadata) {
        return metadata & 3;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);

        if (world.provider.isSurfaceWorld() && world.getGameRules().getGameRuleBooleanValue("doMobSpawning") && random.nextInt(2000) < world.difficultySetting.getDifficultyId()) {
            int l;

            for (l = y; !World.doesBlockHaveSolidTopSurface(world, x, l, z) && l > 0; --l) {
                ;
            }

            if (l > 0 && !world.getBlock(x, l + 1, z).isNormalCube()) {
                Entity entity = ItemMonsterPlacer.spawnCreature(world, 57, (double) x + 0.5D, (double) l + 1.1D, (double) z + 0.5D);

                if (entity != null) {
                    entity.timeUntilPortal = entity.getPortalCooldown();
                }
            }
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int l = getMetadata(world.getBlockMetadata(x, y, z));

        if (l == 0) {
            if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this) {
                l = 2;
            } else {
                l = 1;
            }

            if (world instanceof World && !((World) world).isRemote) {
                ((World) world).setBlockMetadataWithNotify(x, y, z, l, 2);
            }
        }

        float f = 0.125F;
        float f1 = 0.125F;

        if (l == 1) {
            f = 0.5F;
        }

        if (l == 2) {
            f1 = 0.5F;
        }

        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean func_150000_e(World world, int x, int y, int z) {
        BlockPortalGenesis.Size size = new BlockPortalGenesis.Size(world, x, y, z, 1);
        BlockPortalGenesis.Size size1 = new BlockPortalGenesis.Size(world, x, y, z, 2);

        if (size.func_150860_b() && size.field_150864_e == 0) {
            size.func_150859_c();
            return true;
        } else if (size1.func_150860_b() && size1.field_150864_e == 0) {
            size1.func_150859_c();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        int l = getMetadata(world.getBlockMetadata(x, y, z));
        BlockPortalGenesis.Size size = new BlockPortalGenesis.Size(world, x, y, z, 1);
        BlockPortalGenesis.Size size1 = new BlockPortalGenesis.Size(world, x, y, z, 2);

        if (l == 1 && (!size.func_150860_b() || size.field_150864_e < size.field_150868_h * size.field_150862_g)) {
            world.setBlock(x, y, z, Blocks.air);
        } else if (l == 2 && (!size1.func_150860_b() || size1.field_150864_e < size1.field_150868_h * size1.field_150862_g)) {
            world.setBlock(x, y, z, Blocks.air);
        } else if (l == 0 && !size.func_150860_b() && !size1.func_150860_b()) {
            world.setBlock(x, y, z, Blocks.air);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int block, int x, int y, int z) {
        int i1 = 0;

        if (world.getBlock(block, x, y) == this) {
            i1 = getMetadata(world.getBlockMetadata(block, x, y));

            if (i1 == 0) {
                return false;
            }

            if (i1 == 2 && z != 5 && z != 4) {
                return false;
            }

            if (i1 == 1 && z != 3 && z != 2) {
                return false;
            }
        }

        boolean flag = world.getBlock(block - 1, x, y) == this && world.getBlock(block - 2, x, y) != this;
        boolean flag1 = world.getBlock(block + 1, x, y) == this && world.getBlock(block + 2, x, y) != this;
        boolean flag2 = world.getBlock(block, x, y - 1) == this && world.getBlock(block, x, y - 2) != this;
        boolean flag3 = world.getBlock(block, x, y + 1) == this && world.getBlock(block, x, y + 2) != this;
        boolean flag4 = flag || flag1 || i1 == 1;
        boolean flag5 = flag2 || flag3 || i1 == 2;
        return flag4 && z == 4 ? true : (flag4 && z == 5 ? true : (flag5 && z == 2 ? true : flag5 && z == 3));
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (entity.ridingEntity == null && entity.riddenByEntity == null) {
            entity.travelToDimension(Genesis.dimensionID);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (random.nextInt(100) == 0) {
            world.playSound((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "portal.portal", 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int l = 0; l < 4; ++l) {
            double d0 = (double) ((float) x + random.nextFloat());
            double d1 = (double) ((float) y + random.nextFloat());
            double d2 = (double) ((float) z + random.nextFloat());
            double d3 = ((double) random.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double) random.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double) random.nextFloat() - 0.5D) * 0.5D;
            int i1 = random.nextInt(2) * 2 - 1;

            if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this) {
                d0 = (double) x + 0.5D + 0.25D * (double) i1;
                d3 = (double) (random.nextFloat() * 2.0F * (float) i1);
            } else {
                d2 = (double) z + 0.5D + 0.25D * (double) i1;
                d5 = (double) (random.nextFloat() * 2.0F * (float) i1);
            }

            world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemById(0);
    }

    public static class Size {
        private final World field_150867_a;
        private final int field_150865_b;
        private final int field_150866_c;
        private final int field_150863_d;
        private int field_150864_e = 0;
        private ChunkCoordinates field_150861_f;
        private int field_150862_g;
        private int field_150868_h;

        public Size(World p_i45415_1_, int p_i45415_2_, int p_i45415_3_, int p_i45415_4_, int p_i45415_5_) {
            this.field_150867_a = p_i45415_1_;
            this.field_150865_b = p_i45415_5_;
            this.field_150863_d = BlockPortalGenesis.field_150001_a[p_i45415_5_][0];
            this.field_150866_c = BlockPortalGenesis.field_150001_a[p_i45415_5_][1];

            for (int i1 = p_i45415_3_; p_i45415_3_ > i1 - 21 && p_i45415_3_ > 0 && this.func_150857_a(p_i45415_1_.getBlock(p_i45415_2_, p_i45415_3_ - 1, p_i45415_4_)); --p_i45415_3_) {
                ;
            }

            int j1 = this.func_150853_a(p_i45415_2_, p_i45415_3_, p_i45415_4_, this.field_150863_d) - 1;

            if (j1 >= 0) {
                this.field_150861_f = new ChunkCoordinates(p_i45415_2_ + j1 * Direction.offsetX[this.field_150863_d], p_i45415_3_, p_i45415_4_ + j1 * Direction.offsetZ[this.field_150863_d]);
                this.field_150868_h = this.func_150853_a(this.field_150861_f.posX, this.field_150861_f.posY, this.field_150861_f.posZ, this.field_150866_c);

                if (this.field_150868_h < 2 || this.field_150868_h > 21) {
                    this.field_150861_f = null;
                    this.field_150868_h = 0;
                }
            }

            if (this.field_150861_f != null) {
                this.field_150862_g = this.func_150858_a();
            }
        }

        protected int func_150853_a(int p_150853_1_, int p_150853_2_, int p_150853_3_, int p_150853_4_) {
            int j1 = Direction.offsetX[p_150853_4_];
            int k1 = Direction.offsetZ[p_150853_4_];
            int i1;
            Block block;

            for (i1 = 0; i1 < 22; ++i1) {
                block = field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);

                if (!this.func_150857_a(block)) {
                    break;
                }

                Block block1 = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_ - 1, p_150853_3_ + k1 * i1);

                if (block1 != Blocks.obsidian) {
                    break;
                }
            }

            block = this.field_150867_a.getBlock(p_150853_1_ + j1 * i1, p_150853_2_, p_150853_3_ + k1 * i1);
            return block == Blocks.obsidian ? i1 : 0;
        }

        protected int func_150858_a() {
            int i;
            int j;
            int k;
            int l;
            label56:

            for (this.field_150862_g = 0; this.field_150862_g < 21; ++this.field_150862_g) {
                i = this.field_150861_f.posY + this.field_150862_g;

                for (j = 0; j < this.field_150868_h; ++j) {
                    k = this.field_150861_f.posX + j * Direction.offsetX[BlockPortalGenesis.field_150001_a[this.field_150865_b][1]];
                    l = this.field_150861_f.posZ + j * Direction.offsetZ[BlockPortalGenesis.field_150001_a[this.field_150865_b][1]];
                    Block block = this.field_150867_a.getBlock(k, i, l);

                    if (!this.func_150857_a(block)) {
                        break label56;
                    }

                    if (block == Blocks.portal) {
                        ++this.field_150864_e;
                    }

                    if (j == 0) {
                        block = this.field_150867_a.getBlock(k + Direction.offsetX[BlockPortalGenesis.field_150001_a[this.field_150865_b][0]], i, l + Direction.offsetZ[BlockPortalGenesis.field_150001_a[this.field_150865_b][0]]);

                        if (block != Blocks.obsidian) {
                            break label56;
                        }
                    } else if (j == this.field_150868_h - 1) {
                        block = this.field_150867_a.getBlock(k + Direction.offsetX[BlockPortalGenesis.field_150001_a[this.field_150865_b][1]], i, l + Direction.offsetZ[BlockPortalGenesis.field_150001_a[this.field_150865_b][1]]);

                        if (block != Blocks.obsidian) {
                            break label56;
                        }
                    }
                }
            }

            for (i = 0; i < this.field_150868_h; ++i) {
                j = this.field_150861_f.posX + i * Direction.offsetX[BlockPortalGenesis.field_150001_a[this.field_150865_b][1]];
                k = this.field_150861_f.posY + this.field_150862_g;
                l = this.field_150861_f.posZ + i * Direction.offsetZ[BlockPortalGenesis.field_150001_a[this.field_150865_b][1]];

                if (this.field_150867_a.getBlock(j, k, l) != Blocks.obsidian) {
                    this.field_150862_g = 0;
                    break;
                }
            }

            if (this.field_150862_g <= 21 && this.field_150862_g >= 3) {
                return this.field_150862_g;
            } else {
                this.field_150861_f = null;
                this.field_150868_h = 0;
                this.field_150862_g = 0;
                return 0;
            }
        }

        protected boolean func_150857_a(Block p_150857_1_) {
            return p_150857_1_.getMaterial() == Material.air || p_150857_1_ == Blocks.fire || p_150857_1_ == Blocks.portal;
        }

        public boolean func_150860_b() {
            return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
        }

        public void func_150859_c() {
            for (int i = 0; i < this.field_150868_h; ++i) {
                int j = this.field_150861_f.posX + Direction.offsetX[this.field_150866_c] * i;
                int k = this.field_150861_f.posZ + Direction.offsetZ[this.field_150866_c] * i;

                for (int l = 0; l < this.field_150862_g; ++l) {
                    int i1 = this.field_150861_f.posY + l;
                    this.field_150867_a.setBlock(j, i1, k, Blocks.portal, this.field_150865_b, 2);
                }
            }
        }
    }
}