package com.matthewlowe.testmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TestModDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        LogManager.getLogger().debug("Gather data event fired!");
        DataGenerator gen = event.getGenerator();

        if (event.includeClient()) {
            ExistingFileHelper helper = event.getExistingFileHelper();

            gen.addProvider(new TestModLanguageProvider(gen, "en_us"));
            gen.addProvider(new TestModItemModelProvider(gen, helper));
            gen.addProvider(new TestModBlockStateProvider(gen, helper));
        }
    }

}
