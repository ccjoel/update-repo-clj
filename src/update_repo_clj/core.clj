(ns update-repo-clj.core
  (:require [org.httpkit.server :refer [run-server]]
            [clojure.java.shell :as shell]
            [clojure.tools.logging :as log])
  (:use [compojure.core]
        [update-repo-clj.settings]
        [update-repo-clj.lib])
  (:gen-class))

(defn call-script []
  (try
    (let [result (shell/sh script)]
      (if (= (:exit result) 0)
        (handle-success result)
        (handle-errors result)))
  (catch java.io.IOException e
    (handle-errors e))))

(defroutes app
  (GET "/" [] index)
  (GET "/update" [] (call-script)))

(defn -main
  "main"
  [& args]
  (let [shutdown (promise)
        stop-server (run-server app {:port port})]
    (do
      (.addShutdownHook (.. Runtime getRuntime)
                        (Thread. (fn []
                                   (do
                                     (log/info "shutting down..")
                                     (@stop-server)
                                     (deliver shutdown nil)))))
      (log/info (str "listening on " port))
      @shutdown)))
