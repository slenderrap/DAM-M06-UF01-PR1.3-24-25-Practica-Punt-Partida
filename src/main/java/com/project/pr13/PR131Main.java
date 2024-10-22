package com.project.pr13;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe principal que crea un document XML amb informació de llibres i el guarda en un fitxer.
 * 
 * Aquesta classe permet construir un document XML, afegir elements i guardar-lo en un directori
 * especificat per l'usuari.
 */
public class PR131Main {

    private File dataDir;

    /**
     * Constructor de la classe PR131Main.
     * 
     * @param dataDir Directori on es guardaran els fitxers de sortida.
     */
    public PR131Main(File dataDir) {
        this.dataDir = dataDir;
    }

    /**
     * Retorna el directori de dades actual.
     * 
     * @return Directori de dades.
     */
    public File getDataDir() {
        return dataDir;
    }

    /**
     * Actualitza el directori de dades.
     * 
     * @param dataDir Nou directori de dades.
     */
    public void setDataDir(File dataDir) {
        this.dataDir = dataDir;
    }

    /**
     * Mètode principal que inicia l'execució del programa.
     * 
     * @param args Arguments passats a la línia de comandament (no s'utilitzen en aquest programa).
     */
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        File dataDir = new File(userDir, "data" + File.separator + "pr13");

        PR131Main app = new PR131Main(dataDir);
        app.processarFitxerXML("biblioteca.xml");
    }

    /**
     * Processa el document XML creant-lo, guardant-lo en un fitxer i comprovant el directori de sortida.
     * 
     * @param filename Nom del fitxer XML a guardar.
     */
    public void processarFitxerXML(String filename) {
        if (comprovarIDirCrearDirectori(dataDir)) {
            Document doc = construirDocument();
            File fitxerSortida = new File(dataDir, filename);
            guardarDocument(doc, fitxerSortida);
        }
    }

    /**
     * Comprova si el directori existeix i, si no és així, el crea.
     * 
     * @param directori Directori a comprovar o crear.
     * @return True si el directori ja existeix o s'ha creat amb èxit, false en cas contrari.
     */
    private boolean comprovarIDirCrearDirectori(File directori) {
        if (!directori.exists()) {
            return directori.mkdirs();
        }
        return true;
    }

    /**
     * Crea un document XML amb l'estructura d'una biblioteca i afegeix un llibre amb els seus detalls.
     * 
     * @return Document XML creat o null en cas d'error.
     */
    private static Document construirDocument() {
        Document doc = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.newDocument();

            //node arrel
            Element eroot = doc.createElement("biblioteca");

            //node pare
            Element eLlibre = doc.createElement("llibre");

            //creem id
            Attr allibre = doc.createAttribute("id");

            //asignem ID
            allibre.setValue("001");

            //creem fills
            Element eTitol = doc.createElement("titol");
            eTitol.setTextContent("El viatge dels venturons");

            Element eAutor = doc.createElement("autor");
            eAutor.setTextContent("Joan Pla");
            Element eAny = doc.createElement("anyPublicacio");
            eAny.setTextContent("1998");
            Element eEditorial = doc.createElement("editorial");
            eEditorial.setTextContent("Edicions Mar");
            Element eGenere = doc.createElement("genere");
            eGenere.setTextContent("Aventura");
            Element ePagines = doc.createElement("pagines");
            ePagines.setTextContent("320");
            Element eDisponible = doc.createElement("disponible");
            eDisponible.setTextContent("true");



            //fem el pare
            eLlibre.setAttributeNode(allibre);

            //fem els fills
            eLlibre.appendChild(eTitol);

            eLlibre.appendChild(eAutor);

            eLlibre.appendChild(eAny);

            eLlibre.appendChild(eEditorial);

            eLlibre.appendChild(eGenere);

            eLlibre.appendChild(ePagines);

            eLlibre.appendChild(eDisponible);


            eroot.appendChild(eLlibre);
            //afegim root
            doc.appendChild(eroot);
            return doc;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();

        }

        return doc;
    }

    /**
     * Guarda el document XML proporcionat en el fitxer especificat.
     * 
     * @param doc Document XML a guardar.
     * @param fitxerSortida Fitxer de sortida on es guardarà el document.
     */
    private static void guardarDocument(Document doc, File fitxerSortida) {
        if (!fitxerSortida.exists()){
            try {
                if (fitxerSortida.createNewFile()){
                    System.out.println("s'ha generat correctament l'arxiu");
                }
                else {

                    System.out.println("No s'ha pogut generar");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try{

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
            t.setOutputProperty(OutputKeys.INDENT,"yes");


            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(fitxerSortida,false));
            t.transform(source,result);
            System.out.println("S'ha guardat l'arxiu");

        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
