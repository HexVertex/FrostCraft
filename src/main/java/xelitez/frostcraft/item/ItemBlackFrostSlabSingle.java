package xelitez.frostcraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xelitez.frostcraft.registry.IdMap;

public class ItemBlackFrostSlabSingle extends ItemSlab
{
	public ItemBlackFrostSlabSingle(Block block) 
	{
		super(block, IdMap.blockBlackFrostSingleSlabSet, IdMap.blockBlackFrostDoubleSlabSet, false);
	}
	
	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_1_.stackSize == 0)
        {
            return false;
        }
        else if (!p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_))
        {
            return false;
        }
        else
        {
            Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
            int i1 = p_77648_3_.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_);
            int j1 = i1 & 3;
            boolean flag = (i1 & 4) != 0;

            if ((p_77648_7_ == 1 && !flag || p_77648_7_ == 0 && flag) && block == IdMap.blockBlackFrostSingleSlabSet && j1 == p_77648_1_.getItemDamage())
            {
                if (p_77648_3_.checkNoEntityCollision(IdMap.blockBlackFrostDoubleSlabSet.getCollisionBoundingBoxFromPool(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_)) && p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, IdMap.blockBlackFrostDoubleSlabSet, j1, 3))
                {
                    p_77648_3_.playSoundEffect((double)((float)p_77648_4_ + 0.5F), (double)((float)p_77648_5_ + 0.5F), (double)((float)p_77648_6_ + 0.5F), IdMap.blockBlackFrostDoubleSlabSet.stepSound.func_150496_b(), (IdMap.blockBlackFrostDoubleSlabSet.stepSound.getVolume() + 1.0F) / 2.0F, IdMap.blockBlackFrostDoubleSlabSet.stepSound.getPitch() * 0.8F);
                    --p_77648_1_.stackSize;
                }

                return true;
            }
            else
            {
                return this.func_150946_a(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_) ? true : super.onItemUse(p_77648_1_, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
            }
        }
    }
	
    private boolean func_150946_a(ItemStack p_150946_1_, EntityPlayer p_150946_2_, World p_150946_3_, int p_150946_4_, int p_150946_5_, int p_150946_6_, int p_150946_7_)
    {
        if (p_150946_7_ == 0)
        {
            --p_150946_5_;
        }

        if (p_150946_7_ == 1)
        {
            ++p_150946_5_;
        }

        if (p_150946_7_ == 2)
        {
            --p_150946_6_;
        }

        if (p_150946_7_ == 3)
        {
            ++p_150946_6_;
        }

        if (p_150946_7_ == 4)
        {
            --p_150946_4_;
        }

        if (p_150946_7_ == 5)
        {
            ++p_150946_4_;
        }

        Block block = p_150946_3_.getBlock(p_150946_4_, p_150946_5_, p_150946_6_);
        int i1 = p_150946_3_.getBlockMetadata(p_150946_4_, p_150946_5_, p_150946_6_);
        int j1 = i1 & 3;

        if (block == IdMap.blockBlackFrostSingleSlabSet && j1 == p_150946_1_.getItemDamage())
        {
            if (p_150946_3_.checkNoEntityCollision(IdMap.blockBlackFrostDoubleSlabSet.getCollisionBoundingBoxFromPool(p_150946_3_, p_150946_4_, p_150946_5_, p_150946_6_)) && p_150946_3_.setBlock(p_150946_4_, p_150946_5_, p_150946_6_, IdMap.blockBlackFrostDoubleSlabSet, j1, 3))
            {
                p_150946_3_.playSoundEffect((double)((float)p_150946_4_ + 0.5F), (double)((float)p_150946_5_ + 0.5F), (double)((float)p_150946_6_ + 0.5F), IdMap.blockBlackFrostDoubleSlabSet.stepSound.func_150496_b(), (IdMap.blockBlackFrostDoubleSlabSet.stepSound.getVolume() + 1.0F) / 2.0F, IdMap.blockBlackFrostDoubleSlabSet.stepSound.getPitch() * 0.8F);
                --p_150946_1_.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_150936_a(World p_150936_1_, int p_150936_2_, int p_150936_3_, int p_150936_4_, int p_150936_5_, EntityPlayer p_150936_6_, ItemStack p_150936_7_)
    {
        int i1 = p_150936_2_;
        int j1 = p_150936_3_;
        int k1 = p_150936_4_;
        Block block = p_150936_1_.getBlock(p_150936_2_, p_150936_3_, p_150936_4_);
        int l1 = p_150936_1_.getBlockMetadata(p_150936_2_, p_150936_3_, p_150936_4_);
        int i2 = l1 & 3;
        boolean flag = (l1 & 4) != 0;

        if ((p_150936_5_ == 1 && !flag || p_150936_5_ == 0 && flag) && block == IdMap.blockBlackFrostSingleSlabSet && i2 == p_150936_7_.getItemDamage())
        {
            return true;
        }
        else
        {
            if (p_150936_5_ == 0)
            {
                --p_150936_3_;
            }

            if (p_150936_5_ == 1)
            {
                ++p_150936_3_;
            }

            if (p_150936_5_ == 2)
            {
                --p_150936_4_;
            }

            if (p_150936_5_ == 3)
            {
                ++p_150936_4_;
            }

            if (p_150936_5_ == 4)
            {
                --p_150936_2_;
            }

            if (p_150936_5_ == 5)
            {
                ++p_150936_2_;
            }

            Block block1 = p_150936_1_.getBlock(p_150936_2_, p_150936_3_, p_150936_4_);
            int j2 = p_150936_1_.getBlockMetadata(p_150936_2_, p_150936_3_, p_150936_4_);
            i2 = j2 & 3;
            return block1 == IdMap.blockBlackFrostSingleSlabSet && i2 == p_150936_7_.getItemDamage() ? true : super.func_150936_a(p_150936_1_, i1, j1, k1, p_150936_5_, p_150936_6_, p_150936_7_);
        }
    }

}
