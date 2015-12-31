package at.niw.EnableColorManagement;

import com.intellij.openapi.application.Application;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class ApplicationLoadListener implements com.intellij.ide.ApplicationLoadListener {
  private static final String NATIVE_LIBRARY_FILENAME = "libEnableColorManagement.dylib";

  @Override
  public void beforeApplicationLoaded(@NotNull Application application, @NotNull String s) {
    try {
      File tempFile = createTempFileFromResource(NATIVE_LIBRARY_FILENAME);
      System.load(tempFile.toString());
      load();
    } catch (IOException e) {
      System.err.println("Fail to load a temporary file:" + e);
    }
  }

  private File createTempFileFromResource(String filename) throws IOException {
    File resourceFile = new File("/", filename);
    InputStream resourceInputStream = getClass().getResourceAsStream(resourceFile.toString());

    String prefix, suffix = null;
    int lastIndexOfPrefix = filename.lastIndexOf('.');
    if (lastIndexOfPrefix > 0) {
      prefix = filename.substring(0, lastIndexOfPrefix);
      if (lastIndexOfPrefix + 1 > filename.length()) {
        suffix = filename.substring(lastIndexOfPrefix + 1, 0);
      }
    } else {
      prefix = filename;
    }

    File tempFile = File.createTempFile(prefix, suffix);
    OutputStream tempFileOutputStream = new FileOutputStream(tempFile);

    byte[] buffer = new byte[1024];
    int length;
    while ((length = resourceInputStream.read(buffer)) > 0) {
      tempFileOutputStream.write(buffer, 0, length);
    }
    resourceInputStream.close();
    tempFileOutputStream.close();

    return tempFile;
  }

  private native void load();
}
