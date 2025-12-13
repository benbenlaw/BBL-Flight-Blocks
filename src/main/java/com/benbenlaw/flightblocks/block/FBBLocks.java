package com.benbenlaw.flightblocks.block;

import com.benbenlaw.flightblocks.FlightBlocks;
import com.benbenlaw.flightblocks.block.custom.FlightBlock;
import com.benbenlaw.flightblocks.item.FBItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FBBLocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FlightBlocks.MOD_ID);

    public static final DeferredBlock<Block> FLIGHT_BLOCK = registerBlock("flight_block",
            () -> new FlightBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .noOcclusion().setId(createID("flight_block"))));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        FBItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static ResourceKey<Block> createID(String name) {
        return ResourceKey.create(Registries.BLOCK, FlightBlocks.rl(name));
    }

}
