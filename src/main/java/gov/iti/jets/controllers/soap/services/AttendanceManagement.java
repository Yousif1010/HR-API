package gov.iti.jets.controllers.soap.services;


import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.AttendanceDto;
import gov.iti.jets.services.AttendanceServices;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;

import java.util.List;

@WebService
public class AttendanceManagement {

    AttendanceServices attendanceServices = new AttendanceServices();

    @WebMethod
    @WebResult(name = "attendanceList")
    public List<AttendanceDto> getAllAttendances() {
        return attendanceServices.getAllAttendances();
    }

    @WebMethod
    @WebResult(name = "attendanceList")
    public List<AttendanceDto> findAllAttendanceByEmployeeId(@WebParam(name = "employeeId") Integer employeeId) {
        return attendanceServices.findAllAttendanceByEmployeeId(employeeId);
    }

    @WebMethod
    @WebResult(name = "attendanceList")
    public List<AttendanceDto> findAllAttendanceInSpecificDay(
            @WebParam(name = "year") Integer year,
            @WebParam(name = "month") Integer month,
            @WebParam(name = "day") Integer day) {
        return attendanceServices.findAllAttendanceInSpecificDay(year, month, day);
    }

    @WebMethod
    @WebResult(name = "attendance")
    public AttendanceDto getAttendnaceByEmployeeIdAndDate(
            @WebParam(name = "empId") Integer empId,
            @WebParam(name = "year") Integer year,
            @WebParam(name = "month") Integer month,
            @WebParam(name = "day") Integer day) {
        return attendanceServices.getAttendnaceByEmployeeIdAndDate(empId, year, month, day);
    }

    @WebMethod
    @WebResult(name = "attendanceList")
    public List<AttendanceDto> findAllAttendanceInSpecificMonth(
            @WebParam(name = "year") Integer year,
            @WebParam(name = "month") Integer month) {
        return attendanceServices.findAllAttendanceInSpecificMonth(year, month);
    }

    @WebMethod
    @WebResult(name = "attendanceList")
    public List<AttendanceDto> findAttendanceByEmployeeAndSpecificMonth(
            @WebParam(name = "empId") Integer employeeId,
            @WebParam(name = "year") Integer year,
            @WebParam(name = "month") Integer month) {
        return attendanceServices.findAttendanceByEmployeeAndSpecificMonth(year, month, employeeId);
    }

    @WebMethod
    @WebResult(name = "attendanceList")
    public List<AttendanceDto> findAllAttendanceInSpecificYear(
            @WebParam(name = "year") Integer year) {
        return attendanceServices.findAllAttendanceInSpecificYear(year);
    }

    @WebMethod
    @WebResult(name = "attendanceList")
    public List<AttendanceDto> findAllAttendanceInSpecificYearForEmployee(
            @WebParam(name = "empId") Integer employeeId,
            @WebParam(name = "year") Integer year) {
        return attendanceServices.findAllAttendanceInSpecificYearForEmployee(year, employeeId);
    }

    @WebMethod
    @WebResult(name = "message")
    public String enterFingerprintForAttendanceRegistration(
            @WebParam(name = "empId") int empId) {
        boolean added = attendanceServices.enterFingerprintForAttendanceRegistration(empId);
        if (added) {
            return "Fingerprint entered for attendance registration successfully";
        } else {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    @WebResult(name = "message")
    public String enterFingerprintForExitingCompany(
            @WebParam(name = "empId") int empId) {
        boolean added = attendanceServices.enterFingerprintForExitingCompany(empId);
        if (added) {
            return "Fingerprint entered for exiting company successfully";
        } else {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    @WebResult(name = "message")
    public String deleteAttendanceRecord(
            @WebParam(name = "attendanceId") Integer attendanceId) throws Exception {
        boolean deleted = attendanceServices.deleteAttendanceRecord(attendanceId);
        if (deleted) {
            return "Attendance record deleted successfully";
        } else {
            throw new ResourceException("Failed to delete attendance record", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
