package com.cloudtextbe.cloudtextbe_ver2.controllers;

import com.cloudtextbe.cloudtextbe_ver2.daos.UserDao;
import com.cloudtextbe.cloudtextbe_ver2.domain.*;
import com.cloudtextbe.cloudtextbe_ver2.services.FileService;
import com.cloudtextbe.cloudtextbe_ver2.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;
    @Resource
    private UserDao userDao;

    @PostMapping("/save")
    public Result<FileResponse> saveFileController(@RequestBody SaveRequest request) {
        User requestUser = userDao.findByUname(request.getUserName());
        if (requestUser == null) {
            return Result.error("-1", "User doesn't exist!");
        }
        FileResponse response = fileService.saveFile(request);
        if (response.getContext().isEmpty()) {
            return Result.success(response, "Save success!");
        } else {
            return Result.error("2", "Save file error!");
        }
    }

    @PostMapping("/rm")
    public Result<FileResponse> rmFileController(@RequestBody SaveRequest request) {
        User requestUser = userDao.findByUname(request.getUserName());
        if (requestUser == null) {
            return Result.error("-1", "User doesn't exist!");
        }
        FileResponse response = fileService.rmFile(request);
        if (response.getContext().isEmpty()) {
            return Result.success(response, "Remove success!");
        } else {
            return Result.error("3", "Remove file error!");
        }
    }

    @PostMapping("/getContext")
    public Result<FileResponse> getFileContextController(@RequestBody GetRequest request) {
        User requestUser = userDao.findByUname(request.getUserName());
        if (requestUser == null) {
            return Result.error("-1", "User doesn't exist!");
        }
        FileResponse response = fileService.getFileContext(request);
        return Result.success(response, "Get file context success!");
    }

    @PostMapping("/getFileList")
    public Result<FileListResponse> getFileList(@RequestBody GetRequest request) {
        User requestUser = userDao.findByUname(request.getUserName());
        if (requestUser == null) {
            return Result.error("-1", "User doesn't exist!");
        }
        FileListResponse response = fileService.getFileList(request);
        if (response.getList() == null) {
            return Result.error("3", "Get file list error!");
        } else {
            return Result.success(response, "Get file list Success!");
        }
    }
}
