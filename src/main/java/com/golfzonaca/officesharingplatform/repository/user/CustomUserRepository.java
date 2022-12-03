package com.golfzonaca.officesharingplatform.repository.user;

import com.golfzonaca.officesharingplatform.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CustomUserRepository implements UserRepository {
    private final SpringDataJpaUserRepository jpaUserRepository;
    private final QueryUserRepository queryUserRepository;

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return jpaUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("UserNotFoundException::: 찾을 수 없는 유저입니다."));
    }

    @Override
    public User findByMailLike(String email) {
        return jpaUserRepository.findByEmailLike(email).orElseThrow(()-> new UsernameNotFoundException("UsernameNotFoundException::: 사용하지 않는 메일입니다."));
    }

    @Override
    public Boolean isUniqueTel(String tel) {
        return queryUserRepository.findByTelLike(tel).isEmpty();
    }

    @Override
    public Boolean isUniqueEmail(String email) {
        return queryUserRepository.isContainByEmail(email).isEmpty();
    }

    @Override
    public Integer countContainByEmail(String email) {
        return jpaUserRepository.countContainByMail(email);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public List<User> findAll(UserSearchCond cond) {
        return queryUserRepository.findAll(cond);
    }

    @Override
    public Boolean validateUserByUserId(Long userId) {
        return queryUserRepository.isContainById(userId).isEmpty();
    }
}
