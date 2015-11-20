package com.hendrix.lambdas.csvImport;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;

public class AmazonS3BucketReaderFactory implements S3BucketReaderFactory {

	private AmazonS3 client;
	private Parser parser;	
	
	public AmazonS3BucketReaderFactory(AmazonS3 client, Parser parser){
		this.client = client;
		this.parser = parser;
	}
	
	public S3BucketReader create(LambdaLogger logger) {
		return new AmazonS3BucketReader(client, parser, logger);
	}
}
