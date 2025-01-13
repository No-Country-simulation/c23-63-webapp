package com.noCountry.social_media_backend.infrastucture.config.injection;

import com.noCountry.social_media_backend.core.application.port.in.UserInPort;
import com.noCountry.social_media_backend.core.application.port.out.UserOutPort;
import com.noCountry.social_media_backend.core.application.use_case.UserUseCase;
import com.noCountry.social_media_backend.infrastucture.adapter.out.db.repository.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInjection {

    @Bean
    public UserOutPort userOutPort(UserRepositoryAdapter userRepositoryAdapter) {
        return userRepositoryAdapter;
    }

    @Bean
    public UserInPort userInPort(UserOutPort userOutPort) {
        return new UserUseCase(userOutPort);
    }
}
