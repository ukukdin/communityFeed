package org.fastcampus.common.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) //생성과 마지막 수정될때 자동으로 컬럼에 데이터를 채워넣주는 역할을 한다. 이거를 사용하기 위해서는 어플리케이션 상단에 enableJpaAuditing이 필요
@MappedSuperclass //공통매핑 정보가 필요할때 이 속성만 상속받아서 사용할수있게 해주는 어노테이션입니다.
@Getter
public class TimeBaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDt;

    @LastModifiedDate
    private LocalDateTime updDt;

}
