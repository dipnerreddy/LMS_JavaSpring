package com.dipner.software.dao;


import com.dipner.software.beans.Books;
import com.dipner.software.beans.User_Books;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BooksDAO {
    static Log log = LogFactory.getLog(LoginDAO.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String checkBook(String bookName){
        TypedQuery<Long> countQuery=entityManager
                .createQuery("select COUNT (b.bookName) from Books b where b.bookName=:bookName",Long.class);
        countQuery.setParameter("bookName",bookName);
        Long Count=countQuery.getSingleResult();

        if (Count == 1) {
            return bookName;
        } else if (Count == 0) {
            return null;
        } else {
            return "two or more results appeared delete manually";
        }
    }



    @Transactional
    public String checkAuthor(String author){
        TypedQuery<Long> countQuery=entityManager
                .createQuery("select COUNT (b.author) from Books b where b.author=:author",Long.class);
        countQuery.setParameter("author",author);
        Long Count=countQuery.getSingleResult();

        if (Count >= 1) {
            return author;
        } else if (Count == 0) {
            return null;
        } else {
            return "two or more results appeared delete manually";
        }
    }


    @Transactional
    public String checkCategory(String category){
        TypedQuery<Long> countQuery=entityManager
                .createQuery("select COUNT (b.category) from Books b where b.category=:category",Long.class);
        countQuery.setParameter("category",category);
        Long Count=countQuery.getSingleResult();

        if (Count >= 1) {
            return category;
        } else if (Count == 0) {
            return null;
        }
        else {
            return null;
        }
    }

//    @Transactional
//    public int updateUserBookTable(String userMail, String bookSpecialID){
//        TypedQuery<Long> query=entityManager.createQuery("UPDATE User_Books ub set ub.bookSpecialID=:bookSpecialID , ub.userMailId=:userMail where ub.userMailId=:userMail and ub.bookSpecialID=:bookSpecialID", Long.class);
//        query.setParameter("bookSpecialID",bookSpecialID);
//        query.setParameter("userMail",userMail);
//        return query.executeUpdate();
//    }

    @Transactional
    public void insertUserBookTable(String userMail, String bookSpecialID) {
        User_Books newUserBook = new User_Books();
        newUserBook.setUserMailId(userMail);
        newUserBook.setBookSpecialID(bookSpecialID);

        entityManager.persist(newUserBook);
    }



    /*

    @Transactional
	public void updatePassword(String mobile, String password) {
		log.info("DAO class: " + mobile + password);

		Query updateQuery = entityManager
				.createQuery("UPDATE Users u SET u.password = :password WHERE u.mobileNo = :mobile");
		updateQuery.setParameter("password", password);
		updateQuery.setParameter("mobile", mobile);
		int updatedCount = updateQuery.executeUpdate();
		log.info("Updated Successfully: " + updatedCount);
	}

     */

    //select b from Books b where b.bookName=:bookName
//    @Transactional
//    public List<Books> searchBookByBookName(String bookName){
//        log.info("this is hard");
//        TypedQuery<Books> query = entityManager.createQuery("SELECT b FROM Books b WHERE b.bookName = :bookName", Books.class);
//        query.setParameter("bookName", bookName);
//        List<Books> booksList = query.getResultList();
//
//        return booksList;
//    }

    @Transactional
    public List<Books> searchBookByBookName(String bookName){
        log.info("this is hard");
        TypedQuery<Books> query = entityManager.createQuery("SELECT b FROM Books b WHERE b.bookName = :bookName", Books.class);
        query.setParameter("bookName", bookName);
        List<Books> booksList = query.getResultList();

        return booksList;
    }

    @Transactional
    public Long bookIdInDAO(Long id){
        TypedQuery<Long> countQuery=entityManager
                .createQuery("select COUNT (b.id) from Books b where b.id=:id",Long.class);
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

    @Transactional
    public String deleteBook(Long id){
        String hql = "DELETE from Books b where b.id=:id";
        entityManager.createQuery(hql)
                .setParameter("id", id)
                .executeUpdate();
        return hql;
    }





    @Transactional
    public String bookSpeciallIDInDAO(String bookSpecialID){
        TypedQuery<Long> countQuery=entityManager
                .createQuery("select COUNT (b.bookSpecialID) from Books b where b.bookSpecialID=:bookSpecialID",Long.class);
        countQuery.setParameter("bookSpecialID",bookSpecialID);
        Long Count=countQuery.getSingleResult();

        if (Count == 1) {
            return bookSpecialID;
        } else if (Count == 0) {
            return null;
        } else {
            return "two or more results appeared delete manually";
        }
    }


//    @Transactional
//    public String updateBookIssued(String bookSpecialID){
//
//    }

    @Transactional
    public void updateBookIssued(String bookSpecialID) {
//        log.info("DAO class: " + mobile + password);

        Query updateQuery = entityManager
                .createQuery("UPDATE Books u SET u.issued = true WHERE u.bookSpecialID = :bookSpecialID");
        updateQuery.setParameter("bookSpecialID", bookSpecialID);
//        updateQuery.setParameter("mobile", mobile);
        int updatedCount = updateQuery.executeUpdate();
        log.info("Updated Successfully: " + updatedCount);
    }


}

//
//@Transactional
//public String getEmail(String email) {
////        log.info("Mobile in DAO: " + mobileNo);
//
//    TypedQuery<Long> countQuery = entityManager
//            .createQuery("SELECT COUNT(u.email) FROM Users u WHERE u.email = :email", Long.class);
//    countQuery.setParameter("email", email);
//    Long count = countQuery.getSingleResult();
//
//    if (count == 1) {
//        return email;
//    } else if (count == 0) {
//        return null;
//    } else {
//        return "two or more results appeared delete manually";
//    }
//}