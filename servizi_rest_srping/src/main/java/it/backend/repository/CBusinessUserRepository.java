package it.backend.repository;


import it.backend.entity.BusinessUser;
import it.backend.model.BusinessUserDTO;
import it.backend.model.BusinessUserFilterDTO;
import it.backend.model.BusinessUserPageDTO;
import it.backend.repository.custom.IBusinessUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CBusinessUserRepository /*implements IBusinessUserRepository*/ {

    @PersistenceContext
    private EntityManager em;

    //@Override
    @Transactional
    public BusinessUserDTO createUser(BusinessUserDTO user) {
        BusinessUser entity = new BusinessUser();
        entity.setFirstName(user.getFirstName());
        entity.setFiscalCode(user.getFiscalCode());
        entity.setLastName(user.getLastName());
        em.persist(entity);
        user.setId(entity.getId());
        return user;
    }

    //@Override
    @Transactional
    public BusinessUserDTO updateUser(BusinessUserDTO user) {
        BusinessUser entity = em.find(BusinessUser.class, user.getId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setFiscalCode(user.getFiscalCode());
        return user;
    }

    //@Override
    @Transactional
    public BusinessUserDTO mergeUser(BusinessUserDTO user) {
        return null;
    }

    //@Override
    @Transactional
    public void deleteUser(Integer id) {
        BusinessUser entity = em.getReference(BusinessUser.class, id);
        em.remove(entity);
    }

    //@Override
    @Transactional(readOnly = true)
    public BusinessUserDTO findById(Integer id) {
        TypedQuery<BusinessUserDTO> query = em.createQuery("select new it.backend.model.BusinessUserDTO(c.id,c.firstName,c.lastName,c.fiscalCode)" +
                " from BusinessUser c where c.id=:id", BusinessUserDTO.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    //@Override
    @Transactional(readOnly = true)
    public BusinessUserPageDTO findUsers(int start, int max) {
        TypedQuery<Long> queryCount = em.createQuery("select count(c) from BusinessUser c", Long.class);
        TypedQuery<BusinessUser> query = em.createQuery("select c from BusinessUser c", BusinessUser.class)
                .setFirstResult(start).setMaxResults(max);
        return makePage(queryCount,query,start,max);
    }

    //@Override
    /*@Transactional(readOnly = true)
    public BusinessUserPageDTO findUsersByFilter(int start, int max, BusinessUserFilterDTO filter) {
        StringBuilder where = new StringBuilder("");
        String and = "";
        if(filter.getFiscalCode()!=null) {
            where.append("c.fiscalCode=:fiscalCode");
            and="and";
        }
        if(filter.getLastName()!=null) {
            where.append(" " + and + " c.lastName=:lastName");
        }
        if(where.length()>0){
            where.insert(0,"where ");
        }

        String queryString = "select c from BusinessUser c " + where;
        String queryStringCount = "select count(c) from BusinessUser c " + where;

        TypedQuery<Long> queryCount = em.createQuery(queryStringCount, Long.class);
        TypedQuery<BusinessUser> query = em.createQuery(queryString, BusinessUser.class)
                .setFirstResult(start).setMaxResults(max);

        if(filter.getFiscalCode()!=null) {
            queryCount.setParameter("fiscalCode", filter.getFiscalCode());
            query.setParameter("fiscalCode",filter.getFiscalCode());
        }
        if(filter.getLastName()!=null){
            queryCount.setParameter("lastName",filter.getLastName());
            query.setParameter("lastName",filter.getLastName());
        }

        return makePage(queryCount, query, start, max);
    }*/

    //@Override
    @Transactional(readOnly = true)
    public BusinessUserPageDTO findUsersByFilter(int start, int max, BusinessUserFilterDTO filter){
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Long> businessUserCountCriteriaQuery = cb.createQuery(Long.class);
        Root<BusinessUser> rootCount = businessUserCountCriteriaQuery.from(BusinessUser.class);
        businessUserCountCriteriaQuery.select(cb.count(rootCount));

        ParameterExpression<String> lastNameParam = null;
        ParameterExpression<String> fiscalCodeParam = null;

        List<Predicate> predicates = new ArrayList<>();

        if(filter.getLastName()!=null) {
            lastNameParam = cb.parameter(String.class);
            Predicate lastNamePredicate = cb.equal(rootCount.get("lastName"), lastNameParam);
            predicates.add(lastNamePredicate);
        }

        if(filter.getFiscalCode()!=null) {
            fiscalCodeParam = cb.parameter(String.class);
            Predicate fiscalCodePredicate = cb.equal(rootCount.get("fiscalCode"), fiscalCodeParam);
            predicates.add(fiscalCodePredicate);
        }

        Predicate[] arrayPredicate = new Predicate[predicates.size()];

        for(int i=0; i< arrayPredicate.length; i++){
            arrayPredicate[i]=predicates.get(i);
        }

        Predicate and = cb.and(arrayPredicate);

        businessUserCountCriteriaQuery.where(and);

        CriteriaQuery<BusinessUser> businessUserCriteriaQuery = cb.createQuery(BusinessUser.class);
        businessUserCriteriaQuery.from(BusinessUser.class);
        businessUserCriteriaQuery.where(and);

        TypedQuery<Long> queryCount = em.createQuery(businessUserCountCriteriaQuery);
        TypedQuery<BusinessUser> query = em.createQuery(businessUserCriteriaQuery).setFirstResult(start).setMaxResults(max);

        if(lastNameParam!=null) {
            queryCount.setParameter(lastNameParam, filter.getLastName());
            query.setParameter(lastNameParam, filter.getLastName());
        }
        if(fiscalCodeParam!=null) {
            queryCount.setParameter(fiscalCodeParam, filter.getFiscalCode());
            query.setParameter(fiscalCodeParam, filter.getFiscalCode());
        }

        return makePage(queryCount, query, start, max);
    }

    private BusinessUserPageDTO makePage(TypedQuery<Long> queryCount,TypedQuery<BusinessUser> query, int start, int max){
        long total = queryCount.getSingleResult();
        List<BusinessUser> businessUsers = query.getResultList();
        List<BusinessUserDTO> dtoList = new ArrayList<>();
        for(BusinessUser user: businessUsers){
            dtoList.add(new BusinessUserDTO(user.getId(),user.getFirstName(),user.getLastName(),user.getFiscalCode()));
        }
        return new BusinessUserPageDTO(total, start, max, dtoList);
    }
}
