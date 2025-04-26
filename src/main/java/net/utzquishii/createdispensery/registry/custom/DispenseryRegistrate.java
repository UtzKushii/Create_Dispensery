package net.utzquishii.createdispensery.registry.custom;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import java.util.function.Supplier;

public class DispenseryRegistrate extends CreateRegistrate {
    protected DispenseryRegistrate(String modid) {
        super(modid);
    }

    public static DispenseryRegistrate create(String modid) {
        return new DispenseryRegistrate(modid);
    }
}