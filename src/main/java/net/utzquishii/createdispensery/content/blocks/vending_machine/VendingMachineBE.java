package net.utzquishii.createdispensery.content.blocks.vending_machine;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class VendingMachineBE extends KineticBlockEntity {

    public VendingMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public float calculateStressApplied() {
        return 2; // Adjust as necessary
    }

    public boolean flag() {
        var network = getOrCreateNetwork();
        if (network == null || network.members == null || network.members.isEmpty()) return false;
        return Math.abs(getSpeed()) > AllConfigs.server().kinetics.mediumSpeed.get()
                .floatValue() && network.getActualStressOf(this) > 0;
    }

    @Override
    public void tick() {
        super.tick();
        // Handle kinetic-related logic if needed
        var level = getLevel();
        if (level == null) return;
        if (flag()) {
            level.setBlockAndUpdate(getBlockPos().above().above(), Blocks.DIAMOND_BLOCK.defaultBlockState());
        }
    }
}