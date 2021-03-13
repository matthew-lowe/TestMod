package com.matthewlowe.testmod.data;

import com.matthewlowe.testmod.TestMod;
import com.matthewlowe.testmod.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraftforge.common.data.LanguageProvider;

public class TestModLanguageProvider extends LanguageProvider {
    public TestModLanguageProvider(DataGenerator gen, String locale) {
        super(gen, TestMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        addItem(ModItems.COPPER_INGOT, "Copper Ingot");
    }
}
