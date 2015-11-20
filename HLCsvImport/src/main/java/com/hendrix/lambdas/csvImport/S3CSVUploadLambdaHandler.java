package com.hendrix.lambdas.csvImport;

import java.io.IOException;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sns.*;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNS;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;

public class S3CSVUploadLambdaHandler implements RequestHandler<S3Event, Boolean> {

	private static String accessKey = "AKIAI36Z3IWT7IQAUVWA";
	private static String secretKey = "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";
	private S3BucketReaderFactory s3BucketReaderFactory;
		
	public S3CSVUploadLambdaHandler(S3BucketReaderFactory factory){
		this.s3BucketReaderFactory = factory; 
	}
	
	public S3CSVUploadLambdaHandler(){
		this(new AmazonS3BucketReaderFactory(
				new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey), 
									new ClientConfiguration().withProtocol(Protocol.HTTP)), 
				new ParticipantParser()));
	}
	

    public Boolean handleRequest(S3Event input, Context context) {
		try {
			
			LambdaLogger logger = context.getLogger();
			S3BucketReader bucketReader = s3BucketReaderFactory.create(logger);
			
			/*AmazonSNSClient snsClient = new AmazonSNSClient(new ClasspathPropertiesFileCredentialsProvider());
			AmazonSQSClient sqsClient = new  AmazonSQSClient();
			sqsClient.createQueue("pa*/
					
			for (S3EventNotificationRecord record : input.getRecords()) {
								
				for (Participant participant : bucketReader.getParticipants(record)) {
					logger.log("Imported participant : "+
								participant.getEmail()  + " (" +participant.getFullName() + ")");
				}
			}		
			
			return true;
					
		 } catch (IOException e) {
			 throw new RuntimeException(e);
	     }	
    }

}
