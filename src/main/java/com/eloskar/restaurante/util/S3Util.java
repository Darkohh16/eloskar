package com.eloskar.restaurante.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;
import java.util.UUID;

public class S3Util {

    private static final String BUCKET_NAME = "eloskar-static";
    private static final String REGION = "us-east-1";

    private static final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRegion(REGION)
            .build();

    public static String subirImagen(String nombreArchivo,
                                     InputStream inputStream,
                                     long contentLength,
                                     String contentType) {
        try {
            String extension = "";
            int dotIndex = nombreArchivo.lastIndexOf('.');
            if (dotIndex >= 0) {
                extension = nombreArchivo.substring(dotIndex);
            }

            String nuevoNombre = UUID.randomUUID() + extension;
            String key = nuevoNombre;

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);
            if (contentType != null) {
                metadata.setContentType(contentType);
            }

            s3Client.putObject(BUCKET_NAME, key, inputStream, metadata);

            return "https://" + BUCKET_NAME + ".s3." + REGION + ".amazonaws.com/" + key;
        } catch (SdkClientException e) {
            e.printStackTrace();
            return null;
        }
    }
}
