package com.matthewlowe.testmod.tileentity;

import com.matthewlowe.testmod.registry.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.NBTTypes;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class TileEntityRandom extends TileEntity implements ITickableTileEntity {

    public TileEntityRandom() {
        super(ModTileEntities.RANDOM.get());
    }

    private final int INVALID_VALUE = -1;
    private int ticksUntilDisappear = INVALID_VALUE; // The time left until the block disappears

    public void setTicksUntilDisappear(int ticks) {
        ticksUntilDisappear = ticks;
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbtTag = new CompoundNBT();
        this.write(nbtTag);

        return new SUpdateTileEntityPacket(this.pos, 42, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        BlockState blockState = world.getBlockState(pos);
        this.read(blockState, packet.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTag = new CompoundNBT();
        this.write(nbtTag);
        return nbtTag;
    }

    @Override
    public void handleUpdateTag(BlockState blockState, CompoundNBT parentNBTTag) {
        this.read(blockState, parentNBTTag);
    }

    @Override
    public CompoundNBT write(CompoundNBT parentNBTTag) {
        super.write(parentNBTTag); // Super to save the tile's location

        parentNBTTag.putInt("ticksLeft", ticksUntilDisappear);

        return parentNBTTag;
    }

    @Override
    public void read(BlockState blockState, CompoundNBT parentNBTTag) {
        super.read(blockState, parentNBTTag);

        final int NBT_INT_ID = IntNBT.valueOf(0).getId();
        int readTicks = INVALID_VALUE;

        if (parentNBTTag.contains("ticksLeft", NBT_INT_ID)) {
            readTicks = parentNBTTag.getInt("ticksLeft");
            if (readTicks < 0) readTicks = INVALID_VALUE;
        }

        ticksUntilDisappear = readTicks;
    }

    @Override
    public void tick() {
        if (!this.hasWorld()) return; // prevent crash
        World world = this.getWorld();
        if (world.isRemote) return; // We only wanna do this on the server side
        ServerWorld serverWorld = (ServerWorld) world; // We're certain that this is now on the server, so it's safe to cast
        if (ticksUntilDisappear == INVALID_VALUE) return; // Do nothing until the time is valid
        -- ticksUntilDisappear;
        //this.makeDirt(); // If the client needs to know about updates to a tile entity on the server, this forces a resend on each update
        if (ticksUntilDisappear > 0) return; // Not ready to update yet;

        Block [] blockChoices = {Blocks.DIAMOND_BLOCK, Blocks.OBSIDIAN, Blocks.TNT, Blocks.OAK_LOG};
        Random random = new Random();
        Block chosenBlock  = blockChoices[random.nextInt(blockChoices.length)];
        world.setBlockState(this.pos, chosenBlock.getDefaultState());
    }
}
