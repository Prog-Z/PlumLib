package fr.plum.plumlib.chat;

import org.jetbrains.annotations.NotNull;
/**
 * Enum of the type using the chat fonctionnalities
 * @author Meltwin
 * @since 1.1.0
 */
public enum ChatType {
    PLUGIN("plugin"),
    MODULE("module"),
    ERROR("error"),
    /** Unknown caller */
    UNKNOWN("unknown");

    String str;
    private ChatType(@NotNull String toString) {
        str = toString;
    }
    /** Return a string qualifying the caller */
    public String str() { return str; }
}