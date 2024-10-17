package com.project.pr13;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

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
            allibre.setValue("002");

            //creem fills
            Element eTitol = doc.createElement("titol");
            Text tTitol = doc.createTextNode("El juego de Ender");
            Element eAutor = doc.createElement("autor");
            Text tAutor = doc.createTextNode("Orson Scott Card");
            Element eAny = doc.createElement("anyPublicacio");
            Text tAny = doc.createTextNode("1985");
            Element eEditorial = doc.createElement("editorial");
            Text tEditorial = doc.createTextNode("Tor Books");
            Element eGenere = doc.createElement("genere");
            Text tGenere = doc.createTextNode("Ciencia ficció");
            Element ePagines = doc.createElement("pagines");
            Text tPagines = doc.createTextNode("376");
            Element eDisponible = doc.createElement("disponible");
            Text tDisponible = doc.createTextNode("True");

            //afegim root
            doc.appendChild(eroot);

            //fem el pare
            eLlibre.setAttributeNode(allibre);
            eroot.appendChild(eLlibre);

            //fem els fills
            eTitol.appendChild(tTitol);
            eLlibre.appendChild(eTitol);

            eAutor.appendChild(tAutor);
            eLlibre.appendChild(eAutor);

            eAny.appendChild(tAny);
            eLlibre.appendChild(eAny);

            eEditorial.appendChild(tEditorial);
            eLlibre.appendChild(eEditorial);

            eGenere.appendChild(tGenere);
            eLlibre.appendChild(eGenere);

            ePagines.appendChild(tPagines);
            eLlibre.appendChild(ePagines);

            eDisponible.appendChild(tDisponible);
            eLlibre.appendChild(eDisponible);
            return doc;

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);

        }

    }

    /**
     * Guarda el document XML proporcionat en el fitxer especificat.
     * 
     * @param doc Document XML a guardar.
     * @param fitxerSortida Fitxer de sortida on es guardarà el document.
     */
    private static void guardarDocument(Document doc, File fitxerSortida) {
        // *************** CODI PRÀCTICA **********************/
    }
}
