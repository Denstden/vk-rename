package denst.vk.name.replacer;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class VkAudioFileRenamer {
    public void renameFiles(String folderName) {
        File folder = new File(folderName);
        for (File file : folder.listFiles()) {
            try {

                InputStream input = new FileInputStream(file);
                ContentHandler handler = new DefaultHandler();
                Metadata metadata = new Metadata();
                Parser parser = new Mp3Parser();
                ParseContext parseCtx = new ParseContext();
                parser.parse(input, handler, metadata, parseCtx);
                input.close();

                // List all metadata
                String[] metadataNames = metadata.names();

                for (String name : metadataNames) {
                    System.out.println(name + ": " + metadata.get(name));
                }

                String title = metadata.get("title");
                String author = metadata.get("xmpDM:artist");

                if (title != null && title.trim().length() != 0 && author != null && author.trim().length() != 0) {
                    File newFile = new File(file.getParent(), author + " - " + title + ".mp3");
                    Files.move(file.toPath(), newFile.toPath());
                }

            } catch (IOException | SAXException | TikaException e) {
                e.printStackTrace();
            }
        }
    }
}
