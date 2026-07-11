/*
 * Based on code from Placebo
 * https://github.com/Shadows-of-Fire/Placebo
 *
 * Original Copyright (c) 2019 Brennan Ward
 *
 * The following code is licensed under the MIT License:
 *
 * MIT License
 *
 * Copyright (c) 2019 Brennan Ward
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

package net.scratch221171.astralenchant.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.Event;

/**
 * The AnvilFallEvent is fired when a falling anvil lands on a block.
 */
public class AnvilLandEvent extends Event {

    protected final Level level;
    protected final BlockPos pos;
    protected final BlockState newState;
    protected final BlockState oldState;
    protected final FallingBlockEntity entity;

    public AnvilLandEvent(
            Level level, BlockPos pos, BlockState newState, BlockState oldState, FallingBlockEntity entity) {
        this.level = level;
        this.pos = pos;
        this.newState = newState;
        this.oldState = oldState;
        this.entity = entity;
    }

    public Level getLevel() {
        return this.level;
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public BlockState getNewState() {
        return this.newState;
    }

    public BlockState getOldState() {
        return this.oldState;
    }

    public FallingBlockEntity getEntity() {
        return this.entity;
    }
}
