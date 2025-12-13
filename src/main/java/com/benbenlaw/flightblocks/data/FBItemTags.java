package com.benbenlaw.flightblocks.data;

import com.benbenlaw.flightblocks.FlightBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class FBItemTags extends ItemTagsProvider {


    public FBItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, FlightBlocks.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {


    }

    @Override
    public @NotNull String getName() {
        return FlightBlocks.MOD_ID + " Item Tags";
    }
}
