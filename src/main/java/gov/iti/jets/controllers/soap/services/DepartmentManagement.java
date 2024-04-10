package gov.iti.jets.controllers.soap.services;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.DepartmentDto;
import gov.iti.jets.persistence.dtos.EmployeeDto;
import gov.iti.jets.services.DepartmentServices;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;
import java.util.List;
@WebService
public class DepartmentManagement {

    DepartmentServices departmentServices = new DepartmentServices();

    @WebMethod
    @WebResult(name = "departments")
    public List<DepartmentDto> getAllDepartments() {
        try {
            return departmentServices.getAllDepartments();
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }

    @WebMethod
    public String addDepartment(@WebParam(name = "departmentDto") DepartmentDto departmentDto) {
        try {
            boolean added;
            if (departmentDto.getEmpId() != null) {
                added = departmentServices.addDepartment(departmentDto.getDepartmentName(), departmentDto.getEmpId());
            } else {
                added = departmentServices.addDepartmentWithoutManager(departmentDto.getDepartmentName());
            }
            if (added) {
                return "Department added successfully";
            } else {
                throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);            }
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }

    @WebMethod
    public String deleteDepartment(@WebParam(name = "id") int id) {
        try {
            boolean deleted = departmentServices.deleteDepartment(id);
            if (deleted) {
                return "Department deleted successfully";
            } else {
                throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);            }
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }

    @WebMethod
    public String updateDepartment(
            @WebParam(name = "id") int departmentId,
            @WebParam(name = "departmentDto") DepartmentDto departmentDto) {
        try {
            boolean updated = false;

            if (departmentDto.getDepartmentName() != null) {
                updated = departmentServices.updateDeaprtmentName(departmentId, departmentDto.getDepartmentName());
                if (!updated) {
                    throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);                }
            }

            if (departmentDto.getEmpId() != null) {
                updated = departmentServices.updateDepartmentManager(departmentId, departmentDto.getEmpId());
                if (!updated) {
                    throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);                }
            }

            if (updated) {
                return "Department updated successfully";
            } else {
                throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);            }
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }

    @WebMethod
    @WebResult(name = "manager")
    public EmployeeDto getDepartmentManager(@WebParam(name = "id") int departmentId) {
        try {
            EmployeeDto manager = departmentServices.getDepartmentManager(departmentId);
            if (manager != null) {
                return manager;
            } else {
                throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);            }
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }
}

