package com.matthewlowe.testmod.data;

import com.matthewlowe.testmod.TestMod;
import com.matthewlowe.testmod.registry.ModBlocks;
import com.matthewlowe.testmod.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class TestModLanguageProvider extends LanguageProvider {
    public TestModLanguageProvider(DataGenerator gen, String locale) {
        super(gen, TestMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        // Items
        addItem(ModItems.COPPER_INGOT, "Copper Ingot");

        // Blocks
        addBlock(ModBlocks.COPPER_BLOCK, "Copper Block");
        //addBlock(ModBlocks.RANDOM_BLOCK, "Random Block");
    }
}
