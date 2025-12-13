package com.benbenlaw.flightblocks.item;

import com.benbenlaw.flightblocks.FlightBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FBItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FlightBlocks.MOD_ID);

    public static ResourceKey<Item> createID(String name) {
        return ResourceKey.create(Registries.ITEM, FlightBlocks.rl(name));
    }

}
