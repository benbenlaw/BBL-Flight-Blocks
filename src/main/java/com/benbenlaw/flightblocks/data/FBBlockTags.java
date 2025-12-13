package com.benbenlaw.flightblocks.data;

import com.benbenlaw.flightblocks.FlightBlocks;
import com.benbenlaw.flightblocks.block.FBBLocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class FBBlockTags extends BlockTagsProvider {

    FBBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, FlightBlocks.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        //Pickaxe
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(FBBLocks.FLIGHT_BLOCK.get())
        ;

    }

    @Override
    public String getName() {
        return FlightBlocks.MOD_ID + " Block Tags";
    }
}
