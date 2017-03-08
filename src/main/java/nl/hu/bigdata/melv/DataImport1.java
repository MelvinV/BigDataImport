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
public class DataImport1 {

    private DB DB = new DB();

    File getFile(String filePath)
    {
        return new File(getClass().getClassLoader().getResource(filePath).getFile());
    }

    public Person createPerson(String name)
    {
        Person person = new Person();
        person.setName(name);
        return person;
    }

    public PhenomenonType createPhenomenonType(String name)
    {
        PhenomenonType phenomenonType = new PhenomenonType();
        phenomenonType.setName(name);
        return phenomenonType;
    }

    public Quantity createQuantity(float amount, String units)
    {
        Quantity quantity = new Quantity();
        quantity.setAmount(amount);
        quantity.setUnits(units);
        return quantity;
    }

    public Measurement createMeasurement(PhenomenonType phenomenonType, Quantity quantity)
    {
        Measurement measurement = new Measurement();
        measurement.setPhenomenonType(phenomenonType);
        measurement.setQuantity(quantity);
        return measurement;
    }

    public Observation createObservation(Date datetime, Person person, Measurement measurement, CategoryObservation categoryObservation)
    {
        Observation observation = new Observation();
        observation.setDatetime(datetime);
        observation.setPerson(person);
        observation.setMeasurement(measurement);
        observation.setCategoryObservation(categoryObservation);
        return observation;
    }

    public void importDataHeartRate(String csvFilePath, Person person, PhenomenonType phenomenonType)
    {
        File file = getFile(csvFilePath);

        String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));

            int linecounter = 0;
            while ((line = br.readLine()) != null) {

                if(linecounter++ == 0) continue;

                // use comma as separator
                String[] values = line.split(cvsSplitBy);

                System.out.println("(" + linecounter + ") [" + person.getName() + "] HeartRate [timestamp= " + values[0] + " , heartrate= " + values[1] + "]");

                Quantity quantity = createQuantity(Float.parseFloat(values[1]), "Beats per minute");
                Measurement measurement = createMeasurement(phenomenonType, quantity);

                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                DateTime dt = formatter.parseDateTime(values[0]);

                Observation observation = createObservation(dt.toDate(), person, measurement, null);
                DB.save(observation);
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

    public void importDataTemperature(String csvFilePath, Person person, PhenomenonType phenomenonType)
    {
        File file = getFile(csvFilePath);

        String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));

            int linecounter = 0;
            while ((line = br.readLine()) != null) {

                if(linecounter++ == 0) continue;

                // use comma as separator
                String[] values = line.split(cvsSplitBy);

                System.out.println("(" + linecounter + ") [" + person.getName() + "] Temperature [timestamp= " + values[0] + " , temperature= " + values[1] + "]");

                Quantity quantity = createQuantity(Float.parseFloat(values[1]), "Degrees Celcius");
                Measurement measurement = createMeasurement(phenomenonType, quantity);

                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                DateTime dt = formatter.parseDateTime(values[0]);

                Observation observation = createObservation(dt.toDate(), person, measurement, null);
                DB.save(observation);
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

    public void importDataBloodPressure(String csvFilePath, Person person, PhenomenonType phenomenonTypeSystolic, PhenomenonType phenomenonTypeDiastolic)
    {
        File file = getFile(csvFilePath);

        String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));

            int linecounter = 0;
            while ((line = br.readLine()) != null) {

                if(linecounter++ == 0) continue;

                // use comma as separator
                String[] values = line.split(cvsSplitBy);

                System.out.println("(" + linecounter + ") [" + person.getName() + "] Blood Pressure [timestamp= " + values[0] + " , systolic= " + values[1] + " , diastolic= " + values[2] + "]");

                String units = "Blood Pressure";
                Quantity quantitySystolic = createQuantity(Float.parseFloat(values[1]), units);
                Quantity quantityDiastolic = createQuantity(Float.parseFloat(values[2]), units);

                Measurement measurementSystolic = createMeasurement(phenomenonTypeSystolic, quantitySystolic);
                Measurement measurementDiastolic = createMeasurement(phenomenonTypeDiastolic, quantityDiastolic);

                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                DateTime dt = formatter.parseDateTime(values[0]);

                Observation observationSystolic = createObservation(dt.toDate(), person, measurementSystolic, null);
                DB.save(observationSystolic);

                Observation observationDiastolic = createObservation(dt.toDate(), person, measurementDiastolic, null);
                DB.save(observationDiastolic);
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
        long startTime = System.currentTimeMillis();

        DataImport1 dataImport1 = new DataImport1();

        Person personAmadoOheam = dataImport1.createPerson("Amado Oheam");
        Person personJessieDamelio = dataImport1.createPerson("Jessie Damelio");
        Person personMikeNormand = dataImport1.createPerson("Mike Normand");

        PhenomenonType phenomenonTypeHeartRate = dataImport1.createPhenomenonType("Heart Rate");
        dataImport1.importDataHeartRate("csv/hr.AmadoOhearn.csv", personAmadoOheam, phenomenonTypeHeartRate);
        dataImport1.importDataHeartRate("csv/hr.JessieDamelio.csv", personJessieDamelio, phenomenonTypeHeartRate);
        dataImport1.importDataHeartRate("csv/hr.MikeNormand.csv", personMikeNormand, phenomenonTypeHeartRate);

        PhenomenonType phenomenonTypeCelcius = dataImport1.createPhenomenonType("Temperature");
        dataImport1.importDataTemperature("csv/temp.AmadoOhearn.csv", personAmadoOheam, phenomenonTypeCelcius);
        dataImport1.importDataTemperature("csv/temp.JessieDamelio.csv", personJessieDamelio, phenomenonTypeCelcius);
        dataImport1.importDataTemperature("csv/temp.MikeNormand.csv", personMikeNormand, phenomenonTypeCelcius);

        PhenomenonType phenomenonTypeSystolic = dataImport1.createPhenomenonType("Systolic");
        PhenomenonType phenomenonTypeDiastolic = dataImport1.createPhenomenonType("Diastolic");
        dataImport1.importDataBloodPressure("csv/bp.AmadoOhearn.csv", personAmadoOheam, phenomenonTypeSystolic, phenomenonTypeDiastolic);
        dataImport1.importDataBloodPressure("csv/bp.JessieDamelio.csv", personJessieDamelio, phenomenonTypeSystolic, phenomenonTypeDiastolic);
        dataImport1.importDataBloodPressure("csv/bp.MikeNormand.csv", personMikeNormand, phenomenonTypeSystolic, phenomenonTypeDiastolic);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(String.format("Total running time: %d seconds", (int)totalTime/1000));
    }
}
