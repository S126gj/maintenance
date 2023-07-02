package com.device.file.minio.config;

import cn.hutool.core.util.StrUtil;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MinioTemplate implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(MinioTemplate.class);
    private MinioClient client;
    private final MinioConfig config;
    private static int RETRY_NUM = 3;
    @Override
    public void afterPropertiesSet() throws Exception {
        client = MinioClient.builder().endpoint(config.getUrl()).credentials(config.getAccessKey(), config.getSecretKey()).build();
        createBucket(config.getBucketName());
    }

    /**
     * 创建bucket
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            // 给每个新创建的桶，读写策略
            client.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(policy(bucketName)).build());
        }
    }

    /**
     * 读写策略
     * @param bucketName
     * @return
     */
    private String policy(String bucketName) {
        StringBuilder builder=new StringBuilder();
        builder.append("{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\",\"s3:AbortMultipartUpload\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "/*\"]}]}");
        return builder.toString();
    }

    /**
     * 获取全部bucket
     * <p>
     * https://docs.minio.io/cn/java-client-api-reference.html#listBuckets
     */
    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return client.listBuckets();
    }

    /**
     * 根据bucketName获取信息
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public Optional<Bucket> getBucket(String bucketName) {
        return client.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除信息
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
        client.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 根据文件前置查询文件
     *
     * @param bucketName bucket名称
     * @param prefix     前缀
     * @param recursive  是否递归查询
     * @return MinioItem 列表
     */
    @SneakyThrows
    public List getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
        List<Item> list = new ArrayList<>();
        Iterable<Result<Item>> objectsIterator = client.listObjects(ListObjectsArgs.builder().bucket(bucketName).prefix(prefix).recursive(recursive).build());
        if (objectsIterator != null) {
            Iterator<Result<Item>> iterator = objectsIterator.iterator();
            if (iterator != null) {
                while (iterator.hasNext()) {
                    Result<Item> result = iterator.next();
                    Item item = result.get();
                    list.add(item);
                }
            }
        }
        return list;
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     */
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
        return client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(objectName).expiry(expires).build());
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        return client.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 文件上传
     * @param bucketName
     * @param objectName
     * @param stream
     * @param contentType
     * @return
     * @throws Exception
     */
    public String putObject(String bucketName, String objectName, InputStream stream, String contentType){
        String url = "";
        try {
            client.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, stream.available(), -1).contentType(contentType).build());
            url = getObjectUrl(bucketName,objectName);
            stream.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return url;
    }

    /**
     * 文件上传到默认bucket
     * @param objectName
     * @param stream
     * @return
     * @throws Exception
     */
    public String putObject(String objectName,InputStream stream, String contenType)throws Exception{
        return putObject(config.getBucketName(), objectName, stream, contenType);
    }

    /**
     * 上传文件
     * @param bucketName
     * @param minioFilePath
     * @param localFile
     * @param mediaType
     * @return
     */
    public String uploadFile(String bucketName, String minioFilePath, String localFile, String mediaType)throws Exception {
        if (StrUtil.isBlank(mediaType)) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        if (minioFilePath.indexOf(".PDF")>-1 || minioFilePath.indexOf(".pdf")>-1) {
            mediaType = MediaType.APPLICATION_PDF_VALUE;
        }
        putObjectWithRetry(bucketName, minioFilePath, localFile, mediaType);
        return getObjectUrl(bucketName,minioFilePath);

    }
    /**
     * 上传文件
     * @param bucketName
     * @param minioFilePath
     * @param stream
     * @param mediaType
     * @return
     */

    public String uploadFile(String bucketName, String minioFilePath, InputStream stream, String mediaType)throws Exception {
        if (StrUtil.isBlank(mediaType)) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        if (minioFilePath.indexOf(".PDF")>-1 || minioFilePath.indexOf(".pdf")>-1) {
            mediaType = MediaType.APPLICATION_PDF_VALUE;
        }
        putObjectWithRetry(bucketName, minioFilePath, stream, mediaType);
        stream.close();
        return getObjectUrl(bucketName,minioFilePath);
    }

    /**
     * 获取文件地址
     * @param bucketName
     * @param objectName
     * @return
     * @throws Exception
     */
    public String getObjectUrl(String bucketName, String objectName)throws Exception{
        new GetPresignedObjectUrlArgs();
        return client.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(objectName).method(Method.GET).build()
        );
    }

    public void putObjectWithRetry(String bucketName, String objectName, InputStream stream, String contentType) throws InsufficientDataException, InternalException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, XmlParserException, IOException {
        int current = 0;
        boolean isSuccess = false;
        while (!isSuccess && current < RETRY_NUM) {
            putObject(bucketName, objectName, stream, contentType);
            isSuccess = true;
        }
        if (current == RETRY_NUM) {
            log.error("[minio] putObject, backetName={}, objectName={}, failed finally!");
        }
    }
    private void putObjectWithRetry(String bucketName, String objectName, String fileName, String contentType) throws InsufficientDataException, InternalException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, XmlParserException, IOException {
        int current = 0;
        boolean isSuccess = false;
        while (!isSuccess && current < RETRY_NUM) {
            try {
                client.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(objectName).filename(fileName).contentType(contentType).build());
                isSuccess = true;
            } catch (ErrorResponseException | ServerException e) {
                log.warn("[minio] putObject stream, ErrorResponseException occur for time =" + current, e);
                current++;
            }
        }
        if (current == RETRY_NUM) {
            log.error("[minio] putObject, backetName={}, objectName={}, failed finally!");
        }
    }

    private String uploadJpeg(String bucketName, String minioPath, String jpgFilePath)throws Exception {
        return uploadFile(bucketName, minioPath, jpgFilePath, MediaType.IMAGE_JPEG_VALUE);
    }
    private String uploadJpeg(String minioPath, String jpgFilePath)throws Exception {
        return uploadFile(config.getBucketName(), minioPath, jpgFilePath, MediaType.IMAGE_JPEG_VALUE);
    }

    /**
     * 通用图片上传
     * @param minioPath
     * @param inputStream
     * @return
     * @throws Exception
     */
    public String uploadJpeg(String bucketName, String minioPath, InputStream inputStream) throws Exception {
        return uploadFile(bucketName, minioPath, inputStream, MediaType.IMAGE_JPEG_VALUE);
    }

    public String uploadStream(String bucketName, String minioFilePath, InputStream inputStream, String mediaType)throws Exception {
        if (StrUtil.isBlank(mediaType)) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        putObjectWithRetry(bucketName, minioFilePath, inputStream, mediaType);
        return getObjectUrl(bucketName, minioFilePath);

    }

    private String uploadFile(String minioFilePath, String localFile, String mediaType)throws Exception {
        return uploadFile(config.getBucketName(),minioFilePath,localFile,mediaType);

    }
    public String uploadFile(String minioFilePath,InputStream stream, String mediaType)throws Exception {
        return uploadFile(config.getBucketName(),minioFilePath,stream,mediaType);
    }

    /**
     * 上传可直接下载文件，无需预览
     * @param minioFilePath
     * @param stream
     * @param mediaType
     * @return
     * @throws Exception
     */
    public String uploadFileDownload(String bucketName, String minioFilePath,InputStream stream, String mediaType)throws Exception {
        if (StrUtil.isBlank(mediaType)) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        putObjectWithRetry(bucketName, minioFilePath, stream, mediaType);
        stream.close();
        return getObjectUrl(bucketName, minioFilePath);
    }

    /**
     * 通用文件上传
     * @param minioFilePath
     * @param stream
     * @return
     * @throws Exception
     */
    public String uploadFile(String bucketName, String minioFilePath,InputStream stream)throws Exception {
        return uploadFile(bucketName ,minioFilePath,stream,null);
    }

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        client.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 根据工厂db获取桶名称
     * @param db
     * @return
     */
    public String getBucketName(String db) {
        String finalName = db.replaceAll("_", "");
        createBucket(finalName);
        return finalName;
    }
}
