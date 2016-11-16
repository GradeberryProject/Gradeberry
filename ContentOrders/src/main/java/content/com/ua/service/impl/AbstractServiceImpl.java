package content.com.ua.service.impl;

import content.com.ua.entities.AbstractEntity;
import content.com.ua.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import content.com.ua.repository.AbstractRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


public abstract class AbstractServiceImpl<T extends AbstractEntity> implements AbstractService<T> {


    private AbstractRepository<T, Long> repository;

    public AbstractServiceImpl(AbstractRepository<T, Long> repository) {
        super();
        this.repository = repository;
    }

    @Override
    @Transactional
    public void add(T entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public void add(Collection<T> entities) {
        repository.save(entities);
    }

    @Override
    @Transactional
    public void update(T entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public T get(Long id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void remove(T entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.delete(id);
    }

    @Override
    @Transactional
    public void remove(Collection<T> entities) {
        repository.delete(entities);
    }

    @Override
    @Transactional
    public void removeAll() {
        repository.deleteAll();
    }

}
