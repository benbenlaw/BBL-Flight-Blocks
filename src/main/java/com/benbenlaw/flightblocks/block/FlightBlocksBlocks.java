package com.benbenlaw.flightblocks.block;

import com.benbenlaw.flightblocks.FlightBlocks;
import com.benbenlaw.flightblocks.item.FlightBlocksItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class FlightBlocksBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FlightBlocks.MOD_ID);


    public static final DeferredBlock<Block> FLIGHT_BLOCK = BLOCKS.registerBlock("flight_block",
            FlightBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE));


    /* Below is the best practive move to this when understanding how tool tips work in Minecraft.


    public static final DeferredBlock<Block> FLIGHT_BLOCK = registerBlock("flight_block",
            properties -> new FlightBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE).noCollission()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        FlightBlocksItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

     */

}
