package services;

import exception.BadRequestException;
import exception.GateWayTimeout;
import exception.InternalServerError;
import exception.NotFoundException;
import model.Nbp;
import model.NbpApiResponse;
import model.NbpRate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import repository.NbpRepository;
import Enum.CurrencyCode;

@Service
public class NbpService {
    private final NbpRepository nbpRepository;

    private final RestTemplate restTemplate;

    public NbpService(NbpRepository nbpRepository, RestTemplate restTemplate) {
        this.nbpRepository = nbpRepository;
        this.restTemplate = restTemplate;
    }

    public double getDaysAve(CurrencyCode currencyCode, String dateStart, String dateEnd) {
        try {
            String NBP_API_2 = "/";
            String NBP_API_1 = "http://api.nbp.pl/api/exchangerates/rates/a/";
            NbpApiResponse response = restTemplate.getForObject(NBP_API_1 + currencyCode + NBP_API_2 + dateStart + NBP_API_2 + dateEnd, NbpApiResponse.class);
            return response.getRates().stream()
                    .mapToDouble(NbpRate::getMid)
                    .average()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Brak danych dla tej waluty"));
        }catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException();
        } catch (HttpClientErrorException.BadRequest b) {
            throw new BadRequestException();
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerError();
        } catch (HttpServerErrorException.GatewayTimeout m) {
            throw new GateWayTimeout();
        }

    }
    public Nbp getDaysNewAverage(CurrencyCode currencyCode, String dateStart, String dateEnd){
        try {
            Nbp nbp = new Nbp();
            nbp.setCourse(getDaysAve(currencyCode, dateStart, dateEnd));
            nbp.setCurrencyCode(currencyCode);
            nbp.getDateRequest();
            nbp.setDateStart(dateStart);
            nbp.setDateEnd(dateEnd);
            nbp.save(nbp);
            return nbp;
        }catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException();
        } catch (HttpClientErrorException.BadRequest b) {
            throw new BadRequestException();
        } catch (HttpServerErrorException.InternalServerError e) {
            throw new InternalServerError();
        } catch (HttpServerErrorException.GatewayTimeout m) {
            throw new GateWayTimeout();
        }

    }
}
