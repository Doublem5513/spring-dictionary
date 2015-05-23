package controller;

import data.DictionaryEntry;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repositories.DictionaryEntryRepository;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by mmatosevic on 22.5.2015.
 */
@Controller
@RestController
@RequestMapping("/dictionary")
public class DatabaseController {
    public static final String DICT_FILE_PATH = "etc/data/eh.txt";

    @Autowired
    private DictionaryEntryRepository dictionaryEntryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<DictionaryEntry> getAllEntries(){
        final Iterable<DictionaryEntry> all = dictionaryEntryRepository.findAll();
        final LinkedList<DictionaryEntry> dictionaryEntries = new LinkedList<>();
        for(DictionaryEntry entry : all){
            dictionaryEntries.add(entry);
        }
        return dictionaryEntries;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public Collection<DictionaryEntry> search(@RequestParam("eng") String eng, @RequestParam(required = false) Boolean like) {

        Iterable<DictionaryEntry> all = null;
        if (like != null && !like) {
            all = dictionaryEntryRepository.findByEnglish(eng);
        } else {
            all = dictionaryEntryRepository.findByEnglishLike("%" + eng + "%");
        }

        LinkedList<DictionaryEntry> dictionaryEntries = new LinkedList<>();
        for (DictionaryEntry entry : all) {
            dictionaryEntries.add(entry);
        }
        return dictionaryEntries;
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importDictionary(){
        StringBuilder response = new StringBuilder();

        final File dictFile = new File(DICT_FILE_PATH);
        response.append("File exists: ").append(dictFile.exists());

        long imported = 0;
        try {
            imported = importDict(dictFile);
        } catch (IOException e) {
            return "Error loading dictionary! " + e;
        }

        response.append(" Imported (rows): ").append(imported);
        return response.toString();
    }

    private long importDict(File dictFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dictFile));
        String line = "";
        long inserted = 0;
        while((line = reader.readLine()) != null){
            DictEntry dictEntry = parseLine(line);
            if(dictEntry != null){
                DictionaryEntry dictionaryEntry = new DictionaryEntry();
                dictionaryEntry.setEnglish(dictEntry.getEng());
                dictionaryEntry.setCroatian(dictEntry.getCro());
                dictionaryEntryRepository.save(dictionaryEntry);
                inserted++;
            }
        }
        return inserted;
    }

    private static DictEntry parseLine(String line){
        String[] split = line.split("\t");
        if(split.length < 2){
            System.out.println("Invalid row! " + line);
            return null;
        }
        String eng = split[0];
        String cro = split[1];
        return new DictEntry(eng, cro);
    }
}

class DictEntry{
    private final String eng;
    private final String cro;

    public DictEntry(String eng, String cro) {
        this.eng = eng;
        this.cro = cro;
    }

    public String getEng() {
        return eng;
    }

    public String getCro() {
        return cro;
    }

    @Override
    public String toString() {
        return "DictEntry{" +
                "eng='" + eng + '\'' +
                ", cro='" + cro + '\'' +
                '}';
    }
}
