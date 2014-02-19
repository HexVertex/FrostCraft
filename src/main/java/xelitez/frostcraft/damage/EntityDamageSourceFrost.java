package xelitez.frostcraft.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class EntityDamageSourceFrost extends EntityDamageSource
{
    public EntityDamageSourceFrost(Entity par2Entity)
    {
        super("frost", par2Entity);
    }

    public Entity getSourceOfDamage()
    {
        return this.damageSourceEntity;
    }

    /**
     * Returns the message to be displayed on player death.
     */
    public IChatComponent func_151519_b(EntityLivingBase par1EntityLivingBase)
    {
        return new ChatComponentText(par1EntityLivingBase.getCommandSenderName() + " died of frost from " + StatCollector.translateToLocal(this.damageSourceEntity.getCommandSenderName()));
    }
}
