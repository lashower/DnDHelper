package com.lashower.dd.audio;

import io.swagger.client.ApiException;
import io.swagger.client.api.ConversionApi;
import io.swagger.client.api.JobsApi;
import io.swagger.client.api.OutputApi;
import io.swagger.client.api.UploadApi;
import io.swagger.client.model.Conversion;
import io.swagger.client.model.InputFile;
import io.swagger.client.model.Job;
import io.swagger.client.model.OutputFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import oc.api.sample.OcApiSample;

public class AudioTest extends Application {
	
	private ArrayList<AudioClip> clips = new ArrayList<AudioClip>();

	@Override
	public void start(Stage primaryStage) throws Exception {
//        File f = new File("C:/Users/IBM_ADMIN/Music/Game");
      File f = new File("//I7920/d/DnD/Audio");
      AudioClip c = new AudioClip("https://youtu.be/nUKya2DvYSo");
      c.play();
//      File f = new File("C:/Users/IBM_ADMIN/Music/Focus");
//        File[] fs = f.listFiles();
//		for(int i = 0; i < fs.length; i++)
//		{
//			try {
//				if(!fs[i].isHidden())
//				{
//					if(!fs[i].getName().endsWith("wav"))
//					{
//						downloadFromUrl(convertAudio(fs[i],"wav"),fs[i].getPath().substring(0, fs[i].getPath().length()-3) + "wav");
//						System.out.println(fs[i].getPath().substring(0, fs[i].getPath().length()-3) + "wav");
//					}
//					if((fs[i].length()/1024/1024) < 50)
//					{
//						clips.add(new AudioClip(fs[i].toURI().toString()));
//					}
//				}
//			} catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//		final Button start = new Button("Start");
//		start.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				Iterator<AudioClip> cit = clips.iterator();
//				while(cit.hasNext())
//				{
//					AudioClip n = cit.next();
//					n.play(1.0);
//				}
//				
//			}
//		});
//		
//		final Button stop = new Button("Stop");
//		stop.setOnAction(new EventHandler<ActionEvent>() {
//			
//			@Override
//			public void handle(ActionEvent event) {
//				Iterator<AudioClip> cit = clips.iterator();
//				while(cit.hasNext())
//				{
//					cit.next().stop();
//				}
//			}
//		});
//
//		
//		final FlowPane flowPane = new FlowPane();
//		flowPane.setPadding(new Insets(10));
//		flowPane.getChildren().add(start);
//		flowPane.getChildren().add(stop);
//		
//		final Scene scene = new Scene(flowPane, 200, 200);
//		
//		primaryStage.setTitle("Basic AudioClip Example");
//		primaryStage.setScene(scene);
//		primaryStage.show();
	}
	
	private void downloadFromUrl(String convertAudio, String string) {
	}

	public static void main(String[] args)
	{
		launch(args);
	}
	
	public static File convertAudio(String file, String convertTo) throws ApiException
	{
		return convertAudio(new File(file), convertTo);
	}
	
	public static File convertAudio(File oldFile, String convertTo) throws ApiException
	{

        System.out.println("Uploading file");

        Job param = new Job();

        // API Post call to create new job as per parameters
        JobsApi jobsApi  = new JobsApi();
        Job job = jobsApi.jobsPost(OcApiSample.API_KEY, param);

        // API Post call to file upload
        UploadApi uploadApi = new UploadApi();
		InputFile inputFile = uploadApi.filePost(job.getServer(), job.getToken(), job.getId(), oldFile.getPath());
		System.out.println(inputFile.getType());

        job = jobsApi.jobsJobIdGet(null, OcApiSample.API_KEY, job.getId());

        // Create conversion object parameter
        Conversion conversionParam = new Conversion();
        conversionParam.setTarget(convertTo);

        // API call to assign conversion object to created job
        ConversionApi conversionApi = new ConversionApi();
        @SuppressWarnings("unused")
		Conversion conversion = conversionApi.jobsJobIdConversionsPost(conversionParam, null, OcApiSample.API_KEY, job.getId());

        job = jobsApi.jobsJobIdGet(null, OcApiSample.API_KEY, job.getId());

        loop: while (true) {
            switch (job.getStatus().getCode()) {
                case "queued":
                case "downloading":
                case "pending":
                case "processing":
                    job = jobsApi.jobsJobIdGet(null, OcApiSample.API_KEY, job.getId());
                    break;
                case "completed":
                    OutputApi outputApi = new OutputApi();
                    List<OutputFile> output = outputApi.jobsJobIdOutputGet(null, null, null, OcApiSample.API_KEY, job.getId());
                    System.out.println("File converted successfully.");
//                    File newFile = new File(inputFile.getType());
//                    return downloadFromUrl(output.get(0).getUri(),new File(""));
                    return new File("");
                default:
                    break loop;
            }
        }
        return null;
	}
	
	public static void downloadFromUrl(String url, File localFile) throws IOException {
		System.out.println("Downloading from " + url);
		System.out.println("Saving to " + localFile.getPath());
		HttpURLConnection.setFollowRedirects(true);
	    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
	    conn.setInstanceFollowRedirects(true);
	    
	    boolean redirect = true;
	    while(redirect)
	    {
	    	int status = conn.getResponseCode();
	    	if (status != HttpURLConnection.HTTP_OK) {
	    		if (status == HttpURLConnection.HTTP_MOVED_TEMP
	    			|| status == HttpURLConnection.HTTP_MOVED_PERM
	    				|| status == HttpURLConnection.HTTP_SEE_OTHER)
	    		redirect = true;
	    	} else {
	    		redirect = false;
	    		break;
	    	}
	    	String newUrl = conn.getHeaderField("Location");
	    	System.out.println("Redirecting " + newUrl);
	    	conn = (HttpURLConnection) new URL(newUrl).openConnection();
	    }
	    System.out.println(conn.getResponseCode());
	    BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
	    conn.connect();
	
	    BufferedOutputStream outstream = new BufferedOutputStream(new FileOutputStream(localFile));
	    byte[] buffer = new byte[4096];
	    int len;
	    while ((len = is.read(buffer)) > 0) {
	        outstream.write(buffer, 0, len);
	    }
	    outstream.close();
	    System.out.println("File Size: " + localFile.length());
	}

}