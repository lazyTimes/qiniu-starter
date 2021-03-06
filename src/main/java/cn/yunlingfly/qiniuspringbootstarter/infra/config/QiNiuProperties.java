package cn.yunlingfly.qiniuspringbootstarter.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 七牛云属性配置
 *
 * @author yunlingfly@csdn
 */

@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiNiuProperties {
    /**
     * 七牛云的密钥
     */
    private String accessKey = "access-key";
    private String secretKey = "secret-key";
    /**
     * 存储空间名字
     */
    private String bucketName = "bucket-name";
    /**
     * 一般设置为cdn
     */
    private String cdnPrefix = "cdn";

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getCdnPrefix() {
        return cdnPrefix;
    }

    public void setCdnPrefix(String cdnPrefix) {
        this.cdnPrefix = cdnPrefix;
    }
}
