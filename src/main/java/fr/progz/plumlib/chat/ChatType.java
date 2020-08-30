package fr.progz.plumlib.chat;

import org.jetbrains.annotations.NotNull;

public enum ChatType {
    PLUGIN("plugin"),
    MODULE("module"),
    ERROR("error");

    String str;
    private ChatType(@NotNull String toString) {
        str = toString;
    }
    public String str() { return str; }
}