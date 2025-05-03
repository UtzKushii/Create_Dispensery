package net.utzquishii.createdispensery.content.blocks.vending_machine;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityVisual;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.utzquishii.createdispensery.registry.DispenseryPartialModels;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class VendingMachineVisual extends KineticBlockEntityVisual<VendingMachineBE> {

    protected final RotatingInstance rotatingModel;

    // Gotta call my parents to construct my shaft rq
    public VendingMachineVisual(VisualizationContext context, VendingMachineBE blockEntity, float partialTick) {
        super(context, blockEntity, partialTick);
        rotatingModel = instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(DispenseryPartialModels.SHAFT_TINY, Direction.UP))
                .createInstance()
                .rotateToFace(Direction.DOWN, rotationAxis())
                .setup(blockEntity)
                .setPosition(getVisualPosition());

        rotatingModel.setChanged();
    }

    @Override
    public void update(float pt) {
        rotatingModel.setup(blockEntity)
                .setChanged();
    }

    @Override
    public void updateLight(float partialTick) {
        relight(rotatingModel);
    }

    @Override
    protected void _delete() {
        rotatingModel.delete();
    }

    @Override
    public void collectCrumblingInstances(Consumer<Instance> consumer) {
        consumer.accept(rotatingModel);
    }
}

