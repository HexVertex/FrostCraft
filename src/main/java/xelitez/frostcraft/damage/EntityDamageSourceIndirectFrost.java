package xelitez.frostcraft.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class EntityDamageSourceIndirectFrost extends EntityDamageSource
{
    private Entity indirectEntity;

    public EntityDamageSourceIndirectFrost(Entity par2Entity, Entity par3Entity)
    {
        super("frost", par2Entity);
        this.indirectEntity = par3Entity;
    }

    @Override
    public Entity getSourceOfDamage()
    {
        return this.damageSourceEntity;
    }

    @Override
    public Entity getEntity()
    {
        return this.indirectEntity;
    }

    /**
     * Returns the message to be displayed on player death.
     */
    @Override
    public IChatComponent func_151519_b(EntityLivingBase par1EntityLivingBase)
    {
        return new ChatComponentText(par1EntityLivingBase.getCommandSenderName() + " got killed by frost effect " + (this.indirectEntity != null ? this.indirectEntity instanceof EntityPlayer ? "from " + this.indirectEntity.getCommandSenderName() : "from " + StatCollector.translateToLocal(this.indirectEntity.getCommandSenderName()) : "from " + StatCollector.translateToLocal(this.damageSourceEntity.getCommandSenderName())));
    }

}
