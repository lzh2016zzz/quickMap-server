package org.quickMap.fileService.model;

import com.github.tobato.fastdfs.domain.StorePath;

public class UploadB64 {

    /**
     * 存储路径
     */
    private StorePath storePath;

    /**
     * 大小
     */
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public StorePath getStorePath() {
        return storePath;
    }

    public void setStorePath(StorePath storePath) {
        this.storePath = storePath;
    }

}
