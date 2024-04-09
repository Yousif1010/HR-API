package gov.iti.jets.services;

import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.BonusDto;
import gov.iti.jets.persistence.entities.Bonus;
import gov.iti.jets.persistence.mappers.BonusMapper;
import gov.iti.jets.persistence.repositories.BonusRepo;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BonusServices {
    private final BonusRepo bonusRepo ;

    public BonusServices() {
        EntityManager em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        bonusRepo = new BonusRepo(em);
    }
    public List<BonusDto> getAllBonuses() {
        List<Bonus> bonusList =bonusRepo.findAll(Bonus.class).get();
        List<BonusDto> bonusDtoList = BonusMapper.INSTANCE.toBonusDtoList(bonusList);
        return bonusDtoList;
    }
    public BigDecimal getAllBonusesAmount(){
        List<BonusDto> bonusDtoList = getAllBonuses();
        BigDecimal bonusAmount = BigDecimal.ZERO;
        for (BonusDto bonusDto : bonusDtoList) {
            bonusAmount = bonusAmount.add(bonusDto.getAmount());
        }
        return bonusAmount;
    }
    public boolean addBonusToEmployee(int employeeId, BigDecimal amount, String reason, Date date){
        bonusRepo.addBonusToEmployee(employeeId,amount,reason,date);
        return true;
    }
    public List<BonusDto> retrieveAllBonusesForEmployee(int employeeId){
        List<Bonus> bonusList = bonusRepo.retrieveAllBonusesForEmployee(employeeId);
        List<BonusDto> bonusDtoList = BonusMapper.INSTANCE.toBonusDtoList(bonusList);
        return bonusDtoList;
    }
    public BigDecimal retrieveAllBonusesAmountForEmployee(int employeeId){
        List<BonusDto> bonusDtoList = retrieveAllBonusesForEmployee(employeeId);
        BigDecimal bonusAmount = BigDecimal.ZERO;
        for (BonusDto bonusDto : bonusDtoList) {
            bonusAmount = bonusAmount.add(bonusDto.getAmount());
        }
        return bonusAmount;
    }
    public List<BonusDto> retrieveAllBonusesForEmployeeInMonthAndYear(int employeeId, int month, int year){
        List<Bonus> bonusList = bonusRepo.retrieveAllBonusesForEmployeeInMonthAndYear(employeeId, month, year);
        List<BonusDto> bonusDtoList = BonusMapper.INSTANCE.toBonusDtoList(bonusList);
        return bonusDtoList;
    }
    public BigDecimal retrieveAllBonusesAmountForEmployeeInMonthAndYear(int employeeId, int month, int year){
        List<BonusDto> bonusDtoList = retrieveAllBonusesForEmployeeInMonthAndYear(employeeId, month, year);
        BigDecimal bonusAmount = BigDecimal.ZERO;
        for (BonusDto bonusDto : bonusDtoList) {
            bonusAmount = bonusAmount.add(bonusDto.getAmount());
        }
        return bonusAmount;
    }
    public List<BonusDto> retrieveAllBonusesForEmployeeInYear(int employeeId, int year){
        List<Bonus> bonusList = bonusRepo.retrieveAllBonusesForEmployeeInYear(employeeId, year);
        List<BonusDto> bonusDtoList = BonusMapper.INSTANCE.toBonusDtoList(bonusList);
        return bonusDtoList;
    }
    public BigDecimal retrieveAllBonusesAmountForEmployeeInYear(int employeeId, int year){
        List<BonusDto> bonusDtoList = retrieveAllBonusesForEmployeeInYear(employeeId, year);
        BigDecimal bonusAmount = BigDecimal.ZERO;
        for (BonusDto bonusDto : bonusDtoList) {
            bonusAmount = bonusAmount.add(bonusDto.getAmount());
        }
        return bonusAmount;
    }
    public boolean deleteBonusesInMonthAndYear(int month, int year) {
        bonusRepo.deleteBonusesInMonthAndYear(month, year);
        return true;
    }
    public boolean deleteBonusesInYear(int year) {
        bonusRepo.deleteBonusesInYear(year);
        return true;
    }
    public boolean deleteAllBonuses() {
        bonusRepo.deleteAllBonuses();
        return true;
    }
    public boolean deleteBonusesForEmployeeInMonthAndYear(int employeeId, int month, int year) {
        bonusRepo.deleteBonusesForEmployeeInMonthAndYear(employeeId, month, year);
        return true;
    }
    public boolean deleteBonusesForEmployeeInYear(int employeeId, int year) {
        bonusRepo.deleteBonusesForEmployeeInYear(employeeId, year);
        return true;
    }
}
