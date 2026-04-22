package com.benbenlaw.flightblocks.block.entity;

import com.benbenlaw.flightblocks.block.FlightBlockEntities;
import com.benbenlaw.flightblocks.config.StartupConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;

import java.util.HashSet;
import java.util.Set;

import static com.benbenlaw.flightblocks.event.FlightBlockEventHandler.playersWithFlightEnabled;

public class FlightBlockEntity extends BlockEntity {

    public static final int RANGE = StartupConfig.flightBlockRange.get();
    private final Set<ServerPlayer> enabledPlayers = new HashSet<>();
    public static final Set<FlightBlockEntity> ACTIVE_BLOCKS = new HashSet<>();
    private boolean showRange = false;

    public FlightBlockEntity(BlockPos pos, BlockState state) {
        super(FlightBlockEntities.FLIGHT_BLOCK_ENTITY.get(), pos, state);
        ACTIVE_BLOCKS.add(this);
    }

    public void tick() {
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
