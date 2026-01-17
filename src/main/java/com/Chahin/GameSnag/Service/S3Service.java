package com.Chahin.GameSnag.Service;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadImage(@NonNull InputStream file, String fileName, String fileType, long fileLength) {

        String key = "images/games/" + UUID.randomUUID() + "-" + fileName;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(fileType)
                .build();

        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromInputStream(file, fileLength)
        );

        return getPublicUrl(key);
    }

    public void deleteAllS3Images() {
        ArrayList<ObjectIdentifier> keys = getObjectKeys();
        Delete del = Delete.builder()
                .objects(keys)
                .build();

        try {
            DeleteObjectsRequest multipleObjectDelete = DeleteObjectsRequest.builder()
                    .bucket(bucketName)
                    .delete(del)
                    .build();

            s3Client.deleteObjects(multipleObjectDelete);
            System.out.println("[S3 DELETION] - Successful - All Images deleted!");
        } catch (Exception e) {
            System.out.println("[S3 DELETION] - Failed - Unable to delete images:\n" + e);
        }
    }

    private String getPublicUrl(String key) {
        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }

    private ArrayList<ObjectIdentifier> getObjectKeys() {
        ArrayList<ObjectIdentifier> keys = new ArrayList<>();
        try {
            ListObjectsV2Request listReq = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .build();

            ListObjectsV2Iterable listRes = s3Client.listObjectsV2Paginator(listReq);
            listRes.stream()
                    .flatMap(r -> r.contents().stream())
                    .forEach(content -> {
                        ObjectIdentifier objectIdentifier = ObjectIdentifier.builder()
                                .key(content.key())
                                .build();

                        keys.add(objectIdentifier);
                    });

            return keys;
        } catch (Exception e) {
            System.out.println("[S3 DELETION] - Failed - Unable to get image keys:\n" + e);
            return null;
        }
    }
}
