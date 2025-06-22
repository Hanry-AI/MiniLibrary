package com.hanry.minilibrary.xml;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

public final class DiaryXML {

    private DiaryXML() {}

    /* ---------- EXPORT ---------- */
    public static void exportXml(List<DiaryEntry> list, Path file) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = doc.createElement("entries");
        doc.appendChild(root);

        for (DiaryEntry e : list) {
            Element entry = doc.createElement("entry");
            entry.setAttribute("id", String.valueOf(e.getId()));
            entry.setAttribute("title", e.getTitle());
            entry.setAttribute("timestamp", e.getTimestamp().toString());
            entry.setAttribute("mood", e.getMood());

            Element content = doc.createElement("content");
            content.appendChild(doc.createCDATASection(e.getContent() == null ? "" : e.getContent()));
            entry.appendChild(content);

            root.appendChild(entry);
        }
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(doc), new StreamResult(file.toFile()));
    }

    /* ---------- IMPORT ---------- */
    public static List<DiaryEntry> importXml(Path file) throws Exception {
        List<DiaryEntry> list = new ArrayList<>();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file.toFile());
        NodeList nList = doc.getElementsByTagName("entry");
        for (int i = 0; i < nList.getLength(); i++) {
            Element e = (Element) nList.item(i);
            int id = Integer.parseInt(e.getAttribute("id"));
            String title = e.getAttribute("title");
            LocalDateTime timestamp = LocalDateTime.parse(e.getAttribute("timestamp"));
            String mood = e.getAttribute("mood");
            String content = e.getElementsByTagName("content").item(0).getTextContent();
            DiaryEntry d = new DiaryEntry(id, title, timestamp, mood, content);
            list.add(d);
        }
        return list;
    }
} 