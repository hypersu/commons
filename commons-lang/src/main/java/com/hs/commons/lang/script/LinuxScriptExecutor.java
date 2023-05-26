package com.hs.commons.lang.script;

import com.hs.commons.lang.ProcessUtil;
import com.hs.commons.lang.StringPool;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class LinuxScriptExecutor implements ScriptExecutor {
    @Override
    public void execute(File file, String... commands) {
        ProcessUtil.execute(file, commands);
    }

    @Override
    public void execute(File file, String command) {
        execute(file, "/bin/sh", "-c", command);
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
        execute(consumer, file, "/bin/sh", "-c", command);
    }

    @Override
    public String executeString(File file, String... commands) {
        return ProcessUtil.executeString(file, commands);
    }

    @Override
    public String executeString(File file, String command) {
        return executeString(file, "/bin/sh", "-c", command);
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
        return executeBoolean(file, "/bin/sh", "-c", command);
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
        return executeList(file, "/bin/sh", "-c", command);
    }

    @Override
    public List<String> executeList(String command) {
        return executeList(null, command);
    }

    @Override
    public void kill(String grep) {
        execute("ps -ef | grep '" + grep
                + "' | grep -v grep | awk '{print $2}' | xargs kill -9");
    }

    @Override
    public String pid(String grep) {
        return executeString("ps -ef | grep '" + grep
                + "' | grep -v grep | awk '{print $2}'");
    }

    @Override
    public void sed(String filePath, String command) {
        StringBuilder builder = new StringBuilder();
        builder.append("sed").append(StringPool.SPACE).append("-i")
                .append(StringPool.SPACE)
                .append(StringPool.SINGLE_QUOTE)
                .append(StringPool.SLASH)
                .append(command.replace(StringPool.SLASH,
                        StringPool.BACK_SLASH + StringPool.SLASH))
                .append(StringPool.SLASH)
                .append("d")
                .append(StringPool.SINGLE_QUOTE)
                .append(StringPool.SPACE)
                .append(filePath);
        System.out.println("删除命令：" + builder);
        execute(builder.toString());
        // 保证文件内容从文件缓冲区写入磁盘
        execute("sync");
    }
}
