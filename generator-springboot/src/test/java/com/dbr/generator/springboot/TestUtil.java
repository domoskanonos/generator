package com.dbr.generator.springboot;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtil {
    public static <T> List<T> createTestObjects(Class<T> clazz, boolean ignoreIdField, int size)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<T> retval = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            retval.add(createTestObject(clazz, ignoreIdField));
        }
        return retval;
    }

    public static <T> T createTestObject(Class<T> clazz, boolean ignoreIdField)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        T retval = clazz.getConstructor().newInstance();
        for (Field field : retval.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Id.class) != null && ignoreIdField) {
                continue;
            }
            field.setAccessible(true);
            field.set(retval, getSampleDataObject(field));
            field.setAccessible(false);
        }
        return retval;
    }

    public static Object getSampleDataObject(Field field) {

        Integer maxLength = null;
        Column columnAnnotation = field.getAnnotation(Column.class);
        if (columnAnnotation != null && columnAnnotation.length() > 0) {
            maxLength = columnAnnotation.length();
        }

        Class<?> fieldType = field.getType();
        String typeSimpleName = fieldType.getSimpleName();
        int randomInt = ThreadLocalRandom.current().nextInt(0, 10000);
        switch (typeSimpleName) {
        case DataTypes.TYPE_STRING:
            if (field.getAnnotation(Email.class) != null) {
                return "example@example.de";
            }
            return getSampleWord(maxLength);
        case DataTypes.TYPE_DATE:
            return new Date();
        case DataTypes.TYPE_CHAR:
            return "X";
        case DataTypes.TYPE_BOOLEAN:
            return true;
        case DataTypes.TYPE_SHORT:
            return (short) randomInt;
        case DataTypes.TYPE_INTEGER:
            return randomInt;
        case DataTypes.TYPE_LONG:
            return (long) randomInt;
        case DataTypes.TYPE_FLOAT:
            return (float) randomInt;
        case DataTypes.TYPE_DOUBLE:
            return (double) randomInt;
        case DataTypes.TYPE_BIG_DECIMAL:
            return BigDecimal.ZERO;
        case DataTypes.JAVA_TYPE_BYTE_ARRAY:
            return getSampleWord(maxLength).getBytes();
        default:
            if (field.getType().isEnum()) {
                return field.getType().getEnumConstants()[0];
            }
            return null;
        }
    }

    public static String getSampleWord(Integer maxLength) {
        HashMap<Integer, List<String>> sampleWords = new HashMap<>();
        List<String> lines = new ArrayList<>();
        lines.add("Flazlame");
        lines.add("Woowoosoft");
        lines.add("Headatist");
        lines.add("Diligord");
        lines.add("Alerassa");
        lines.add("Rocogged");
        lines.add("Strizzes");
        lines.add("Clossyo");
        lines.add("Voyflen");
        lines.add("Bluewolf");
        lines.add("Rendann");
        lines.add("Reflupper");
        lines.add("Acirassi");
        lines.add("Sohanidd");
        lines.add("Voonex");
        lines.add("Jukelox");
        lines.add("Unoseth");
        lines.add("Dohi");
        lines.add("Plifal");
        lines.add("Groopster");
        lines.add("Drywest");
        lines.add("Fapster");
        lines.add("Chaintwist");
        lines.add("Sislaf");
        lines.add("Glozzom");
        lines.add("Zapster");
        lines.add("Pricenano");
        lines.add("Chesture");
        lines.add("Feandra");
        lines.add("Boaclick");
        lines.add("Yammoe");
        lines.add("Mofoblitz");
        lines.add("Xanpon");
        lines.add("Singlewave");
        lines.add("Roplixoo");
        lines.add("Losenoid");
        lines.add("Loodon");
        lines.add("Rowlow");
        lines.add("Claster");
        lines.add("Pepelexa");
        lines.add("Sertave");
        lines.add("Dropellet");
        lines.add("Jeebus");
        lines.add("Noodile");
        lines.add("Drearien");
        lines.add("Kaloolon");
        lines.add("Norrisology");
        lines.add("Ybuwyn");
        lines.add("Fuffapster");
        lines.add("Jobox");
        lines.add("Creabird");
        lines.add("Astauand");
        lines.add("Mizuxe");
        lines.add("Slabdrill");
        lines.add("Zestybus");
        lines.add("Ferirama");
        lines.add("Tuttadit");
        lines.add("Printure");
        lines.add("Geosyog");
        lines.add("Plakill");
        lines.add("Waratel");
        lines.add("Sexatoo");
        lines.add("Moderock");
        lines.add("Replitz");
        lines.add("Pegmode");
        lines.add("Mizule");
        lines.add("Lazap");
        lines.add("Dizoolexa");
        lines.add("Skizze");
        lines.add("Aloidia");
        lines.add("Tribop");
        lines.add("Sepezz");
        lines.add("Loheckle");
        lines.add("Pounit");
        lines.add("Pirend");
        lines.add("Zestpond");
        lines.add("Naperone");
        lines.add("Bistup");
        lines.add("Sinpad");
        lines.add("Reiltas");
        lines.add("Steeplump");
        lines.add("Kizerain");
        lines.add("Spusious");
        lines.add("Lotadilo");
        lines.add("Swooflia");
        lines.add("Bronea");
        lines.add("Chevesic");
        lines.add("Skaxis");
        lines.add("Lulerain");
        lines.add("Nekmunnit");
        lines.add("Crestboot");
        lines.add("Foxclore");
        lines.add("Vasagle");
        lines.add("Stepjump");
        lines.add("Gleblu");
        lines.add("Castrealm");
        lines.add("Luezoid");
        lines.add("Chesture");
        lines.add("Feandra");
        lines.add("Feandra");
        lines.add("Boaclick");
        lines.add("Yammoe");
        lines.add("Mofoblitz");
        lines.add("Xanpon");
        lines.add("Singlewave");
        lines.add("Roplixoo");
        lines.add("Losenoid");
        lines.add("Loodon");
        lines.add("Rowlow");
        lines.add("Claster");
        lines.add("Pepelexa");
        lines.add("Yeinydd");
        lines.add("Hysleria");
        lines.add("Hooppler");
        lines.add("Dropperclear");
        lines.add("Esuyp");
        lines.add("Nanoarrow");
        lines.add("Qiameth");
        lines.add("Alerrawia");
        lines.add("Pulappli");
        lines.add("Wetchop");
        lines.add("Frorealm");
        lines.add("Vacso");
        lines.add("Arrowgance");
        lines.add("Shiphile");
        lines.add("Gogogox");
        lines.add("Siopp");
        lines.add("Bestfloor");
        lines.add("Gogopo");
        lines.add("Busyglide");
        lines.add("Spourmo");
        lines.add("Yodacloud");
        lines.add("Fluffster");
        lines.add("Ociramma");
        lines.add("Skizzle");
        lines.add("Sevit");
        lines.add("Defas");
        lines.add("Looplab");
        lines.add("Doggax");
        lines.add("Qerrassa");
        lines.add("Nalpure");
        lines.add("Shorogyt");
        lines.add("Zestpond");
        lines.add("Naperone");
        lines.add("Noxu");
        lines.add("Hexteria");
        lines.add("Eggmode");
        lines.add("Chucknology");
        lines.add("Bistup");
        lines.add("Sinpad");
        lines.add("Reiltas");
        lines.add("Steeplump");
        lines.add("Kizerain");
        lines.add("Spusious");
        lines.add("Lotadilo");
        lines.add("Swooflia");
        lines.add("Bronea");
        lines.add("Hawkloon");
        lines.add("Chevesic");
        lines.add("Skaxis");
        lines.add("Neocast");
        lines.add("Galikath");
        lines.add("Flazlame");
        lines.add("Woowoosoft");
        lines.add("Headatist");
        lines.add("Diligord");
        lines.add("Alerassa");
        lines.add("Rocogged");
        lines.add("Strizzes");
        lines.add("Clossyo");
        lines.add("Voyflen");
        lines.add("Bluewolf");
        lines.add("Rendann");
        lines.add("Reflupper");
        lines.add("Acirassi");
        lines.add("Sohanidd");
        lines.add("Voonex");
        lines.add("Jukelox");
        lines.add("Unoseth");
        lines.add("Dohi");
        lines.add("Plifal");
        lines.add("Groopster");
        lines.add("Drywest");
        lines.add("Sislaf");
        lines.add("Glozzom");
        lines.add("Zapster");
        lines.add("Pricenano");
        lines.add("Pentwist");
        lines.add("Trealop");
        lines.add("Shizzo");
        lines.add("Dododox");
        lines.add("Weepeggle");
        lines.add("Bookbox");
        lines.add("Duzafizz");
        lines.add("Fliondeso");
        lines.add("Ahoy-wut");
        lines.add("Miresa");
        lines.add("Looncan");
        lines.add("Cheilith");
        lines.add("Kiraric");
        lines.add("Parede");
        lines.add("Besloor");
        lines.add("Wavefire");
        lines.add("Glomtom");
        lines.add("Aferraron");
        lines.add("Tupacase");
        lines.add("Didiza");
        lines.add("Printure");
        lines.add("Geosyog");
        lines.add("Plakill");
        lines.add("Bioyino");
        lines.add("Waratel");
        lines.add("Sexatoo");
        lines.add("Moderock");
        lines.add("Replitz");
        lines.add("Pegmode");
        lines.add("Mizule");
        lines.add("Lazap");
        lines.add("Dizoolexa");
        lines.add("Skizze");
        lines.add("Aloidia");
        lines.add("Tribop");
        lines.add("Sepezz");
        lines.add("Loheckle");
        lines.add("Pounit");
        lines.add("Pounit");
        lines.add("Pounit");
        lines.add("Pirend");
        lines.add("Uliratha");
        lines.add("Uliratha");
        lines.add("Firehorse");
        lines.add("Loopnova");
        lines.add("Mogotrevo");
        lines.add("Ethosien");
        lines.add("Momoweb");
        lines.add("Hioffpo");
        lines.add("Yokovich");
        lines.add("Goulbap");
        lines.add("Locobot");
        lines.add("Bopster");
        lines.add("Neoskizzle");
        lines.add("Modeflick");
        lines.add("Gooc");
        lines.add("Rux");
        lines.add("Mal");
        lines.add("Gev");
        lines.add("Alf");
        lines.add("Karl");
        lines.add("Ball");
        lines.add("Sonne");
        lines.add("Mond");
        lines.add("Ela");
        lines.add("El");
        lines.add("Ebe");
        lines.add("Eba");
        lines.add("Rega");
        lines.add("Rudde");
        lines.add("Ruddi");
        lines.add("A");
        for (String line : lines) {
            int wordLength = line.length();
            List<String> words = sampleWords.computeIfAbsent(wordLength, key -> new ArrayList<>());
            sampleWords.put(wordLength, words);
            words.add(line);
        }

        int wordSize = sampleWords.size();
        if (maxLength == null || maxLength > wordSize) {
            maxLength = ThreadLocalRandom.current().nextInt(1, wordSize + 1);
        }

        List<String> wordsByLength = sampleWords.get(maxLength);
        return wordsByLength.get(ThreadLocalRandom.current().nextInt(0, wordsByLength.size()));

    }

    public class DataTypes {
        public static final String JAVA_TYPE_BYTE_ARRAY = "byte[]";
        public static final String TYPE_STRING = "String";
        public static final String TYPE_INTEGER = "Integer";
        public static final String TYPE_BOOLEAN = "Boolean";
        public static final String TYPE_BIG_DECIMAL = "BigDecimal";
        public static final String TYPE_LONG = "Long";
        public static final String TYPE_FLOAT = "Float";
        public static final String TYPE_SHORT = "Short";
        public static final String TYPE_DOUBLE = "Double";
        public static final String TYPE_CHAR = "Char";
        public static final String TYPE_DATE = "Date";
        public static final String TYPE_DATE_ISO8601 = "TYPE_DATE_ISO8601";
        public static final String TYPE_ARRAY_STRING = "String[]";

    }

}
