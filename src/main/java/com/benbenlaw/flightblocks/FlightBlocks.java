package com.benbenlaw.flightblocks;


import com.benbenlaw.flightblocks.block.FBBLocks;
import com.benbenlaw.flightblocks.block.FlightBlockEntities;
import com.benbenlaw.flightblocks.config.StartupConfig;
import com.benbenlaw.flightblocks.item.FBItems;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.apache.logging.log4j.LogManager;

@Mod(FlightBlocks.MOD_ID)
public class FlightBlocks{

    public static final String MOD_ID = "flightblocks";
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public FlightBlocks(final IEventBus eventBus, final ModContainer modContainer) {

        FBItems.ITEMS.register(eventBus);
        FBBLocks.BLOCKS.register(eventBus);
        FlightBlockEntities.BLOCK_ENTITIES.register(eventBus);

        modContainer.registerConfig(ModConfig.Type.STARTUP, StartupConfig.SPEC, "bbl/flightblocks/startup.toml");

        eventBus.addListener(this::addItemToCreativeTab);
    }

    public static Identifier rl(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    private void addItemToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(FBBLocks.FLIGHT_BLOCK.get());
        }
    }

}
