package com.hs.commons.lang.script;

import com.hs.commons.lang.ProcessUtil;
import com.hs.commons.lang.StringPool;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class WindowScriptExecutor implements ScriptExecutor {
    @Override
    public void execute(File file, String... commands) {
        ProcessUtil.execute(file, commands);
    }

    @Override
    public void execute(File file, String command) {
        execute(file, "cmd", "/c", command);
    }

    @Override
    public void execute(String command) {
        execute(null, command);
    }

    @Override
    public void execute(Consumer<String> consumer, File file, String... commands) {
        ProcessUtil.executeFunction(consumer, file, commands);
    }

    @Override
    public void execute(Consumer<String> consumer, File file, String command) {
        execute(consumer, file, "cmd", "/c", command);
    }

    @Override
    public String executeString(File file, String... commands) {
        return ProcessUtil.executeString(file, commands);
    }

    @Override
    public String executeString(File file, String command) {
        return executeString(file, "cmd", "/c", command);
    }

    @Override
    public String executeString(String command) {
        return executeString(null, command);
    }

    @Override
    public boolean executeBoolean(File file, String... commands) {
        return ProcessUtil.executeBoolean(file, commands);
    }

    @Override
    public boolean executeBoolean(File file, String command) {
        return executeBoolean(file, "cmd", "/c", command);
    }

    @Override
    public boolean executeBoolean(String command) {
        return executeBoolean(null, command);
    }

    @Override
    public List<String> executeList(File file, String... commands) {
        return ProcessUtil.executeList(file, commands);
    }

    @Override
    public List<String> executeList(File file, String command) {
        return executeList(file, "cmd", "/c", command);
    }

    @Override
    public List<String> executeList(String command) {
        return executeList(null, command);
    }

    @Override
    public void kill(String grep) {
        String netstat = "netstat -ano | findstr " + grep;
        List<String> content = executeList(netstat);
        if (content != null && !content.isEmpty()) {
            content.forEach(c -> {
                String[] s = c.split(StringPool.SPACE);
                execute("taskkill /f /pid " + s[s.length - 1]);
            });
        }
    }

    @Override
    public String pid(String grep) {
        String netstat = "netstat -ano | findstr " + grep;
        return executeString(netstat);
    }

    @Override
    public void sed(String filePath, String command) {
        // 暂不实现
    }
}
