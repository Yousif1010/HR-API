package gov.iti.jets.services;

import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.DeductionDto;
import gov.iti.jets.persistence.entities.Deduction;
import gov.iti.jets.persistence.mappers.DeductionMapper;
import gov.iti.jets.persistence.repositories.DeductionRepo;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DeductionServices {
    private final DeductionRepo deductionRepo ;

    public DeductionServices() {
        EntityManager em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        deductionRepo = new DeductionRepo(em);
    }

    public List<DeductionDto> getAllDeductions() {
        List<Deduction> deductionList = deductionRepo.findAll(Deduction.class).get();
        List<DeductionDto> deductionDtoList = DeductionMapper.INSTANCE.ToDeductionDtoList(deductionList);
        return deductionDtoList;
    }
    public BigDecimal getAllDeductionsAmount() {
        List<DeductionDto> deductionDtoList = getAllDeductions();
        BigDecimal deductionAmount = BigDecimal.ZERO;
        for (DeductionDto deductionDto : deductionDtoList) {
            deductionAmount = deductionAmount.add(deductionDto.getAmount());
        }
        return deductionAmount;
    }
    public boolean addDeductionToEmployee(int employeeId, BigDecimal amount, String reason, Date date) {
        deductionRepo.addDeductionToEmployee(employeeId, amount, reason, date);
        return true;
    }
    public List<DeductionDto> retrieveAllDeductionsForEmployee(int employeeId) {
        List<Deduction> deductionList = deductionRepo.retrieveAllDeductionsForEmployee(employeeId);
        List<DeductionDto> deductionDtoList = DeductionMapper.INSTANCE.ToDeductionDtoList(deductionList);
        return deductionDtoList;
    }
    public BigDecimal retrieveAllDeductionsAmountForEmployee(int employeeId) {
        List<DeductionDto> deductionDtoList = retrieveAllDeductionsForEmployee(employeeId);
        BigDecimal deductionAmount = BigDecimal.ZERO;
        for (DeductionDto deductionDto : deductionDtoList) {
            deductionAmount = deductionAmount.add(deductionDto.getAmount());
        }
        return deductionAmount;
    }
    public List<DeductionDto> retrieveAllDeductionsForEmployeeInMonthAndYear(int employeeId, int month, int year) {
        List<Deduction> deductionList = deductionRepo.retrieveAllDeductionsForEmployeeInMonthAndYear(employeeId, month, year);
        List<DeductionDto> deductionDtoList = DeductionMapper.INSTANCE.ToDeductionDtoList(deductionList);
        return deductionDtoList;
    }
    public BigDecimal retrieveAllDeductionsAmountForEmployeeInMonthAndYear(int employeeId, int month, int year) {
        List<DeductionDto> deductionDtoList = retrieveAllDeductionsForEmployeeInMonthAndYear(employeeId, month, year);
        BigDecimal deductionAmount = BigDecimal.ZERO;
        for (DeductionDto deductionDto : deductionDtoList) {
            deductionAmount = deductionAmount.add(deductionDto.getAmount());
        }
        return deductionAmount;
    }
    public List<DeductionDto> retrieveAllDeductionsForEmployeeInYear(int employeeId, int year) {
        List<Deduction> deductionList = deductionRepo.retrieveAllDeductionsForEmployeeInYear(employeeId, year);
        List<DeductionDto> deductionDtoList = DeductionMapper.INSTANCE.ToDeductionDtoList(deductionList);
        return deductionDtoList;
    }
    public BigDecimal retrieveAllDeductionsAmountForEmployeeInYear(int employeeId, int year) {
        List<DeductionDto> deductionDtoList = retrieveAllDeductionsForEmployeeInYear(employeeId, year);
        BigDecimal deductionAmount = BigDecimal.ZERO;
        for (DeductionDto deductionDto : deductionDtoList) {
            deductionAmount = deductionAmount.add(deductionDto.getAmount());
        }
        return deductionAmount;
    }
    public boolean deleteDeductionsInMonthAndYear(int month, int year) {
        deductionRepo.deleteDeductionsInMonthAndYear(month, year);
        return true;
    }
    public boolean deleteDeductionsInYear(int year) {
        deductionRepo.deleteDeductionsInYear(year);
        return true;
    }
    public boolean deleteAllDeductions() {
        deductionRepo.deleteAllDeductions();
        return true;
    }
    public boolean deleteDeductionsForEmployeeInMonthAndYear(int employeeId, int month, int year) {
        deductionRepo.deleteDeductionsForEmployeeInMonthAndYear(employeeId, month, year);
        return true;
    }
    public boolean deleteDeductionsForEmployeeInYear(int employeeId, int year) {
        deductionRepo.deleteDeductionsForEmployeeInYear(employeeId, year);
        return true;
    }

}
