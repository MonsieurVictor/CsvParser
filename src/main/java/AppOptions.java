package main.java;

import java.io.File;


public class AppOptions implements IAppOptions {

    private String filePath;

    public String getFilePath() {
        return this.filePath;
    }

    private AppOptions setFilePath() {

        File folder = new File("files");
        File[] listOfFiles = folder.listFiles();
        System.out.println("The .csv found in folder 'files':");

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                }
            }
            this.filePath = "files" + File.separatorChar + listOfFiles[0].getName();

        System.out.println("\nThe file " + this.filePath + " was selected\n");
        return this;
    }

    /**
     * most full command format: <filePath> -s -f -w <ignoreListFilePath>
     * @return
     */
    public void parseOptions() throws Exception {
        this
                .setFilePath();
    }
}

