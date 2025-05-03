package net.utzquishii.createdispensery;

import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.utzquishii.createdispensery.registry.DispenseryPartialModels;

//@Mod(value = CreateDispensery.MOD_ID, dist = Dist.CLIENT)
public class CreateDispenseryClient {

    public static void clientInit(FMLClientSetupEvent event) {
        DispenseryPartialModels.init();

        //PonderIndex.addPlugin(new LuncheonPonderPlugin());
    }
}