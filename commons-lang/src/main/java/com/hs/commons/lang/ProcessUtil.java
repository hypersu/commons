package com.hs.commons.lang;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class ProcessUtil {

    public static Process getProcess(File dir, String... command) throws IOException {
        return new ProcessBuilder(command).directory(dir).start();
    }

    /**
     * 注意：运行进程返回的内容较少时，才可以使用
     */
    public static String executeString(String... command) {
        return executeString(null, command);
    }

    public static void executeFunction(Consumer<String> consumer,
                                       File file, String... command) {
        Process process = null;
        try {
            process = getProcess(file, command);
            ProcessFunction input = new ProcessFunction(process.getInputStream(), consumer);
            ProcessVoid error = new ProcessVoid(process.getErrorStream());
            input.start();
            error.start();
            process.waitFor();
            input.join();
            error.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeProcess(process);
        }
    }

    /**
     * 注意：运行进程返回的内容较少时，才可以使用
     */
    public static String executeString(File dir, String... command) {
        Process process = null;
        try {
            process = getProcess(dir, command);
            ProcessOutputThread output = new ProcessOutputThread(process.getInputStream());
            ProcessVoid error = new ProcessVoid(process.getErrorStream());
            output.start();
            error.start();
            process.waitFor();
            output.join();
            error.join();
            return output.getOutput().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeProcess(process);
        }
    }

    public static List<String> executeList(File dir, String... commands) {
        Process process = null;
        try {
            process = getProcess(dir, commands);
            ProcessList output = new ProcessList(process.getInputStream());
            ProcessVoid error = new ProcessVoid(process.getErrorStream());
            output.start();
            error.start();
            process.waitFor();
            output.join();
            error.join();
            return output.getOutput();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            closeProcess(process);
        }
    }


    public static void closeProcess(Process process) {
        if (process != null) {
            process.destroy();
        }
    }

    public static boolean executeBoolean(String command) {
        return executeBoolean(null, command);
    }

    /**
     * 在相对路径下运行命令
     */
    public static boolean executeBoolean(File dir, String command) {
        return executeBoolean(dir, command);
    }

    /**
     * 在相对路径下运行命令
     */
    public static boolean executeBoolean(File dir, String... commands) {
        if (execute(dir, commands) == 0) {
            return true;
        }
        return false;
    }

    public static void execute(String... command) {
        execute(null, command);
    }

    public static int execute(File dir, String... command) {
        Process process = null;
        try {
            process = getProcess(dir, command);
            ProcessVoid output = new ProcessVoid(process.getInputStream());
            ProcessVoid error = new ProcessVoid(process.getErrorStream());
            output.start();
            error.start();
            int state = process.waitFor();
            output.join();
            error.join();
            return state;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeProcess(process);
        }
    }

    static class ProcessOutputThread extends Thread {
        private InputStream is;
        private StringBuilder output;

        public ProcessOutputThread(InputStream is) throws IOException {
            if (null == is) {
                throw new IOException("the provided InputStream is null");
            }
            this.is = is;
            this.output = new StringBuilder();
        }

        public StringBuilder getOutput() {
            return this.output;
        }

        @Override
        public void run() {
            InputStreamReader ir;
            BufferedReader br = null;
            try {
                ir = new InputStreamReader(this.is);
                br = new BufferedReader(ir);
                String output;
                while (null != (output = br.readLine())) {
                    this.output.append(output);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseUtil.close(br);
            }
        }
    }

    static class ProcessFunction extends Thread {
        private InputStream is;
        private Consumer<String> consumer;

        public ProcessFunction(InputStream is,
                               Consumer<String> consumer) throws IOException {
            if (null == is) {
                throw new IOException("the provided InputStream is null");
            }
            this.is = is;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            InputStreamReader ir;
            BufferedReader br = null;
            try {
                ir = new InputStreamReader(this.is);
                br = new BufferedReader(ir);
                String output;
                while (null != (output = br.readLine())) {
                    consumer.accept(output);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseUtil.close(br);
            }
        }
    }

    static class ProcessList extends Thread {
        private InputStream is;
        private List<String> output;

        public ProcessList(InputStream is) throws IOException {
            if (null == is) {
                throw new IOException("the provided InputStream is null");
            }
            this.is = is;
            this.output = new ArrayList<>(16);
        }

        public List<String> getOutput() {
            return this.output;
        }

        @Override
        public void run() {
            InputStreamReader ir;
            BufferedReader br = null;
            try {
                ir = new InputStreamReader(this.is);
                br = new BufferedReader(ir);
                String output;
                while (null != (output = br.readLine())) {
                    this.output.add(output);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseUtil.close(br);
            }
        }
    }

    static class ProcessVoid extends Thread {
        private InputStream is;

        public ProcessVoid(InputStream is) throws IOException {
            if (null == is) {
                throw new IOException("the provided InputStream is null");
            }
            this.is = is;
        }

        @Override
        public void run() {
            InputStreamReader ir = null;
            BufferedReader br = null;
            try {
                ir = new InputStreamReader(this.is);
                br = new BufferedReader(ir);
                while (null != br.readLine()) {
                    // 忽略
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                CloseUtil.close(br);
            }
        }
    }
}
