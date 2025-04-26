package net.utzquishii.createdispensery.content.blocks.vending_machine;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;

public class VendingMachineVisual extends SingleAxisRotatingVisual<VendingMachineBE> {
    // Gotta call my parents to construct my shaft rq
    public VendingMachineVisual(VisualizationContext context, VendingMachineBE blockEntity, float partialTick) {
        super(context, blockEntity, partialTick, Models.partial(AllPartialModels.SHAFT_HALF, Direction.DOWN));
    }

}

