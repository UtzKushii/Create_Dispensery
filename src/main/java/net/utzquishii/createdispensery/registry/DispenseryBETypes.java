package net.utzquishii.createdispensery.registry;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.core.Direction;
import net.utzquishii.createdispensery.CreateDispensery;
import net.utzquishii.createdispensery.content.blocks.vending_machine.VendingMachineBE;
import net.utzquishii.createdispensery.content.blocks.vending_machine.VendingMachineRenderer;
import net.utzquishii.createdispensery.content.blocks.vending_machine.VendingMachineVisual;

public class DispenseryBETypes {

    private static final CreateRegistrate REGISTRATE = CreateDispensery.registrate();

    // Registers the bottom half of the Vending Machine Block Entity
    public static final BlockEntityEntry<VendingMachineBE> VENDING_MACHINE = REGISTRATE
            .blockEntity("vending_machine", VendingMachineBE::new)
            .visual(() -> VendingMachineVisual::new, false )
            .validBlocks(DispenseryBlocks.VENDING_MACHINE)
            .renderer(() -> VendingMachineRenderer::new)
            .register();

    public static void register() {}
}