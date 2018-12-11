package org.quickMap.fileService.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FdfsConstant {

    @Value("${fdfs.downloadServer}")
    private String downloadServer;

    public String getDownloadServer() {
        return downloadServer;
    }
}
