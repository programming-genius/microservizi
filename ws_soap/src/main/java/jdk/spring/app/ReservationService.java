package jdk.spring.app;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import jdk.spring.ws.*;

@Endpoint
public class ReservationService {

        private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

        @PayloadRoot(
                namespace = "http://www.titan.com/Customer", //namespace
                localPart = "Customer") //tipo dichiarato
        @ResponsePayload
        public Reservation prenotazione(@RequestPayload Customer customerMessage) {
            LOGGER.info("Endpoint received reservation from[firstName={},lastName={},city={},state={},zip={},street={}]",
                    customerMessage.getDati().getFirstName(), customerMessage.getDati().getLastName(),
                    customerMessage.getDati().getAddress().getCity(),
                    customerMessage.getDati().getAddress().getState(),
                    customerMessage.getDati().getAddress().getZip(),customerMessage.getDati().getAddress().getStreet());

            ObjectFactory factory = new ObjectFactory();
            Reservation response = factory.createReservation();
            response.setCustomer(customerMessage.getDati());
            response.setAmount(80);
            response.setRoom(1);
            return response;
        }
}

