package com.benbenlaw.flightblocks.data;

import com.benbenlaw.flightblocks.FlightBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

public class FBLangProvider extends LanguageProvider {

    public FBLangProvider(PackOutput output) {
        super(output, FlightBlocks.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        //Item Group
        add("itemGroup.flightblocks", "Flight Blocks");

        //Blocks
        add("block.flightblocks.flight_block", "Flight Block");

        //Flight Block Chat Messages
        add("chat.flightblocks.enabled", "Flight Enabled");
        add("chat.flightblocks.disabled", "Flight Disabled");
        add("chat.flightblocks.range", "Range: %s");
        add("chat.flightblocks.info", "Place in world to allow flight around the block! Range: %s");

        //Tooltips
        add("tooltip.flightblocks.shift", "Hold SHIFT for more info");
        add("tooltip.flightblocks.flight_block", "Provies flight around the block! Right click to show range");

    }

    @Override
    public @NotNull String getName() {
        return FlightBlocks.MOD_ID + " Language Provider";
    }
}
