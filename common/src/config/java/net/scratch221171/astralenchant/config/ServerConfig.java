package net.scratch221171.astralenchant.config;

import net.scratch221171.astralenchant.mdk.config.ConfigEntries;
import net.scratch221171.astralenchant.mdk.config.ConfigEntryBuilder;

public class ServerConfig {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntries ENTRIES = BUILDER.build();
}
