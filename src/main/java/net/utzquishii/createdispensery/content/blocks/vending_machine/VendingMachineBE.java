package net.utzquishii.createdispensery.content.blocks.vending_machine;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.api.equipment.goggles.IHaveHoveringInformation;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.*;

public class VendingMachineBE extends KineticBlockEntity implements IHaveGoggleInformation, IHaveHoveringInformation {
    private final ItemStackHandler stockInventory;
    private final ItemStackHandler purchasedInventory;
    private UUID owner;

    // For possible future use: track pending purchases or reservations
    private final List<PendingPurchase> pendingPurchases = new ArrayList<>();

    private static final int SLOTS = 9;   // 3 per shelf, 3 shelves

    public VendingMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.stockInventory = new ItemStackHandler(SLOTS);      // For-sale items (not dropped)
        this.purchasedInventory = new ItemStackHandler(8);      // Purchased items (dropped)
    }

    //Kinetics
    @Override
    public float calculateStressApplied() {
        // Optionally override to add custom stress values
        return super.calculateStressApplied();
    }

    @Override
    public float calculateAddedStressCapacity() {
        // Optionally override to change stress capacity
        return super.calculateAddedStressCapacity();
    }

    // --- Ownership ---

    public @Nullable UUID getOwner() { return owner; }
    public void setOwner(@Nullable UUID owner) { this.owner = owner; }

    /**
     * Notify the owner (if online) that this vending machine was broken.
     */
    public void notifyOwnerBroken() {
        if (level == null || level.isClientSide || owner == null) return;
        Player ownerPlayer = ((Level) level).getPlayerByUUID(owner);
        if (ownerPlayer != null) {
            ownerPlayer.displayClientMessage(
                    Component.translatable("message.createvendingmachines.vending_machine_broken"), true
            );
        }
    }

    // --- Inventory Access ---

    public ItemStackHandler getStockInventory() { return stockInventory; }
    public ItemStackHandler getPurchasedInventory() { return purchasedInventory; }

    // --- Inventory Summary (for GUIs/tooltips) ---

    public Map<String, Integer> getStockSummary() {
        Map<String, Integer> summary = new LinkedHashMap<>();
        for (int i = 0; i < stockInventory.getSlots(); i++) {
            ItemStack stack = stockInventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                String name = stack.getHoverName().getString();
                summary.put(name, summary.getOrDefault(name, 0) + stack.getCount());
            }
        }
        return summary;
    }

    public Map<String, Integer> getPurchasedSummary() {
        Map<String, Integer> summary = new LinkedHashMap<>();
        for (int i = 0; i < purchasedInventory.getSlots(); i++) {
            ItemStack stack = purchasedInventory.getStackInSlot(i);
            if (!stack.isEmpty()) {
                String name = stack.getHoverName().getString();
                summary.put(name, summary.getOrDefault(name, 0) + stack.getCount());
            }
        }
        return summary;
    }

    // --- Pending Purchases (for advanced features, optional) ---

    public static class PendingPurchase {
        public final UUID buyer;
        public final ItemStack item;
        public final int count;

        public PendingPurchase(UUID buyer, ItemStack item, int count) {
            this.buyer = buyer;
            this.item = item.copy();
            this.count = count;
        }
    }

    public List<PendingPurchase> getPendingPurchases() {
        return Collections.unmodifiableList(pendingPurchases);
    }

    public void addPendingPurchase(UUID buyer, ItemStack item, int count) {
        pendingPurchases.add(new PendingPurchase(buyer, item, count));
    }

    public void clearPendingPurchases() {
        pendingPurchases.clear();
    }

    // --- Utility: Add summary as tooltip (example for GUI use) ---

    public List<Component> getStockTooltip() {
        Map<String, Integer> summary = getStockSummary();
        if (summary.isEmpty())
            return List.of(Component.translatable("tooltip.createvendingmachines.stock.empty"));
        List<Component> tooltips = new ArrayList<>();
        summary.forEach((name, count) -> tooltips.add(Component.literal(name + " x" + count)));
        return tooltips;
    }

    public List<Component> getPurchasedTooltip() {
        Map<String, Integer> summary = getPurchasedSummary();
        if (summary.isEmpty())
            return List.of(Component.translatable("tooltip.createvendingmachines.purchased.empty"));
        List<Component> tooltips = new ArrayList<>();
        summary.forEach((name, count) -> tooltips.add(Component.literal(name + " x" + count)));
        return tooltips;
    }

    // --- For Renderer: get all stock stacks in shelf order ---
    public List<ItemStack> getStockStacks() {
        List<ItemStack> stacks = new ArrayList<>();
        for (int i = 0; i < stockInventory.getSlots(); i++)
            stacks.add(stockInventory.getStackInSlot(i));
        return stacks;
    }

    public VendingMachineBE(BlockEntityType<?> type, BlockPos pos, BlockState state, ItemStackHandler stockInventory, ItemStackHandler purchasedInventory) {
        super(type, pos, state);
        this.stockInventory = stockInventory;
        this.purchasedInventory = purchasedInventory;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        // Add any Create-compatible behaviors here
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        // Add translated text to the tooltip components
        tooltip.add(Component.translatable("gui.goggles.kinetic_stats"));

        // You can add more info if needed
        tooltip.add(Component.translatable("tooltip.vending_machine.example", "Extra Info Here"));

        return true;
    }
}
