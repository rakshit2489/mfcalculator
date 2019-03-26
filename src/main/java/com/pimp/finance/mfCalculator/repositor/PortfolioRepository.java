package com.pimp.finance.mfCalculator.repositor;

import com.pimp.finance.mfCalculator.model.Funds;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface PortfolioRepository extends JpaRepository<Funds, String> {

    public List<Funds> findByClientName(String clientName);

}