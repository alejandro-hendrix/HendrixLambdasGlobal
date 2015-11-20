package com.hendrix.lambdas.csvImport;

import java.util.Arrays;
import java.util.List;

public class ParticipantParser implements Parser {

	public Participant Parse(String[] columnNames, String[] row) {
		
		List<String> names = Arrays.asList(columnNames);
		String email = getColumnValue(names, "email", row);
		String firstName = getColumnValue(names, "name", row);
		String lastName = getColumnValue(names, "family_name", row);
		
		return new Participant(email, firstName, lastName);
	}
	
	protected String getColumnValue(List<String> columnNames, String columnName, String[] row) {
		int index = columnNames.indexOf(columnName);	
		
		return index >=0 && index < row.length ? row[index] : null;
	}

}
