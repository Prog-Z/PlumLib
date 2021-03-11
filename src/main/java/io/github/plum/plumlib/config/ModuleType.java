package io.github.plum.plumlib.config;


/**
 * Basic type for plums modular architecture
 *
 * @author Meltin
 * @since 2.0.0
 */
public enum ModuleType {
    PLUGIN("plugin"),
    MODULE("module"),
    UNKNOWN("unknown");

    private final String name;
    ModuleType(final String name) {
        this.name = name;
    }

    /**
     * @return the module under a String format
     */
    public String getName() {
        return name;
    }
}
