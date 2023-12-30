package com.cloudtextbe.cloudtextbe_ver2.services;

import com.cloudtextbe.cloudtextbe_ver2.domain.FileListResponse;
import com.cloudtextbe.cloudtextbe_ver2.domain.FileResponse;
import com.cloudtextbe.cloudtextbe_ver2.domain.GetRequest;
import com.cloudtextbe.cloudtextbe_ver2.domain.SaveRequest;

import java.util.List;


public interface FileService {
    /**
     * As its name, save a file
     */
    FileResponse saveFile(SaveRequest request);
    /**
     * As its name, remove a file
     */
    FileResponse rmFile(SaveRequest request);
    /**
     * As its name, return a list of files specified by userName
     */
    FileListResponse getFileList(GetRequest request);

    /**
     * As its name, return the context of a file.
     */
    FileResponse getFileContext(GetRequest request);
}
