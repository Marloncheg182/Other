package Service;

import Entity.Category;
import Entity.Device;
import Entity.Model;
import Exceptions.ValidationException;
import Utils.Loggson;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * @author Romanenchuk Oleg
 * CRUD catalog methods
 */

@Stateless
@Loggson
public class CatalogService implements Serializable {

    @Inject
    private EntityManager manager;

    /**
     * In this class we will build our CRUD methods.
     */

    public Category findCategoryById(Long categoryId) {
        if (categoryId == null)
            throw new ValidationException("Wrong id inputted");

        return manager.find(Category.class, categoryId);
    }

    public Category findCategoryByType(String categoryType) {
        if (categoryType == null)
            throw new ValidationException("Wrong name inputted");

        TypedQuery<Category> query = manager.createNamedQuery(Category.GET_BY_NAME, Category.class);
        query.setParameter("dmodel", categoryType);
        return query.getSingleResult();
    }

    public List<Category> findAll() {
        TypedQuery<Category> query = manager.createNamedQuery(Category.GET_ALL, Category.class);
        return query.getResultList();
    }

    public Category create(Category category) {
        if (category == null)
            throw new ValidationException("Empty");

        manager.persist(category);
        return category;
    }

    public Category update(Category category) {
        if (category == null)
            throw new ValidationException("Empty");

        return manager.merge(category);
    }

    public void remove(Category category) {
        if (category == null)
            throw new ValidationException("Empty");

        manager.remove(manager.merge(category));
    }

    public List<Device> findDevice(String categoryType) {
        if (categoryType == null)
            throw new ValidationException("Wrong type");

        TypedQuery<Device> query = manager.createNamedQuery(Device.GET_BY_CATEGORY, Device.class);
        query.setParameter("dmodel", categoryType);
        return query.getResultList();
    }

    public Device findDeviceById(Long deviceId) {
        if (deviceId == null)
            throw new ValidationException("Wrong id");

        Device device = manager.find(Device.class, deviceId);
        if (device != null) {
            device.getModels();
        }
        return device;
    }


    public Device createDevice(Device device) {
        if (device == null)
            throw new ValidationException("Empty");

        if (device.getCategory() != null && device.getCategory().getId() == null)
            manager.persist(device.getCategory());

        manager.persist(device);

        return device;
    }

    public Device updateDevice(Device device) {
        if (device == null)
            throw new ValidationException("Empty");

        return manager.merge(device);

    }

    public void removeDevice(Device device) {
        if (device == null)
            throw new ValidationException("Empty");

        manager.remove(manager.merge(device));
    }

    public void removeDeviceById(Long deviceId) {
        if (deviceId == null)
            throw new ValidationException("Wrong id");

        removeDevice(findDeviceById(deviceId));
    }

    public List<Model> findModels(Long deviceId) {
        if (deviceId == null)
            throw new ValidationException("Wrong id");

        TypedQuery<Model> query = manager.createNamedQuery(Model.GET_BY_DEVICE_ID, Model.class);
        query.setParameter("deviceId", deviceId);
        return query.getResultList();
    }

    public Model findModel(final Long modelId) {
        if (modelId == null)
            throw new ValidationException("Wrong id");

        return manager.find(Model.class, modelId);
    }

    public List<Model> searchModels(String keyword){
        if (keyword == null)
            keyword = "";

        TypedQuery<Model> query = manager.createNamedQuery(Model.SEARCH, Model.class);
        query.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
        return query.getResultList();
    }

    public List<Model> findAllModels(){
        TypedQuery<Model> query = manager.createNamedQuery(Model.GET_ALL, Model.class);
        return query.getResultList();
    }

    public Model createModel(Model model){
        if (model == null)
            throw  new  ValidationException("Empty");

        if (model.getDevice() !=null && model.getDevice().getId() == null){
            manager.persist(model.getDevice());
            if (model.getDevice().getCategory()!= null && model.getDevice().getCategory().getId() == null)
                manager.persist(model.getDevice().getCategory());
        }
        manager.persist(model);
        return model;
    }

    public Model updateModel(Model model){
        if (model == null)
            throw new ValidationException("Empty");

        return manager.merge(model);
    }

    public void removeModel(Model model){
        if (model == null)
            throw new ValidationException("Empty");

        manager.remove(manager.merge(model));
    }

    /**
     * Overloaded method for erasing by id
     */

    public void removeModel(Long modelId){
        if (modelId == null)
            throw new ValidationException("Empty id");

        removeModel(findModel(modelId));
    }


}
