package com.cloudtextbe.cloudtextbe_ver2.daos;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

import static com.cloudtextbe.cloudtextbe_ver2.utils.FileUtils.*;

@Repository
public class FileDao {
    public static final File CWD = new File(System.getProperty("user.dir"));

    public static final File SAVE_DIR = join(CWD, "saved_files");

    public FileDao() {
        if (!SAVE_DIR.exists()) {
            SAVE_DIR.mkdir();
        }
    }

    /**
     * This method saves user's file.
     */
    public void saveFile(String userName, String fileName, String context) {
        File userPath = join(SAVE_DIR, userName);
        if (!userPath.exists()) {
            userPath.mkdir();
        }
        File destination = join(userPath, fileName);
        writeContents(destination, context);
    }

    /**
     * This method removes a user's file.
     */
    public void rmFile(String userName, String fileName) {
        File userPath = join(SAVE_DIR, userName);
        if (!userPath.exists()) {
            userPath.mkdir();
        }
        File destination = join(userPath, fileName);
        destination.delete();
    }

    /**
     * This method clears the ENTIRE saved_files foler
     */
    public void clearSaveFolder() {
        clearSaveFolderHelper(SAVE_DIR);
    }

    private void clearSaveFolderHelper(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        clearSaveFolderHelper(f);
                    }
                }
            }
            file.delete();
        }
    }

    /**
     * This method returns a list of files specified by userName
     */
    public List<String> getFileList(String userName) {
        try {
            return plainFilenamesIn(join(SAVE_DIR, userName));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method returns the context of a file
     */
    public String getFileContext(String userName, String fileName) {
        File destination = join(SAVE_DIR, userName, fileName);
        try {
            return readContentsAsString(destination);
        } catch (Exception e) {
            return "";
        }
    }
}
