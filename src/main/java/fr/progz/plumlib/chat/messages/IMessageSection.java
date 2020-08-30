package fr.progz.plumlib.chat.messages;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import fr.progz.plumlib.io.yaml.MessageLoader;

@Retention(RetentionPolicy.RUNTIME)
public @interface IMessageSection {
    Class<? extends MessageLoader> clazz();
    String section();
}