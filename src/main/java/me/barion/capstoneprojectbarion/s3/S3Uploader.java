package me.barion.capstoneprojectbarion.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    // MultipartFile을 전달받아 File로 전환한 후 S3에 업로드
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 변환에 실패했습니다."));

        return upload(uploadFile, dirName);
    }

    // Base64 인코딩된 이미지를 S3에 업로드
    public String uploadBase64Image(String base64Image, String dirName) throws IOException {
        // Base64 문자열에서 데이터 부분만 추출 (data:image/jpeg;base64,로 시작하는 부분 제거)
        String base64Data = base64Image;
        String contentType = "image/jpeg"; // 기본값

        if (base64Image.contains(";base64,")) {
            // 콘텐츠 타입 추출
            contentType = base64Image.substring(5, base64Image.indexOf(";base64,"));
            // 실제 Base64 데이터 추출
            base64Data = base64Image.substring(base64Image.indexOf(",") + 1);
        }

        // Base64 디코딩
        byte[] imageBytes = Base64.getDecoder().decode(base64Data);

        // S3에 업로드할 파일명 생성
        String fileName = dirName + "/" + UUID.randomUUID() + getExtensionFromContentType(contentType);

        // S3에 업로드
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageBytes.length);
        metadata.setContentType(contentType);

        try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return amazonS3Client.getUrl(bucket, fileName).toString();
        }
    }

    private String getExtensionFromContentType(String contentType) {
        switch (contentType) {
            case "image/jpeg":
                return ".jpg";
            case "image/png":
                return ".png";
            case "image/gif":
                return ".gif";
            case "image/webp":
                return ".webp";
            default:
                return ".jpg";
        }
    }

    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();
        String uploadImageUrl = putS3(uploadFile, fileName);

        // 로컬에 생성된 파일 삭제
        removeNewFile(uploadFile);

        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        if (convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
