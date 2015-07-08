package Forms;

import Entity.Device;
import Entity.Model;
import Service.CatalogService;
import Utils.Loggson;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Romanenchuk Oleg
 * Catalog Controller
 */

@Named
@SessionScoped
@Loggson
@CatchException
public class CatalogController extends Controller implements Serializable {

    @Inject
    private CatalogService catalogService;

    private String categoryType;

    private Long deviceId;

    private Long modelId;

    private String keyword;

    private Device device;

    private Model model;

    private List<Device> devices;

    private List<Model> models;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public CatalogService getCatalogService() {
        return catalogService;
    }

    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public String findDevices(){
        devices = catalogService.findDevice(categoryType);
        return "showdevices.faces";
    }

    public String findModels(){
        device = catalogService.findDeviceById(deviceId);
        models = catalogService.findModels(deviceId);
        return "showmodels.faces";
    }

    public String findModel(){
        model = catalogService.findModel(modelId);
        return "showmodel.faces";
    }

    public String search(){
        models = catalogService.searchModels(keyword);
        return "searchresult.faces?keyword=" + "&faces-redirect=true";
    }
}
