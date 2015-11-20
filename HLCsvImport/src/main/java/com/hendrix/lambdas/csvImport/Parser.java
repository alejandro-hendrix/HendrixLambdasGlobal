package com.hendrix.lambdas.csvImport;

public interface Parser {
	Participant Parse(String[] columnNames, String[] row);
}
