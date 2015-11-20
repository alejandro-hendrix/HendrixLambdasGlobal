package com.hendrix.lambdas.csvImport;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public interface S3BucketReaderFactory {
	S3BucketReader create(LambdaLogger logger);
}
