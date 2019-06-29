package main.java;

public interface IAppOptions {
    void parseOptions() throws Exception;

    String getFilePath();

}
