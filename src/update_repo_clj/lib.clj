(ns update-repo-clj.lib
  (:require [clojure.tools.logging  :as log]
            [update-repo-clj.settings :refer [script-file-name]])
  (:use [selmer.parser]))

(defn with-abs-path [filename]
  (str (.getCanonicalPath (clojure.java.io/file ".")) (java.io.File/separator) filename))

(def success-template "<h1>Success</h1>")
(def error-template "<h1>Fail</h1>")
(def index "<h1>Hello, world!</h1>")

(defn handle-errors [error]
  (log/error (str error))
  error-template)

(defn handle-success [res]
  (log/info (str res))
  success-template)

(def script
  (let [r (with-abs-path script-file-name)]
    (log/info (str "Using script: " r))
    r))
