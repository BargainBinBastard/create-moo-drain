package com.anton.createmoodrain;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;

@Mod(CreateMooDrain.MOD_ID)
public class CreateMooDrain {
    public static final String MOD_ID = "createmoodrain";

    public CreateMooDrain(IEventBus modBus, ModContainer container) {
        // NeoForge's milk fluid is off unless a mod asks for it. Create already does,
        // but calling it again is harmless and keeps us safe.
        NeoForgeMod.enableMilkFluid();

        container.registerConfig(ModConfig.Type.SERVER, Config.SPEC);
        NeoForge.EVENT_BUS.addListener(CowDrainHandler::onEntityTick);
    }
}
