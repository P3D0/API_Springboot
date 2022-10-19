package com.example.crudmobil.Repository;

import com.example.crudmobil.Model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long>{
    Optional<CarEntity> findCarEntityById(Long kode);
    Boolean existsByNomor(String kode); ///ini existBy harus sesuai dengan nama yang ada pada entity;
}
