package com.hs.commons.lang.script;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public interface ScriptExecutor {
    void execute(File file, String... commands);

    void execute(File file, String command);

    void execute(String command);

    void execute(Consumer<String> consumer, File file, String... commands);

    void execute(Consumer<String> consumer, File file, String command);

    String executeString(File file, String... commands);

    String executeString(File file, String command);

    String executeString(String command);

    boolean executeBoolean(File file, String... commands);

    boolean executeBoolean(File file, String command);

    boolean executeBoolean(String command);

    List<String> executeList(File file, String... commands);

    List<String> executeList(File file, String command);

    List<String> executeList(String command);

    void kill(String grep);

    String pid(String grep);

    void sed(String filePath, String command);
}
