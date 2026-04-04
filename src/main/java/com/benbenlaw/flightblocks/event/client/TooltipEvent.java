package com.benbenlaw.flightblocks.event.client;

import com.benbenlaw.flightblocks.FlightBlocks;
import com.benbenlaw.flightblocks.block.FBBLocks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = FlightBlocks.MOD_ID)
public class TooltipEvent {

    @SubscribeEvent
    public static void onTooltipEvent(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        addShiftTooltip(stack, event, FBBLocks.FLIGHT_BLOCK.asItem(), "tooltip.flightblocks.flight_block");

    }

    public static void addShiftTooltip(ItemStack stack, ItemTooltipEvent event, Item item, String tooltipText) {
        if (stack.is(item)) {
            if (Minecraft.getInstance().hasShiftDown()) {
                event.getToolTip().add(Component.translatable(tooltipText).withStyle(ChatFormatting.BLUE));
            } else {
                event.getToolTip().add(Component.translatable("tooltip.flightblocks.shift").withStyle(ChatFormatting.YELLOW));
            }

        }
    }
}

