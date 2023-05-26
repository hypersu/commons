package com.hs.commons.lang.script;

public class ScriptExecutors {
    private static final String os = System.getProperty("os.name");
    public static final String LINUX = "Linux";
    public static final String WINDOWS = "Windows";
    private static final ScriptExecutor linuxEngine = new LinuxScriptExecutor();
    private static final ScriptExecutor windowEngine = new WindowScriptExecutor();
    private static final String system;

    static {
        system = os.startsWith(WINDOWS) ? WINDOWS : LINUX;
    }

    public static String getSystem() {
        return system;
    }

    public static boolean isWindows() {
        return WINDOWS.equals(system);
    }

    public static ScriptExecutor getExecutor() {
        if (WINDOWS.equals(system)) {
            return windowEngine;
        } else {
            return linuxEngine;
        }
    }
}
