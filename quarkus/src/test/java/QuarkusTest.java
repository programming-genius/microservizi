import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

@DisplayName(value="JUNIT Jupiter Test Class")
@TestInstance(value= TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuarkusTest {

    @BeforeEach
    public void setup(){
        System.out.println("Setup");
    }

    @ParameterizedTest
    @Tag("Tag1")
    @ValueSource(strings = {"value1","value2"})
    public void testWithValues(String value){
        System.out.println(value);
    }

    @ParameterizedTest
    @Tag("Tag1")
    @CsvSource({"Tiger, selvatic","Dog, domestic","Lion, selvatic"})
    public void loadValuesFromCsv(String animalName, String animalType){
        Assertions.assertNotNull(animalName);
        Assertions.assertNotNull(animalType);
    }

    @Test
    @Tag("Tag 2")
    @Timeout(value = 3, unit= TimeUnit.SECONDS)
    public void testTimeoutException(){
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @RepeatedTest(4)
    @DisplayName("Repeat")
    public void repeatTest(RepetitionInfo repetitionInfo){
        System.out.println("Repeat");
    }

    @Test
    @Order(1)
    public void testOrdered1(){
        System.out.println("Test ordered 1");
    }

    @Test
    @Order(2)
    public void testOrdered2(){
        System.out.println("Test ordered 2");
    }

    @AfterEach
    public void cleanUp(){
        System.out.println("Clean");
    }
}
