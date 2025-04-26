package net.utzquishii.createdispensery.content.blocks.vending_machine;

import net.neoforged.neoforge.items.ItemStackHandler;
import net.minecraft.world.item.ItemStack;

public class VendingMachineItemHandler extends ItemStackHandler {
    public VendingMachineItemHandler(int slots) {
        super(slots);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        // Customize what items can go in each slot if needed
        return !stack.isEmpty();
    }
}
