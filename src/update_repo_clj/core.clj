(ns update-repo-clj.core
  (:require [org.httpkit.server :refer [run-server]]
            [update-repo-clj.settings :refer [port]]
            [update-repo-clj.lib :refer [call-script index loginfo]])
  (:use [compojure.core])
  (:gen-class))

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
                                     (loginfo "shutting down..")
                                     (@stop-server)
                                     (deliver shutdown nil)))))
      (loginfo (str "listening on " port))
      @shutdown)))
