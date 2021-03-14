package client.clientModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class Main {

    public static void main(String [] args)
    {
        ClientDTO clientDTO = new ClientDTO("sdk-test");

        clientDTO.setBudget(5000,Frequency.Monthly,true);

        clientDTO.setStartDate(LocalDate.now());

        clientDTO.setEndDate(LocalDate.now().minusDays(7));

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator =  factory.getValidator();

        Set<ConstraintViolation<ClientDTO>> constraintViolations=   validator.validate(clientDTO);

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<ClientDTO> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
        } else {
            System.out.println("Valid Object");
        }


        ObjectMapper objectMapper =  new ObjectMapper();

            objectMapper.registerModule(new JavaTimeModule());

            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            try {
                String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(clientDTO);
                System.out.println(jsonStr);
            } catch (IOException var4) {
                var4.printStackTrace();
            }
    }

}
