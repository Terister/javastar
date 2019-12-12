package com.wangke.javastar.biz.agent.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CephAgent {


    private static final String AWS_ACCESS_KEY = "my_AWS_ACCESS_KEY"; // 【你的 access_key】
    private static final String AWS_SECRET_KEY = "my_AWS_SECRET_KEY"; // 【你的 aws_secret_key】
    private static final String bucketName = "my_bucketName"; // 【你 bucket 的名字】 # 首先需要保证 s3 上已经存在该存储桶

    private static final String ENDPOINT = "http://192.168.xx.xx:7480";


    private static AmazonS3 s3Client;

    //静态块：初始化S3的连接对象s3Client！ 需要3个参数：AWS_ACCESS_KEY，AWS_SECRET_KEY，AWS_REGION
    static {
        AWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);
        //这是一个构建者模式，通过不停地来追加各种参数；spark和flink中有很常见
/*        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(AWS_REGION)
                .build();*/
/*        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setSignerOverride("S3SignerType");
        clientConfig.setProtocol(Protocol.HTTP);
        s3Client = new AmazonS3Client(awsCredentials, clientConfig);
        s3Client.setEndpoint(ENDPOINT);*/

        //注意：因为是本地方式，访问相应的S3文件系统，所以signingRegion可以默认为空。
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(ENDPOINT, ""))
                .build();

        //测试是否连接上去S3
        System.out.println("||| 【list all buckets:】: " + s3Client.listBuckets() + "\n");


    }

    /**
     * 上传本地文件到AWS S3
     *
     * @param tempFile
     * @param keyName
     * @throws IOException
     */
    public static void uploadToS3(File tempFile, String keyName) throws IOException {
        try {
            PutObjectRequest request = new PutObjectRequest(bucketName, keyName, tempFile);
            s3Client.putObject(request);
            System.out.println("上传文件成功！！！");
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        } catch (AmazonClientException ace) {
            ace.printStackTrace();
        }
    }

    /**
     * 下载相应的S3数据到本地文件系统
     *
     * @param s3Client
     * @param bucketName
     * @param key
     * @param targetFilePath
     */
    public static void downloadFromS3(AmazonS3 s3Client, String bucketName, String key, String targetFilePath) {
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));
        if (object != null) {
            System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
            InputStream input = null;
            FileOutputStream fileOutputStream = null;
            byte[] data = null;
            try {
                //获取文件流
                input = object.getObjectContent();
                data = new byte[input.available()];
                int len = 0;
                fileOutputStream = new FileOutputStream(targetFilePath);
                while ((len = input.read(data)) != -1) {
                    fileOutputStream.write(data, 0, len);
                }
                System.out.println("下载文件成功");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
