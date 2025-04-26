package net.utzquishii.createdispensery.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.utzquishii.createdispensery.CreateDispenseryMod;
import net.utzquishii.createdispensery.content.blocks.vending_machine.VendingMachineBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;

public class DispenseryBlocks {
    public static final CreateRegistrate REGISTRATE = CreateDispenseryMod.REGISTRATE;

    public static final BlockEntry<VendingMachineBlock> VENDING_MACHINE = REGISTRATE
            .block("vending_machine", VendingMachineBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK) // Use properties of the IRON_BLOCK
            .properties(p -> p.mapColor(MapColor.METAL).strength(4.0f).noOcclusion()) // Additional properties

            .register();

    public static void register() {
        // No-op; all registrations are handled by CreateRegistrate
    }
}

