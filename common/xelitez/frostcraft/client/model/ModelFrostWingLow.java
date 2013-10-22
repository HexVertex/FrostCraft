package xelitez.frostcraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelFrostWingLow extends ModelBase
{
	public ModelRenderer modelLegLeft;
	public ModelRenderer modelLegRight;
	public ModelRenderer modelBody;
	public ModelRenderer modelHead;
	public ModelRenderer modelWingLeft;
	public ModelRenderer modelWingRight;

	public ModelFrostWingLow()
	{
		this(0.0F, 0.0F, 0, 0);
	}
	
	public ModelFrostWingLow(float par1, float par2, int par3, int par4)
	{
        this.textureWidth = par3;
        this.textureHeight = par4;
	}
}
