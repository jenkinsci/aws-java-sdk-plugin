package com.amazonaws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ecr.AmazonECRClient;
import jenkins.model.Jenkins;

/**
 * Factory to use Jenkins Proxy configuration with a AmazonECRClient.
 *
 * @author Marcus Wallin, kuisathaverat
 */
public class AmazonECRClientFactory {

    /**
     * @param credentials {@link AWSCredentials} to use.
     * @return {@link AmazonECRClient} configured to use the proxy configured on Jenkins.
     */
    public AmazonECRClient getAmazonECRClientWithProxy(AWSCredentials credentials) {
        ClientConfiguration conf = new ClientConfiguration();
        Jenkins j = Jenkins.getInstance();
        if (j != null && j.proxy != null) {
            conf.setProxyHost(j.proxy.name);
            conf.setProxyPort(j.proxy.port);
            conf.setProxyUsername(j.proxy.getUserName());
            conf.setProxyPassword(j.proxy.getPassword());
        }
        return new AmazonECRClient(credentials, conf);
    }
}
