package com.dev.usersweb.dao.impl;

import com.dev.usersweb.dao.UserDao;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    private static final String FROM = " FROM UserModel";
    private static final String FIND_BY_USERNAME = " WHERE username=:username";

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public UserDetails findByUsername(String username) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery(FROM + FIND_BY_USERNAME, UserDetails.class)
                    .setParameter("username", username)
                    .getResultList().get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
