package net.utzquishii.createdispensery.registry;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.OrientedRotatingVisual;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.utzquishii.createdispensery.CreateDispenseryMod;
import net.utzquishii.createdispensery.content.blocks.vending_machine.VendingMachineBE;
import net.utzquishii.createdispensery.content.blocks.vending_machine.VendingMachineRenderer;

public class DispenseryBETypes {

    private static final CreateRegistrate REGISTRATE = CreateDispenseryMod.registrate();

    // Register the Vending Machine Block Entity
    public static final BlockEntityEntry<VendingMachineBE> VENDING_MACHINE = REGISTRATE
            .blockEntity("vending_machine", VendingMachineBE::new)
            .visual(() -> OrientedRotatingVisual.of(AllPartialModels.SHAFT_HALF), false)
            .validBlocks(DispenseryBlocks.VENDING_MACHINE)
            .renderer(() -> VendingMachineRenderer::new)
            .register();

    public static void register() {}
}