package com.hanry.minilibrary.xml;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.hanry.minilibrary.model.DiaryEntry;

public class DiaryXML {
    
    public static void exportToXML(List<DiaryEntry> entries, String filename) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            Element root = doc.createElement("diary");
            doc.appendChild(root);
            
            for (DiaryEntry entry : entries) {
                Element entryElement = doc.createElement("entry");
                
                Element idElement = doc.createElement("id");
                idElement.setTextContent(String.valueOf(entry.getId()));
                entryElement.appendChild(idElement);
                
                Element titleElement = doc.createElement("title");
                titleElement.setTextContent(entry.getTitle());
                entryElement.appendChild(titleElement);
                
                Element timestampElement = doc.createElement("timestamp");
                timestampElement.setTextContent(entry.getTimestamp().toString());
                entryElement.appendChild(timestampElement);
                
                Element moodElement = doc.createElement("mood");
                moodElement.setTextContent(entry.getMood());
                entryElement.appendChild(moodElement);
                
                Element contentElement = doc.createElement("content");
                contentElement.setTextContent(entry.getContent());
                entryElement.appendChild(contentElement);
                
                root.appendChild(entryElement);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<DiaryEntry> importFromXML(String filename) {
        List<DiaryEntry> entries = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filename));
            
            NodeList entryNodes = doc.getElementsByTagName("entry");
            for (int i = 0; i < entryNodes.getLength(); i++) {
                Element entryElement = (Element) entryNodes.item(i);
                
                int id = Integer.parseInt(getElementText(entryElement, "id"));
                String title = getElementText(entryElement, "title");
                LocalDateTime timestamp = LocalDateTime.parse(getElementText(entryElement, "timestamp"));
                String mood = getElementText(entryElement, "mood");
                String content = getElementText(entryElement, "content");
                
                entries.add(new DiaryEntry(id, title, timestamp, mood, content));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entries;
    }
    
    private static String getElementText(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
} 