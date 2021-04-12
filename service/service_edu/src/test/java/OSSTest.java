import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import org.junit.jupiter.api.Test;

public class OSSTest {
    String endpoint = "oss-cn-beijing.aliyuncs.com";
    String accessKeyId = "LTAI5tHm2sQPzH7rypt9bVKV";
    String accessKeySecret = "rUXuXxJHQWcLYGo5Z4F9DJqyzv0V5O";
    String bucketName = "file-admin-1";

    @Test
    public void testCreateBucket(){

        //OSS实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //创建bucketrequ对象
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);

        //创建存储空间
        ossClient.createBucket(createBucketRequest);

        ossClient.shutdown();
    }

    @Test
    public void testExist(){
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 判断存储空间examplebucket是否存在。如果返回值为true，则存储空间存在，否则存储空间不存在。
        boolean exists = ossClient.doesBucketExist("examplebucket");
        System.out.println(exists);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testAccessControl(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 设置存储空间的访问权限为私有。
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
