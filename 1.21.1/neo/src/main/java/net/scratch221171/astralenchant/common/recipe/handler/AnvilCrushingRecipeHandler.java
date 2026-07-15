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

package net.scratch221171.astralenchant.common.recipe.handler;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.scratch221171.astralenchant.common.event.AnvilLandEvent;
import net.scratch221171.astralenchant.common.registry.AEItems;
import net.scratch221171.astralenchant.common.tag.AETags;

public class AnvilCrushingRecipeHandler {

    public static void anvilCrushing(AnvilLandEvent event) {
        var level = event.getLevel();
        var pos = event.getPos();
        var items = level.getEntitiesOfClass(ItemEntity.class, new AABB(pos));
        for (var e : items) {
            var stack = e.getItem();
            if (stack.is(AETags.Items.GEMS_ARCANE_QUARTZ)) {
                e.setItem(new ItemStack(AEItems.ARCANE_QUARTZ_TINY_DUST.get(), stack.getCount()));
            }
            if (stack.is(AEItems.GROWN_ARCANE_QUARTZ.get())) {
                e.setItem(new ItemStack(AEItems.ARCANE_QUARTZ_DUST.get(), stack.getCount()));
            }
        }
    }
}
