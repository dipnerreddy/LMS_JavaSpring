package com.dipner.software.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginDAO {

    static Log log = LogFactory.getLog(LoginDAO.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public Integer checkUserByEmailId(String email) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT count(u) FROM Users u WHERE u.email = :email", Long.class);
            query.setParameter("email", email);
            return Math.toIntExact(query.getSingleResult());
        } catch (NoResultException e) {
            return 0;
        }
    }


    @Transactional
    public String getEmailCount(String email){
        TypedQuery<String> query=entityManager.createQuery("SELECT count(u.email) from Users u where u.email= :email", String.class);
        query.setParameter("email",email);
        String email1= query.getSingleResult();
        return email1;
    }

//    @Transactional
//    public String getPassword(String email){
//        TypedQuery<String> query=entityManager.createQuery("SELECT u.password from Users u where u.email= :email",String.class);
//        query.setParameter("email",email);
//        String password1=query.getSingleResult();
//        return password1;
//    }


    @Transactional
    public String getPassword(String email) {
//        log.info("Mobile in DAO: " + mobileNo);

        TypedQuery<Long> countQuery = entityManager
                .createQuery("SELECT COUNT(u.password) FROM Users u WHERE u.email = :email", Long.class);
        countQuery.setParameter("email", email);
        Long count = countQuery.getSingleResult();

        if (count == 1) {
            TypedQuery<String> mobileQuery = entityManager
                    .createQuery("SELECT u.password FROM Users u WHERE u.email = :email", String.class);
            mobileQuery.setParameter("email", email);
            String dbpassword = mobileQuery.getSingleResult();
//            log.info("Mobile No: " + dbpassword);
            return dbpassword;
        } else if (count == 0) {
            return null;
        } else {
            return "two or more results appeared delete manually";
        }
    }



    @Transactional
    public String getEmail(String email) {
//        log.info("Mobile in DAO: " + mobileNo);

        TypedQuery<Long> countQuery = entityManager
                .createQuery("SELECT COUNT(u.email) FROM Users u WHERE u.email = :email", Long.class);
        countQuery.setParameter("email", email);
        Long count = countQuery.getSingleResult();

        if (count == 1) {
            return email;
        } else if (count == 0) {
            return null;
        } else {
            return "two or more results appeared delete manually";
        }
    }

    //    INSERT INTO Users(u.username, u.email, u.password) VALUES (:username, :email, :password)")

//    @Transactional
//    public void setLogin(String email, String password) {
//        String sql = "INSERT INTO LoginUsers (email, password) VALUES (:email, :password)";
//        entityManager.createNativeQuery(sql,String.class)
//                .setParameter("email", email)
//                .setParameter("password", password)
//                .executeUpdate();
//    }

    @Transactional
    public String createLogin(String email, String password){
        TypedQuery<String> query=entityManager.createQuery("INSERT INTO LoginUsers (email, password) VALUES (:email, :password)" ,String.class);
        query.setParameter("password",password);
        query.setParameter("email",email);
        query.executeUpdate();


        return null;
    }

    @Transactional
    public String deleteUser(Long id){
        String hql="DELETE from Users u where u.id=:id";
        entityManager.createQuery(hql).setParameter("id",id).executeUpdate();
        return hql;
    }

    @Transactional
    public Long userIdInDAO(Long id){
        TypedQuery<Long> countQuery=entityManager
                .createQuery("select COUNT (b.id) from Users b where b.id=:id",Long.class);
        countQuery.setParameter("id",id);
        Long Count=countQuery.getSingleResult();

        if (Count >= 1) {
            return id;
        } else if (Count == 0) {
            return null;
        }
        else {
            return null;
        }
    }

/*

@Transactional
	public String getOTP(String contact_no) {
		TypedQuery<String> sql = entityManager.createQuery("SELECT o.otp FROM OTP o WHERE mobileNo = :mobileno ",
				String.class);
		sql.setParameter("mobileno", contact_no);
		sql.setMaxResults(1);
		String otp = sql.getSingleResult();
		return otp;
	}

 */

}
