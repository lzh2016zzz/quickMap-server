package org.quickmap.dataService.cfg;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis mapper接口扫描配置
 */
@Configuration
@AutoConfigureAfter(MybatisConfig.class)
@MapperScan("org.quickmap.dataService.dao")
public class MybatisMapperScannerConfig {

    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(MybatisConfig.SqlSessionFactoryName);
        mapperScannerConfigurer.setBasePackage("org.quickmap.dataService.dao");
        return mapperScannerConfigurer;
    }
}