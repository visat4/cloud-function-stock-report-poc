package com.vigneshsn.gcsbucket_client;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class GCSBucketClient {
    public static final String PROJECT_ID = "<YOUR_PROJECT_ID>";
    private final Storage storage;
    private Bucket bucket;

    private final String bucketName = "stock-reports-16022023";
    private final String serviceCredentialsFile = "<SERVICE_CREDENTIAL_FILE_NAME>";

    public GCSBucketClient()  {
        Resource resource = new ClassPathResource(serviceCredentialsFile);
        try(InputStream is = resource.getInputStream()) {
            Credentials credentials = GoogleCredentials.fromStream(is);
            storage = StorageOptions.newBuilder()
                    .setProjectId(PROJECT_ID)
                    .setCredentials(credentials)
                    .build().getService();

            bucket = storage.get(bucketName);
            if( !bucket.exists() ) {
                bucket = storage.create(BucketInfo.of(bucketName));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("not able to connect to gcs bucket === "+ ex.getMessage());
        }
    }

    public void save(String bucketId, String data) {
        bucket.create(bucketId, data.getBytes(Charset.forName("UTF-8")));
        System.out.println("successfully saved "+ bucketId);
    }

    @SneakyThrows
    public String get(String key)  {
        byte[] data = bucket.get(key).getContent();
        return new String(data);
    }
}
