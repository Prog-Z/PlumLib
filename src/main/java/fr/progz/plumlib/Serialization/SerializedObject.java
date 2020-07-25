package fr.progz.plumlib.Serialization;

import java.util.Arrays;
import java.util.List;

public final class SerializedObject<T> implements Cloneable {
    public final String name;
    public final String description;
    public T value;

    public SerializedObject(String name, T value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }    

    public SerializedObject(SerializedObject<T> rhs) {
        this.name = rhs.name;
        this.value = rhs.value;
        this.description = rhs.description;
    }    

    @Override
    public SerializedObject<T> clone() {
        return new SerializedObject<T>(this);
    }
}