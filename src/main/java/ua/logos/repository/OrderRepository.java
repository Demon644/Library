package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.logos.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<OrderEntity> findByPhoneNumber(String phoneNumber);

    List<OrderEntity> findByUsersId(Long id);
}
