package aes.util;

import java.io.*;

/**
 * Created by chenhansen on 2018/5/19.
 */
public class FileUtil {

    public static int write(final String filePath, final String contents) {
        File file = new File(filePath);
        if (contents == null) {
            System.out.println("file [" + filePath + "] invalid!!!");
            return 0;
        }

        // 当文件存在但不可写时
        if (isFileExists(file) && (!file.canRead())) {
            return 0;
        }

        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            if (!isFileExists(file)) {
                file.createNewFile();
            }

            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(contents);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeQuietly(bw);
            closeQuietly(fw);
        }

        return 1;
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
        }
    }

    private static boolean isFileExists(final File file) {
        if (file.exists() && file.isFile()) {
            return true;
        }

        return false;
    }
}
