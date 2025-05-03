package net.utzquishii.createdispensery.registry;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.utzquishii.createdispensery.CreateDispensery;

public class DispenseryPartialModels {
   public static final PartialModel SHAFT_TINY = block("shaft_tiny");

    private static PartialModel block(String path) {
        return PartialModel.of(CreateDispensery.asResource("block/partials/" + path));
    }

    public static void init() {}
}