package nl.hu.bigdata.melv;

import nl.hu.bigdata.melv.models.bd2.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * Created by melvin on 8-3-2017.
 */
public class DataImport2 {

    private DB DB = new DB();

    public nl.hu.bigdata.melv.DB getDB() {
        return DB;
    }

    public void setDB(nl.hu.bigdata.melv.DB DB) {
        this.DB = DB;
    }

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

    public nl.hu.bigdata.melv.models.bd2.Date createDatetime(Date datetime)
    {
        nl.hu.bigdata.melv.models.bd2.Date date = new nl.hu.bigdata.melv.models.bd2.Date();
        date.setDatetime(datetime);
        return date;
    }

    public Phenomenon createPhenomenon(String name, String type)
    {
        Phenomenon phenomenon = new Phenomenon();
        phenomenon.setName(name);
        phenomenon.setType(type);
        return phenomenon;
    }

    public Unit createUnit(String name)
    {
        Unit unit = new Unit();
        unit.setName(name);
        return unit;
    }

    public Measurement createMeasurement(Person person, nl.hu.bigdata.melv.models.bd2.Date datetime, Phenomenon phenomenon, Unit unit, float amount)
    {
        Measurement measurement = new Measurement();
        measurement.setPerson_id((long)person.getId());
        measurement.setDatetime_id((long)datetime.getId());
        measurement.setPhenomenon_id((long)phenomenon.getId());
        measurement.setUnit_id((long)unit.getId());
        measurement.setAmount(amount);
        return measurement;
    }

    public void importDataHeartRate(String csvFilePath, Person person, Phenomenon phenomenon, Unit unit)
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

                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                DateTime dt = formatter.parseDateTime(values[0]);

                nl.hu.bigdata.melv.models.bd2.Date datetime = createDatetime(dt.toDate());
                DB.save(datetime);

                float amount = Float.parseFloat(values[1]);

                Measurement measurement = createMeasurement(person, datetime, phenomenon, unit, amount);
                DB.save(measurement);
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

    public void importDataTemperature(String csvFilePath, Person person, Phenomenon phenomenon, Unit unit)
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

                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                DateTime dt = formatter.parseDateTime(values[0]);

                nl.hu.bigdata.melv.models.bd2.Date datetime = createDatetime(dt.toDate());

                float amount = Float.parseFloat(values[1]);

                Measurement measurement = createMeasurement(person, datetime, phenomenon, unit, amount);
                DB.save(measurement);
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

    public void importDataBloodPressure(String csvFilePath, Person person, Phenomenon phenomenonSystolic, Phenomenon phenomenonDiastolic, Unit unit)
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

                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                DateTime dt = formatter.parseDateTime(values[0]);

                nl.hu.bigdata.melv.models.bd2.Date datetime = createDatetime(dt.toDate());

                float amountSystolic = Float.parseFloat(values[1]);
                float amountDiastolic = Float.parseFloat(values[2]);

                Measurement measurementSystolic = createMeasurement(person, datetime, phenomenonSystolic, unit, amountSystolic);
                DB.save(measurementSystolic);

                Measurement measurementDiastolic = createMeasurement(person, datetime, phenomenonDiastolic, unit, amountDiastolic);
                DB.save(measurementDiastolic);
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

        DataImport2 dataImport2 = new DataImport2();

        Person personAmadoOheam = dataImport2.createPerson("Amado Oheam");
        dataImport2.getDB().save(personAmadoOheam);
        Person personJessieDamelio = dataImport2.createPerson("Jessie Damelio");
        dataImport2.getDB().save(personJessieDamelio);
        Person personMikeNormand = dataImport2.createPerson("Mike Normand");
        dataImport2.getDB().save(personMikeNormand);

        Phenomenon phenomenonHeartRate = dataImport2.createPhenomenon("Heart Rate", "Heart Rate");
        dataImport2.getDB().save(phenomenonHeartRate);
        Unit unitHeartRate = dataImport2.createUnit("Beats per minute");
        dataImport2.getDB().save(unitHeartRate);
        dataImport2.importDataHeartRate("csv/hr.AmadoOhearn.csv", personAmadoOheam, phenomenonHeartRate, unitHeartRate);
        dataImport2.importDataHeartRate("csv/hr.JessieDamelio.csv", personJessieDamelio, phenomenonHeartRate, unitHeartRate);
        dataImport2.importDataHeartRate("csv/hr.MikeNormand.csv", personMikeNormand, phenomenonHeartRate, unitHeartRate);

        Phenomenon phenomenonTemperature = dataImport2.createPhenomenon("Temperature", "Temperature");
        dataImport2.getDB().save(phenomenonTemperature);
        Unit unitTemperature = dataImport2.createUnit("Degrees Celcius");
        dataImport2.getDB().save(unitTemperature);
        dataImport2.importDataTemperature("csv/temp.AmadoOhearn.csv", personAmadoOheam, phenomenonTemperature, unitTemperature);
        dataImport2.importDataTemperature("csv/temp.JessieDamelio.csv", personJessieDamelio, phenomenonTemperature, unitTemperature);
        dataImport2.importDataTemperature("csv/temp.MikeNormand.csv", personMikeNormand, phenomenonTemperature, unitTemperature);

        Phenomenon phenomenonBloodPressureSystolic = dataImport2.createPhenomenon("Systolic", "Systolic");
        dataImport2.getDB().save(phenomenonBloodPressureSystolic);
        Phenomenon phenomenonBloodPressureDiastolic = dataImport2.createPhenomenon("Diastolic", "Diastolic");
        dataImport2.getDB().save(phenomenonBloodPressureDiastolic);
        Unit unitBloodPressure = dataImport2.createUnit("Blood Pressure");
        dataImport2.getDB().save(unitBloodPressure);
        dataImport2.importDataBloodPressure("csv/bp.AmadoOhearn.csv", personAmadoOheam, phenomenonBloodPressureSystolic, phenomenonBloodPressureDiastolic, unitBloodPressure);
        dataImport2.importDataBloodPressure("csv/bp.JessieDamelio.csv", personJessieDamelio, phenomenonBloodPressureSystolic, phenomenonBloodPressureDiastolic, unitBloodPressure);
        dataImport2.importDataBloodPressure("csv/bp.MikeNormand.csv", personMikeNormand, phenomenonBloodPressureSystolic, phenomenonBloodPressureDiastolic, unitBloodPressure);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(String.format("Total running time: %d seconds", (int)totalTime/1000));
    }
}
