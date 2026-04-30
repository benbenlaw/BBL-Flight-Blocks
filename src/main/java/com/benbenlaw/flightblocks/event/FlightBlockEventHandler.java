package com.benbenlaw.flightblocks.event;

import com.benbenlaw.flightblocks.FlightBlocks;
import com.benbenlaw.flightblocks.block.entity.FlightBlockEntity;
import com.benbenlaw.flightblocks.config.StartupConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@EventBusSubscriber(modid = FlightBlocks.MOD_ID)
public class FlightBlockEventHandler {

    public static final Set<UUID> playersWithFlightEnabled = new HashSet<>();
    private static final int RANGE = StartupConfig.flightBlockRange.get();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (!player.level().isClientSide()) {
            ServerLevel level = (ServerLevel) player.level();

            if (level.getGameTime() % 20 == 0) {
                if (isPlayerNearFlightBlock(player, level)) {
                    enableFlight(player);
                } else {
                    disableFlight(player);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        ServerLevel level = (ServerLevel) player.level();

        if (isPlayerNearFlightBlock(player, level)) {
            enableFlight(player);
        } else {
            disableFlight(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        playersWithFlightEnabled.remove(event.getEntity().getUUID());
    }

    private static boolean isPlayerNearFlightBlock(Player player, ServerLevel level) {
        for (FlightBlockEntity block : FlightBlockEntity.ACTIVE_BLOCKS) {

            // Defensive checks
            if (block == null || block.isRemoved()) continue;
            if (block.getLevel() != level) continue;

            AABB range = block.createArea();
            if (range.contains(player.position())) {
                return true;
            }
        }
        return false;
    }

    private static void enableFlight(Player player) {
        if (!player.isCreative() && !player.isSpectator() && !player.getAbilities().mayfly) {

            player.addTag("flight_blocks_flight");
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();

            player.sendOverlayMessage(
                    Component.translatable("chat.flightblocks.enabled")
                            .withStyle(ChatFormatting.GREEN)
            );

            playersWithFlightEnabled.add(player.getUUID());
        }
    }

    private static void disableFlight(Player player) {
        if (!player.isCreative()
                && !player.isSpectator()
                && player.getAbilities().mayfly
                && player.entityTags().contains("flight_blocks_flight")) {
            player.removeTag("flight_blocks_flight");
            player.getAbilities().mayfly = false;
            player.onUpdateAbilities();

            player.sendOverlayMessage(
                    Component.translatable("chat.flightblocks.disabled")
                            .withStyle(ChatFormatting.RED)
            );

            playersWithFlightEnabled.remove(player.getUUID());
        }
    }
}