package com.noCountry.social_media_backend.demo.service.post;

import com.noCountry.social_media_backend.demo.dto.posts.*;
import com.noCountry.social_media_backend.demo.entity.post.Post;
import com.noCountry.social_media_backend.demo.entity.user.User;
import com.noCountry.social_media_backend.demo.entity.user_profile.UserProfile;
import com.noCountry.social_media_backend.demo.repository.PostRepository;
import com.noCountry.social_media_backend.demo.repository.UserRepository;
import com.noCountry.social_media_backend.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PostService {

    private static final String UPLOAD_DIR = "src/main/resources/images";
    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final UserProfileService userProfileService;

    @Autowired
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, UserProfileService userProfileService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userProfileService = userProfileService;
        this.userRepository = userRepository;
    }

    public PostResponseDTO getPostsByUserId(Integer userId) {
        List<Post> posts = postRepository.findByUserId(userId);

        List<PostDTO> postDTOs = posts.stream().map(post ->
                new PostDTO(
                        post.getId(),
                        post.getCreatedAt(),
                        new DescriptionDTO(post.getTitle(),post.getContent(),post.getImageUrl()),
                        post.getCategory()
                )
        ).collect(Collectors.toList());

        return new PostResponseDTO(userId, postDTOs.size(), postDTOs);
    }

    public Post savePost(PostRequestDTO postRequestDto, MultipartFile file) {
        User user = userRepository.findById(postRequestDto.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String imageUrl = saveImage(file);

        Post post = Post.builder()
                .user(user)
                .title(postRequestDto.title())
                .content(postRequestDto.content())
                .imageUrl(imageUrl)
                .category(postRequestDto.category())
                .createdAt(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }

    public String saveImage(MultipartFile file) {
        String path = "";
        try {
            // Define la ruta personalizada para guardar la imagen
            String uploadDir = "C:\\Users\\Claudia\\Documents\\c23-63-webapp\\social_media-backend\\src\\main\\resources\\images" + File.separator;

            // Crea el directorio si no existe
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();  // Crea el directorio si no existe
            }

            // Guardar la imagen en la nueva ruta
            File destinationFile = new File(uploadDir + file.getOriginalFilename()); // Asegúrate de que la ruta sea correcta

            // Transferir el contenido del archivo
            file.transferTo(destinationFile);

            // Devuelve la ruta completa donde se ha guardado la imagen
            path = destinationFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();  // Maneja el error de manera adecuada, si es necesario
        }

        return path;
    }

    public List<FeedResponseDTO> getAllPosts() {
        return postRepository.findAll().stream().map(post -> {
            User user = post.getUser();
            UserProfile profile = userProfileService.findByUserId(user.getId());

            UserDataDTO userDataDTO = new UserDataDTO(
                    user.getId(),
                    profile != null ? user.getUsername() : null,
                    profile != null ? profile.getProfilePhoto() : null
            );

            ContentDTO contentDTO = new ContentDTO(
                    post.getTitle(),
                    post.getImageUrl()
            );

            List<String> categories = List.of(post.getCategory().split(", "));

            return new FeedResponseDTO(
                    post.getId(),
                    userDataDTO,
                    contentDTO,
                    categories
            );
        }).toList();
    }

    public PostDTO getPostByPostId(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post no encontrado con ID: " + postId));

        return new PostDTO(
                post.getId(),
                post.getCreatedAt(),
                new DescriptionDTO(post.getTitle(), post.getContent(), post.getImageUrl()),
                post.getCategory()
        );

    }
}
