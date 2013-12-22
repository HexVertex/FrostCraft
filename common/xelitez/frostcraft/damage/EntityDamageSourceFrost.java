package xelitez.frostcraft.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EntityDamageSource;
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
    public ChatMessageComponent getDeathMessage(EntityLivingBase par1EntityLivingBase)
    {
        return ChatMessageComponent.createFromText(par1EntityLivingBase.getTranslatedEntityName() + " died of frost from " + StatCollector.translateToLocal(this.damageSourceEntity.getEntityName()));
    }
}
