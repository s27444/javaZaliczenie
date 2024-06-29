package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import Enum.CurrencyCode;

import java.util.List;

public class NbpApiResponse {
    private String table;
    private String currency;
    private CurrencyCode currencyCode;

    @JsonProperty("rate")
    private List<NbpRate> rates;

    public NbpApiResponse(String table, String currency, CurrencyCode currencyCode, List<NbpRate> rates) {
        this.table = table;
        this.currency = currency;
        this.currencyCode = currencyCode;
        this.rates = rates;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public List<NbpRate> getRates() {
        return rates;
    }

    public void setRates(List<NbpRate> rates) {
        this.rates = rates;
    }
}
