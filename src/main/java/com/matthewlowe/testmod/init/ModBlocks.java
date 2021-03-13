package com.matthewlowe.testmod.init;

import com.matthewlowe.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MODID);

    public static final RegistryObject<Block> COPPER_BLOCK = registerBlock("copper_block",
            () -> new Block(Block.Properties.create(Material.IRON)));

    public static void initialise(final IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }

    private static <B extends Block> RegistryObject<B> registerBlock(final String name, final Supplier<B> blockSupplier) {
        return registerBlock(name, blockSupplier, block -> new BlockItem(block, defaultItemProperties()));
    }

    private static <B extends Block> RegistryObject<B> registerBlock(final String name, final Supplier<B> blockSupplier, final IBlockItemFactory<B> itemFactory) {
        final RegistryObject <B> block = BLOCKS.register(name, blockSupplier);

        ITEMS.register(name, () -> itemFactory.create(block.get()));

        return block;
    }

    private static Item.Properties defaultItemProperties() {
        return new Item.Properties().group(ItemGroup.MISC);
    }

    @FunctionalInterface
    private interface IBlockItemFactory<B extends Block> {
        Item create(B block);
    }
}
