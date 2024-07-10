package project.bookstore.service;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile multipartFile, String path) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString(),
                                "overwrite", true,
                                "folder", path)
                )
                .get("url")
                .toString();
    }

    public void deleteImage(String imageUrl) throws IOException {
        String publicId = extractPublicIdFromUrl(imageUrl);

        if (publicId != null) {
            try {
                Map result = cloudinary.uploader().destroy(publicId, Map.of());

                if (result.get("result").equals("ok")) {
                    System.out.println("Image deleted successfully");
                } else {
                    System.out.println("Failed to delete image: " + result.get("result"));
                }
            } catch (IOException e) {
                System.err.println("Error deleting image: " + e.getMessage());
                throw e;
            }
        } else {
            System.err.println("Invalid Cloudinary URL");
        }
    }

    private String extractPublicIdFromUrl(String imageUrl) {
        String[] urlParts = imageUrl.split("/");
        boolean foundUpload = false;
        StringBuilder publicId = new StringBuilder();

        for (String part : urlParts) {
            if (foundUpload) {
                if (publicId.length() > 0) {
                    publicId.append("/");
                }
                publicId.append(part);
            }
            if (part.equals("upload")) {
                foundUpload = true;
            }
        }

        String result = publicId.toString();
        int lastDotIndex = result.lastIndexOf('.');
        if (lastDotIndex != -1) {
            result = result.substring(0, lastDotIndex);
        }

        return result.isEmpty() ? null : result;
    }
}

