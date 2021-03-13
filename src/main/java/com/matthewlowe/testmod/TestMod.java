package com.matthewlowe.testmod;

import com.matthewlowe.testmod.init.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("testmod")
public class TestMod
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "testmod";
    public static final String NAME = "Test Mod";

    public TestMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        final ClientEvents clientEvents = new ClientEvents(modEventBus);

        ModItems.initialise(modEventBus);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> clientEvents::registerClientEvents);
    }
}
