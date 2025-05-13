package com.fusionhs.modulithdojo.pizza.repository;

import com.fusionhs.modulithdojo.pizza.model.Pizza;
import com.fusionhs.modulithdojo.pizza.model.PizzaOrderStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PizzaOrderStatsRepository extends JpaRepository<PizzaOrderStats, Long> {
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ps FROM PizzaOrderStats ps WHERE ps.pizza = :pizza AND ps.orderDate = :orderDate")
    Optional<PizzaOrderStats> findByPizzaAndDateForUpdate(@Param("pizza") Pizza pizza, @Param("orderDate") LocalDate orderDate);
    
    @Query("SELECT COALESCE(SUM(ps.quantity), 0) FROM PizzaOrderStats ps WHERE ps.pizza = :pizza AND ps.orderDate BETWEEN :startDate AND :endDate")
    int getTotalOrdersForPizzaInDateRange(@Param("pizza") Pizza pizza, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
} 