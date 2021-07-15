package cn.edu.nju.FindIt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String NJUID;

    private String wxOpenId;
    private String wxSessionKey;

    private String qqOpenId;
    private String qqSessionKey;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
