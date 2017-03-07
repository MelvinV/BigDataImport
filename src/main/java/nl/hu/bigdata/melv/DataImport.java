package nl.hu.bigdata.melv;

import nl.hu.bigdata.melv.models.bd1.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * Created by melvin on 7-3-2017.
 */
public class DataImport {

    private DB DB = new DB();

    File getFile(String filePath)
    {
        return new File(getClass().getClassLoader().getResource(filePath).getFile());
    }

    Person createPerson(String name)
    {
        Person person = new Person();
        person.setName(name);
        return person;
    }

    PhenomenomType createPhenomenomType(String name)
    {
        PhenomenomType phenomenomType = new PhenomenomType();
        phenomenomType.setName(name);
        return phenomenomType;
    }

    Quantity createQuantity(float amount, String units)
    {
        Quantity quantity = new Quantity();
        quantity.setAmount(amount);
        quantity.setUnits(units);
        return quantity;
    }

    Measurement createMeasurement(PhenomenomType phenomenomType, Quantity quantity)
    {
        Measurement measurement = new Measurement();
        measurement.setPhenomenomType(phenomenomType);
        measurement.setQuantity(quantity);
        return measurement;
    }

    Observation createObservation(Date datetime, Person person, Measurement measurement, CategoryObservation categoryObservation)
    {
        Observation observation = new Observation();
        observation.setDatetime(datetime);
        observation.setPerson(person);
        observation.setMeasurement(measurement);
        observation.setCategoryObservation(categoryObservation);
        return observation;
    }

    void importData()
    {
        String csvFile = "csv/hr.AmadoOhearn.csv";
        File file = getFile(csvFile);

        String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));

            PhenomenomType phenomenomType = createPhenomenomType("Heart Rate");
            Person person = createPerson("Amado Oheam");

            int linecounter = 0;
            while ((line = br.readLine()) != null) {

                if(linecounter == 0) continue;

                // use comma as separator
                String[] values = line.split(cvsSplitBy);

                System.out.println("HeartRate [timestamp= " + values[0] + " , heartrate=" + values[1] + "]");

                Quantity quantity = createQuantity(Float.parseFloat(values[1]), "Beats per minute");
                Measurement measurement = createMeasurement(phenomenomType, quantity);

                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
                DateTime dt = formatter.parseDateTime(values[0]);

                Observation observation = createObservation(dt.toDate(), person, measurement, null);

                DB.insert(observation);

                linecounter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new DataImport().importData();
    }
}
