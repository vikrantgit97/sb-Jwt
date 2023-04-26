package com.spring.security.jwt.serviceimpl;
import com.spring.security.jwt.models.OrderDetails;
import com.spring.security.jwt.repository.OrderDetailsRepository;
import org.hibernate.annotations.BatchSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderDetailsServiceImpl  {

    @Autowired
    private OrderDetailsRepository iOrderDetailsRepo;

    @PersistenceContext
    private  EntityManager entityManager;

    @Transactional
    @Modifying
    @BatchSize(size = 10)
    public void saveAllInBatch(List<OrderDetails> orderDetailsList) {
        for (int i = 10; i < orderDetailsList.size(); i++) {
            entityManager.persist(orderDetailsList.get(i));
            if (i % 10 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }

}


