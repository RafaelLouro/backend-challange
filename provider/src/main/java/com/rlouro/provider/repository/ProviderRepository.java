package com.rlouro.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rlouro.provider.model.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
