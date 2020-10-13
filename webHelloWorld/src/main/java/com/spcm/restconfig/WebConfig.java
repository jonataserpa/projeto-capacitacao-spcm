package com.spcm.restconfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/services") // set the path to REST web services
public class WebConfig extends Application {}