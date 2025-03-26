package net.barrufet.mc.master.parser.aos;

import net.barrufet.mc.master.model.Faction;
import net.barrufet.mc.master.model.GameEdition;
import net.barrufet.mc.master.parser.ParserService;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ParserServiceAoS4 extends ParserService {

    @Value( "${mcparser.wahapedia_url}")
    private String wahapediaURL;

    @Override
    public List<Faction> parseFactions(GameEdition gameEdition) {
        return List.of();
    }

    @Override
    public Faction parseFaction(String factionName) {
        return null;
    }


    private List<FactionInfo> getFactionsInfo() {
        Document doc = retrieveHTML(wahapediaURL + "/aos4/the-rules/quick-start-guide/");
        assert doc != null;
        Elements alliances =  Objects.requireNonNull(doc.select("div.NavColumns3").first())
                                        .select("div.FactionHeader");
        return List.of();
    }




}
