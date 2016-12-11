import org.eclipse.rdf4j.RDF4JException;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.query.*;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

import java.net.URL;
import java.util.List;

/**
 * Created by Le Pham Minh Duc on 12/11/2016.
 */
public class RDFGetter {
    public static String resultString;
    public static void Initialize(String query) {
        Repository repo = new SailRepository(new MemoryStore());
        repo.initialize();
        String baseURI = "http://users.jyu.fi/~miduleph/TIES452/Individuals.owl";
        try {
            RepositoryConnection con = repo.getConnection();
            try {
                //Get the REPO
                URL url = new URL("http://users.jyu.fi/~miduleph/TIES452/Individuals.owl");
                con.add(url, baseURI, RDFFormat.TURTLE);
                con.add(url, url.toString(), RDFFormat.TURTLE);
                //get query here
                try {
//                    String queryString= " SELECT ?Person ?Something ?AnotherPerson WHERE { ?Person ?Something ?AnotherPerson } ";
                    String queryString= query;
                    TupleQuery tupleQuery= con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
                    TupleQueryResult result = tupleQuery.evaluate();
                    try {
                        List<String> bindingNames= result.getBindingNames();
                        resultString = "";
                        while (result.hasNext()) {
                            resultString += "<br/><br/>";
                            BindingSet bindingSet= result.next();
                            for(int i = 0; i <bindingSet.size(); i ++) {
                                Value newValue = bindingSet.getValue(bindingNames.get(i));
                                resultString += newValue.toString() + " ";
                            }
                        }
                    } finally {
                        result.close();
                    }
                } finally {
                    con.close();
                }
            } finally {
                con.close();
            }
        } catch (RDF4JException e) {
        } catch (java.io.IOException e) {
        }
    }

    public static String formatQuery(String query) {
        String returnString = "";
        char[] charArray = query.toCharArray();
        boolean isURL = false;
        for (int i = 0; i < charArray.length; i ++) {
            if (charArray[i] == '<') {
                returnString += "&#60";
                isURL = true;
                continue;
            }

            returnString += charArray[i];
            if (charArray[i] == '>') {
                isURL = false;
                returnString += "<br/>";
            }

            if (charArray[i] == '{') {
                returnString += "<br/>";
            }
            if (!isURL && charArray[i] == '.') {
                returnString += "<br/>";
            }
        }
        return returnString;
    }

    public static String formatNamespace(String result) {
        String baseNamespace = "http://users.jyu.fi/~miduleph/TIES452/Individuals.owl#";
        String ontoNamespace = "http://users.jyu.fi/~miduleph/TIES452/Final_Assignment.owl#";
        String owlNamespace = "http://www.w3.org/2002/07/owl#";
        String xsdNamespace = "http://www.w3.org/2001/XMLSchema#";

        String returnString = result;

        if (result.contains(baseNamespace)) {
            returnString = result.replaceAll(baseNamespace,"\\:");
        }

        if (result.contains(ontoNamespace)) {
            returnString = result.replaceAll(ontoNamespace,"onto\\:");
        }

        if (result.contains(owlNamespace)) {
            returnString = result.replaceAll(owlNamespace,"owl\\:");
        }

        if (result.contains(xsdNamespace)) {
            returnString = result.replaceAll(xsdNamespace,"xsd\\:");
        }

        return returnString;
    }
}
