package net.utzquishii.createdispensery.content.blocks.vending_machine;

import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock; // Very important heheheha
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.utzquishii.createdispensery.registry.DispenseryBETypes;
import org.jetbrains.annotations.Nullable;

public class VendingMachineBlock extends HorizontalKineticBlock implements IBE<VendingMachineBE> {

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public VendingMachineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(HORIZONTAL_FACING, Direction.NORTH)); // Default HORIZONTAL_FACING and half
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        // Return a BlockEntity only for the bottom half
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return new VendingMachineBE(DispenseryBETypes.VENDING_MACHINE.get(), pos, state);
        }
        return null; // Top half has no BlockEntity
    }

    @Override
    public Class<VendingMachineBE> getBlockEntityClass() {
        return VendingMachineBE.class;
    }

    @Override
    public BlockEntityType<? extends VendingMachineBE> getBlockEntityType() {
        return DispenseryBETypes.VENDING_MACHINE.get();
    }

    // Specify that the shaft (kinetic behavior) logic only applies to the bottom half
    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER && face == Direction.DOWN;
    }

    @Override
    public SpeedLevel getMinimumRequiredSpeedLevel() {
        return SpeedLevel.MEDIUM;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y; // Always rotate around the Y-axis
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, HORIZONTAL_FACING);
    }

    // Ensure the block is correctly placed as a double block
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();

        // Ensure there's enough space above to place the top half
        if (clickedPos.getY() >= level.getMaxBuildHeight() - 1 || !level.getBlockState(clickedPos.above()).canBeReplaced(context)) {
            return null;
        }

        return this.defaultBlockState()
                .setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite())
                .setValue(HALF, DoubleBlockHalf.LOWER);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (!level.isClientSide) {
            // Automatically place the top half
            BlockState topHalf = this.defaultBlockState()
                    .setValue(HORIZONTAL_FACING, state.getValue(HORIZONTAL_FACING))
                    .setValue(HALF, DoubleBlockHalf.UPPER);
            level.setBlock(pos.above(), topHalf, 3);
        }
        super.setPlacedBy(level, pos, state, placer, stack);
    }

    // Ensure both halves are destroyed when either half is broken
    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf half = state.getValue(HALF);
        BlockPos otherPos = half == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
        BlockState otherState = level.getBlockState(otherPos);

        // Destroy the other half if it matches
        if (otherState.getBlock() == this && otherState.getValue(HALF) != half) {
            level.destroyBlock(otherPos, !player.isCreative(), player);
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        // Simple collision shape for both halves
        return Shapes.block();
    }
}
