package com.aag.getme.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.UUID;

@Service
public class S3Service {
    private static final String BUCKET_NAME = "aag-s3";
    private AmazonS3 s3Client;

    @Autowired
    public S3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(String base64) {
        try {

            byte[] data = Base64.getDecoder().decode(base64.split(",")[1]);
            String fileExtension = extractFileExtensionFromBase64(base64);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(data.length);
            metadata.setContentType("image/" + fileExtension);  // Adjust content type based on file type

            String fileName = UUID.randomUUID().toString() + "." + fileExtension;

            PutObjectRequest request = new PutObjectRequest(BUCKET_NAME, fileName, byteArrayInputStream, metadata);

            s3Client.putObject(request);

            return s3Client.getUrl(BUCKET_NAME, fileName).toString();

        } catch (Exception e) {
            throw new RuntimeException("Error uploading file: " + e.getMessage());
        }
    }

    public static String extractFileExtensionFromBase64(String base64String) {
        String[] parts = base64String.split(",");
        if (parts.length > 1 && parts[0].startsWith("data:") && parts[0].contains("/")) {
            String mimeType = parts[0].substring(5, parts[0].indexOf(";"));
            return mimeType.substring(mimeType.indexOf("/") + 1);
        }
        return "";
    }
}
