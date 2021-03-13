package com.matthewlowe.testmod.init;

import com.matthewlowe.testmod.TestMod;
import com.matthewlowe.testmod.item.SimpleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MODID);

    public static final RegistryObject<SimpleItem> COPPER_INGOT = ITEMS.register("copper_ingot",
            () -> new SimpleItem(ItemGroup.MISC, 64)
    );

    public static void initialise(final IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
