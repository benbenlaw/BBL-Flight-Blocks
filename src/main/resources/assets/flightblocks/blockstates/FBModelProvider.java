package com.benbenlaw.flightblocks.data;

import com.benbenlaw.flightblocks.FlightBlocks;
import com.benbenlaw.flightblocks.block.FBBLocks;
import com.benbenlaw.flightblocks.item.FBItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class FBModelProvider extends ModelProvider {

    public FBModelProvider(PackOutput output) {
        super(output, FlightBlocks.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        //Blocks
        //blockModels.createTrivialCube(FBBLocks.FLIGHT_BLOCK.get());
    }

    @Override
    protected @NotNull Stream<? extends Holder<Block>> getKnownBlocks() {
        return FBBLocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected @NotNull Stream<? extends Holder<Item>> getKnownItems() {
        return FBItems.ITEMS.getEntries().stream();
    }

    @Override
    public @NotNull String getName() {
        return FlightBlocks.MOD_ID + " Models";
    }
}
