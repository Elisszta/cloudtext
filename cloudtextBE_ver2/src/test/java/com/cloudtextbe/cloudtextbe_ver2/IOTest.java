package com.cloudtextbe.cloudtextbe_ver2;

import com.cloudtextbe.cloudtextbe_ver2.daos.FileDao;
import static com.cloudtextbe.cloudtextbe_ver2.utils.FileUtils.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class IOTest {
    @Test
    public void testSave() {
        FileDao fileDao = new FileDao();
        fileDao.saveFile("hi1", "mark", "hi, java!");
        Assertions.assertEquals("hi, java!",
                readContentsAsString(join(FileDao.SAVE_DIR, "hi1", "mark")));
        fileDao.clearSaveFolder();
    }

    @Test
    public void testRm() {
        FileDao fileDao = new FileDao();
        fileDao.saveFile("hi1", "mark", "hi, java!");
        Assertions.assertEquals("hi, java!",
                readContentsAsString(join(FileDao.SAVE_DIR, "hi1", "mark")));
        fileDao.rmFile("hi1", "mark");
        Assertions.assertFalse(join(FileDao.SAVE_DIR, "hi1", "mark").exists());
        fileDao.clearSaveFolder();
    }

    @Test
    public void testGetList() {
        FileDao fileDao = new FileDao();
        fileDao.saveFile("hi1", "mark", "hi, java!");
        fileDao.saveFile("hi1", "hi", "hi, java!");
        fileDao.saveFile("hi1", "jack", "hi, java!");
        fileDao.saveFile("hi2", "jack", "hi, java!");
        List<String> list = fileDao.getFileList("hi1");
        Assertions.assertTrue(list.contains("mark"));
        Assertions.assertTrue(list.contains("hi"));
        Assertions.assertTrue(list.contains("jack"));
        fileDao.clearSaveFolder();
    }
}
