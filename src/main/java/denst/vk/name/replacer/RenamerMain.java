package denst.vk.name.replacer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RenamerMain {

    public static void main(String[] args) {
        SpringApplication.run(RenamerMain.class, args);

        String folderName = "C:\\Users\\dstorozh\\Downloads\\test";
        new VkAudioFileRenamer().renameFiles(folderName);

    }
}
