package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author italo
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class EntityId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long Id;
  
}
