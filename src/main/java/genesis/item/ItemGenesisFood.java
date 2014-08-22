package genesis.item;

import genesis.common.Genesis;
import genesis.common.GenesisTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Arbiter
 */
public class ItemGenesisFood extends ItemFood {
    private Map<Float, PotionEffect> effects;

    public ItemGenesisFood(int hunger, float saturation) {
        this(hunger, saturation, false);
    }

    public ItemGenesisFood(int hunger, float saturation, boolean isWolfsFavoriteMeat) {
        super(hunger, saturation, isWolfsFavoriteMeat);
        effects = new HashMap<Float, PotionEffect>();
        setCreativeTab(GenesisTabs.tabGenesisFood);
    }

    /**
     * Adds a potion effect to the food. Can have multiple potion effects per food
     *
     * @param id        The potion id
     * @param duration  Duration in seconds (will be multiplied by 20)
     * @param amplifier Amplifier of the potion
     * @param chance    Chance of the potion effect happening ranging from 0.0 to 1.0
     */
    public ItemGenesisFood addPotionEffect(int id, int duration, int amplifier, float chance) {
        return (ItemGenesisFood) setPotionEffect(id, duration, amplifier, chance);
    }

    @Override
    public ItemFood setPotionEffect(int id, int duration, int amplifier, float chance) {
        effects.put(chance, new PotionEffect(id, duration * 20, amplifier));
        return this;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            for (Entry<Float, PotionEffect> entry : effects.entrySet()) {
                if (entry.getValue().getPotionID() > 0 && world.rand.nextFloat() < entry.getKey()) {
                    PotionEffect effect = entry.getValue();
                    player.addPotionEffect(new PotionEffect(effect.getPotionID(), effect.getDuration(), effect.getAmplifier())); // defensive copying
                }
            }
        }
    }

    @Override
    public Item setTextureName(String name) {
        return super.setTextureName(Genesis.ASSETS + name);
    }
}