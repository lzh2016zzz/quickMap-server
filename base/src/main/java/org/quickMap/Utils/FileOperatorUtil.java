package org.quickMap.Utils;

import org.quickMap.constant.FileServiceConstant;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

import static org.quickMap.constant.FileServiceConstant.MineType.getMineTypes;

public class FileOperatorUtil {

    private static ConcurrentHashMap<String, String> mineTypeSuffixMap;

    static {
        mineTypeSuffixMap = createMineTypeSuffixMap();
    }

    /**
     * 根据后缀获取contentType
     *
     * @param suffix
     * @return
     */
    public static String getContentType(String suffix) {
        String s;
        return (s = mineTypeSuffixMap.get(suffix)) == null ? FileServiceConstant.MineType.DEFAULT_TYPE : s;
    }

    /**
     * 解码路径
     *
     * @param path
     * @return
     */
    public static String decodePath(String path) {
        return new String(Base64Utils.decodeFromUrlSafeString(path), Charset.forName("utf-8"));
    }

    /**
     * 编码路径
     *
     * @param path
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodePath(String path) throws UnsupportedEncodingException {
        return Base64Utils.encodeToUrlSafeString(path.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成mineTypeMap
     */
    private static ConcurrentHashMap<String, String> createMineTypeSuffixMap() {
        ConcurrentHashMap<String, String> mimeHashMap = new ConcurrentHashMap<>();
        String[][] mimeStrTable = FileServiceConstant.MineType.getMineTypes();
        for (int i = 0; i < getMineTypes().length; i++) {
            if (mimeStrTable[i][0].length() > 0 && (!mimeHashMap.containsKey(mimeStrTable[i][0]))) {
                mimeHashMap.put(mimeStrTable[i][0], mimeStrTable[i][1]);
            }
        }
        return mimeHashMap;
    }
}
