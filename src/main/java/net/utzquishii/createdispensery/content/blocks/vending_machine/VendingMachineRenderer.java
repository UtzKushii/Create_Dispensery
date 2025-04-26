package net.utzquishii.createdispensery.content.blocks.vending_machine;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import com.mojang.blaze3d.vertex.PoseStack;

public class VendingMachineRenderer extends KineticBlockEntityRenderer<VendingMachineBE> {

    public VendingMachineRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(VendingMachineBE blockEntity, float partialTicks, PoseStack ms,
                              MultiBufferSource buffer, int light, int overlay) {
        super.renderSafe(blockEntity, partialTicks, ms, buffer, light, overlay);

        // Render Vertical Shaft Logic
        BlockState blockState = blockEntity.getBlockState();
        SuperByteBuffer shaftBuffer = CachedBuffers.partialFacing(AllPartialModels.SHAFT, blockState);

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.solid());
        rotateVerticalShaft(shaftBuffer, blockEntity).light(light).renderInto(ms, vertexConsumer);
    }

    @Override
    protected SuperByteBuffer getRotatedModel(VendingMachineBE be, BlockState state) {
        // Returns a vertical shaft model aligned to the block's bottom face
        return CachedBuffers.partialFacing(AllPartialModels.SHAFT, state, Direction.DOWN);
    }

    private SuperByteBuffer rotateVerticalShaft(SuperByteBuffer buffer, VendingMachineBE blockEntity) {
        // Apply rotations and translations (pivoting on center)
        buffer.center() // Center it within the block space
                .rotateXDegrees(90) // Rotate 90 degrees to make it vertical
                .uncenter(); // Un-center to move it back into correct block space
        return buffer;
    }
}