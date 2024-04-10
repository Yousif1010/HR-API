package gov.iti.jets.controllers.rest;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.DepartmentDto;
import gov.iti.jets.persistence.dtos.EmployeeDto;
import gov.iti.jets.services.DepartmentServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/departments")
public class DepartmentController {

    DepartmentServices departmentServices = new DepartmentServices();

    // http://localhost:9090/HR_API/webapi/departments
    @GET
    @Produces("application/json")
    public Response getAllDepartments() {
        try {
            List<DepartmentDto> departmentDtoList = departmentServices.getAllDepartments();
            return Response.ok(departmentDtoList).build();
        }catch(ResourceException ex){
            throw ex;
            }
         catch (Exception e) {
            throw new ResourceException("Failed to retrieve all departments: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes("application/json")
    public Response addDepartment(DepartmentDto departmentDto) {
        try {
            boolean added;
            if (departmentDto.getEmpId() != null) {
                added = departmentServices.addDepartment(departmentDto.getDepartmentName(), departmentDto.getEmpId());
            } else {
                added = departmentServices.addDepartmentWithoutManager(departmentDto.getDepartmentName());
            }
            if (added) {
                String msg = "Department added successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to add department", Response.Status.INTERNAL_SERVER_ERROR);
            }
        }catch(ResourceException ex){
            throw ex;
        }
        catch (Exception e) {
            throw new ResourceException("Failed to add department: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartment(@PathParam("id") int id) {
        try {
            boolean deleted = departmentServices.deleteDepartment(id);
            if (deleted) {
                String msg = "Department deleted successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to delete department", Response.Status.INTERNAL_SERVER_ERROR);
            }
        }catch(ResourceException ex){
            throw ex;
        } catch (Exception e) {
            throw new ResourceException("Failed to delete department: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

//    @PUT
//    @Path("/{id}")
//    @Consumes("application/json")
//    public Response updateDepartmentName(@PathParam("id") int departmentId, DepartmentDto departmentDto) {
//        try {
//            boolean updated = departmentServices.updateDeaprtmentName(departmentId, departmentDto.getDepartmentName());
//            if (updated) {
//                String msg = "Department name updated successfully";
//                return Response.ok(msg).build();
//            } else {
//                throw new ResourceException("Failed to update department name", Response.Status.INTERNAL_SERVER_ERROR);
//            }
//        } catch (Exception e) {
//            throw new ResourceException("Failed to update department name: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PUT
//    @Path("/{id}/manager")
//    @Consumes("application/json")
//    public Response updateDepartmentManager(@PathParam("id") int departmentId, DepartmentDto departmentDto) {
//        try {
//            boolean updated = departmentServices.updateDepartmentManager(departmentId, departmentDto.getEmpId());
//            if (updated) {
//                String msg = "Department manager updated successfully";
//                return Response.ok(msg).build();
//            } else {
//                throw new ResourceException("Failed to update department manager", Response.Status.INTERNAL_SERVER_ERROR);
//            }
//        } catch (Exception e) {
//            throw new ResourceException("Failed to update department manager: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateDepartment(@PathParam("id") int departmentId, DepartmentDto departmentDto) {
        try {
            boolean updated = false;

            // Update department name if provided
            if (departmentDto.getDepartmentName() != null) {
                updated = departmentServices.updateDeaprtmentName(departmentId, departmentDto.getDepartmentName());
                if (!updated) {
                    throw new ResourceException("Failed to update department name", Response.Status.INTERNAL_SERVER_ERROR);
                }
            }

            // Update department manager if provided
            if (departmentDto.getEmpId() != null) {
                updated = departmentServices.updateDepartmentManager(departmentId, departmentDto.getEmpId());
                if (!updated) {
                    throw new ResourceException("Failed to update department manager", Response.Status.INTERNAL_SERVER_ERROR);
                }
            }

            // Check if any update was performed
            if (updated) {
                String msg = "Department updated successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("No update parameters provided", Response.Status.BAD_REQUEST);
            }
        } catch (ResourceException ex) {
            throw ex;
        } catch (Exception e) {
            throw new ResourceException("Failed to update department: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/{id}/manager")
    @Produces("application/json")
    public Response getDepartmentManager(@PathParam("id") int departmentId) {
        try {
            EmployeeDto manager = departmentServices.getDepartmentManager(departmentId);
            if (manager != null) {
                return Response.ok(manager).build();
            } else {
                throw new ResourceException("No manager found for this department", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve department manager: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}

