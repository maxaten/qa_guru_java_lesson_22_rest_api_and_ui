package demoqa.config;

@org.aeonbits.owner.Config.Sources({
        "classpath:${launch}.properties"
})
public interface Config extends org.aeonbits.owner.Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("version")
    @DefaultValue("100.0")
    String browserVersion();

    @Key("size")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("remoteUrl")
    String remoteUrl();
}
