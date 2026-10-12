package com.anton.createmoodrain;

import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public final class CowDrainHandler {

    public static void onEntityTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof Cow cow)) return;

        Level level = cow.level();
        if (level.isClientSide() || cow.isBaby() || !cow.isAlive()) return;
        if (cow instanceof MushroomCow && !Config.INCLUDE_MOOSHROOMS.get()) return;

        // Stagger by entity id so a herd doesn't all fire on the same tick
        int interval = Config.INTERVAL_TICKS.get();
        if ((cow.tickCount + cow.getId()) % interval != 0) return;

        ItemDrainBlockEntity drain = findDrainUnder(level, cow);
        if (drain == null) return;

        SmartFluidTankBehaviour tank = drain.getBehaviour(SmartFluidTankBehaviour.TYPE);
        if (tank == null) return;

        // Fill the internal tank directly: the drain's public fluid capability is
        // extract-only, so pipes can't push into it, but we can.
        FluidStack milk = new FluidStack(NeoForgeMod.MILK.get(), Config.MB_PER_MILKING.get());
        int filled = tank.getPrimaryHandler().fill(milk, IFluidHandler.FluidAction.EXECUTE);

        if (filled > 0 && Config.PLAY_SOUND.get()) {
            level.playSound(null, cow.blockPosition(), SoundEvents.COW_MILK,
                    SoundSource.NEUTRAL, 0.5f, 1.0f);
        }
    }

    /** Checks the block the cow stands on, plus its feet block in case the drain is shorter than a full block. */
    private static ItemDrainBlockEntity findDrainUnder(Level level, Cow cow) {
        if (!cow.onGround()) return null;
        for (BlockPos pos : new BlockPos[] { cow.getOnPos(), cow.blockPosition() }) {
            if (level.getBlockEntity(pos) instanceof ItemDrainBlockEntity drain) return drain;
        }
        return null;
    }

    private CowDrainHandler() {}
}
