package com.p1.demo.backend.domain.jms;

import java.util.List;

public class CollectdData {
    List<String> values;
    List<String> dstypes;
    List<String> dsnames;
    String time;
    String interval;
    String host;
    String plugin;
    String plugin_instance;
    String type;
    String type_instance;

    public CollectdData() {
    }

    public CollectdData(List<String> values, List<String> dstypes, List<String> dsnames, String time, String interval, String host, String plugin, String plugin_instance, String type, String type_instance) {
        this.values = values;
        this.dstypes = dstypes;
        this.dsnames = dsnames;
        this.time = time;
        this.interval = interval;
        this.host = host;
        this.plugin = plugin;
        this.plugin_instance = plugin_instance;
        this.type = type;
        this.type_instance = type_instance;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public List<String> getDstypes() {
        return dstypes;
    }

    public void setDstypes(List<String> dstypes) {
        this.dstypes = dstypes;
    }

    public List<String> getDsnames() {
        return dsnames;
    }

    public void setDsnames(List<String> dsnames) {
        this.dsnames = dsnames;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getPlugin_instance() {
        return plugin_instance;
    }

    public void setPlugin_instance(String plugin_instance) {
        this.plugin_instance = plugin_instance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_instance() {
        return type_instance;
    }

    public void setType_instance(String type_instance) {
        this.type_instance = type_instance;
    }
}
