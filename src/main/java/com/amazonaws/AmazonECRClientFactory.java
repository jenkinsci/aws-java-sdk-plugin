package com.amazonaws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ecr.AmazonECRClient;
import hudson.ProxyConfiguration;
import jenkins.model.Jenkins;

/**
 * Factory to use Jenkins Proxy configuration with a AmazonECRClient.
 *
 * @author Marcus Wallin, kuisathaverat
 */
public class AmazonECRClientFactory {
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
            value="NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE",
            justification="The null value it is checked")
    private final static class ClientConfigurationWrapper extends ClientConfiguration {
        public ClientConfigurationWrapper() {
            super();
            Jenkins j = Jenkins.getInstance();
            if (j != null && j.proxy != null) {
                setProxyHost(j.proxy.name);
                setProxyPort(j.proxy.port);
                setProxyUsername(j.proxy.getUserName());
                setProxyPassword(j.proxy.getPassword());
            }
        }

    }

    /**
     * @return {@link AmazonECRClient} without proxy configuration.
     */
    public AmazonECRClient getAmazonECRClient() {
        return new AmazonECRClient();
    }

    /**
     * @param credentials {@link AWSCredentials} to use.
     * @return {@link AmazonECRClient} configured to use the proxy configured on Jenkins.
     */
    public AmazonECRClient getAmazonECRClientWithProxy(AWSCredentials credentials) {
        return getAmazonECRClient(credentials, new ClientConfigurationWrapper());
    }

    /**
     * @param credentials  {@link AWSCredentials} to use.
     * @param clientConfig {@link ClientConfiguration} to create AmazonECRClient object.
     * @return {@link AmazonECRClient} configured using credentials and clientConfig passed as parameters.
     */
    public AmazonECRClient getAmazonECRClient(AWSCredentials credentials, ClientConfiguration clientConfig) {
        return new AmazonECRClient(credentials, clientConfig);
    }
}
