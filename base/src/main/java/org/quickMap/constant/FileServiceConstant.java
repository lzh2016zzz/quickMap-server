package org.quickMap.constant;

import org.springframework.stereotype.Component;

@Component
public class FileServiceConstant {


    public static class Meta {

        public static final String AUTHOR = "author";//作者
        public static final String FILENAME = "fileName";//文件名
        public static final String TIMESTAMP = "timestamp";//时间戳
        public static final String FILEID = "fid";//文件id
    }

    public static class DelStatus{

        /**
         * 删除
         */
        public static final int del =  0;

        /**
         * 正常
         */
        public static final int common = 1;
    }

    public static class MineType {

        public static final String DEFAULT_TYPE = "application/octet-stream";

        public static String[][] getMineTypes() {
            return new String[][]{
                    {".3gp", "video/3gpp"},
                    {".asf", "video/x-ms-asf"},
                    {".avi", "video/x-msvideo"},
                    {".m4u", "video/vnd.mpegurl"},
                    {".m4v", "video/x-m4v"},
                    {".mov", "video/quicktime"},
                    //mp4 统一使用mp4
                    {".mp4", "video/mp4"},
                    {".mpg4", "video/mp4"},

                    {".mpe", "video/x-mpeg"},
                    //mpeg 使用相应的默认程序打开，但不添加文件拓展名
                    {"", "video/mpg"},
                    {".mpeg", "video/mpg"},
                    {".mpg", "video/mpg"},

                    //audio
                    {".m3u", "audio/x-mpegurl"},
                    //mp4a-latm 使用相应的默认程序打开，但不添加文件拓展名
                    {"", "audio/mp4a-latm"},
                    {".m4a", "audio/mp4a-latm"},
                    {".m4b", "audio/mp4a-latm"},
                    {".m4p", "audio/mp4a-latm"},

                    //x-mpeg
                    {".mp2", "x-mpeg"},
                    {".mp3", "audio/x-mpeg"},

                    {".mpga", "audio/mpeg"},
                    {".ogg", "audio/ogg"},
                    {".rmvb", "audio/x-pn-realaudio"},
                    {".wav", "audio/x-wav"},
                    {".wma", "audio/x-ms-wma"},
                    {".wmv", "audio/x-ms-wmv"},

                    //text
                    //plain 使用相应的默认程序打开，但不添加文件拓展名
                    {"", "text/plain"},
                    {".c", "text/plain"},
                    {".java", "text/plain"},
                    {".conf", "text/plain"},
                    {".cpp", "text/plain"},
                    {".h", "text/plain"},
                    {".prop", "text/plain"},
                    {".rc", "text/plain"},
                    {".sh", "text/plain"},
                    {".log", "text/plain"},
                    {".txt", "text/plain"},
                    {".xml", "text/plain"},

                    //统一使用html
                    {".html", "text/html"},
                    {".htm", "text/html"},

                    {".css", "text/css"},

                    //image
                    //jpeg统一使用jpg
                    {".jpg", "image/jpeg"},
                    {".jpeg", "image/jpeg"},
                    {".bmp", "image/bmp"},
                    {".gif", "image/gif"},
                    {".png", "image/png"},

                    //application
                    {"", "application/octet-stream"},
                    {".bin", "application/octet-stream"},
                    {".class", "application/octet-stream"},
                    {".exe", "application/octet-stream"},
                    {"class", "application/octet-stream"},

                    {".apk", "application/vnd.android.package-archive"},
                    {".doc", "application/msword"},
                    {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
                    {".xls", "application/vnd.ms-excel"},
                    {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},

                    {".gtar", "application/x-gtar"},
                    {".gz", "application/x-gzip"},
                    {".jar", "application/java-archive"},
                    {".js", "application/x-javascript"},
                    {".mpc", "application/vnd.mpohun.certificate"},
                    {".msg", "application/vnd.ms-outlook"},
                    {".pdf", "application/pdf"},
                    //vnd.ms-powerpoint 使用相应的默认程序打开，但不添加文件拓展名
                    {"", "application/vnd.ms-powerpoint"},
                    {".pps", "application/vnd.ms-powerpoint"},
                    {".ppt", "application/vnd.ms-powerpoint"},

                    {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
                    {".rtf", "application/rtf"},
                    {".tar", "application/x-tar"},
                    {".tgz", "application/x-compressed"},
                    {".wps", "application/vnd.ms-works"},
                    {".z", "application/x-compress"},
                    {".zip", "application/x-zip-compressed"},
            };
        }

    }
}
