package genesis.world;

import genesis.Genesis;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderGenesis extends WorldProvider {
    /**
     * Array for sunrise/sunset colors (RGBA)
     */
    private float[] colorsSunriseSunset = new float[4];
    /*======================================= Forge Start =========================================*/
    private IRenderHandler skyRenderer = null;
    private IRenderHandler cloudRenderer = null;
    private IRenderHandler weatherRenderer = null;

    public static WorldProvider getProviderForDimension(int par0) {
        return DimensionManager.createProviderFor(par0);
    }

    /**
     * Creates the light to brightness table
     */
    @Override
    protected void generateLightBrightnessTable() {
        float f = 0.0F;

        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float) i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    /**
     * creates a new world chunk manager for WorldProvider
     */
    @Override
    protected void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerGenesis(worldObj);
        this.dimensionId = Genesis.dimensionID;
    }

    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    @Override
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderGenesis(worldObj, worldObj.getSeed());
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2) {
        return this.worldObj.getTopBlock(par1, par2) == Blocks.grass;
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    @Override
    public float calculateCelestialAngle(long par1, float par3) {
        int j = (int) (par1 % 34000L);
        float f1 = ((float) j + par3) / 34000.0F - 0.25F;

        if (f1 < 0.0F) {
            ++f1;
        }

        if (f1 > 1.0F) {
            --f1;
        }

        float f2 = f1;
        f1 = 1.0F - (float) ((Math.cos((double) f1 * Math.PI) + 1.0D) / 2.0D);
        f1 = f2 + (f1 - f2) / 3.0F;
        return f1;
    }

    @Override
    public int getMoonPhase(long par1) {
        return (int) (par1 / 34000L % 8L + 8L) % 8;
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    /**
     * Returns array with sunrise/sunset colors
     */
    @Override
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float par1, float par2) {
        float f2 = 0.4F;
        float f3 = MathHelper.cos(par1 * (float) Math.PI * 2.0F) - 0.0F;
        float f4 = -0.0F;

        if (f3 >= f4 - f2 && f3 <= f4 + f2) {
            float f5 = (f3 - f4) / f2 * 0.5F + 0.5F;
            float f6 = 1.0F - (1.0F - MathHelper.sin(f5 * (float) Math.PI)) * 0.99F;
            f6 *= f6;
            this.colorsSunriseSunset[0] = f5 * 0.3F + 0.7F;
            this.colorsSunriseSunset[1] = f5 * f5 * 0.7F + 0.2F;
            this.colorsSunriseSunset[2] = f5 * f5 * 0.0F + 0.2F;
            this.colorsSunriseSunset[3] = f6;
            return this.colorsSunriseSunset;
        } else {
            return null;
        }
    }

    /**
     * Return Vec3D with biome specific fog color
     */
    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        float f2 = MathHelper.cos(par1 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        float f3 = 0.7529412F;
        float f4 = 0.84705883F;
        float f5 = 1.0F;
        f3 *= f2 * 0.94F + 0.06F;
        f4 *= f2 * 0.94F + 0.06F;
        f5 *= f2 * 0.91F + 0.09F;
        return Vec3.createVectorHelper((double) f3, (double) f4, (double) f5);
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    @Override
    public boolean canRespawnHere() {
        return true;
    }

    /**
     * the y level at which clouds are rendered.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return this.terrainType.getCloudHeight();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return true;
    }

    /**
     * Gets the hard-coded portal location to use when entering this dimension.
     */
    @Override
    public ChunkCoordinates getEntrancePortalLocation() {
        return null;
    }

    @Override
    public int getAverageGroundLevel() {
        return this.terrainType.getMinimumSpawnHeight(this.worldObj);
    }

    /**
     * returns true if this dimension is supposed to display void particles and pull in the far plane based on the
     * user's Y offset.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean getWorldHasVoidParticles() {
        return this.terrainType.hasVoidParticles(this.hasNoSky);
    }

    /**
     * Returns a double value representing the Y value relative to the top of the map at which void fog is at its
     * maximum. The default factor of 0.03125 relative to 256, for example, means the void fog will be at its maximum at
     * (256*0.03125), or 8.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public double getVoidFogYFactor() {
        return this.terrainType.voidFadeMagnitude();
    }

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int par1, int par2) {
        return false;
    }

    /**
     * Sets the providers current dimension ID, used in default getSaveFolder()
     * Added to allow default providers to be registered for multiple dimensions.
     *
     * @param dim Dimension ID
     */
    @Override
    public void setDimension(int dim) {
        this.dimensionId = dim;
    }

    /**
     * Returns the sub-folder of the world folder that this WorldProvider saves to.
     * EXA: DIM1, DIM-1
     *
     * @return The sub-folder name to save this world's chunks to.
     */
    @Override
    public String getSaveFolder() {
        return (dimensionId == 0 ? null : "DIM" + dimensionId);
    }

    /**
     * A message to display to the user when they transfer to this dimension.
     *
     * @return The message to be displayed
     */
    @Override
    public String getWelcomeMessage() {
        return null;
    }

    /**
     * A Message to display to the user when they transfer out of this dismension.
     *
     * @return The message to be displayed
     */
    @Override
    public String getDepartMessage() {
        return null;
    }

    /**
     * The dimensions movement factor. Relative to normal overworld.
     * It is applied to the players position when they transfer dimensions.
     * Exa: Nether movement is 8.0
     *
     * @return The movement factor
     */
    @Override
    public double getMovementFactor() {
        return 1.0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        return this.skyRenderer;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setSkyRenderer(IRenderHandler skyRenderer) {
        this.skyRenderer = skyRenderer;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getCloudRenderer() {
        return cloudRenderer;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setCloudRenderer(IRenderHandler renderer) {
        cloudRenderer = renderer;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getWeatherRenderer() {
        return weatherRenderer;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setWeatherRenderer(IRenderHandler renderer) {
        weatherRenderer = renderer;
    }

    @Override
    public ChunkCoordinates getRandomizedSpawnPoint() {
        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(this.worldObj.getSpawnPoint());

        boolean isAdventure = worldObj.getWorldInfo().getGameType() == GameType.ADVENTURE;
        int spawnFuzz = terrainType.getSpawnFuzz();
        int spawnFuzzHalf = spawnFuzz / 2;

        if (!hasNoSky && !isAdventure) {
            chunkcoordinates.posX += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
            chunkcoordinates.posZ += this.worldObj.rand.nextInt(spawnFuzz) - spawnFuzzHalf;
            chunkcoordinates.posY = this.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
        }

        return chunkcoordinates;
    }

    /**
     * Determine if the cusor on the map should 'spin' when rendered, like it does for the player in the nether.
     *
     * @param entity The entity holding the map, playername, or frame-ENTITYID
     * @param x      X Position
     * @param y      Y Position
     * @param z      Z Postion
     * @return True to 'spin' the cursor
     */
    @Override
    public boolean shouldMapSpin(String entity, double x, double y, double z) {
        return false;
    }

    /**
     * Determines the dimension the player will be respawned in, typically this brings them back to the overworld.
     *
     * @param player The player that is respawning
     * @return The dimension to respawn the player in
     */
    @Override
    public int getRespawnDimension(EntityPlayerMP player) {
        return Genesis.dimensionID;
    }

    /*======================================= Start Moved From World =========================================*/

    @Override
    public boolean isDaytime() {
        return worldObj.skylightSubtracted < 4;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        return worldObj.getSkyColorBody(cameraEntity, partialTicks);
    }

    @Override
    public long getWorldTime() {
        return worldObj.getWorldInfo().getWorldTime();
    }

    @Override
    public void setWorldTime(long time) {
        worldObj.getWorldInfo().setWorldTime(time);
    }

    @Override
    public int getHeight() {
        return 256;
    }

    @Override
    public int getActualHeight() {
        return hasNoSky ? 128 : 256;
    }

    @Override
    public double getHorizon() {
        return worldObj.getWorldInfo().getTerrainType().getHorizon(worldObj);
    }

    @Override
    public String getDimensionName() {
        return "Genesis";
    }
}