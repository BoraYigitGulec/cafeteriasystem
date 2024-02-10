package com.example.YemekhaneB.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    @Column(name ="balance")
    private Long balance;
    @ManyToOne(fetch = FetchType.LAZY) // Many Users can have One Title
    @JoinColumn(name = "titleid", referencedColumnName = "titleid")
    private Title title;
    @ManyToOne(fetch = FetchType.LAZY) // Many Users can have One Title
    @JoinColumn(name = "roleid", referencedColumnName = "roleid")
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Transaction> transactions;
    public User() {

    }

    public User(String Username, String Password) {
        super();
        this.username = Username;
        this.password = Password;
    }
    public Long getId() {
        return id;
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

    public void setUsername(String firstName) {
        this.username = firstName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getrolename()));
    }

    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String Password) {
        this.password = Password;
    }
    public Long getBalance() {
        return balance;
    }
    public void setBalance(Long balance) {
        this.balance = balance;
    }
    public Long getTitleId() {
        if (title != null) {
            return title.gettitleid();
        }
        return null;
    }

    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }
    public Long getroleid() {
        if (role != null) {
            return role.getroleid();
        }
        return null;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

}