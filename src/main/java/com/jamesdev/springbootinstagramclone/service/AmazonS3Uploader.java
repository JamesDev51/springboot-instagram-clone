package com.jamesdev.springbootinstagramclone.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AmazonS3Uploader {

      private final AmazonS3Client amazonS3Client;

      @Value("${cloud.aws.s3.bucket}")
      public String bucket;

      public String uploadImage(MultipartFile multipartFile){
            String filename=createFileName(multipartFile.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(multipartFile.getSize());
            objectMetadata.setContentType(multipartFile.getContentType());
            try(InputStream inputStream= multipartFile.getInputStream()){
                  uploadFile(inputStream,objectMetadata,filename);
            }catch(IOException e){
                  throw new IllegalArgumentException(String.format("파일 변환 중 에러가 발생하였습니다. (%s)",multipartFile.getOriginalFilename()));
            }
            return getFileUrl(filename);
      }
      private String createFileName(String originalFileName){
            return UUID.randomUUID().toString().concat("_").concat(originalFileName);
      }

      public void uploadFile(InputStream inputStream,ObjectMetadata objectMetadata,String fileName){
            amazonS3Client.putObject(new PutObjectRequest(bucket,fileName,inputStream,objectMetadata));
      }

      public String getFileUrl(String fileName){
            return amazonS3Client.getUrl(bucket,fileName).toString();
      }
}
