(ns lg
  "Utility functions to interfae with slf4j"
  (:import org.slf4j.LoggerFactory))

; mapping between logging levels and slf4j methods.
(def levels {:error {:enabled? #(.isErrorEnabled %)
                     :emit #(.error %1 %2)}
             :info {:enabled? #(.isInfoEnabled %)
                    :emit #(.info %1 %2)}
             :warn {:enabled? #(.isWarnEnabled %)
                    :emit #(.warn %1 %2)}
             :debug {:enabled? #(.isDebugEnabled %)
                    :emit #(.debug %1 %2)}
             :trace {:enabled? #(.isTraceEnabled %)
                    :emit #(.trace %1 %2)}})

(defmacro log
  "Log a message using the logger for the current namespace

  Usage:
    (log :error \"unexpected argument: %s\" arg)
  "
  [level msg & args]
  (let [log-ns# (str *ns*)]
    `(let [logger# (LoggerFactory/getLogger ~log-ns#)
           {{enabled?# :enabled?
             emit# :emit} ~level} levels]
       (when (enabled?# logger#)
         (let [args# (vector ~@args)
               msg0# ~msg
               msg# (if (empty? args#) msg0# (apply format msg0# args#))]
           (emit# logger# msg#))))))

(defmacro error
  "Log an error message"
  [msg & args]
  `(log :error ~msg ~@args))

(defmacro info
  "Log an info message"
  [msg & args]
  `(log :info ~msg ~@args))

(defmacro debug
  "Log a debug message"
  [msg & args]
  `(log :debug ~msg ~@args))

(defmacro warn
  "Log a warn message"
  [msg & args]
  `(log :warn ~msg ~@args))

(defmacro trace
  "Log a trace message"
  [msg & args]
  `(log :trace ~msg ~@args))

