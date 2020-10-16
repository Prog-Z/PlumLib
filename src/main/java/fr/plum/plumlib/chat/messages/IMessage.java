package fr.plum.plumlib.chat.messages;

import java.lang.reflect.InvocationTargetException;

import org.jetbrains.annotations.Nullable;

import fr.plum.plumlib.io.yaml.MessageLoader;
/**
 * Interface for loading message in local file (as Plum/message.yml)
 * @author Meltwin
 * @since 1.1.0
 */
public interface IMessage {

    @Nullable
    @SuppressWarnings("unchecked")
    public default <T extends MessageLoader> T getMessageLoader(Object obj) {
        Class<?> clazz = obj.getClass();
        IMessageSection section = clazz.getAnnotation(IMessageSection.class);
        if (section != null && section.getClass().equals(IMessageSection.class)) {
            try {
                return (T) section.clazz().getMethod("getInstance", null).invoke(null, null);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String m();
}