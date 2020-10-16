package fr.plum.plumlib.io.database.builder;
/**
 * Steps in tables checking & buildings
 * @author Meltwin
 * @since 1.1.0
 */
public enum BuildSteps {
    /** Checking if the registered tables are presents */
    CHECK,
    /** Add missing tables */
    BUILD,
    /** Adding lines on missing tables */
    LINES;
}