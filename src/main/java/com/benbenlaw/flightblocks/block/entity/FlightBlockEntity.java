package com.benbenlaw.flightblocks.block.entity;

import com.benbenlaw.flightblocks.block.FlightBlockEntities;
import com.benbenlaw.flightblocks.config.StartupConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public class FlightBlockEntity extends BlockEntity {

    public static final int RANGE = StartupConfig.flightBlockRange.get();

    public static final Set<FlightBlockEntity> ACTIVE_BLOCKS =
            Collections.newSetFromMap(new WeakHashMap<>());

    public FlightBlockEntity(BlockPos pos, BlockState state) {
        super(FlightBlockEntities.FLIGHT_BLOCK_ENTITY.get(), pos, state);
        ACTIVE_BLOCKS.add(this);
    }

    public static void tick() {

    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        ACTIVE_BLOCKS.remove(this);
    }

    public AABB createArea() {
        return new AABB(this.getBlockPos()).inflate(RANGE);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
    }

    @Override
    protected void loadAdditional(ValueInput input) {
    }
}