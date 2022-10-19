package com.example.crudmobil.Controller;

import com.example.crudmobil.Model.CarEntityDTO;
import com.example.crudmobil.Services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarServices carServices;

    @GetMapping("")
    public ResponseEntity<Object> getAllCar(){
        return carServices.getAll();
    }

    @GetMapping("/getid")
    public ResponseEntity<Object> getAllCarbyId(@RequestParam(value = "id")Long id){
        //return  ResponseEntity.ok().body(id);
     return carServices.getCarById(id);
    }

    @PostMapping("")
    public ResponseEntity<Object> addNewCar(@RequestBody CarEntityDTO carEntityDTO){
        return carServices.addNewCar(carEntityDTO);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteCar(@RequestParam(value = "id") Long id){
        return carServices.deleteCarbyId(id);
    }

    @PutMapping("")
    public ResponseEntity<Object> updateCar(@RequestParam(value = "id") Long id, @RequestBody CarEntityDTO carEntityDTO){
        return carServices.updateByID(id, carEntityDTO);
    }
}
