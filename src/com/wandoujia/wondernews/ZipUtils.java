package com.wandoujia.wondernews;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

public class ZipUtils {
    public ZipUtils() {
        
    }
    public void unzipArchive(File archive, File outputDir) {
        try {
            ZipFile zipfile = new ZipFile(archive);
            for (Enumeration e = zipfile.getEntries(); e.hasMoreElements();) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                unzipEntry(zipfile, entry, outputDir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unzipEntry(ZipFile zipfile, ZipEntry entry, File outputDir)
            throws IOException {

        if (entry.isDirectory()) {
            createDir(new File(outputDir, entry.getName()));
            return;
        }

        File outputFile = new File(outputDir, entry.getName());
        if (!outputFile.getParentFile().exists()) {
            createDir(outputFile.getParentFile());
        }

        Logger.d("Extracting: " + entry);
        BufferedInputStream inputStream = new BufferedInputStream(zipfile
                .getInputStream((ZipArchiveEntry) entry));
        BufferedOutputStream outputStream = new BufferedOutputStream(
                new FileOutputStream(outputFile));

        try {
            IOUtils.copy(inputStream, outputStream);
        } finally {
            outputStream.close();
            inputStream.close();
        }
    }

    private void createDir(File dir) {
        dir.setWritable(true);
        if (!dir.mkdirs())
            throw new RuntimeException("Can not create dir " + dir);
        
    }
}
