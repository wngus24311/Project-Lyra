package com.lyra.project_lyra.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(value= {AuditingEntityListener.class})
@Getter
@Setter
public abstract class BaseEntity {

	@CreatedDate
	@Column(name="regdate", updatable=false)
	protected LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name="updatedate")
	protected LocalDateTime updateDate;
}