package net.utzquishii.createdispensery;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.utzquishii.createdispensery.registry.DispenseryBETypes;
import net.utzquishii.createdispensery.content.blocks.vending_machine.VendingMachineRenderer;

@Mod(value = CreateDispenseryMod.MOD_ID, dist = Dist.CLIENT)
public class CreateDispenseryClient {

    /** public CreateDispenseryClient(IEventBus modEventBus) {
        // Add Client-side event listener for renderer registration
        modEventBus.addListener(CreateDispenseryClient::onRegisterRenderers);
    }

    public static void onRegisterRenderers(RegisterRenderers event) {
        // Use the event to register your custom renderer
        event.registerBlockEntityRenderer(
                DispenseryBETypes.VENDING_MACHINE.get(), // Ensure this is a valid BlockEntityType
                VendingMachineRenderer::new // Render provider (constructor reference works here)
        );
    } **/
}