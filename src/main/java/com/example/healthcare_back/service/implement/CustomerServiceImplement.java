package com.example.healthcare_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.customer.GetCustomerResponseDto;
import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserMuscleFatResponseDto;
import com.example.healthcare_back.dto.response.customer.GetUserThreeMajorLiftResponseDto;
import com.example.healthcare_back.entity.CustomerEntity;
import com.example.healthcare_back.repository.CustomerRepository;
import com.example.healthcare_back.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId) {
        
        CustomerEntity customerEntity = null;

        try {

            customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.authenticationFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInResponseDto.success();
        
    }

    @Override
    public ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, String userId) {
        
        try {

            CustomerEntity customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.noExistUserId();

            customerRepository.save(customerEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(String userId) {
       
        CustomerEntity customerEntity = null;

        try {
            
            customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.noExistUserId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();

        }
        
        return GetCustomerResponseDto.success(customerEntity);

    }

    @Override
    public ResponseEntity<? super GetUserMuscleFatResponseDto> getUserMuscleFat(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserMuscleFat'");
    }

    @Override
    public ResponseEntity<? super GetUserThreeMajorLiftResponseDto> getUserThreeMajorLift(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserThreeMajorLift'");
    }

}
