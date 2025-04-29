package net.utzquishii.createdispensery.content.blocks.vending_machine;

import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock; // Very important heheheha
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.utzquishii.createdispensery.CreateDispenseryMod;
import org.jetbrains.annotations.Nullable;

public class VendingMachineBlock extends DirectionalKineticBlock {

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public VendingMachineBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public Axis getRotationAxis(BlockState state) {
        return Axis.Y; // Always rotate around vertical axis
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER && face == Direction.DOWN;
    }

    // Blockstate properties
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }

    // Placement: 2 blocks tall, faces player
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Log the player's placement action
        Direction facing = context.getHorizontalDirection().getOpposite();
        CreateDispenseryMod.LOGGER.info("Player placed Vending Machine facing {} at {}.", facing, context.getClickedPos());

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        // Check for space above
        if (pos.getY() >= level.getMaxBuildHeight() - 1 || !level.getBlockState(pos.above()).canBeReplaced(context)) {
            CreateDispenseryMod.LOGGER.error("Block placement failed: no space for upper half at {}", pos.above());
            return null;
        }

        return this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(HALF, DoubleBlockHalf.LOWER);
    }

    // Place upper half automatically
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (!level.isClientSide) {
            BlockState upper = state.setValue(HALF, DoubleBlockHalf.UPPER);
            level.setBlock(pos.above(), upper, 3);
        }
        super.setPlacedBy(level, pos, state, placer, stack);
    }

    // Break both halves when one is broken
    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf half = state.getValue(HALF);
        BlockPos otherPos = (half == DoubleBlockHalf.LOWER) ? pos.above() : pos.below();
        BlockState otherState = level.getBlockState(otherPos);
        if (otherState.getBlock() == this && otherState.getValue(HALF) != half) {
            level.destroyBlock(otherPos, !player.isCreative(), player);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    // Optional: Custom hitbox (covers both blocks if you want)
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return false; // Prevent replacing by rotating states via wrench
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (newState.getBlock() == this) {
            // Prevent recursive removal when replacing BlockState
            return;
        }

        // Determine if this is the upper or lower half.
        boolean isUpper = state.getValue(HALF) == DoubleBlockHalf.UPPER;
        BlockPos otherHalf = isUpper ? pos.below() : pos.above();
        BlockState otherState = level.getBlockState(otherHalf);

        // Remove the other half if it still exists and matches
        if (otherState.is(this) && otherState.getValue(HALF) != state.getValue(HALF)) {
            level.destroyBlock(otherHalf, false); // Set to true if you want drops for the other half
        }

        // Call the parent class (important for proper cleanup)
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
