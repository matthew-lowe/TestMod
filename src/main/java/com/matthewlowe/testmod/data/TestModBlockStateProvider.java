package com.matthewlowe.testmod.data;

import com.matthewlowe.testmod.TestMod;
import com.matthewlowe.testmod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class TestModBlockStateProvider extends BlockStateProvider {
    public TestModBlockStateProvider(DataGenerator gen,ExistingFileHelper exFileHelper) {
        super(gen, TestMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.COPPER_BLOCK);
    }

    public void simpleBlock(Supplier<? extends Block> blockSupplier) {
        simpleBlock(blockSupplier.get());
    }

    @Override
    public void simpleBlock(Block block, ModelFile model) {
        super.simpleBlock(block, model);
        this.simpleBlockItem(block, model);
    }
}
