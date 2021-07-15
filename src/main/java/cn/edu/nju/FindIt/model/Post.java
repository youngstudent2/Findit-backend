package cn.edu.nju.FindIt.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@Entity
public class Post implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(updatable = false)
    private Long id;

    private String title;


    private String content;
    
    @CreatedDate
    private Timestamp createdTime;
}
