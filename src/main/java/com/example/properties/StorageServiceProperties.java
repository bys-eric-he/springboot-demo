package com.example.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 表示默认读取application.properties 配置文件中的以storage.service打头的配置。
 */
@ConfigurationProperties(prefix = "storage.service")
public class StorageServiceProperties {

    private String username;
    private String password;
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
