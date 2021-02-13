package com.example.helloworld;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.ContextWrapper;
import android.os.Environment;
import java.io.InputStreamReader;
import android.util.Log;
import android.app.ProgressDialog;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.File;
import java.net.URLConnection;

public class DownloadFile extends AsyncTask<String, Integer, String> {

    private Context mContext;
    String fileName;
    static int filecount  = 0;
    public DownloadFile(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... sUrl) {

// works for text file
//        try {
//            FileOutputStream fileout=mContext.openFileOutput("mytextfile.txt", Context.MODE_PRIVATE);
//            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
//            outputWriter.write("Hello!!!!");
//            outputWriter.close();
//
//            //display file saved message
//            Toast.makeText(mContext, "File saved successfully!",Toast.LENGTH_SHORT).show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        InputStream input = null;
//        OutputStream output = null;
        FileOutputStream fileout = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(sUrl[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
            }
//            Toast.makeText(mContext, "Connected successfully!",Toast.LENGTH_SHORT).show();
//            fileout = mContext.openFileOutput("mydownloadedfile.mp3", Context.MODE_PRIVATE);
            int fileLength = connection.getContentLength();
            // download the file
            input = connection.getInputStream();

//            output = new FileOutputStream("/data/data/com.example.vadym.test1/textfile.txt");
//            output = new FileOutputStream(mContext.getFilesDir() + "/file.mp4");
//            Toast.makeText(mContext, "Beginning Download!",Toast.LENGTH_SHORT).show();
             fileout = mContext.openFileOutput("mydownloadedfile.mp3", Context.MODE_PRIVATE);

            //display file saved message


            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
                    publishProgress((int) (total * 100 / fileLength));
//                output.write(data, 0, count);
                fileout.write(data,0,count);
            }
//                        Toast.makeText(mContext, "File saved successfully!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();

        } finally {
            try {
                if (fileout != null)
                    fileout.close();
//                    output.close();

                if (input != null)
                    input.close();
            } catch (IOException e) {
                e.printStackTrace();
            System.out.println(e);
            }

            if (connection != null)
                connection.disconnect();
        }

//        String fileName = "downloaded_file.mp3";
//        File op = new File(context.getFilesDir(), fileName);
//        File dir = new File(op.getAbsoluteFile()+"/TRIALS");
//        if(!dir.exists()){
//            dir.mkdir();
//        }
//        File destination = new File(dir+ "/" +"trail" + ".mp3", fileName);
//        if (destination.exists()) {
//            destination.delete();
//        }
//        try {
//            destination.createNewFile();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////
////
//        InputStream input = null;
//        OutputStream output = null;
//        FileOutputStream fos =null;
//        HttpURLConnection connection = null;
//        try {
//            URL url = new URL(sUrl[0]);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.connect();
//
//            // expect HTTP 200 OK, so we don't mistakenly save error report
//            // instead of the file
//            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
//                return "Server returned HTTP " + connection.getResponseCode()
//                        + " " + connection.getResponseMessage();
//            }
//
//            // this will be useful to display download percentage
//            // might be -1: server did not report the length
//            int fileLength = connection.getContentLength();
//
//            // download the file
//            input = connection.getInputStream();
//         //   InputStreamReader reader = new InputStreamReader(input);
////            output = new FileOutputStream(mTargetFile,false);
//
////            output = new FileOutputStream(destination.getPath());
////         fos = context.openFileOutput("d1.mp3", Context.MODE_PRIVATE);
////         output =fos;
//         fos = context.openFileOutput("d1.mp3", Context.MODE_PRIVATE);
//
//
//            byte data[] = new byte[4096];
//            long total = 0;
//            int count = -1 ;
//            while ((count = input.read(data)) != -1) {
////                while ((count = reader.read()) != -1) {
//                // allow canceling with back button
//                if (isCancelled()) {
//                    input.close();
////                    reader.close();
//                    return null;
//                }
//                total += count;
//                // publishing the progress....
//                if (fileLength > 0) // only if total length is known
//                    publishProgress((int) (total * 100 / fileLength));
////                output.write(data, 0, count);
//                fos.write(data, 0, count);
//            }
//        } catch (Exception e) {
//            return e.toString();
//        } finally {
//            try {
////                if (output != null)
////                    output.close();
//                if (fos != null)
//                    fos.close();
//                if (input != null)
//                    input.close();
//            } catch (IOException ignored) {
//            }
//
//            if (connection != null)
//                connection.disconnect();
//        }


//        try {
//            URL ul = new URL(sUrl[0]);
//            Log.i("UI",ul.toString());
//            //   int len = CopyOfMusicDownloader.mp3urls.size();
//            //   URL url2 = new URL(CopyOfMusicDownloader.mp3urls.get(len-1));
//            HttpURLConnection c = (HttpURLConnection) ul.openConnection();
//            c.setRequestMethod("GET");
//            c.setDoOutput(true);
//            c.connect();
//            Log.v("connect", "Connected to Internet");
//            int lengthOfFile = c.getContentLength();
//
//            String PATH = Environment.getExternalStorageDirectory()
//                    + "/download/";
//            Log.v("", "PATH: " + PATH);
//            File file = new File(PATH);
//            file.mkdirs();
//
//            fileName = "Track";
//            filecount++;
//
//            fileName =  fileName + Integer.toString(filecount) + ".mp3";
//
//            File outputFile = new File(file, fileName);
//            FileOutputStream fos = new FileOutputStream(outputFile);
//            InputStream is = c.getInputStream();
//
//            byte[] buffer = new byte[1024];
//            int len1 = 0;
//            while ((len1 = is.read(buffer)) != -1) {
////                myProgress = (int)(len1*100/lengthOfFile);
//                //myProgress = (int)(len1);
//
////                Log.i("My Progress", Integer.toString(myProgress));
////                publishProgress(myProgress);
//                //  publishProgress((int)(len1*100/lengthOfFile));
//                fos.write(buffer, 0, len1);
//            }
//            fos.close();
//            is.close();
//
//        }catch (IOException e) {
//            e.printStackTrace();
//            System.out.println(e);
//        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("ptg", "onProgressUpdate: " + values[0]);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}
