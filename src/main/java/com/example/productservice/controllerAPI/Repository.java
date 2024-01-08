package com.example.productservice.controllerAPI;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<UserDetailsData, Long> {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public List<UserDetailsData> getAllUsers() {
//        return entityManager.createQuery("SELECT u FROM UserDetailsData u", UserDetailsData.class).getResultList();
//    }


}
