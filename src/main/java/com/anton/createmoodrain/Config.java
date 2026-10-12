package com.anton.createmoodrain;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class Config {
    private static final ModConfigSpec.Builder B = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue INTERVAL_TICKS = B
            .comment("Ticks between milkings while a cow stands on a drain (20 ticks = 1 second)")
            .defineInRange("intervalTicks", 40, 1, 72000);

    public static final ModConfigSpec.IntValue MB_PER_MILKING = B
            .comment("Millibuckets of milk added per milking (1000 = one bucket; a drain holds 1500)")
            .defineInRange("mbPerMilking", 100, 1, 1500);

    public static final ModConfigSpec.BooleanValue INCLUDE_MOOSHROOMS = B
            .comment("Whether mooshrooms also count as cows")
            .define("includeMooshrooms", true);

    public static final ModConfigSpec.BooleanValue PLAY_SOUND = B
            .comment("Play the cow milking sound when milk is added")
            .define("playSound", true);

    public static final ModConfigSpec SPEC = B.build();

    private Config() {}
}
