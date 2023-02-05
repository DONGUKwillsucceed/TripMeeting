package tripmeeting.com.tripmeeting.domain.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;

@Component
public class S3Service {
    //Amazon-s3-sdk
    private AmazonS3 s3Client;

    private String accessKey;

    private String secretKey;
    private final Regions clientRegion = Regions.AP_NORTHEAST_2;


    private String bucket;

    private long expiryMillis;

    public S3Service(@Value("${aws.access.id}") String accessKey, @Value("${aws.access.key}") String secretKey,
                     @Value("${aws.s3.bucket}") String bucket, @Value("${aws.s3.access.expiryMillis}") long expiryMillis) {
        initValues(accessKey, secretKey, bucket,expiryMillis);
        createS3Client();
    }

    private void initValues(String accessKey, String secretKey, String bucket, long expiryMillis){
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucket = bucket;
        this.expiryMillis = expiryMillis;
    }

    //aws S3 client 생성
    private void createS3Client() {

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(clientRegion)
                .build();
    }

    public String getObjectUrl(String s3Path){
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += expiryMillis; // 1 hour
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, s3Path.replace(File.separatorChar, '/'))
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        return s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    public String getUploadUrl(String s3Path) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += expiryMillis; // 1 hour
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, s3Path.replace(File.separatorChar, '/'))
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(expiration);
        return s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }
}
