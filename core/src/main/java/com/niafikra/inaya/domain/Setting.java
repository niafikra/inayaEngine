package com.niafikra.inaya.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * A setting is a key value pair,
 * it basically allows modules to use it to store various configurations
 * <p/>
 * the key should follow the format: "Module:KeyName"
 *
 * @author mbwana mbura
 */

@Entity
@Table(name = "CR_Setting")
@Data
@EqualsAndHashCode(callSuper = true)
public class Setting extends InayaEntity implements HasName {

    public static final String BASE_CURRENCY = "Engine:base currency";
    public static final String EXCHANGE_RATE_RELOAD_SCHEDULE = "Engine:exchange rate reload schedule";
    public static final String DATE_FORMAT = "Engine:date format";
    public static final String DATE_TIME_FORMAT = "Engine:date time format";
    public static final String NO_MSG = "Engine:no message";
    public static final String YES_MSG = "Engine:yes message";
    public static final String DEFAULT_EMAIL_FROM = "Engine:email from";
    public static final String DEFAULT_EMAIL_HOSTNAME = "Engine:email hostname";
    public static final String DEFAULT_EMAIL_SMTP_PORT = "Engine:email smtp port";
    public static final String DEFAULT_EMAIL_AUTHENTICATION_USERNAME = "Engine:email authentication username";
    public static final String DEFAULT_EMAIL_AUTHENTICATION_PASSWORD = "Engine:email authentication password";
    public static final String DEFAULT_EMAIL_USE_SSL_ON_CONNECT = "Engine:email use SSL on connect";
    public static final String DEFAULT_NOTIFICATION_SOUND = "Engine:default notification sound";
    public static final String NUMBER_GROUPING_SIZE = "Engine:number grouping size";
    public static final String NUMBER_DECIMAL_PRECISION = "Engine:number decimal precision";
    public static final String NUMBER_GROUPING_SEPARATOR = "Engine:number grouping separator";
    public static final String NUMBER_DECIMAL_SEPARATOR = "Engine:number decimal separator";
    public static final String CURRENT_THEME = "Engine:current theme";
    public static final String DEFAULT_PASSWORD = "Engine:default password";
    public static final String BARCODE_PREFIX = "Engine:barcode prefix";
    public static final String BARCODE_POSTFIX = "Engine:barcode postfix";
    public static final String SHOULD_SAVE_LOADED_RATES = "Engine:should save loaded rates";
    public static final String CURRENCY_RATES_TO_SAVE = "Engine:currency rates to save";

    public static final String SYNC_RESOURCES_TO_DB = "Engine:sync resources to db";
    public static final String PRODUCTION_MODE = "Engine:production mode";
    public static final String ALLOW_SOUND_PLAYER = "Engine:allow sound player";

    public static final String LDAP_DOMAIN = "Engine:LDAP domain";
    public static final String LDAP_HOST = "Engine:LDAP host";
    public static final String REQUIRES_SMS_DELIVERY_REPORT = "Engine:requires SMS delivery report";
    public static final String MAXIMUM_SMS_BATCH_LIMIT = "Engine:maximum SMS batch limit";
    public static final String FIRE_FOR_DESKTOP_NOTIFICATION_REPEAT_INTERVAL = "Engine:firefox desktop notification repeat interval";

    public static final String FIREBASE_REPOSITORY = "Engine:firebase repository";
    public static final String USE_FIREBASE = "Engine:use firebase";
    public static final String LOCALE_LANGUANGE = "Engine:locale language";
    public static final String LOCALE_COUNTRY = "Engine:locale country";

    public static final String MESSAGE_MAXIMUM_AGE = "Engine:Message Maximum Age";
    public static final String OLD_MESSAGES_DELETE_SCHEDULE = "Engine:Old messages delete schedule";
    public static final String LOCAL_SERVER_URL = "Engine:Local server URL";
    public static final String NOTIFICATIONS_SOURCE = "Engine:notifications source";

    public static final String SETTING_EMAIL_HOSTNAME = "Engine:email hostname";
    public static final String SETTING_EMAIL_SMTPPORT = "Engine:email smtp port";
    public static final String SETTING_EMAIL_AUTH_USERNAME = "Engine:email authentication username";
    public static final String SETTING_EMAIL_AUTH_PASSWORD = "Engine:email authentication password";
    public static final String SETTING_EMAIL_SSL_ON_CONNECT = "Engine:email use SSL on connect";
    public static final String SETTING_EMAIL_FROM = "Engine:email from";
    public static final String SETTING_ALLOW_SEND_EMAIL = "Engine:allow sending email";
    public static final String LOCATIONS = "Engine:locations";

    public static final String PRINT_TO_PDF = "Engine:print to pdf";

    @Lob
    private String name;
    @Lob
    private String value;

    public Setting() {
    }


    public Setting(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
