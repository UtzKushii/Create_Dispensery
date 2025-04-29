package net.utzquishii.createdispensery.content.blocks.vending_machine;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.utzquishii.createdispensery.CreateDispenseryMod;

public class VendingMachineRenderer extends KineticBlockEntityRenderer<VendingMachineBE> {

    public VendingMachineRenderer(BlockEntityRendererProvider.Context dispatcher) {
        super(dispatcher);
    }

    @Override
    protected SuperByteBuffer getRotatedModel(VendingMachineBE be, BlockState state) {
        return CachedBuffers.partialFacing(AllPartialModels.SHAFT_HALF, state);
    }
}