package com.caad.wechat.util;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("springDataJpaBase")
@Transactional(value = "baseEM")
public class SpringDataJpaBase {

    @PersistenceContext(unitName = "unitBase")
    private EntityManager em;
    private static int MAX_CACHE_SIEZ = 200;
    @SuppressWarnings("rawtypes")
    Map<Class<?>, SimpleJpaRepository> cache = new CacheHashMap();

    /**
     * 保存实体
     *
     * @param entity--要保存的实体
     * @param entityClass--要保存的类型
     * @return 保存后的实体
     */
    public <T> T saveOne(Class<T> entityClass, T entity) {
        JpaEntityInformation<T, ?> entityInfomation = JpaEntityInformationSupport.getMetadata(entityClass, em);
        if (entityInfomation.isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            T t = em.merge(entity);
            return t;
        }
    }

    /**
     * 根据id删除实体
     */
    @Transactional
    public <T, ID extends Serializable> void deleteOne(Class<T> entityClass, ID id) {
        this.getRepository(entityClass).delete(id);

    }

    /**
     * 根据id查询一个实体
     */
    public <T, ID extends Serializable> T findOne(Class<T> entityClass, ID id) {
        return this.getRepository(entityClass).findOne(id);
    }

    /**
     * 查询所有实体
     */
    public <T> List<T> findAll(Class<T> entityClass) {
        return this.getRepository(entityClass).findAll();
    }

    /**
     * 分页查询
     */
    public <T> Page<T> findPage(Class<T> entityClass, Pageable request) {
        return this.getRepository(entityClass).findAll(request);
    }

    /**
     * 条件查询
     */
    public <T> List<T> findAll(Class<T> entityClass, Specification<T> spec) {
        return this.getRepository(entityClass).findAll(spec);
    }


    /**
     * 条件查询
     */
    public <T> List<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort) {
        return this.getRepository(entityClass).findAll(spec, sort);
    }

    /**
     * 分页条件查询
     */
    public <T> Page<T> findPage(Class<T> entityClass, Specification<T> spec, Pageable request) {
        return this.getRepository(entityClass).findAll(spec, request);
    }

    /**
     * 获取repository
     */
    @SuppressWarnings("unchecked")
    private <T, ID extends Serializable> SimpleJpaRepository<T, ID> getRepository(Class<T> entityClass) {
        if (cache.containsKey(entityClass)) {
            return cache.get(entityClass);
        }
        SimpleJpaRepository<T, ID> repository = new SimpleJpaRepository<T, ID>(JpaEntityInformationSupport.getMetadata(entityClass, em), em);
        cache.put(entityClass, repository);
        return repository;
    }

    /**
     * 缓存
     *
     * @author ppt
     */
    @SuppressWarnings({"serial", "rawtypes"})
    private static class CacheHashMap extends LinkedHashMap<Class<?>, SimpleJpaRepository> {
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_CACHE_SIEZ;
        }
    }

}  