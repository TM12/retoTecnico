package com.jvc.msvc.currency.repositories;

import com.jvc.msvc.currency.models.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query("SELECT cu FROM Currency cu where cu.abbreviation=?1")
    List<Currency> searchCurrencies(String abbreviation);
}
