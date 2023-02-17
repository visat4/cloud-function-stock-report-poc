

Deploy function app

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


Function app with spring adapter
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