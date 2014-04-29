package packModelo;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler {

    private static XMLParser mXMLParser = new XMLParser();
    private String texto = null;
    private Character letra = null;
    private Definicion definicionActual = null;

    private TagOperatorFactory tagOperatorFactory = null;

    private XMLParser() {
        tagOperatorFactory = new TagOperatorFactory();
    }

    public static XMLParser getPDF2XMLParser() {
        return mXMLParser;
    }

    public void parseXmlFile(String pFile)
            throws XmlParsingException {
        // Utiliza el patron Factory para obtener la instancia de parser
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            /*
             * Procesa el fichero XML. Le pasamos la instancia
             * que procesa las etiquetas, documento, etc.
             * Tiene que proporcionar los metodos que se implementan
             * debajo
             */
            saxParser.parse(new FileInputStream(pFile), this);
        } catch (Exception e) {
            throw new XmlParsingException(e);
        }
    }

    @Override
    public void characters(char[] pCh, int pStart, int pLength)
            throws SAXException {
        texto = new String(pCh, pStart, pLength).trim();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Final");
    }

    @Override
    public void endElement(String pUri, String pLocalName, String pName)
            throws SAXException {
        TagOperator operator = tagOperatorFactory.getOperator(pName);
        if (operator != null) {
            operator.tratarFinalEtiqueta();
        }
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Inicio");
    }

    @Override
    public void startElement(String pUri, String pLocalName, String pName,
            Attributes pAttributes) throws SAXException {
        System.out.println(pName);
    }

    //Definir el TagOperatorFactory
    private class TagOperatorFactory {

        private Map<String, TagOperator> operators;

        public TagOperatorFactory() {
            operators = new HashMap<String, TagOperator>();
            operators.put("Letra", new LetraTagOperator());
            operators.put("Enunciado", new EnunciadoTagOperator());
            operators.put("Definicion", new DefinicionTagOperator());
            operators.put("Respuesta", new RespuestaTagOperator());
        }

        public TagOperator getOperator(String pName) {
            return operators.get(pName);
        }
    }

    private class LetraTagOperator implements TagOperator {

        @Override
        public void tratarFinalEtiqueta() {
            letra = new Character(texto.charAt(0));
            texto = null;
        }

    }

    private class RespuestaTagOperator implements TagOperator {

        @Override
        public void tratarFinalEtiqueta() {
            definicionActual.addRespuesta(texto);
            texto = null;
        }

    }

    private class EnunciadoTagOperator implements TagOperator {

        @Override
        public void tratarFinalEtiqueta() {
            definicionActual = new Definicion(texto);
            texto = null;
        }

    }

    private class DefinicionTagOperator implements TagOperator {

        @Override
        public void tratarFinalEtiqueta() {
            CatalogoDefiniciones.getCatalogoDefiniciones().addDefinicion(letra, definicionActual);
            letra = null;
            definicionActual = null;
        }

    }
}
