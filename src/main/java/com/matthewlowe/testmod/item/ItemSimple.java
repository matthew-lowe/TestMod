package com.matthewlowe.testmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemSimple extends Item {
    public ItemSimple(ItemGroup group, int stackSize) {
        super(new Properties().group(group).maxStackSize(stackSize));
    }

    public ItemSimple(int stackSize) {
        this(ItemGroup.MISC, stackSize);
    }

    public ItemSimple(ItemGroup group) {
        this(group, 64);
    }

    public ItemSimple() {
        this(ItemGroup.MISC, 64);
    }
}
