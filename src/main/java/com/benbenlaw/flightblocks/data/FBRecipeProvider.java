package com.benbenlaw.flightblocks.data;

import com.benbenlaw.flightblocks.FlightBlocks;
import com.benbenlaw.flightblocks.block.FBBLocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class FBRecipeProvider extends RecipeProvider {

    public FBRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull RecipeOutput recipeOutput) {
            return new FBRecipeProvider(provider, recipeOutput);
        }

        @Override
        public @NotNull String getName() {
            return FlightBlocks.MOD_ID + " Recipes";
        }
    }

    @Override
    protected void buildRecipes() {

        //Flight Block
        shaped(RecipeCategory.MISC, FBBLocks.FLIGHT_BLOCK.get())
                .pattern("ABA")
                .pattern("CCC")
                .define('A', Items.IRON_BARS)
                .define('B', Items.FIREWORK_ROCKET)
                .define('C', Tags.Items.INGOTS_IRON)
                .group("utility")
                .unlockedBy("has_item", has(Tags.Items.INGOTS_IRON))
                .save(output);
    }
}
