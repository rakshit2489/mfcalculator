package com.pimp.finance.mfCalculator.service;

import com.pimp.finance.mfCalculator.model.FUNDDATA;
import com.pimp.finance.mfCalculator.model.Funds;
import com.pimp.finance.mfCalculator.repositor.FundsMetadataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Callable;

public class CalculatePriceCallabe<FundsResponse> implements Callable {

    Logger log = LoggerFactory.getLogger(CalculatePriceCallabe.class);

    private Funds fund;
    private FundsMetadataRepository fundsMetadataRepository;
    private MFService mfService;

    CalculatePriceCallabe(MFService mfService, FundsMetadataRepository fundsMetadataRepository, Funds fund ){
        this.fund=fund;
        this.fundsMetadataRepository=fundsMetadataRepository;
        this.mfService=mfService;

    }

    @Override
    public Object call() {
        BigDecimal fValue = null;
        FUNDDATA meta = fundsMetadataRepository.findById(fund.getId());
        try {
            fValue = mfService.getMFPrice(meta.getFundUrl(), Arrays.asList(meta.getFundKeys()));
        } catch (Exception e){
            e.getMessage();
        }
        //BigDecimal currentFValue = Optional.ofNullable(fValue).orElse(new BigDecimal(0)).multiply(fund.getUnitsPurchased());
        BigDecimal currentFValue = Optional.ofNullable(fValue).orElse(new BigDecimal(0)).multiply(fund.getUnitsPurchased());
        BigDecimal profit = currentFValue.subtract(fund.getInvestmantAmount());
        log.info(" Fund -> " + fund.getFundName() + " Current Value -> " + currentFValue
                + " Profit -> " + profit);
        return new com.pimp.finance.mfCalculator.model.FundsResponse(fund.getFundName(),
                profit.setScale(0, BigDecimal.ROUND_DOWN));
    }
}
