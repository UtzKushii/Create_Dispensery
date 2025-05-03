package net.utzquishii.createdispensery.content.blocks.vending_machine;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.millstone.MillstoneBlockEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.utzquishii.createdispensery.registry.DispenseryPartialModels;

public class VendingMachineRenderer extends KineticBlockEntityRenderer<VendingMachineBE> {

    public VendingMachineRenderer(BlockEntityRendererProvider.Context context) { super(context); }

    @Override
    protected SuperByteBuffer getRotatedModel(VendingMachineBE be, BlockState state) {
        return CachedBuffers.partialFacing(DispenseryPartialModels.SHAFT_TINY, state, Direction.DOWN);
    }

    /** @Override
    protected void renderSafe(VendingMachineBE be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {

        if (VisualizationManager.supportsVisualization(be.getLevel()))
            return;

        BlockState blockState = be.getBlockState();

        VertexConsumer vb = buffer.getBuffer(RenderType.solid());
        SuperByteBuffer superBuffer = CachedBuffers.partialFacing(DispenseryPartialModels.SHAFT_TINY, blockState, Direction.DOWN);
        standardKineticRotationTransform(superBuffer, be, light).renderInto(ms, vb);
    } **/


}