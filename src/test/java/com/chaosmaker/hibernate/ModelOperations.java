package com.chaosmaker.hibernate;

import static com.chaosmaker.hibernate.helpers.EntityFactoryBuilder.getEntityManagerFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.chaosmaker.hibernate.models.Item;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class ModelOperations
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        getEntityManagerFactory();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(new Date());
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        assertEquals(1, violations.size());
        ConstraintViolation<Item> violation = violations.iterator().next();
        String failedPropertyName =
                violation.getPropertyPath().iterator().next().getName();
        assertEquals(failedPropertyName, "auctionEnd");
        if (Locale.getDefault().getLanguage().equals("en"))
            assertEquals(violation.getMessage(), "must be in the future");

    }
}
