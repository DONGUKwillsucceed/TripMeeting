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

import java.io.File;
import java.util.Date;
import java.util.Objects;

public class S3Service {
    //Amazon-s3-sdk
    private AmazonS3 s3Client;

    @Value("${aws.access.id}")
    private String accessKey;

    @Value("${aws.access.key}")
    private String secretKey;
    private final Regions clientRegion = Regions.AP_NORTHEAST_2;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.s3.access.expiryMillis}")
    private long expiryMillis;

    private S3Service() {
        createS3Client();
    }

    //singleton pattern
    static private final S3Service instance = null;

    public static S3Service getInstance() {
        return Objects.requireNonNullElseGet(instance, S3Service::new);
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
