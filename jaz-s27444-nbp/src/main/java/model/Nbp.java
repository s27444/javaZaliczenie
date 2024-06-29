package model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import Enum.CurrencyCode;

import java.time.LocalDateTime;
@Entity
@Table(name = "nbp")
@Schema(description = "Details about a nbp")
public class Nbp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique ID of the nbp", example = "1")
    private Integer id;

    @Schema(description = "The course currency", example = "4.32")
    private Double course;

    @Schema(description = "The currency Enum", example = "GBP")
    private CurrencyCode currencyCode;
    @Schema(description = "The date start of search", example = "2024-02-01")
    private String dateStart;

    @Schema(description = "The date start of search", example = "2024-02-01")
    private String dateEnd;

    @Schema(description = "The return value", example = "2500.99")
    private float value;

    @Schema(description = "The date time request task", example = "2024-02-01 00:00:00")
    private LocalDateTime dateRequest = LocalDateTime.now();

    public Nbp(Integer id, Double course, CurrencyCode currencyCode, String dateStart, String dateEnd, float value, LocalDateTime dateRequest) {
        this.id = id;
        this.course = course;
        this.currencyCode = currencyCode;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.value = value;
        this.dateRequest = dateRequest;
    }

    public Nbp() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCourse() {
        return course;
    }

    public void setCourse(Double course) {
        this.course = course;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public LocalDateTime getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDateTime dateRequest) {
        this.dateRequest = dateRequest;
    }

    public void save(Nbp nbp) {
    }
}
