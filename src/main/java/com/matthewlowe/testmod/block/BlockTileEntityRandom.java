package com.matthewlowe.testmod.block;

import com.matthewlowe.testmod.tileentity.TileEntityRandom;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockTileEntityRandom extends Block {
    public BlockTileEntityRandom() {
        super(AbstractBlock.Properties.create(Material.ROCK));
    }

    private final int TIMER_COUNTDOWN_TICKS = 20 * 3; // 20 ticks per second * 10 = 10 seconds

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityRandom();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);

        TileEntity tileEntity = world.getTileEntity(pos);

        if (tileEntity instanceof TileEntityRandom) {
            TileEntityRandom tileEntityRandom = (TileEntityRandom) tileEntity;
            tileEntityRandom.setTicksUntilDisappear(TIMER_COUNTDOWN_TICKS);
        }
    }
}
