package com.benbenlaw.flightblocks.block.entity;

import com.benbenlaw.flightblocks.config.StartupConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;

import static com.benbenlaw.flightblocks.event.FlightBlockEventHandler.playersWithFlightEnabled;

public class FlightBlockEntity extends BlockEntity {

    public static final int RANGE = StartupConfig.flightBlockRange.get();

    // Weak set prevents memory leaks if something goes wrong
    public static final Set<FlightBlockEntity> ACTIVE_BLOCKS =
            Collections.newSetFromMap(new WeakHashMap<>());

    private boolean showRange = false;

    public FlightBlockEntity(BlockPos pos, BlockState state) {
        super(FlightBlockEntities.FLIGHT_BLOCK_ENTITY.get(), pos, state);
        ACTIVE_BLOCKS.add(this);
    }

    public void tick() {
        if (level == null || level.isClientSide()) return;

        if (!showRange) return;

        ServerLevel serverLevel = (ServerLevel) level;

        if (level.getGameTime() % 40 != 0) return;

        for (UUID uuid : playersWithFlightEnabled) {
            ServerPlayer player = serverLevel.getServer()
                    .getPlayerList()
                    .getPlayer(uuid);

            if (player != null) {
                showFlightRangeOutline(serverLevel, player);
            }
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        ACTIVE_BLOCKS.remove(this);
    }

    public void onRightClick(ServerPlayer player) {
        showRange = !showRange;

        if (showRange) {
            player.sendSystemMessage(
                    net.minecraft.network.chat.Component.translatable(
                            "block.flightblocks.flight_block.range", RANGE
                    )
            );
        } else {
            if (level instanceof ServerLevel serverLevel) {
                showFlightRangeOutline(serverLevel, player);
            }
        }
    }

    private void showFlightRangeOutline(ServerLevel level, ServerPlayer player) {
        AABB range = new AABB(this.worldPosition).inflate(RANGE);

        int minX = (int) Math.floor(range.minX);
        int minY = (int) Math.floor(range.minY);
        int minZ = (int) Math.floor(range.minZ);
        int maxX = (int) Math.ceil(range.maxX);
        int maxY = (int) Math.ceil(range.maxY);
        int maxZ = (int) Math.ceil(range.maxZ);

        int step = 1;

        for (int x = minX; x <= maxX; x += step) {
            spawn(level, player, x, minY, minZ);
            spawn(level, player, x, minY, maxZ);
            spawn(level, player, x, maxY, minZ);
            spawn(level, player, x, maxY, maxZ);
        }

        for (int y = minY; y <= maxY; y += step) {
            spawn(level, player, minX, y, minZ);
            spawn(level, player, minX, y, maxZ);
            spawn(level, player, maxX, y, minZ);
            spawn(level, player, maxX, y, maxZ);
        }

        for (int z = minZ; z <= maxZ; z += step) {
            spawn(level, player, minX, minY, z);
            spawn(level, player, minX, maxY, z);
            spawn(level, player, maxX, minY, z);
            spawn(level, player, maxX, maxY, z);
        }
    }

    private void spawn(ServerLevel level, ServerPlayer player, double x, double y, double z) {
        level.sendParticles(
                player,
                ParticleTypes.END_ROD,
                true,
                x + 0.5, y + 0.5, z + 0.5,
                1,
                0, 0, 0,
                0
        );
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putBoolean("showRange", showRange);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        showRange = tag.getBoolean("showRange");
    }
}