Converts information provided in CSV format into JSON format and vice-versa.

## Usage (App jar level)- ##

`java -cp YmlToJSONConverter-1.0.jar:lib/* main.java.hrk.timeline.App YML /data/yaml /data `



# YAML File Format #

``` 
title: string]
headers: 
- title: [string]
  dateFrom: [yyyy-mm-dd]
  dateTo:  [yyyy-mm-dd]
  texts:[array of strings]
  subHeaders: 
  - title: [string]
  dateFrom: [yyyy-mm-dd]
  dateTo:  [yyyy-mm-dd]
  texts:
    - arr[0]
    - arr[1]

```
