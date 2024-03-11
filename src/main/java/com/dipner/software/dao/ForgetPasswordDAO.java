package com.dipner.software.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class ForgetPasswordDAO {


    static Log log = LogFactory.getLog(LoginDAO.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String getUserName(String email) {
//        log.info("Mobile in DAO: " + mobile);
        TypedQuery<String> query = entityManager
                .createQuery("SELECT u.username FROM Users u WHERE u.email = :email", String.class);
        query.setParameter("email", email);
        String userName = query.getSingleResult();

        return userName;
    }
}
