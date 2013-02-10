package xelitez.frostcraft.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.StatCollector;

public class EntityDamageSourceIndirectFrost extends EntityDamageSource
{
    private Entity indirectEntity;

    public EntityDamageSourceIndirectFrost(Entity par2Entity, Entity par3Entity)
    {
        super("frost", par2Entity);
        this.indirectEntity = par3Entity;
    }

    public Entity getSourceOfDamage()
    {
        return this.damageSourceEntity;
    }

    public Entity getEntity()
    {
        return this.indirectEntity;
    }

    /**
     * Returns the message to be displayed on player death.
     */
    public String getDeathMessage(EntityPlayer par1EntityPlayer)
    {
        return par1EntityPlayer.username + " got killed by frost effect " + (this.indirectEntity != null ? this.indirectEntity instanceof EntityPlayer ? "from " + ((EntityPlayer)this.indirectEntity).username : "from " + StatCollector.translateToLocal(this.indirectEntity.getEntityName()) : "from " + StatCollector.translateToLocal(this.damageSourceEntity.getEntityName()));
    }

}
