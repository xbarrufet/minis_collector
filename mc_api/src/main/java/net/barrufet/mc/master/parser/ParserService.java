package net.barrufet.mc.master.parser;

import net.barrufet.mc.master.model.Faction;
import net.barrufet.mc.master.model.GameEdition;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public abstract class ParserService {

    public abstract List<Faction> parseFactions(GameEdition gameEdition);
    public abstract Faction parseFaction(String factionName);

    protected static Document retrieveHTML(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            System.err.println("Unable to fetch HTML of: " + url);
        }
        return null;
    }


}
