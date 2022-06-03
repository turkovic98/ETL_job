# ETL_job

## How to run the application

Application is written using Java Spring Boot (Java version 11).

1. After cloning this repository, project should be opened with IntelliJ
2. Download all sources using Maven and reload all Maven projects
3. Initialize & start shifts API and target Postgres database in the background
with

```bash
$ docker-compose up -d
```
4. In IntelliJ run EtlJobApplication 
5. The application has finished with all data manipulation when all KPI values are printed in the console.

## Solution Explained

On application startup two methods are called:
1. **getShifts** - calls Shifts API, extracts models and and inserts them into corresponding Postgres tables (logic is written inside **ETLJobService** class)
2. **calculateKPIs** - using extracted shifts data, this method calculates required KPI values and saves them in database (logic is written inside **KPIService** class) 
