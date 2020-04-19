Converts information provided in CSV format into JSON format and vice-versa.

## Usage - ##

`TestFormat testFormatJSON = CSV_JSON_ADAPTER.fromJSON(new FileReader(new File(directory, "data.json")));
TestFormat testFormatCSV = CSV_JSON_ADAPTER.fromCSV(new FileReader(new File(directory, "data.csv")));`

`try (Writer jsonW = new StringWriter(); Writer csvW = new StringWriter()) {
   CSV_JSON_ADAPTER.asCSVInto(testFormat, csvW);
  CSV_JSON_ADAPTER.asJSONInto(testFormat, jsonW);
}`
            
            