package cn.yunlingfly.qiniuspringbootstarter.api.service.impl;

import cn.yunlingfly.qiniuspringbootstarter.api.service.IQiniuService;
import cn.yunlingfly.qiniuspringbootstarter.infra.config.QiNiuProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class QiniuServiceImpl implements IQiniuService {
    private Logger logger = Logger.getLogger(this.getClass());    //log4j日志

    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private BucketManager bucketManager;
    @Autowired
    private Auth auth;
    @Autowired
    private QiNiuProperties qiNiuProperties;

    @Override
    public Response uploadFile(File file, String key, boolean existed) throws QiniuException {
        Response response;
        // 覆盖上传
        if (existed) {
            response = this.uploadManager.put(file, key, getUploadToken(key));
        } else {
            System.out.println("使用文件上传");
            response = this.uploadManager.put(file, key, getUploadToken());
            int retry = 0;
            while (response.needRetry() && retry < 3) {
                response = this.uploadManager.put(file, key, getUploadToken());
                retry++;
            }
        }

        return response;
    }

    @Override
    public Response uploadFile(String filePath, String key, boolean existed) throws QiniuException {
        Response response;
        // 覆盖上传
        if (existed) {
            response = this.uploadManager.put(filePath, key, getUploadToken(key));
        } else {
            System.out.println("使用文件路径上传");
            response = this.uploadManager.put(filePath, key, getUploadToken());
            int retry = 0;
            while (response.needRetry() && retry < 3) {
                response = this.uploadManager.put(filePath, key, getUploadToken());
                retry++;
            }
        }

        return response;
    }

    @Override
    public void deleteFile(String key) throws QiniuException {
        bucketManager.delete(qiNiuProperties.getBucket(), key);
    }

    /**
     * 获取上传凭证，覆盖上传
     */
    private String getUploadToken(String fileName) {
        return this.auth.uploadToken(qiNiuProperties.getBucket(), fileName);
    }

    /**
     * 获取上传凭证，普通上传
     */
    private String getUploadToken() {
        return this.auth.uploadToken(qiNiuProperties.getBucket(), null);
    }
}