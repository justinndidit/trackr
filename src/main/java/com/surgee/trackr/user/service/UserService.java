package com.surgee.trackr.user.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.surgee.trackr.user.DAO.UserRepository;
import com.surgee.trackr.user.DTO.HttpResponseDTO;
import com.surgee.trackr.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final FileUploadImpl fileUpload;

    public ResponseEntity<HttpResponseDTO> updateUserProfile(Long userId, MultipartFile multipartFile, User user) throws IOException {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        boolean success = false;
        try {

            Optional<User> userOptional = repository.findById(userId);

            if(userOptional.get().getFirstName() == null || userOptional.get().getLastName() == null)  {
                status = HttpStatus.NOT_FOUND;
                throw new IllegalStateException("User not found");
            }

            status = HttpStatus.OK;
            success = true;

            String imageURL = fileUpload.uploadFile(multipartFile);
            User userObj = userOptional.get();
            user.setId(userObj.getId());
            user.setRole(userObj.getRole());
            user.setPassword(userObj.getPassword());
            user.setEmail(userObj.getEmail());
            user.setFirstName(userObj.getFirstName());
            user.setLastName(userObj.getLastName());
            user.setAvatarUrl(imageURL);
            repository.save(user);

        HttpResponseDTO response = HttpResponseDTO.builder()
                .data(user)
                .error(null)
                .message("User profile updated successfully")
                .success(success)
                .status(status)
                .build();
        return new ResponseEntity<>(response, status);
        } catch (Error err) {
            return new ResponseEntity<>(HttpResponseDTO.builder()
            .data(null)
            .error(err.getMessage())
            .message(err.getMessage())
            .success(success)
            .status(status)
            .build(), status);
        }
    }
}
