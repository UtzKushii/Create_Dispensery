package net.utzquishii.createdispensery.registry;

import net.utzquishii.createdispensery.CreateDispenseryMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("all")
public class DispenseryCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> REG = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateDispenseryMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DISPENSERY_TAB = REG.register("vending_machine_tab", () -> CreativeModeTab.builder()
            .title(Component.translatableWithFallback("itemGroup.createdispensery.vending_machine_tab", "Create: Dispensery"))
            .icon(() -> new ItemStack(DispenseryBlocks.VENDING_MACHINE.get()))
            .displayItems((params, output) -> {
                output.accept(DispenseryItems.VENDING_MACHINE.get());
            })

            .build());

    public static void register(IEventBus eventBus) {
        REG.register(eventBus);
    }
}
