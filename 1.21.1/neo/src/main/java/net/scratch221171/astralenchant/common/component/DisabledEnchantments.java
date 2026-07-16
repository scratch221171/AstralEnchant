package net.scratch221171.astralenchant.common.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public record DisabledEnchantments(List<Entry> entries) {

    public static final DisabledEnchantments EMPTY = new DisabledEnchantments(List.of());

    public record Entry(ResourceKey<Enchantment> id, int level) {
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        ResourceKey.codec(Registries.ENCHANTMENT).fieldOf("id").forGetter(Entry::id),
                        Codec.INT.fieldOf("level").forGetter(Entry::level))
                .apply(instance, Entry::new));
    }

    public static final Codec<DisabledEnchantments> CODEC =
            Entry.CODEC.listOf().xmap(DisabledEnchantments::new, DisabledEnchantments::entries);

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public Optional<Entry> get(ResourceKey<Enchantment> key) {
        return entries.stream().filter(e -> e.id().equals(key)).findFirst();
    }

    public DisabledEnchantments with(ResourceKey<Enchantment> key, int level) {
        if (get(key).isPresent()) return this;
        List<Entry> list = new ArrayList<>(entries);
        list.add(new Entry(key, level));
        return new DisabledEnchantments(List.copyOf(list));
    }

    public DisabledEnchantments without(ResourceKey<Enchantment> key) {
        if (get(key).isEmpty()) return this;
        List<Entry> list = entries.stream().filter(e -> !e.id().equals(key)).toList();
        return new DisabledEnchantments(list);
    }
}
