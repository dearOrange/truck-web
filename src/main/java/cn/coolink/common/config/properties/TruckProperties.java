package cn.coolink.common.config.properties;

import org.springframework.beans.factory.annotation.Value;

/**
 * Project Name:
 * Package Name:
 * Function: 系统个性化配置
 * user:
 * Date:2018/3/8
 */
public class TruckProperties {

    /**
     * 系统环境类型
     */
    @Value("${systemType}")
    private String systemType;


    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

}
