package com.cloudtextbe.cloudtextbe_ver2.services.serviceimpl;

import com.cloudtextbe.cloudtextbe_ver2.daos.FileDao;
//import com.cloudtextbe.cloudtextbe_ver2.domain.ActionType;
import com.cloudtextbe.cloudtextbe_ver2.domain.FileListResponse;
import com.cloudtextbe.cloudtextbe_ver2.domain.FileResponse;
import com.cloudtextbe.cloudtextbe_ver2.domain.GetRequest;
import com.cloudtextbe.cloudtextbe_ver2.domain.SaveRequest;
import com.cloudtextbe.cloudtextbe_ver2.services.FileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileDao fileDao;

    @Override
    public FileResponse saveFile(SaveRequest request) {
        FileResponse response = new FileResponse(request.getUserName(),
                request.getFileName(), request.getContext());
        try{
            fileDao.saveFile(request.getUserName(), request.getFileName(), request.getContext());
            response.setContext("");
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    @Override
    public FileResponse rmFile(SaveRequest request) {
        FileResponse response = new FileResponse(request.getUserName(),
                request.getFileName(), request.getContext());
        try{
            fileDao.rmFile(request.getUserName(), request.getFileName());
            response.setContext("");
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    @Override
    public FileListResponse getFileList(GetRequest request) {
        FileListResponse response = new FileListResponse();
        response.setUserName(request.getUserName());
        response.setList(fileDao.getFileList(request.getUserName()));
        return response;
    }

    @Override
    public FileResponse getFileContext(GetRequest request) {
        FileResponse response = new FileResponse();
        response.setUserName(request.getUserName());
        response.setFileName(request.getFileName());
        response.setContext(fileDao.getFileContext(request.getUserName(), response.getFileName()));
        return response;
    }
}
