package net.scratch221171.astralenchant.mixin.compat.client.appleskin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import squeek.appleskin.client.HUDOverlayHandler;

@Mixin(value = HUDOverlayHandler.class, remap = false)
public interface HUDOverlayHandlerAccessor {
    @Accessor("flashAlpha")
    static float getAlpha() {
        throw new AssertionError();
    }
}
