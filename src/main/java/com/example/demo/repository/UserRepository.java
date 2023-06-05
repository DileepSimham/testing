package com.example.demo.repository;

import com.example.demo.models.Mechanic_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Mechanic_Details,Integer> {

    public Mechanic_Details findByName(String user);
}
