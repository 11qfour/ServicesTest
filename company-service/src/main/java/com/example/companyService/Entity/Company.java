package com.example.companyService.Entity;

import com.example.service_test_task.DTO.UserResponseDto;
import com.example.service_test_task.DTO.UserResponseFromCompanyDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long budget;
    @Transient //not to create in DB
    private List<UserResponseFromCompanyDto> employees = new ArrayList<>(); //Поле будет наполняться через REST-запросы к user-service
    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Company company = (Company) object;
        return getId() != null && Objects.equals(getId(), company.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
