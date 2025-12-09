package com.desafio.pagamentos.infrastructure.repository.jpa;

import com.desafio.pagamentos.infrastructure.repository.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.lang.String;

@Repository
public interface SpringPaymentJpaRepository extends JpaRepository<PaymentEntity, Long> { 

    @Query("""
        SELECT p FROM PaymentEntity p
        WHERE (:tipo IS NULL OR p.tipo = :tipo)
        AND (:inicio IS NULL OR p.data >= :inicio)
        AND (:fim IS NULL OR p.data <= :fim)
    """)
    List<PaymentEntity> findByFilters(
            @Param("tipo") String tipo,
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim
    );

}
