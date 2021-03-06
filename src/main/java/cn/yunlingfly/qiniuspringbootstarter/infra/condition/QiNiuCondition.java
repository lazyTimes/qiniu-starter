package cn.yunlingfly.qiniuspringbootstarter.infra.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * 校验类
 *
 * @author yunlingfly@csdn
 */

public class QiNiuCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String ak = context.getEnvironment().getProperty("qiniu.access-key");
        String sk = context.getEnvironment().getProperty("qiniu.secret-key");
        String bucketName = context.getEnvironment().getProperty("qiniu.bucket-name");

        if (StringUtils.isEmpty(ak)) {
            throw new RuntimeException("Lack of qiniuyun configuration:access-key");
        } else if (StringUtils.isEmpty(sk)) {
            throw new RuntimeException("Lack of qiniuyun configuration:qiniu.secret-key");
        } else if (StringUtils.isEmpty(bucketName)) {
            throw new RuntimeException("Lack of qiniuyun configuration:qiniu.bucket-name");
        } else {
            return true;
        }
    }
}
