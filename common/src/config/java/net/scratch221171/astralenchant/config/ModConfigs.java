package net.scratch221171.astralenchant.config;

import java.util.List;
import net.scratch221171.astralenchant.mdk.config.ConfigDeclaration;
import net.scratch221171.astralenchant.mdk.config.ConfigSide;

public final class ModConfigs {
    public static final ConfigDeclaration SERVER = ConfigDeclaration.of(ConfigSide.SERVER, ServerConfig.ENTRIES);
    public static final ConfigDeclaration COMMON = ConfigDeclaration.of(ConfigSide.COMMON, CommonConfig.ENTRIES);

    public static final List<ConfigDeclaration> ALL = List.of(SERVER, COMMON);

    private ModConfigs() {}
}
