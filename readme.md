

Deploy function app

```
gcloud functions deploy gcp-demo-function \
--gen2 \
--region=us-central1 \
--runtime=java17 \
--source=. \
--entry-point=com.vigneshsn.Main \
--project=corded-streamer-377516 \
--trigger-http
```


Function app with spring adapter
```
gcloud alpha functions deploy gcp-demo-function \
--entry-point org.springframework.cloud.function.adapter.gcp.GcfJarLauncher \
--gen2 \
--region=us-central1 \
--runtime=java17 \
--source target/deploy \
--project=corded-streamer-377516 \
--trigger-http
```