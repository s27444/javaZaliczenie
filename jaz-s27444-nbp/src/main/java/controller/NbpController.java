package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import model.Nbp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import repository.NbpRepository;
import services.NbpService;
import Enum.CurrencyCode;

@RestController
public class NbpController {
    private final NbpService nbpService;
    private final NbpRepository nbpRepository;

    public NbpController(NbpService nbpService, NbpRepository nbpRepository) {
        this.nbpService = nbpService;
        this.nbpRepository = nbpRepository;
    }

    @Operation(summary = "Wyszukaj sredni kurs wybranej waluty w wybranym przez siebie okresie czasu Yyyy-mm-dd")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfull"),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request - Request was bad", content = @Content)
    })
    @GetMapping("/id6/{code}/{start}/{stop}")
    public ResponseEntity<Nbp> getDaysAverage(@PathVariable CurrencyCode currencyCode, @PathVariable String dateStart, @PathVariable String dateEnd){
        return ResponseEntity.ok(nbpService.getDaysNewAverage(currencyCode, dateStart, dateEnd));
    }
}
