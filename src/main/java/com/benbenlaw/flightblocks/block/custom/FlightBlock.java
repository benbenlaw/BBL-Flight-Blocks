package com.benbenlaw.flightblocks.block.custom;

import com.benbenlaw.flightblocks.block.FlightBlockEntities;
import com.benbenlaw.flightblocks.block.entity.FlightBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FlightBlock extends BaseEntityBlock {

    public static final MapCodec<FlightBlock> CODEC = simpleCodec(FlightBlock::new);
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0);
    public FlightBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof FlightBlockEntity) {
                ((FlightBlockEntity) blockEntity).onRightClick((ServerPlayer) player);
            }
        }
        return InteractionResult.SUCCESS;
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState p_52402_, BlockGetter p_52403_, BlockPos p_52404_, CollisionContext p_52405_) {
        return SHAPE;
    }

    //@Override
    //public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
    //
    //    if (Screen.hasShiftDown()) {
    //        list.add(Component.translatable("tooltip.flight_block.info").append(String.valueOf(StartupConfig.flightBlockRange.get())).withStyle(ChatFormatting.YELLOW));
    //    } else {
    //        list.add(Component.translatable("tooltip.flightblocks.shift").withStyle(ChatFormatting.YELLOW));
    //    }
    //}

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new FlightBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState blockState, @NotNull BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, FlightBlockEntities.FLIGHT_BLOCK_ENTITY.get(),
                (world, blockPos, thisBlockState, blockEntity) -> blockEntity.tick());
    }
}