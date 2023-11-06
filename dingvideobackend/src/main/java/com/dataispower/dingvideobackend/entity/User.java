package com.dataispower.dingvideobackend.entity;

import com.dataispower.dingvideobackend.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static com.dataispower.dingvideobackend.common.TableColumns.USER.*;
/**
 * author:heroding
 * date:2023/10/31 21:02
 * description：用户表
 **/

@Entity
@Data
@Table(name = com.dataispower.dingvideobackend.common.TableColumns.USER)
public class User extends CommonEntity implements UserDetails {
    @Id
    @Column(name = USER_ID, unique = true)
    private Long id;
    @Column(name = USERNAME, unique = true)
    private String username;
    @Column(name = NICKNAME)
    private String nickname;
    @Column(name = PHONE)
    private String phone;
    @Column(name = PASSWORD)
    private String password;
    @Column(name = EMAIL)
    private String email;
    @Column(name = AGE)
    private String age;
    @Column(name = FOLLOWS)
    private Long follows;
    @Column(name = GENDER)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = AVATAR, columnDefinition = "text")
    private String avatar;
    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Video> videos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

