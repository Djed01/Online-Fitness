package org.unibl.etf.onlinefitness.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "fitness", catalog = "")
public class UserEntity implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "Username")
    private String username;
    @Basic
    @Column(name = "PasswordHash")
    private String passwordHash;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Surname")
    private String surname;
    @Basic
    @Column(name = "City")
    private String city;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "Status")
    private Boolean status;
    @Basic
    @Column(name = "ActivationStatus")
    private Boolean activationStatus;
    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private AvatarEntity avatar;
    @Column(name = "Role")
    private String role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }
}
