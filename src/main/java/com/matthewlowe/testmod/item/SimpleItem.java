package com.matthewlowe.testmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class SimpleItem extends Item {
    public SimpleItem(ItemGroup group, int stackSize) {
        super(new Properties().group(group).maxStackSize(stackSize));
    }

    public SimpleItem(int stackSize) {
        this(ItemGroup.MISC, stackSize);
    }

    public SimpleItem(ItemGroup group) {
        this(group, 64);
    }

    public SimpleItem() {
        this(ItemGroup.MISC, 64);
    }
}
