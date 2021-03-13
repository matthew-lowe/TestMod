package com.matthewlowe.testmod.data;

import com.matthewlowe.testmod.TestMod;
import com.matthewlowe.testmod.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class TestModItemModelProvider extends ItemModelProvider {
    public TestModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TestMod.MODID, existingFileHelper);
    }

    private void simpleItem(Supplier<? extends Item> itemSupplier) {
        ResourceLocation location = itemSupplier.get().getRegistryName();

        this.getBuilder(location.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(location.getNamespace(), ITEM_FOLDER + "/" + location.getPath()));
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.COPPER_INGOT);
    }
}
