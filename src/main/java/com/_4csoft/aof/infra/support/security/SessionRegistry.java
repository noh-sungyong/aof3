/*
 * Copyright (c) 1999 4CSoft Inc. All right reserved.
 * This software is the proprietary information of 4CSoft Inc.
 *
 */
package com._4csoft.aof.infra.support.security;

import org.springframework.security.core.session.SessionRegistryImpl;

/**
 * @Project : aof5-infra
 * @Package : com._4csoft.aof.infra.support.security
 * @File : SessionRegistry.java
 * @Title : Session Registry
 * @date : 2013. 4. 17.
 * @author : 김종규
 * @descrption : For this class to function correctly in a web application, it is important that you register an HttpSessionEventPublisher in the web.xml file
 *             so that this class is notified of sessions that expire.
 */
public class SessionRegistry extends SessionRegistryImpl {

}
