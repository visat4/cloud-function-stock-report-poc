
### Introduction
This is a poc using google cloud functions inorder to process the incoming stock reports
from stores.

Use case realtime file processing
https://cloud.google.com/functions#section-9

### Development
I have used Java to write the functions in this poc, but you can use other language as well like python, GO, nodejs etc.
In this POC, I have used Spring framework adapter, so we don't have to write boiler plates wiring beans.

#### Spring adapter documentation
https://cloud.spring.io/spring-cloud-function/reference/html/gcp.html#_http_functions

### Implementation
This function app takes a stock report from the HTTP request and puts it on GCS bucket.
In order to access GCS bucket, you need service account credentials, which you have to provide to ```GCSBucketClient```


### Testing in local
Use ```mvn function:run``` to run the function locally and use the preffered REST client to invoke it.

### Package
Use command ```mvn package```

### Deployment
Deploy function app (when not using spring adapter)
```
gcloud functions deploy gcp-demo-function \
--gen2 \
--region=<REGION> \
--runtime=java17 \
--source=. \
--entry-point=<MAIN_CLASS_FILE \
--project=<YOUR_PROJECT_ID> \
--trigger-http
```

Deploy function app when using spring adapter
```
gcloud alpha functions deploy gcp-demo-function \
--entry-point org.springframework.cloud.function.adapter.gcp.GcfJarLauncher \
--gen2 \
--region=<REGION> \
--runtime=java17 \
--source target/deploy \
--project=<YOUR_PROJECT_ID> \
--trigger-http
```