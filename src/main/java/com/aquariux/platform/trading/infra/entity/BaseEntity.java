package com.aquariux.platform.trading.infra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
public abstract class BaseEntity {
    @LastModifiedDate
    @Builder.Default
    @JsonIgnore
    private LocalDateTime modifiedDate = LocalDateTime.now();

    @CreatedDate
    @Builder.Default
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();

    @PreUpdate
    public void onUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }
}
