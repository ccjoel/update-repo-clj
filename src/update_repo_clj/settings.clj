(ns update-repo-clj.settings)

(def port 3000)

(def script-file-name "script.sh")

(def log-config
  [:configuration {:scan true, :scanPeriod "10 seconds"}
   [:appender {:name "FILE" :class "ch.qos.logback.core.rolling.RollingFileAppender"}
    [:encoder [:pattern "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"]]
    [:rollingPolicy {:class "ch.qos.logback.core.rolling.TimeBasedRollingPolicy"}
     [:fileNamePattern "logs/%d{yyyy-MM-dd}.%i.log"]
     [:timeBasedFileNamingAndTriggeringPolicy {:class "ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP"}
      [:maxFileSize "64 MB"]]]
    [:prudent true]]
   [:appender {:name "STDOUT" :class "ch.qos.logback.core.ConsoleAppender"}
    [:encoder [:pattern "%-5level %logger{36} - %msg%n"]]
    [:filter {:class "ch.qos.logback.classic.filter.ThresholdFilter"}
     [:level "INFO"]]]
   [:root {:level "INFO"}
    [:appender-ref {:ref "FILE"}]
    [:appender-ref {:ref "STDOUT"}]]
   [:logger {:name "user" :level "ALL"}]
   [:logger {:name "boot.user" :level "ALL"}]])
