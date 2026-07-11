/*
 * Based on code from Apotheosis
 * https://github.com/Shadows-of-Fire/Apotheosis
 *
 * Original Copyright (c) 2018-2025 Stormraven Studios, LLC
 *
 * The following code is licensed under the MIT License:
 *
 * MIT License
 *
 * Copyright (c) 2018-2025 Stormraven Studios, LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.scratch221171.astralenchant.mixin;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.scratch221171.astralenchant.common.enchantment.AEEnchantments;
import net.scratch221171.astralenchant.common.util.IDamageSourceExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DamageSource.class, remap = false)
public class DamageSourceMixin implements IDamageSourceExtension {

    @Unique private Set<TagKey<DamageType>> astralenchant$extraTags;

    @Unique private Set<TagKey<DamageType>> astralenchant$disabledTags;

    /**
     * {@link AEEnchantments#NULLIFICATION} または {@link AEEnchantments#REACTIVE_ARMOR} によるタグ編集を反映する。
     */
    @Inject(method = "is(Lnet/minecraft/tags/TagKey;)Z", at = @At("RETURN"), cancellable = true)
    private void astralenchant$isExtraTag(TagKey<DamageType> tag, CallbackInfoReturnable<Boolean> cir) {
        if (this.astralenchant$extraTags != null && this.astralenchant$extraTags.contains(tag)) {
            cir.setReturnValue(true);
        } else if (this.astralenchant$disabledTags != null && this.astralenchant$disabledTags.contains(tag)) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public void astralenchant$addExtraTag(TagKey<DamageType> tag) {
        if (this.astralenchant$extraTags == null) {
            this.astralenchant$extraTags = new HashSet<>();
        }
        this.astralenchant$extraTags.add(tag);
    }

    @Override
    public void astralenchant$removeExtraTag(TagKey<DamageType> tag) {
        if (this.astralenchant$extraTags != null) {
            this.astralenchant$extraTags.remove(tag);
        }
    }

    @Override
    public void astralenchant$addDisabledTag(TagKey<DamageType> tag) {
        if (this.astralenchant$disabledTags == null) {
            this.astralenchant$disabledTags = new HashSet<>();
        }
        this.astralenchant$disabledTags.add(tag);
    }

    @Override
    public void astralenchant$removeDisabledTag(TagKey<DamageType> tag) {
        if (this.astralenchant$disabledTags != null) {
            this.astralenchant$disabledTags.remove(tag);
        }
    }
}
