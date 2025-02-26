package Microservice.FoodAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Microservice.FoodAPI.Entity.Pagamentos;

public interface PagamentosRepository extends JpaRepository<Pagamentos, Long> {

    
    
} 