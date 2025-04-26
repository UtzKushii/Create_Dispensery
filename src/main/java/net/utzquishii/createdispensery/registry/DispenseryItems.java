package net.utzquishii.createdispensery.registry;

import net.utzquishii.createdispensery.CreateDispenseryMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DispenseryItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateDispenseryMod.MOD_ID);

    public static final DeferredItem<BlockItem> VENDING_MACHINE = ITEMS.register(
            "vending_machine",
            () -> new BlockItem(DispenseryBlocks.VENDING_MACHINE.get(), new Item.Properties())
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
