package content.com.ua.service;

import content.com.ua.entities.AbstractEntity;

import java.util.Collection;
import java.util.List;

public interface AbstractService <T extends AbstractEntity>{

   public void add(T entity);

    public void add(Collection<T> entities);

    public void update(T entity);

    public T get(Long id);

    public List<T> getAll();

    public void remove(T entity);

    public void remove(Long id);

    public void remove(Collection<T> entities);

    public void removeAll();

}
