package com.pimp.finance.mfCalculator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "FUNDDATA")
@Data
public class FUNDDATA {

    @Id
    @GeneratedValue
    @Column(name="FUND_ID")
    private int fundId;
    @Column(name="FUND_NAME")
    private String fundName;
    @Column(name="FUND_URL")
    private String fundUrl;
    @Column(name="FUND_KEYS")
    private String fundKeys;


}
