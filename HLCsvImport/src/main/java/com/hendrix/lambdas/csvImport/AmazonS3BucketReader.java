package com.hendrix.lambdas.csvImport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.opencsv.CSVReader;

public class AmazonS3BucketReader implements S3BucketReader {
	
	private LambdaLogger logger;
	private AmazonS3 s3Client;

	private Parser parser;
	public final static String UTF8EncodingFormat = "UTF-8";

	public AmazonS3BucketReader(AmazonS3 s3Client, Parser parser, LambdaLogger logger){
		this.logger = logger;	
		this.s3Client = s3Client;
		this.parser = parser;
	}

	public List<Participant> getParticipants(S3EventNotificationRecord notificationRecord) 
			throws IOException {
		
		String sourceBucket = notificationRecord.getS3().getBucket().getName();
		logger.log("bucket : " + sourceBucket);
		
		String sourceKey = getSourceKey(notificationRecord, UTF8EncodingFormat);
		logger.log("received : " + sourceKey);
		
		S3Object s3Object = s3Client.getObject(new GetObjectRequest(sourceBucket, sourceKey));
		List<Participant> results = new ArrayList<Participant>();
				
		
		//CSVReader csvReader = createCSVReader(s3Object.getObjectContent());
		 CSVReader csvReader = new CSVReader( new BufferedReader(new InputStreamReader(s3Object.getObjectContent(), UTF8EncodingFormat))); 
		try {
			
			
			String[] columnNames = csvReader.readNext();
			verifyMandatoryColumns(columnNames);
			
			String[] row = null;		
		
			while ((row = csvReader.readNext()) != null) {
				results.add(parser.Parse(columnNames, row));
			}
		}
		finally{
			csvReader.close();
		}
		
		return results; 
	}
	
	
	
	protected String getSourceKey(S3EventNotificationRecord record, String encodingFormat) 
			throws UnsupportedEncodingException {
		
		String sourcekey = record.getS3().getObject().getKey().replace('+', ' ');
		return URLDecoder.decode(sourcekey, encodingFormat);	
	}
	
	protected void verifyMandatoryColumns(String[] columnNames) {
		// TODO Auto-generated method stub		
	}
}
