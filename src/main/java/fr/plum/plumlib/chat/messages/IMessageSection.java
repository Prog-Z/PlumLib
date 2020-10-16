package fr.plum.plumlib.chat.messages;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import fr.plum.plumlib.io.yaml.MessageLoader;
/**
 * Annotation for selecting the loader for the messages
 * @author Meltwin
 * @since 1.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IMessageSection {
    Class<? extends MessageLoader> clazz();
    String section();
}