package net.scratch221171.astralenchant.mdk.config;

interface ConfigElement {
    void bindTo(ConfigVisitor visitor);
}
