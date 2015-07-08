package RestServices;

import Entity.Category;
import Entity.Device;
import Entity.Model;
import Service.CatalogService;
import Utils.Loggson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.net.URI;
import java.util.List;

/**
 * @author Romanenchuk Oleg
 * Represential State Transfer config for all access methods to web data.
 * We will work with ws.rs package (webservice.rest)
 */
@Loggson
@Path("/catalog")
public class RestService implements Serializable {

    @Inject
    private CatalogService catalogService;

    @Inject
    private UriInfo uriInfo;

    @GET       // get method , get all data in request
    @Path("/categories")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Category> findAll() {
        return catalogService.findAll();
    }

    @GET      // get method , get requested data
    @Path("/category/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Category findCategoryById(@PathParam("id") Long categoryId) {
        return catalogService.findCategoryById(categoryId);
    }

    @POST    // post method , create a new data on web shop
    @Path("/category")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createCategory(JAXBElement<Category> element) {
        Category category = catalogService.create(element.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(category.getId().toString()).build();
        return Response.ok(uri).build();
    }

    @PUT     // put method , save updated data
    @Path("/category")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateCategory(JAXBElement<Category> element ){
        Category category = catalogService.update(element.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(category.getId().toString()).build();
        return Response.ok(uri).build();
    }

    @DELETE  // delete method, deleting from data
    @Path("/category/{id}")
    public Response removeCategoryById(@PathParam("id") Long categoryId){
        catalogService.removeCategoryById(categoryId);
        return Response.noContent().build();
    }

    @GET     // get method , get all requested devices data
    @Path("/devices")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Device> findAllDevices(){
        return catalogService.findAllDevices();
    }

    @GET     // get method , get device data by id requested
    @Path("/device/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Device findDeviceById(@PathParam("id") Long categoryId){
        return catalogService.findDeviceById(categoryId);
        }

    @POST    // post method , create a new data on web shop
    @Path("/device")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createDevice(JAXBElement<Device> element){
        Device device = catalogService.createDevice(element.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(device.getId().toString()).build();
        return Response.ok(uri).build();
    }

    @PUT     // put method , save updated data
    @Path("/device")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateDevice(JAXBElement<Device> element){
        Device device = catalogService.updateDevice(element.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(device.getId().toString()).build();
        return Response.ok(uri).build();
    }

    @DELETE  // delete method, deleting from data
    @Path("/device/{id}")
    public Response removeDeviceById(@PathParam("id") Long deviceId){
        catalogService.removeDeviceById(deviceId);
        return Response.noContent().build();
    }

    @GET     // get method , get all requested devices data
    @Path("/models")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Model> findAllModels(){
        return catalogService.findAllModels();
    }

    @GET     // get method , get model data by id requested
    @Path("/model/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Model findModelById(@PathParam("id") Long modelId){
        return catalogService.findModel(modelId);
    }

    @POST    // post method , create a new data on web shop
    @Path("/models")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createModel(JAXBElement<Model> element){
        Model model = catalogService.createModel(element.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(model.getId().toString()).build();
        return Response.ok(uri).build();
    }

    @PUT     // put method , save updated data
    @Path("/model")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateModel(JAXBElement<Model> element){
        Model model = catalogService.updateModel(element.getValue());
        URI uri = uriInfo.getAbsolutePathBuilder().path(model.getId().toString()).build();
        return Response.ok(uri).build();
    }

    @DELETE  // delete method, deleting from data
    @Path("/model/{id}")
    public Response removeModelById(@PathParam("id") Long modelId){
        catalogService.removeModel(modelId);
        return Response.noContent().build();
    }
}

