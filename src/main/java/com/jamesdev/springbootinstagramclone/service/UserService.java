package com.jamesdev.springbootinstagramclone.service;

import com.jamesdev.springbootinstagramclone.domain.subscribe.SubscribeRepository;
import com.jamesdev.springbootinstagramclone.domain.user.RoleType;
import com.jamesdev.springbootinstagramclone.domain.user.User;
import com.jamesdev.springbootinstagramclone.domain.user.UserRepository;
import com.jamesdev.springbootinstagramclone.dto.auth.EmailDupCheckDto;
import com.jamesdev.springbootinstagramclone.dto.auth.JoinDto;
import com.jamesdev.springbootinstagramclone.dto.auth.UsernameDupCheckDto;
import com.jamesdev.springbootinstagramclone.dto.user.UserProfileDto;
import com.jamesdev.springbootinstagramclone.dto.user.UserUpdateDto;
import com.jamesdev.springbootinstagramclone.handler.ex.CustomApiException;
import com.jamesdev.springbootinstagramclone.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

      private final UserRepository userRepository;
      private final SubscribeRepository subscribeRepository;
      private final BCryptPasswordEncoder bCryptPasswordEncoder;

      @Value("${file.path}")
      private String uploadFolder;

      @Transactional(readOnly = true)
      public boolean isUsernameAvailable(UsernameDupCheckDto usernameDupCheckDto){
            String username= usernameDupCheckDto.getUsername();
            return userRepository.findByUsername(username).isPresent();
      }

      @Transactional(readOnly = true)
      public boolean isEmailAvailable(EmailDupCheckDto emailDupCheckDto) {
            String email= emailDupCheckDto.getEmail();
            return userRepository.findByEmail(email).isPresent();
      }

      private boolean isPasswordEqual(JoinDto joinDto){
            return Objects.equals(joinDto.getPassword1(), joinDto.getPassword2());
      }

      @Transactional
      public boolean signUp(JoinDto joinDto){
            //TODO: 권한설정 (USER, ADMIN) 지금은 그냥 USER
            if (!isPasswordEqual(joinDto)) return false;
            String rawPassword=joinDto.getPassword1();
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
            User newUser=joinDto.toEntity(encPassword);
            newUser.setRole(RoleType.USER);
            userRepository.save(newUser);
            return true;

      }

      @Transactional
      public User editUser(int id, UserUpdateDto userUpdateDto) {
            User userEntity = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("찾을 수 없음"));

            String rawPassword = userUpdateDto.getPassword();
            String encPassword= Objects.equals(rawPassword, "") ?userEntity.getPassword():bCryptPasswordEncoder.encode(rawPassword); //비었으면 원래 비밀번호, 있으면 인코딩

            userEntity.setPassword(encPassword);
            userEntity.setName(userUpdateDto.getName());
            userEntity.setBio(userUpdateDto.getBio());
            userEntity.setWebsite(userUpdateDto.getWebsite());
            userEntity.setPhone(userUpdateDto.getPhone());
            return userEntity;
      }

      @Transactional(readOnly = true)
      public UserProfileDto getUserProfile(int pageUserId,int principalId){
            UserProfileDto userProfileDto = new UserProfileDto();
            User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
                  throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
            });
            userProfileDto.setUser(userEntity);
            userProfileDto.setPageOwnerState(pageUserId==principalId);
            userProfileDto.setImageCount(userEntity.getImages().size());

            int subscribeState = subscribeRepository.subscribeState(principalId,pageUserId);
            int subscribeCount=subscribeRepository.subscribeCount(pageUserId);

            userProfileDto.setSubscribeState(subscribeState==1);
            userProfileDto.setSubscribeCount(subscribeCount);

            //이미지 좋아요
//            userEntity.getImages().forEach((image)->{
//                  image.setLikeCount
//            })
            return userProfileDto;
      }

      @Transactional
      public User updateProfileImage(int principalId, MultipartFile profileImageFile){
            UUID uuid = UUID.randomUUID();
            String imageFileName=uuid+"_"+profileImageFile.getOriginalFilename();
            System.out.println("이미지 파일 이름 " + imageFileName );

            Path imageFilePath = Paths.get(uploadFolder+imageFileName);

            try {
                  Files.write(imageFilePath,profileImageFile.getBytes());
            } catch (IOException e) {
                  e.printStackTrace();
            }
            User userEntity = userRepository.findById(principalId).orElseThrow(()->{
                  throw new CustomApiException("유저를 찾을 수 없습니다.");
            });
            userEntity.setProfileImageUrl(imageFileName);
            return userEntity;
      }

}
