package com.hendrix.lambdas.csvImport;

import java.io.IOException;
import java.util.List;

import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;

public interface S3BucketReader {
	
	public List<Participant> getParticipants(S3EventNotificationRecord notificationRecord) throws IOException;
}

