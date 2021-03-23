package jobGroup;

import BeanValidation.models.CampaignDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class Base {

    public static void main(String [] args) throws JsonProcessingException {

        JobGroupDTO jobGroupDTO = new JobGroupDTO("test_jG","campId","clientId");

       ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());;
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator =  factory.getValidator();

        Set<ConstraintViolation<JobGroupDTO>> constraintViolations=   validator.validate(jobGroupDTO);

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<JobGroupDTO> violation : constraintViolations) {
                System.out.println(violation.getMessage());
            }
        } else {
            System.out.println("Valid JobGroup");
        }

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jobGroupDTO));

    }
}
