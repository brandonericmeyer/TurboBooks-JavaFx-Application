package com.turbobooks.model.services.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.turbobooks.model.business.exception.PropertyFileNotFoundException;

/**
 * 
 * @author brandonmeyer
 *
 */
public class SAXPropertyManager extends DefaultHandler {

	static Logger log = Logger.getLogger("com.turbobookslog4jsample");

	private static Properties properties;

	private StringBuffer buffer;

	private String interfaceClass;

	private String implementationClass;

	private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
	private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	/**
	 * 
	 * @param propertyFileLocation
	 * @throws PropertyFileNotFoundException
	 */
	public void loadProperties(String propertyFileLocation) throws PropertyFileNotFoundException {

		log.info("------------------------------------------");
		log.info("Loading Properties File via SAX");
		log.info("------------------------------------------");

		DefaultHandler handler = this;

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			factory.setValidating(true);

			buffer = new StringBuffer();

			SAXParser saxParser = factory.newSAXParser();
			saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			saxParser.setProperty(JAXP_SCHEMA_SOURCE, new File(propertyFileLocation + "ApplicationProperties.xsd"));

			File file = new File(propertyFileLocation + "application.properties.xml");
			InputStream inputStream = new FileInputStream(file);
			Reader reader = new InputStreamReader(inputStream, "UTF-8");

			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");
			saxParser.parse(is, handler);
		} catch (SAXParseException spe) {
			log.info("\n** Parsing error" + ", line " + spe.getLineNumber() + ", uri " + spe.getSystemId());
			log.info("   " + spe.getMessage());

			Exception x = spe;
			if (spe.getException() != null) {
				x = spe.getException();
			}
			log.error(x);

		} catch (SAXException sxe) {
			Exception x = sxe;
			if (sxe.getException() != null) {
				x = sxe.getException();
			}
			log.error(x);

		} catch (ParserConfigurationException pce) {
			log.error(pce);

		} catch (IOException ioe) {
			log.error(ioe);
		}

		log.info("------------------------------------------");
		log.info("Loading Properties File via SAX Completed");
		log.info("------------------------------------------");

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrib) throws SAXException {
		buffer.setLength(0);

		if (qName.equals("applicationproperties")) {
			log.debug("\t<applicationproperites> tag encountered");

			properties = new Properties();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("url")) {
			String url = buffer.toString().trim();
			log.debug("\t\t\t<url>: " + url);
			properties.setProperty("jdbc.url", url);
		}

		if (qName.equals("user")) {
			String user = buffer.toString().trim();
			log.debug("\t\t\t<user>: " + user);
			properties.setProperty("jdbc.user", user);
		}

		if (qName.equals("password")) {
			String password = buffer.toString().trim();
			log.debug("\t\t\t<password>: " + password);
			properties.setProperty("jdbc.password", password);
		}

		if (qName.equals("interface")) {
			interfaceClass = buffer.toString().trim();
			log.debug("\t\t\t\t<interface>: '" + interfaceClass + "'");
		}

		if (qName.equals("implementation")) {
			implementationClass = buffer.toString().trim();
			log.debug("\t\t\t\t<implementation>: " + implementationClass);
		}

		if (qName.equals("service")) {
			log.debug("\t\t\t<service> end tag encountered");
			properties.setProperty(interfaceClass, implementationClass);
		}

		if (qName.equals("servicemapping")) {
			log.debug("\t\t</servicemapping> end tag encountered");
		}

		if (qName.equals("applicationproperties")) {
			log.debug("</applicationproperties> end tag encountered");
			log.info("Property File after SAX Parsing : \n" + properties.toString());
		}
		buffer.setLength(0);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		buffer.append(ch, start, length);
	}

	@Override
	public void error(SAXParseException e) throws SAXParseException {
		throw e;
	}

	@Override
	public void warning(SAXParseException err) throws SAXParseException {
		log.info("** Warning" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
		log.info("   " + err.getMessage());
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	static public String getPropertyValue(String key) {
		return properties.getProperty(key);
	}

}
