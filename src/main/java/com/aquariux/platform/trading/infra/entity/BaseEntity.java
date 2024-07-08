package com.aquariux.platform.trading.infra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
    @LastModifiedDate
    @Builder.Default
    @JsonIgnore
    protected LocalDateTime modifiedDate = LocalDateTime.now();

    @CreatedDate
    @Builder.Default
    @JsonIgnore
    protected LocalDateTime createdDate = LocalDateTime.now();

    @PreUpdate
    public void onUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }
}
