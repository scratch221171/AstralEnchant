package net.scratch221171.astralenchant.config;

import net.scratch221171.astralenchant.mdk.config.ConfigDeclaration;
import net.scratch221171.astralenchant.mdk.config.ConfigSide;

import java.util.List;

public final class ModConfigs {
    public static final ConfigDeclaration SERVER = ConfigDeclaration.of(ConfigSide.SERVER, ServerConfig.ENTRIES);

    public static final List<ConfigDeclaration> ALL = List.of(SERVER);

    private ModConfigs() {}
}
