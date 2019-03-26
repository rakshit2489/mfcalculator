package com.pimp.finance.mfCalculator.repositor;

import com.pimp.finance.mfCalculator.model.FUNDDATA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface FundsMetadataRepository  extends JpaRepository<FUNDDATA, Integer> {

    List<FUNDDATA> findByFundName(String fundName);
    FUNDDATA findById(int id);

}
