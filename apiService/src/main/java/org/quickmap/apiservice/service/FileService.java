package org.quickmap.apiservice.service;

import feign.Request;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 */
@FeignClient(value = "fileService", configuration = {FileService.MultipartSupportConfig.class, FileService.TimeoutConfig.class})
public interface FileService {

    /**
     * 上传单个文件
     *
     * @param files
     * @return
     */
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(@RequestPart("file") MultipartFile files, @RequestParam(required = false) boolean thumbImage);

    /**
     * 上传base64
     *
     * @param b64
     * @return
     * @throws Exception
     */
    @PostMapping("/file/uploadB64")
    String uploadB64(@RequestParam("b64") String b64, @RequestParam("fileName") String fileName, @RequestParam(required = false) boolean thumbImage);

    /**
     * 下载文件
     *
     * @param path
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/file/{group}/{path}")
    ResponseEntity<byte[]> download(@PathVariable("group") String group, @PathVariable("path") String path);

    /**
     * 删除文件
     *
     * @param delPath
     * @return
     */
    @RequestMapping(value = "/file/del/{delPath}")
    String delete(@PathVariable("delPath") String delPath);

    /**
     * 根据名称精确搜索
     *
     * @param fileName
     * @return
     */
    @RequestMapping("/search/exec")
    String search(@RequestParam(value = "fileName", required = false) String fileName, @RequestParam(value = "before", required = false) Long before, @RequestParam(value = "after", required = false) Long after, @RequestParam(value = "suffix", required = false) String suffix);

    /**
     * 根据前缀获取完成建议
     *
     * @param prefix
     * @return
     */
    @RequestMapping(value = "/search/prefix/{prefix}")
    String prefix(@PathVariable("prefix") String prefix);

    /**
     * 初始化自动补全数据
     * @param
     * @return
     */
    @RequestMapping("/search/initAuto")
    String initAuto(@RequestParam("rebuild") boolean rebuild) throws Exception;

    /**
     * 配置上传文件支持
     */
    class MultipartSupportConfig {

        @Autowired
        FeignClientsConfiguration feignClientsConfiguration;

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }

    /**
     * 设置超时时间.防止因为超时导致下载/上传失败
     */
    class TimeoutConfig {

        @Bean
        Request.Options requestOptions(ConfigurableEnvironment env) {
            int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 1000 * 120);
            int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 1000 * 30);
            return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
        }
    }
}
