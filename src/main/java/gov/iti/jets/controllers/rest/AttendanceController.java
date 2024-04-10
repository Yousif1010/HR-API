package gov.iti.jets.controllers.rest;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.AttendanceDto;
import gov.iti.jets.services.AttendanceServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/attendances")
public class AttendanceController {
    AttendanceServices attendanceServices = new AttendanceServices();
    //http://localhost:9090/HR_API/webapi/attendances
    @GET
    @Produces("application/json")
    public Response getAllAttendances() {
        try {
            List<AttendanceDto> attendanceDtoList = attendanceServices.getAllAttendances();
            return Response.ok(attendanceDtoList).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve all attendances: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/attendance/employee/1
    @GET
    @Path("/employee/{empId}")
    @Produces("application/json")
    public Response findAllAttendanceByEmployeeId(@PathParam("empId") Integer employeeId) {
        try {
            List<AttendanceDto> attendanceDtoList = attendanceServices.findAllAttendanceByEmployeeId(employeeId);
            return Response.ok(attendanceDtoList).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve attendances for employee: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    // http://localhost:9090/HR_API/webapi/attendance/day
    @GET
    @Path("/day")
    @Produces("application/json")
    public Response findAllAttendanceInSpecificDay(@QueryParam("year") Integer year, @QueryParam("month") Integer month, @QueryParam("day") Integer day) {
        try {
            List<AttendanceDto> attendanceDtoList = attendanceServices.findAllAttendanceInSpecificDay(year, month, day);
            return Response.ok(attendanceDtoList).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve attendances for the specified day: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    // http://localhost:9090/HR_API/webapi/attendance/employee/1/day
    @GET
    @Path("/employee/{empId}/day")
    @Produces("application/json")
    public Response getAttendnaceByEmployeeIdAndDate(@PathParam("empId") Integer empId, @QueryParam("year") Integer year, @QueryParam("month") Integer month, @QueryParam("day") Integer day) {
        try {
            AttendanceDto attendanceDto = attendanceServices.getAttendnaceByEmployeeIdAndDate(empId, year, month, day);
            return Response.ok(attendanceDto).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve attendance for the employee on the given date: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Implement other methods similarly...

    // http://localhost:9090/HR_API/webapi/attendance/month
    @GET
    @Path("/month")
    @Produces("application/json")
    public Response findAllAttendanceInSpecificMonth(@QueryParam("year") Integer year, @QueryParam("month") Integer month) {
        try {
            List<AttendanceDto> attendanceDtoList = attendanceServices.findAllAttendanceInSpecificMonth(year, month);
            return Response.ok(attendanceDtoList).build();
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve attendances for the specified month: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/attendance/employee/1/month
    @GET
    @Path("/employee/{empId}/month")
    @Produces("application/json")
    public Response findAttendanceByEmployeeAndSpecificMonth(@PathParam("empId") Integer employeeId,@QueryParam("year") Integer year, @QueryParam("month") Integer month) {
        try {
            List<AttendanceDto> attendanceDtoList = attendanceServices.findAttendanceByEmployeeAndSpecificMonth(year, month, employeeId);
            return Response.ok(attendanceDtoList).build();
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve attendances for the specified employee and month: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/attendance/year
    @GET
    @Path("/year")
    @Produces("application/json")
    public Response findAllAttendanceInSpecificYear(@QueryParam("year") Integer year) {
        try {
            List<AttendanceDto> attendanceDtoList = attendanceServices.findAllAttendanceInSpecificYear(year);
            return Response.ok(attendanceDtoList).build();
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve attendances for the specified year: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/attendance/employee/1/year
    @GET
    @Path("/employee/{empId}/year")
    @Produces("application/json")
    public Response findAllAttendanceInSpecificYearForEmployee( @PathParam("empId") Integer employeeId,@QueryParam("year") Integer year) {
        try {
            List<AttendanceDto> attendanceDtoList = attendanceServices.findAllAttendanceInSpecificYearForEmployee(year, employeeId);
            return Response.ok(attendanceDtoList).build();
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve attendances for the specified employee and year: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/attendance/register/1
    @POST
    @Path("/register/{empId}")
    @Consumes("application/json")
    public Response enterFingerprintForAttendanceRegistration(@PathParam("empId") int empId) {
        try {
            boolean added = attendanceServices.enterFingerprintForAttendanceRegistration(empId);
            if (added) {
                String msg = "Fingerprint entered for attendance registration successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    // http://localhost:9090/HR_API/webapi/attendance/exit/1
    @POST
    @Path("/exit/{empId}")
    @Consumes("application/json")
    public Response enterFingerprintForExitingCompany(@PathParam("empId") int empId) {
        try {
            boolean added = attendanceServices.enterFingerprintForExitingCompany(empId);
            if (added) {
                String msg = "Fingerprint entered for exiting company successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    // http://localhost:9090/HR_API/webapi/attendance/delete
    @DELETE
    @Path("/{attendnaceId}")
    @Produces("application/json")
    public Response deleteAttendanceRecord(@PathParam("attendnaceId") Integer attendanceId) {
        try {
            boolean deleted = attendanceServices.deleteAttendanceRecord(attendanceId);
            if (deleted) {
                String msg = "Attendance record deleted successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to delete attendance record", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to delete attendance record: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
