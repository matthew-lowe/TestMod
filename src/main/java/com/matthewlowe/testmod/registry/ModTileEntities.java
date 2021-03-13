package com.matthewlowe.testmod.registry;

import com.matthewlowe.testmod.TestMod;
import com.matthewlowe.testmod.tileentity.TileEntityRandom;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModTileEntities {

    private static DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, TestMod.MODID);

    public static final RegistryObject<TileEntityType<TileEntityRandom>> RANDOM = registerTileEntityType(
            "random",
            TileEntityRandom::new,
            ModBlocks.RANDOM_BLOCK
    );

    public static void initialise(final IEventBus modEventBus) {
        TILE_ENTITY_TYPES.register(modEventBus);
    }

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> registerTileEntityType(
            final String name,
            final Supplier<T> tileEntityFactory,
            final RegistryObject<? extends Block> validBlock) {

        return TILE_ENTITY_TYPES.register(name, () -> {
            final TileEntityType<T> tileEntityType = TileEntityType.Builder
                    .create(tileEntityFactory, validBlock.get())
                    .build(null);

            return tileEntityType;
        });

    }

}
