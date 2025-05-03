package net.utzquishii.createdispensery;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.utzquishii.createdispensery.config.DispenseryConfig;
import net.utzquishii.createdispensery.registry.DispenseryBlocks;
import net.utzquishii.createdispensery.registry.DispenseryBETypes;
import net.utzquishii.createdispensery.registry.DispenseryItems;
import net.utzquishii.createdispensery.registry.DispenseryCreativeTabs;
import org.slf4j.Logger;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(CreateDispensery.MOD_ID)
public class CreateDispensery {
    public static final String MOD_ID = "createdispensery";
    public static final String NAME = "Create: Dispensery";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);
    public CreateDispensery(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("{} is initializing!", NAME);

        // Register Registrate event listeners FIRST!
        REGISTRATE.registerEventListeners(modEventBus);

        // Registration order: blocks -> block entities -> items -> creative tabs
        DispenseryBlocks.register();
        DispenseryBETypes.register();
        DispenseryItems.register(modEventBus);
        DispenseryCreativeTabs.register(modEventBus);

        // Event Handlers
        modEventBus.addListener(CreateDispenseryClient::clientInit);
        modEventBus.addListener(this::commonSetup);

        // DispenseryConfig load
        modContainer.registerConfig(ModConfig.Type.COMMON, DispenseryConfig.SPEC);

        NeoForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common setup for {}...", NAME);
    }

    @net.neoforged.bus.api.SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server is starting!");
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }
}