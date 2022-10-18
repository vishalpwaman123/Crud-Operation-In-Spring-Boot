package com.ecommerce.eshoping.controllers;

import com.ecommerce.eshoping.dto.*;
import com.ecommerce.eshoping.models.CrudDetail;
import com.ecommerce.eshoping.models.User;
import com.ecommerce.eshoping.repositories.CrudRepository;
import com.ecommerce.eshoping.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/Crud")
public class CrudController {

    @Autowired
    CrudRepository crudRepository;

    @PostMapping("InsertOperation")
    public ResponseEntity<BasicResponse> InsertOperation(@RequestBody InsertOperationRequestDTO Request) {

        BasicResponse response = new BasicResponse();
        response.setSuccess(true);
        response.setMessage("Insert Data Successfully");
        try {
            CrudDetail crud = new CrudDetail();
            crud.setUserName(Request.getUserName());
            crud.setAge(Request.getAge());
            crudRepository.save(crud);

        }catch (Exception ex){
            response.setSuccess(false);
            response.setMessage("Exception Message : "+ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("UpdateOperation")
    public ResponseEntity<BasicResponse> UpdateOperation(@RequestBody UpdateOperationRequestDTO Request) {

        BasicResponse response = new BasicResponse();
        response.setSuccess(true);
        response.setMessage("Update Data Successfully");
        try {
            Optional<CrudDetail> data = crudRepository
                                        .findAll()
                                        .stream()
                                        .filter(X->X.getId()==Request.getId()).findFirst();

            if(!data.isPresent())
            {
                response.setSuccess(false);
                response.setMessage("User Not Present");
                return ResponseEntity.ok(response);
            }

            data.get().setUserName(Request.getUserName());
            data.get().setAge(Request.getAge());
            crudRepository.save(data.get());

        }catch (Exception ex){
            response.setSuccess(false);
            response.setMessage("Exception Message : "+ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("DeleteOperation")
    public ResponseEntity<BasicResponse> DeleteOperation(@RequestParam Long Id) {

        BasicResponse response = new BasicResponse();
        response.setSuccess(true);
        response.setMessage("Delete Data Successfully");
        try {

            Optional<CrudDetail> data = crudRepository
                    .findAll()
                    .stream()
                    .filter(X->X.getId()==Id).findFirst();

            if(!data.isPresent())
            {
                response.setSuccess(false);
                response.setMessage("User Not Present");
                return ResponseEntity.ok(response);
            }

            crudRepository.delete(data.get());

        }catch (Exception ex){
            response.setSuccess(false);
            response.setMessage("Exception Message : "+ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("GetOperation")
    public ResponseEntity<GetOperationResponseDTO> GetOperation(@RequestBody GetOperationRequestDTO Request) {

        GetOperationResponseDTO response = new GetOperationResponseDTO();
        response.setIsSuccess(true);
        response.setMessage("Read Data Successfully");
        try {

            List<CrudDetail> data = crudRepository
                    .findAll()
                    .stream().skip((Request.pageNumber-1)*Request.numberOfRecordPerPage)
                    .limit(Request.numberOfRecordPerPage).toList();

            response.setData(data);

            if(data.stream().count()==0)
            {
                response.setIsSuccess(false);
                response.setMessage("Data Not Present");
                return ResponseEntity.ok(response);
            }

        }catch (Exception ex){
            response.setIsSuccess(false);
            response.setMessage("Exception Message : "+ex.getMessage());
        }

        return ResponseEntity.ok(response);
    }


}
