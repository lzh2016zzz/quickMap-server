package org.quickMap.fileService.model;

public class FileInfoData {

    private String filename;
    private long size;
    private String path;
    private String delParam;
    private String downloadUrl;
    private long timestamp;
    private Integer author;
    private String thumbImagePath;

    public String getDelParam() {
        return delParam;
    }

    public void setDelParam(String delParam) {
        this.delParam = delParam;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getThumbImagePath() {
        return thumbImagePath;
    }

    public void setThumbImagePath(String thumbImagePath) {
        this.thumbImagePath = thumbImagePath;
    }

    public static final class Builder {
        private FileInfoData fileInfoData;

        private Builder() {
            fileInfoData = new FileInfoData();
        }

        public static Builder aFileInfoData() {
            return new Builder();
        }

        public Builder setFilename(String filename) {
            fileInfoData.setFilename(filename);
            return this;
        }

        public Builder setSize(long size) {
            fileInfoData.setSize(size);
            return this;
        }

        public Builder setPath(String path) {
            fileInfoData.setPath(path);
            return this;
        }

        public Builder setDelParam(String delParam) {
            fileInfoData.setDelParam(delParam);
            return this;
        }

        public Builder setDownloadUrl(String downloadUrl) {
            fileInfoData.setDownloadUrl(downloadUrl);
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            fileInfoData.setTimestamp(timestamp);
            return this;
        }

        public Builder setAuthor(Integer author) {
            fileInfoData.setAuthor(author);
            return this;
        }

        public Builder setThumbImagePath(String thumbImagePath) {
            fileInfoData.setThumbImagePath(thumbImagePath);
            return this;
        }

        public FileInfoData build() {
            return fileInfoData;
        }
    }
}
