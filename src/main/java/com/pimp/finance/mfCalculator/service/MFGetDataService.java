package com.pimp.finance.mfCalculator.service;

import com.pimp.finance.mfCalculator.model.Funds;
import com.pimp.finance.mfCalculator.model.FundsResponse;
import com.pimp.finance.mfCalculator.repositor.FundsMetadataRepository;
import com.pimp.finance.mfCalculator.repositor.PortfolioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class MFGetDataService {

    Logger log = LoggerFactory.getLogger(MFGetDataService.class);

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private FundsMetadataRepository fundsMetadataRepository;

    @Autowired
    private MFService mfService;

    public List<FundsResponse> getData(String name) {

        //mfService.getSensexDetails()

        List<FundsResponse> fundsResponseList = new ArrayList<>();
        List<Funds> fundsList = portfolioRepository.findByClientName(name);

        ExecutorService es = Executors.newFixedThreadPool(10);
        List<Future<FundsResponse>> futureList = new ArrayList<>();
        for(Funds fund : fundsList) {
            Future submit = es.submit(new CalculatePriceCallabe<FundsResponse>(mfService, fundsMetadataRepository, fund));
            futureList.add(submit);
        }

        for(Future<FundsResponse> callableResponse : futureList) {
            try {
                FundsResponse response = callableResponse.get();
                fundsResponseList.add(response);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        es.shutdownNow();


        /*for (Funds fund : fundsList) {
            //FUNDDATA meta = fundsMetadataRepository.findByFundName(fund.getFundName()).get(0);
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
            fundsResponseList.add(new FundsResponse(fund.getFundName(), profit.setScale(0, BigDecimal.ROUND_DOWN)));


        }*/

        return  fundsResponseList;
    }
}
