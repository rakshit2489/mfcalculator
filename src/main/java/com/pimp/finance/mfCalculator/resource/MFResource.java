package com.pimp.finance.mfCalculator.resource;

import com.pimp.finance.mfCalculator.model.FUNDDATA;
import com.pimp.finance.mfCalculator.model.Funds;
import com.pimp.finance.mfCalculator.model.FundsResponse;
import com.pimp.finance.mfCalculator.repositor.FundsMetadataRepository;
import com.pimp.finance.mfCalculator.repositor.PortfolioRepository;
import com.pimp.finance.mfCalculator.service.MFGetDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MFResource {

    Logger log = LoggerFactory.getLogger(MFResource.class);

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private FundsMetadataRepository metadataRepository;

    @Autowired
    private MFGetDataService mfGetDataService;

    @GetMapping("/allportfolio")
    public List<Funds> retrieveAllPortfolios() {
        return portfolioRepository.findAll();
    }

    @GetMapping("/allportfolio/{name}")
    public List<FundsResponse> retrievePortfolios(@PathVariable String name) {
        log.info("Request to fetch portfolio for " + name);
        return mfGetDataService.getData(name);
    }

    @GetMapping("/summary/{name}")
    public FundsResponse retrieveSummary(@PathVariable String name) {
        log.info("Request to fetch summary for " + name);
        List<FundsResponse> list = mfGetDataService.getData(name);
        log.info("Response sent to client ");
        return new FundsResponse(name, list.stream().map(FundsResponse::getProfit)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    @GetMapping("/summaryView/{name}")
    public FundsResponse retrieveSummaryView(@PathVariable String name) {
        log.info("Request to fetch summary for " + name);
        List<FundsResponse> list = mfGetDataService.getData(name);
        log.info("Response sent to client ");
        FundsResponse user = new FundsResponse(name, list.stream().map(FundsResponse::getProfit)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        //return "summaryTemplate";
        return user;
    }

    @GetMapping("fundsmeta")
    public List<FUNDDATA> getFundMeta() {
        return metadataRepository.findAll();
    }
}