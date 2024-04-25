package com.dev.usersweb.dao.impl;

import com.dev.usersweb.controller.UserController;
import com.dev.usersweb.dao.UserDao;
import com.dev.usersweb.model.UserModel;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private static final String FROM = " FROM UserModel";
    private static final String FIND_BY_USERNAME = " username=:username";
    private static final String FIND_BY_ID = " id=:id";
    private static final String WHERE = " WHERE";
    private static final String AND = " AND";
    private static final String REMOVED = " removed =:removed";

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public UserDetails findByUsername(String username) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery(FROM + WHERE + FIND_BY_USERNAME + AND + REMOVED, UserDetails.class)
                    .setParameter("username", username)
                    .setParameter("removed", false)
                    .getResultList().get(0);
        } catch (Exception e) {
            LOGGER.error("findByUsernameExp: ", e);
            return null;
        }
    }

    @Override
    public List<UserModel> getUsers() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery(FROM + WHERE + REMOVED, UserModel.class)
                    .setParameter("removed", false)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.error("getUsersExp: ", e);
            return Collections.emptyList();
        }
    }

    @Override
    public UserModel findById(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery(FROM + WHERE + FIND_BY_ID, UserModel.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.error("findByIdExp: ", e);
            return null;
        }
    }

    @Override
    public boolean save(UserModel userModel) {
        try {
            Session session = sessionFactory.getCurrentSession();
            if(userModel.getId() != null){
                session.merge(userModel);
            } else{
                session.persist(userModel);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("saveExp: ", e);
            return false;
        }
    }
}
