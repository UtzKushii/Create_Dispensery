package net.utzquishii.createdispensery.content.blocks.vending_machine;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class VendingMachineBE extends KineticBlockEntity {

    public VendingMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    //Kinetics
    @Override
    public float calculateStressApplied() {
        // Optionally override to add custom stress values
        return super.calculateStressApplied();
    }

    @Override
    public float calculateAddedStressCapacity() {
        // Optionally override to change stress capacity
        return super.calculateAddedStressCapacity();
    }
}
