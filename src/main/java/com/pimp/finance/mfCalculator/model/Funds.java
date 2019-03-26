package com.pimp.finance.mfCalculator.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "PORTFOLIO")
@Data
public class Funds {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private int id ;
    @Column(name="FOLIONUMBER")
    private String folioNumber ;
    @Column(name="CLIENTNAME")
    private String clientName ;
    @Column(name="PURCHASEDDATE")
    private Date purchaseDate ;
    @Column(name="FUNDNAME")
    private String fundName;
    @Column(name="OPTION")
    private String option ;
    @Column(name="INVESTMENTAMOUNT")
    private BigDecimal investmantAmount ;
    @Column(name="PURCHASENAV")
    private BigDecimal purchasedNav ;
    @Column(name="UNITSPURCHASED")
    private BigDecimal unitsPurchased ;

}
