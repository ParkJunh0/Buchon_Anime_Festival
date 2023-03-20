package com.biaf.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.Data;

@EnableJpaAuditing
@EntityListeners(value= {AuditingEntityListener.class})
@MappedSuperclass
@Data
public abstract class BaseTimeEntity {

	@CreatedDate
	@Column(name = "reg_Time", updatable =false)
	private LocalDateTime regTime;
	
	@LastModifiedDate
	private LocalDateTime updateTime;
}
