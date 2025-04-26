package net.utzquishii.createdispensery.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.utzquishii.createdispensery.CreateDispenseryMod;
import net.utzquishii.createdispensery.content.blocks.vending_machine.VendingMachineBE;
import net.utzquishii.createdispensery.content.blocks.vending_machine.VendingMachineRenderer;
import net.utzquishii.createdispensery.content.blocks.vending_machine.VendingMachineVisual;

public class DispenseryBETypes {

    private static final CreateRegistrate REGISTRATE = CreateDispenseryMod.registrate();

    // Register the Vending Machine Block Entity
    public static final BlockEntityEntry<VendingMachineBE> VENDING_MACHINE = REGISTRATE
            .blockEntity("vending_machine", VendingMachineBE::new)
            .validBlocks(DispenseryBlocks.VENDING_MACHINE)
            .renderer(() -> VendingMachineRenderer::new)
            .visual(() -> VendingMachineVisual::new)
            .register();

    public static void register() {
    }
}
