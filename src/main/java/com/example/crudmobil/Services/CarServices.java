package com.example.crudmobil.Services;

import com.example.crudmobil.Model.CarEntity;
import com.example.crudmobil.Model.CarEntityDTO;
import com.example.crudmobil.Repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CarServices {
    @Autowired
    CarRepository carRepository;

    public ResponseEntity<Object> getAll(){
        try{
            List<CarEntity> listCar = carRepository.findAll();
            return ResponseEntity.ok().body(listCar);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<Object> addNewCar(CarEntityDTO carEntityDTO){
        if(carRepository.existsByNomor(carEntityDTO.getNomor())){
            return ResponseEntity.badRequest().body("Kode Sudah Ada");
        }

        CarEntity carEntity = CarEntity.builder()
                .nama(carEntityDTO.getNama())
                .warna(carEntityDTO.getWarna())
                .nomor(carEntityDTO.getNomor())
                .build();

        carRepository.save(carEntity);
        return ResponseEntity.ok().body("Car berhasil di input" );
    }

    public ResponseEntity<Object> getCarById(Long id){
        Optional<CarEntity> carEntity = carRepository.findCarEntityById(id);
        //kalo idnya gak ada
        if(carEntity.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(carEntity);
        //return ResponseEntity.ok().body(carRepository.findById(id));
    }

    public ResponseEntity<Object> deleteCarbyId(Long id){
        Optional<CarEntity> carEntity = carRepository.findCarEntityById(id);
        carRepository.delete(carEntity.get());
        return ResponseEntity.ok().body("Berhasil menghapus data");
    }

    //update ribet gan
    public ResponseEntity<Object> updateByID(Long id, CarEntityDTO carEntityDTO){
        Optional<CarEntity> carEntity = carRepository.findCarEntityById(id);

        //kalo idnya gak ada
        if(carEntity.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        //klo sama dengan yang ada didatabase dan tidak sama idnya
        if(carRepository.existsByNomor(carEntityDTO.getNomor()) && !(id == carEntity.get().getId())){
            return ResponseEntity.badRequest().body("Kode Sudah Ada");
        }

        carEntity.ifPresent(car -> {
            car.setNomor(carEntityDTO.getNomor());
            car.setNama(carEntityDTO.getNama());
            car.setWarna(carEntityDTO.getWarna());
            carRepository.save(car);
        });
        return ResponseEntity.ok().body("berhasil mengubah data");
    }
}
