package com.joumer.microservice.currencyexchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyExchange {
    @Id private Long id;
    @Column(name = "currency_from") private String from;
    @Column(name = "currency_to") private String to;
    private BigDecimal conversionMultiple;
    private String environment;
}
