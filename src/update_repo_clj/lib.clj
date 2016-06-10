(ns update-repo-clj.lib
  (:require [update-repo-clj.settings :refer [script-file-name templates-folder]]
            [clojure.java.shell :as shell]
            [taoensso.timbre :as timbre
              :refer (log  trace  debug  info  warn  error  fatal get-env log-env)]
            [taoensso.timbre.appenders.core :as appenders])
  (:use [selmer.parser]))

(selmer.parser/set-resource-path! templates-folder)

(defn with-abs-path [filename]
  (str (.getCanonicalPath (clojure.java.io/file ".")) (java.io.File/separator) filename))


(timbre/merge-config!
  {:appenders {:spit (appenders/spit-appender {:fname (with-abs-path "update-repo.log")})}})


(def index (render-file "index.html" {}))


(defn handle-errors [error]
  (timbre/error (str error))
  {:status 400,
   :headers {"Content-Type" "text/html"},
   :body (render-file "error.html" {})
  })


(defn handle-success [res]
  (info (str res))
  {:status 200,
   :headers {"Content-Type" "text/html"},
   :body (render-file "success.html" {:result (str res)})
  })


(def script
  (let [r (with-abs-path script-file-name)]
    (info (str "Using script: " r))
    r))


(defn call-script []
  (try
    (let [result (shell/sh script)]
      (if (= (:exit result) 0)
        (handle-success result)
        (handle-errors result)))
  (catch java.io.IOException e
    (handle-errors e))))


(defn loginfo [text]
  (info text))
