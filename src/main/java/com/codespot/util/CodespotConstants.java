package com.codespot.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;

public class CodespotConstants {

	public static final String APP_DEPLOY_URL = "app_deploy_url";
	public static final String APPLICATION_NAME = "application_name";

	public static final String SENDER_EMAIL = "sender_email";
	public static final String SENDER_EMAIL_PASSWORD = "sender_email_password";

	public static final String MESSAGE_SOURCE_BASENAME = "message.source.basename";
	public static final String DB_DRIVER = "db.driver";
	public static final String DB_URL = "db.url";
	public static final String DB_USERNAME = "db.username";
	public static final String DB_PASSWORD = "db.password";
	public static final String HIBERNATE_DIALECT = "hibernate.dialect";
	public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	public static final String HIBERNATE_HBM2DDL_IMPORT_FILES = "hibernate.hbm2ddl.import_files";
	public static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	public static final String SPRING_MVC_VIEW_PREFIX = "spring.mvc.view.prefix";
	public static final String SPRING_MVC_VIEW_SUFFIX = "spring.mvc.view.suffix";
	public static final String SPRING_JPA_HIBERNATE_DDL_AUTO = "spring.jpa.hibernate.ddl-auto";
	public static final String SPRING_JPA_SHOW_SQL = "spring.jpa.show-sql";

	public static final String MAIL_SMTP_HOST = "mail.smtp.host";
	public static final String MAIL_SMTP_PORT = "mail.smtp.port";
	public static final String MAIL_SMTP_SOCKETFACTORY_PORT = "mail.smtp.socketFactory.port";
	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	public static final String TRANSPORT_LOCALHOST = "transport.localhost";
	public static final String TRANSPORT_HOST = "transport.host";
	public static final String TRANSPORT_PORT = "transport.port";
	public static final String MAIL_BUG_PNG_IMAGE = "mail.bug.png.image";
	public static final String MAIL_SMTP_SOCKETFACTORY_CLASS = "mail.smtp.socketFactory.class";
	public static final String MULTIPART_RESOLVER_DEFAULT_ENCODING = "utf-8";

	public static final String ULOAD_DIRECTORY = "/codespot/upload/tmp/";
	public static final long MAX_UPLOAD_SIZE = FileUtils.ONE_MB * 256;

	public static final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss' 'Z");

}
